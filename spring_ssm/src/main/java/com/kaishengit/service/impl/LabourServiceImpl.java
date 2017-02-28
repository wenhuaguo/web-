package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.LabourRentDto;
import com.kaishengit.dto.wx.TextMessage;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.*;
import com.kaishengit.pojo.*;
import com.kaishengit.service.LabourService;
import com.kaishengit.service.WxService;
import com.kaishengit.shiro.ShiroUtil;
import com.kaishengit.utile.SerialNumUtil;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Created by Acer on 2017/2/18.
 */
@Service
public class LabourServiceImpl implements LabourService {
    @Autowired
    private LabourTypeMapper labourTypeMapper;

    @Autowired
    private LabourContractMapper companyContractMapper;

    @Autowired
    private LabourDetailMapper labourDetailMapper;

    @Autowired
    private DeviceDocMapper deviceDocMapper;

    @Autowired
    private FinanceMapper financeMapper;

    @Autowired
    private WxService wxService;

    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public List<LabourType> findAll() {
        List<LabourType> labourTypeList = labourTypeMapper.findAll();
        return labourTypeList;
    }

    @Override
    public LabourType findLabourById(Integer id) {
        LabourType labourType = labourTypeMapper.findLabourById(id);
        return labourType;
    }

    //要有事务因为是保存到不同的表中所有要成功都成功 不成功都不成功
    @Override
    @Transactional
    public String addLabourRentContract(LabourRentDto labourRent) {
        String serialNum = SerialNumUtil.getSerialNumber();
        //保存公司合同
        LabourContract companyContract = new LabourContract();
        companyContract.setRentCompany(labourRent.getRentCompany());
        companyContract.setAddress(labourRent.getAddress());
        companyContract.setCompanyTel(labourRent.getCompanyTel());
        companyContract.setRentTime(labourRent.getRentDay());
        companyContract.setBackTime(labourRent.getBackDay());
        companyContract.setRentDays(labourRent.getDayNum());
        companyContract.setLegalRepresent(labourRent.getLegalRepresent());
        companyContract.setLegalTel(labourRent.getLegalTel());
        companyContract.setTotalPrice(0F);
        companyContract.setPreCost(0F);
        companyContract.setLastCost(0F);
        companyContract.setState("未完成");
        companyContract.setCardNum(labourRent.getIdNum());
        //设置当前保存的用户也就是是谁保存的
        //产生具有一定意义的流水号通过工具类产生提供
        companyContract.setCreateUser(ShiroUtil.getCurrentUserName());
        companyContract.setSerialNum(serialNum);
        companyContractMapper.save(companyContract);

        //工种列表的保存进行批量保存
        List<LabourDetail> labourDetailList = Lists.newArrayList();
        List<LabourRentDto.LabourArrayBean> labourArrayBeanList = labourRent.getLabourArray();
        Float totalPrice = 0F;
        if(!labourArrayBeanList.isEmpty()){
            for(LabourRentDto.LabourArrayBean labourArrayBean :labourArrayBeanList){
                //每创建一个对象保存集合中的一个数据
                //为需要租赁的设备验证是否超过当前库存数量
                LabourType labourType = labourTypeMapper.findLabourById(labourArrayBean.getId());
                if (labourType != null){
                    labourType.setCurrentNum(labourType.getCurrentNum() - labourArrayBean.getDispatchNum());
                    labourTypeMapper.updateLabourTypeCurrentNum(labourType);
                }else {
                    //抛出异常
                    throw new ServiceException(labourType.getWorkType() + "库存不足");
                }
                LabourDetail labourDetail = new LabourDetail();
                labourDetail.setRentNum(labourArrayBean.getDispatchNum());
                labourDetail.setWorkType(labourArrayBean.getWorkType());
                labourDetail.setTotal(labourArrayBean.getTotal());
                labourDetail.setUnitMoney(labourArrayBean.getUnitMoney());
                labourDetail.setRentId(companyContract.getId());
                totalPrice+=labourArrayBean.getTotal();
                labourDetailList.add(labourDetail);
            }
        }

        labourDetailMapper.save(labourDetailList);
        //计算总佣金费用和预付款以及尾款cost花费、费用、成本的意思
        Float preCost = totalPrice*0.3F;
        Float lastCost = totalPrice - preCost;
        companyContractMapper.updateCost(totalPrice,preCost,lastCost,companyContract.getId());

        //文件上传保存，批量保存
        List<DeviceDoc> labourDocList = Lists.newArrayList();
        List<LabourRentDto.FileUploadBean> fileUploadBeanList = labourRent.getFileUpload();
        if(!fileUploadBeanList.isEmpty()){
            for(LabourRentDto.FileUploadBean fileUploadBean :fileUploadBeanList){
                DeviceDoc deviceDoc = new DeviceDoc();
                deviceDoc.setRentId(companyContract.getId());
                deviceDoc.setSourceFileName(fileUploadBean.getSourceFile());
                deviceDoc.setNewFileName(fileUploadBean.getNewFile());
                labourDocList.add(deviceDoc);
            }
        }
        //批量进行保存
        deviceDocMapper.batchSave(labourDocList);
        //给客户端返回序列号也就是流水号流水号通过特定的工具进行产生
        //保存到财务流水
        Finance finance = new Finance();
        finance.setRemark("预付款收入");
        finance.setSerialNum(SerialNumUtil.getSerialNumber());
        finance.setMoney(preCost);
        finance.setType(Finance.TYPE_IN);
        finance.setModule("劳务租赁");
        finance.setCreateUser(ShiroUtil.getCurrentUserName());
        finance.setState(Finance.STATE_NEW);
        finance.setModuleSerial(serialNum);
        finance.setCreateDate(labourRent.getRentDay());
        financeMapper.saveFinanceAssembly(finance);

        //给财务人员发送消息
        TextMessage textMessage = new TextMessage();
        TextMessage.TextBean textBean = new TextMessage.TextBean();
        textBean.setContent("劳务租赁添加一笔[预付款]流水，请确认");
        textMessage.setToparty("3");
        textMessage.setText(textBean);
        wxService.sendMessage(textMessage);
        return companyContract.getSerialNum();
    }

