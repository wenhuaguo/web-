package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.dto.wx.TextMessage;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.*;
import com.kaishengit.pojo.*;
import com.kaishengit.service.DeviceService;
import com.kaishengit.service.WxService;
import com.kaishengit.shiro.ShiroUtil;
import com.kaishengit.utile.SerialNumUtil;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.record.chart.TextRecord;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Acer on 2017/1/18.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private CompanyContractMapper companyContractMapper;

    @Autowired
    private RentDeviceDetailMapper deviceDetailMapper;

    @Autowired
    private DeviceDocMapper deviceRentDocMapper;

    @Autowired
    private FinanceMapper financeMapper;

    @Autowired
    private WxService wxService;
    //获得文件上传的路径读取配置文件
    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public void add(Device device) {
        //当前库存量与总数量相同
        device.setCurrentNum(device.getTotalNum());
        //默认最后修改时间和创建时间一致
        device.setModifyTime(new Timestamp(System.currentTimeMillis()));
        deviceMapper.add(device);
    }

    @Override
    public List<Device> findAll() {
        List<Device> deviceList = deviceMapper.findAll();
        return deviceList;
    }

    @Override
    public List<Device> findDeviceByPage(String start, String length) {
       List<Device> deviceList =  deviceMapper.findDeviceByPage(start,length);
        return deviceList;
    }

    @Override
    public Long count() {
        Long count = companyContractMapper.count();
        return count;
    }

    @Override
    public List<Device> findDeviceBySearch(Map<String, Object> searchParam) {
        List<Device> deviceList = deviceMapper.findBySearchParam(searchParam);
        return deviceList;
    }

    @Override
    public Long countBySearchParam(Map<String,Object> searchParam) {
        Long countSearch = deviceMapper.countBySearchParam(searchParam);
        return countSearch;
    }

    @Override
    public void del(Integer id) {
        deviceMapper.del(id);
    }

    @Override
    public Device findDeviceById(int deviceId) {
        Device device = deviceMapper.findDeviceById(deviceId);
        return device;
    }

//    @Override
//    public void addRentDevice(DeviceSerial deviceSerial) {
//        Device device = deviceMapper.findDeviceById(deviceSerial.getDeviceId());
//        device.setCurrentNum(device.getCurrentNum() - deviceSerial.getNum());
//        deviceSerialMapper.addRentDevice(deviceSerial);
//    }

//    @Override
//    public List<DeviceSerial> findDeviceSerialAll() {
//        List<DeviceSerial> deviceSerialList = deviceSerialMapper.findAll();
//        return deviceSerialList;
//    }

