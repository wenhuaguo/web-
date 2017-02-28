package com.kaishengit.mapper;

import com.kaishengit.pojo.RentDeviceDetail;

import java.util.List;

/**
 * Created by Acer on 2017/2/17.
 */
public interface RentDeviceDetailMapper {
    void batchSave(List<RentDeviceDetail> deviceDetails);


    List<RentDeviceDetail> findDetailByRentId(Integer id);
}
