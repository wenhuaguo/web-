package com.kaishengit.service;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.CompanyContract;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceDoc;
import com.kaishengit.pojo.RentDeviceDetail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by Acer on 2017/1/18.
 */
public interface DeviceService {
    void add(Device device);

    List<Device> findAll();

    List<Device> findDeviceByPage(String start, String length);

    Long count();

    List<Device> findDeviceBySearch(Map<String, Object> searchParam);

    Long countBySearchParam(Map<String,Object> searchParam);

    void del(Integer id);

    Device findDeviceById(int deviceId);

    //void addRentDevice(DeviceSerial deviceSerial);

    //List<DeviceSerial> findDeviceSerialAll();

    //List<DeviceSerial> findDeviceSerialBySearch(Map<String, Object> searchParam);



    String addRentDeivce(DeviceRentDto deviceRentDto);

    CompanyContract findCompantContractBySerialNum(String serialNum);

    List<RentDeviceDetail> findDetailByRentId(Integer id);

    List<DeviceDoc> findFileListByRentId(Integer id);

    InputStream downLoadFile(Integer id) throws FileNotFoundException;

    DeviceDoc findDeviceDocById(Integer id);

    CompanyContract finCompanyContractById(Integer companyId);

    void downLoadZipFile(CompanyContract companyContract, ZipOutputStream zipOutputStream) throws IOException;

    List<CompanyContract> finaAllDeivceContractByQueryParam(Map<String, String> queryParam);

    void changeState(Integer id);

    Long filteredCount(Map<String, String> queryParam);
}