//    @Override
//    public List<DeviceSerial> findDeviceSerialBySearch(Map<String, Object> searchParam) {
//        List<DeviceSerial> deviceSerialList = deviceSerialMapper.findDeviceSerialBySearch(searchParam);
//        return deviceSerialList;
//    }



    //保存合同
    @Override
    @Transactional
    public String addRentDeivce(DeviceRentDto deviceRentDto) {
        //要有事务避免出现有的保存上有的保存不上
        String serialNum = SerialNumUtil.getSerialNumber();
        CompanyContract companyContract = new CompanyContract();
        companyContract.setRentCompany(deviceRentDto.getRentCompany());
        companyContract.setAddress(deviceRentDto.getAddress());
        companyContract.setRentTime(deviceRentDto.getRentDay());
        companyContract.setBackTime(deviceRentDto.getBackDay());
        companyContract.setCardNum(deviceRentDto.getCardNum());
        companyContract.setCompanyTel(deviceRentDto.getTel());
        companyContract.setFax(deviceRentDto.getFax());
        companyContract.setTotalPrice(0F);
        companyContract.setPreCost(0F);
        companyContract.setLastCost(0F);
        companyContract.setSerialNum(serialNum);
        companyContract.setLegalRepresent(deviceRentDto.getLegalRepresent());
        companyContract.setLegalTel(deviceRentDto.getLegalTel());
        companyContract.setCreateUser(ShiroUtil.getCurrentUserName());
        companyContract.setRentDays(deviceRentDto.getTotalDate());
        companyContract.setState("未完成");
        companyContractMapper.save(companyContract);

        //租赁设备详情列表

        List<DeviceRentDto.DeviceArrayBean> deviceArray = deviceRentDto.getDeviceArray();
        //批量加入
        List<RentDeviceDetail> deviceDetails = Lists.newArrayList();
        Float totalPrice = 0F;
        if(deviceRentDto.getDeviceArray() != null) {
            for (DeviceRentDto.DeviceArrayBean deviceArrayBean : deviceArray) {
                //每一个设备都需要创建一个新的对象进行保存
                //查询新增租赁设备的数量是否足够根据数据传输对象里面包含设备id
                Device device = findDeviceById(deviceArrayBean.getId());
                if(deviceArrayBean.getNum() > device.getCurrentNum()){
                    throw new ServiceException(device.getName() + "库存不足");
                }else {
                    //如果足够的话就进行相减并更新响应数量
                    device.setCurrentNum(device.getCurrentNum() - deviceArrayBean.getNum());
                    deviceMapper.updateCurrentNum(device);
                }
                RentDeviceDetail deviceDetail = new RentDeviceDetail();
                deviceDetail.setDeviceName(deviceArrayBean.getName());
                deviceDetail.setRentPrice(deviceArrayBean.getPrice());
                deviceDetail.setNum(deviceArrayBean.getNum());
                deviceDetail.setUnit(deviceArrayBean.getUnit());
                deviceDetail.setRentId(companyContract.getId());
                deviceDetail.setTotalPrice(deviceArrayBean.getTotal());
                System.out.println(deviceArrayBean.getTotal());
                deviceDetails.add(deviceDetail);
                totalPrice+= deviceArrayBean.getTotal();
            }
        }
        if(!deviceDetails.isEmpty()){
            deviceDetailMapper.batchSave(deviceDetails);
        }

        //计算合同总结尾款以及预付款
        totalPrice = totalPrice*companyContract.getRentDays();
        Float preCost = totalPrice*0.3F;
        Float lastCost = totalPrice - preCost;
        companyContractMapper.updateCost(totalPrice,preCost,lastCost,companyContract.getId());

        //保存文件
        //也是批量进行存储
        List<DeviceRentDto.FileArrayBean> fileArrayBeanList = deviceRentDto.getFileArray();
        List<DeviceDoc> deviceRentDocs = Lists.newArrayList();
        for(DeviceRentDto.FileArrayBean fileArrayBean : fileArrayBeanList){
            DeviceDoc deviceRentDoc = new DeviceDoc();
            deviceRentDoc.setRentId(companyContract.getId());
            deviceRentDoc.setNewFileName(fileArrayBean.getNewFileName());
            deviceRentDoc.setSourceFileName(fileArrayBean.getSourceFileName());
            deviceRentDocs.add(deviceRentDoc);
        }
        if(!deviceRentDocs.isEmpty()){
            deviceRentDocMapper.batchSave(deviceRentDocs);
        }

        //写入财务流水
        Finance finance = new Finance();
        finance.setState(Finance.STATE_NEW);
        finance.setCreateUser(ShiroUtil.getCurrentUserName());
        finance.setCreateDate(DateTime.now().toString("yyyy-MM-dd"));
        System.out.println("mm:" + DateTime.now().toString("yyyy-mm-dd"));
        finance.setModule("设备租赁");
        finance.setMoney(preCost);//金额是预付款金额
        finance.setRemark("设备租赁预付款");
        finance.setType(Finance.TYPE_IN);
        finance.setSerialNum(SerialNumUtil.getSerialNumber());
        finance.setModuleSerial(serialNum);
        //确认者和确认时间暂时为空
        //对财务流水进行保存
        financeMapper.saveFinanceAssembly(finance);

        //给财务部门人员发送消息
        TextMessage textMessage = new TextMessage();
        TextMessage.TextBean textBean = new TextMessage.TextBean();
        textBean.setContent("设备租赁添加一笔[预付款]流水，请确认");
        textMessage.setToparty("3");
        textMessage.setText(textBean);
        wxService.sendMessage(textMessage);
        return companyContract.getSerialNum();
    }

    @Override
    public CompanyContract findCompantContractBySerialNum(String serialNum) {
        CompanyContract companyContract = companyContractMapper.findCompanyContractBySerialNum(serialNum);
        return companyContract;
    }

    @Override
    public List<RentDeviceDetail> findDetailByRentId(Integer id) {
        List<RentDeviceDetail> rentDeviceDetail = deviceDetailMapper.findDetailByRentId(id);
        return rentDeviceDetail;
    }

    @Override
    public List<DeviceDoc> findFileListByRentId(Integer id) {
        List<DeviceDoc> deviceDoc = deviceRentDocMapper.findFileListByRentId(id);
        return deviceDoc;
    }

    @Override
    public InputStream downLoadFile(Integer id) throws FileNotFoundException {
        //获得id对应的文件对象
        DeviceDoc deviceDoc = deviceRentDocMapper.findDeviceDocById(id);
        //判断对象是否存在
        if(deviceDoc == null){
            return null;
        }else {
            //因为在路径中保存的是新创建的名称所有需要获得新的文件的名称的保存路径
            File file = new File(new File(uploadPath),deviceDoc.getNewFileName());
            //file.exists()测试抽象路径名表示的文件或目录是否存在
            if(file.exists()){
                //从文件系统中的某个文件获得输入字节也就是获得输入流参数可以是File对象或者String 字符串指定的文件对象
                return new FileInputStream(file);
            }else {
                return null;
            }
        }
    }

    @Override
    public DeviceDoc findDeviceDocById(Integer id) {
        DeviceDoc deviceDoc = deviceRentDocMapper.findDeviceDocById(id);
        return deviceDoc;
    }

    @Override
    public CompanyContract finCompanyContractById(Integer companyId) {
        CompanyContract companyContract = companyContractMapper.findCompanyContractById(companyId);
        return companyContract;
    }

    //文件批量打包下载
    @Override
    public void downLoadZipFile(CompanyContract companyContract, ZipOutputStream zipOutputStream) throws IOException {
        //查找合同对应的附件
        List<DeviceDoc> deviceDocList = deviceRentDocMapper.findFileByCompanyContractId(companyContract.getId());
        //是否需要判断集合是否为空
        for(DeviceDoc deviceDoc : deviceDocList){
            //每一个文件对应的是zipEntry实体
            ZipEntry zipEntry = new ZipEntry(deviceDoc.getSourceFileName());
            zipOutputStream.putNextEntry(zipEntry);
            //创建输入流
            InputStream inputStream = downLoadFile(deviceDoc.getId());
            IOUtils.copy(inputStream,zipOutputStream);
        }

        //等循环完之后再关闭输出流
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    @Override
    public List<CompanyContract> finaAllDeivceContractByQueryParam(Map<String, String> queryParam) {
        List<CompanyContract> companyContractList = companyContractMapper.findCompanyContractByQueryParam(queryParam);
        return companyContractList;
    }

    //设备状态的修改
    @Override
    @Transactional
    public void changeState(Integer id) {
        //是否用判断根据id查找的对应合同是否为空
       CompanyContract companyContract = companyContractMapper.findCompanyContractById(id);
            companyContract.setState("完成");
            companyContractMapper.changeState(companyContract);
        System.out.println("companySerialNum:" + companyContract.getSerialNum());
        //向财务模块添加尾款记录根据流水号进行查找
        Finance finance = new Finance();
        finance.setMoney(companyContract.getLastCost());//将尾款添加到财务流水里面
        //添加新的财务流水
        finance.setState(Finance.STATE_NEW);
        finance.setModule("设备租赁");
        finance.setType(Finance.TYPE_IN);
        finance.setSerialNum(SerialNumUtil.getSerialNumber());
        finance.setModuleSerial(companyContract.getSerialNum());
        finance.setRemark("尾款收回");
        finance.setCreateUser(ShiroUtil.getCurrentUserName());//
        finance.setCreateDate(DateTime.now().toString("yyyy-MM-dd"));//
        System.out.println("MM:" + DateTime.now().toString("yyyy-MM-dd"));
        financeMapper.saveFinanceAssembly(finance);
        //给财务部门人员发送消息
        TextMessage textMessage = new TextMessage();
        TextMessage.TextBean textBean = new TextMessage.TextBean();
        textBean.setContent("设备租赁添加一笔[尾款]流水，请确认");
        textMessage.setToparty("3");
        textMessage.setText(textBean);
        wxService.sendMessage(textMessage);
    }

    @Override
    public Long filteredCount(Map<String, String> queryParam) {
        Long filteredCount = companyContractMapper.filteredCount(queryParam);
        return null;
    }

}
