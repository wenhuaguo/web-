package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.DiskMapper;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import com.kaishengit.shiro.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by Acer on 2017/2/21.
 */
@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskMapper diskMapper;

    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public List<Disk> findDiskByFid(Integer path) {
        List<Disk> diskList = diskMapper.findDiskByFid(path);
        return diskList;
    }

    @Override
    public void newFolder(Disk disk) {
        disk.setCreateUser(ShiroUtil.getCurrentUserName());
        disk.setDocType(Disk.DIRECTORY);
        disk.setCreateTime(DateTime.now().toString("yyyy-mm-dd HH:mm"));
        diskMapper.save(disk);
    }

    @Override
    @Transactional
    public void fileUpload(MultipartFile file, Integer fid)  {
        System.out.println(fid);
        //要有事务因为要保存到数据库和写入到磁盘两种操作
        String sourceName = file.getOriginalFilename();
        //保存到磁盘的需要给文件新命名
        String newFileName = UUID.randomUUID().toString();
        try {
            //判断是否有.
            if(sourceName.lastIndexOf(".") != -1){
                newFileName += sourceName.substring(sourceName.lastIndexOf("."));
            }
            InputStream inputStream = file.getInputStream();
            File fileUpload = new File(new File(uploadPath),newFileName);
            FileOutputStream outputStream = new FileOutputStream(fileUpload);
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (IOException e){
            throw new ServiceException("文件写入磁盘异常",e);
        }


        //写入数据库操作
        Disk disk = new Disk();
        disk.setCreateUser(ShiroUtil.getCurrentUserName());
        disk.setCreateTime(DateTime.now().toString("yyyy-mm-dd HH:mm"));
        disk.setDocType(Disk.FILE);
        disk.setName(newFileName);
        disk.setFid(fid);
        disk.setSourceName(sourceName);
        //将字节转换为可表示的大小文件工具类
        disk.setSize(FileUtils.byteCountToDisplaySize(file.getSize()));

        diskMapper.save(disk);
    }

    @Override
    public InputStream downLoad(Integer fileId) throws FileNotFoundException {
        //根据id查找对应的文件对象
        Disk disk = diskMapper.findDiskById(fileId);
        //判断是否存在
        if(disk != null){
            //获得文件所在路径
            File file = new File(new File(uploadPath),disk.getName());
            //判断文件对应的文件是否存在
            if(file.exists()) {
                InputStream inputStream = new FileInputStream(file);
                return inputStream;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public Disk findDiskById(Integer fileId) {
        Disk disk = diskMapper.findDiskById(fileId);
        return disk;
    }

    @Override
    @Transactional
    public void delFileOrFolder(Integer id) {
        //根据id查询出来对象的disk对象
        Disk disk = diskMapper.findDiskById(id);
        if(disk != null){
            if(disk.getDocType().equals("file")){
                //说明文件类型是文件可以直接删掉
                File file = new File(new File(uploadPath),disk.getName());
                //调用file的delete方法
                file.delete();
                //删除数据库中的记录
                diskMapper.del(id);
            }else {
                //文件类型是文件要查询文件夹下面的子文件夹和文件
                //老师的思路是先查找出来全部文件和文件夹因为要找到磁盘的文件和数据库中存储的文件夹都删掉所有需要事务
                List<Disk> diskList = diskMapper.findAll();
                //记录即将被删除的id
                List<Integer> delIdList = Lists.newArrayList();
                findDelId(diskList,delIdList,id);//此id为文件夹id
                //删除id的集合不包括源id所有需要添加id
                delIdList.add(id);
                diskMapper.batchDel(delIdList);

            }
        }


    }
    //用到递归思想
    private void findDelId(List<Disk> diskList, List<Integer> delIdList, Integer id) {
        //首先是循环此集合
        for (Disk disk :diskList){
            if(disk.getFid().equals(id)){
                //当此对象的fid为id时表示此对象是此id的子文件夹或子文件
                //将此id放入到要删除的id序列当中去用于删除数据库中的记录
                delIdList.add(disk.getId());
                //判断此id是文件还是文件夹如果是文件直接可以进行删除
                if(disk.getDocType().equals(Disk.DIRECTORY)){
                    //如果此对象的文件类型为问价夹那么要判断此文件夹下面是否有子文件或者文件夹
                    //用到递归思想调用该方法
                    findDelId(diskList,delIdList,disk.getId());//圆圈表示递归函数
                }else {
                    //如果是文件可以直接进行删除
                    File file = new File(new File(uploadPath),disk.getName());
                    file.delete();
                }

            }
        }
    }

}
