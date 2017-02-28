package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceDoc;

import java.util.List;

/**
 * Created by Acer on 2017/2/17.
 */
public interface DeviceDocMapper {
    void batchSave(List<DeviceDoc> deviceRentDocs);

    List<DeviceDoc> findFileListByRentId(Integer id);

    DeviceDoc findDeviceDocById(Integer id);

    List<DeviceDoc> findFileByCompanyContractId(Integer id);
}