    @Override
    public LabourContract findCompanyLabourContract(String id) {
        LabourContract labourContract = companyContractMapper.findCompanyContractBySerialNum(id);
        System.out.println(labourContract);
        return labourContract;
    }

    @Override
    public List<LabourDetail> findLabourDetailByRentId(Integer id) {
        List<LabourDetail> labourDetailList = labourDetailMapper.findLabourRentDetail(id);
        return labourDetailList;
    }

    @Override
    public List<DeviceDoc> findFileUploadListByRentId(Integer id) {
        List<DeviceDoc> deviceDocList = deviceDocMapper.findFileListByRentId(id);
        return deviceDocList;
    }

    @Override
    public List<LabourContract> findLabourContractByQueryParam(Map<String, String> queryParam) {
        List<LabourContract> labourContractList = companyContractMapper.findLabourContractByQueryParam(queryParam);
        return labourContractList;
    }

    //查询数据库记录的总条数
    @Override
    public Long count() {
        Long count = companyContractMapper.count();
        return count;
    }

    //根据id修改劳务合同的状态
    @Override
    @Transactional
    public void changeContractStateById(Integer id) {
        //还有财务流水所有需要事务
        LabourContract labourContract = companyContractMapper.findLabourContractById(id);
        //是否需要判断该id对应的合同是否存在
        labourContract.setState("完成");
        companyContractMapper.updateState(labourContract);
        //向财务模块中添加尾款记录
        Finance finance = new Finance();
        finance.setState(Finance.STATE_NEW);
        finance.setCreateUser(ShiroUtil.getCurrentUserName());
        finance.setModuleSerial(labourContract.getSerialNum());
        finance.setMoney(labourContract.getLastCost());
        finance.setRemark("尾款收回");
        finance.setType(Finance.TYPE_IN);
        finance.setModule("劳务租赁");
        finance.setSerialNum(SerialNumUtil.getSerialNumber());
        finance.setCreateDate(DateTime.now().toString("yyyy-MM-dd"));
        System.out.println(DateTime.now().toString("yyyy-mm-dd"));
        System.out.println(DateTime.now().toString("yyyy-MM-dd"));
        financeMapper.saveFinanceAssembly(finance);

        //给财务人员发送消息
        TextMessage textMessage = new TextMessage();
        TextMessage.TextBean textBean = new TextMessage.TextBean();
        textBean.setContent("劳务租赁添加一笔[尾款]流水，请确认");
        textMessage.setToparty("3");
        textMessage.setText(textBean);
        wxService.sendMessage(textMessage);
    }

    @Override
    public LabourContract findLabourContractById(Integer id) {
        LabourContract labourContract = companyContractMapper.findLabourContractById(id);
        return labourContract;
    }

    @Override
    public void downloadZip(LabourContract labourContract, ZipOutputStream zipOutputStream) throws IOException {
        //首先根据合同id查询出所有合同扫描件
        List<DeviceDoc> deviceDocList = deviceDocMapper.findFileListByRentId(labourContract.getId());
        //循环所有文件合同扫描件输入流
        for(DeviceDoc deviceDoc : deviceDocList){
            //每一个文件对应的是ZipEntry实体
            ZipEntry zipEntry = new ZipEntry(deviceDoc.getSourceFileName());
           //现将文件对应的实体放入到压缩文件流当中去
            zipOutputStream.putNextEntry(zipEntry);
            //创建输入流
            InputStream inputStream = downloadFile(deviceDoc.getId());
            IOUtils.copy(inputStream,zipOutputStream);
        }
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    public InputStream downloadFile(Integer id) throws IOException {
        //查询id对应的文件
        DeviceDoc deviceDoc = deviceDocMapper.findDeviceDocById(id);
        //判断文件是否为空如果为空返回空值
        if(deviceDoc != null){
            File file = new File(new File(uploadPath),deviceDoc.getNewFileName());
            //判断文件是否存在
            if(file.exists()) {
                return new FileInputStream(file);
            }else {
                return null;
            }

        }else {
            return null;
        }
    }

    @Override
    public DeviceDoc findDeviceDoById(Integer id) {
        DeviceDoc deviceDoc = deviceDocMapper.findDeviceDocById(id);
        return deviceDoc;
    }

}
