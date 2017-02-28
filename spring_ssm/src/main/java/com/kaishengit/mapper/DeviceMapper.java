package com.kaishengit.mapper;

import com.kaishengit.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/1/18.
 */
public interface DeviceMapper {

    void add(Device device);

    List<Device> findAll();

    List<Device>findDeviceByPage(@Param("star") String star,@Param("length") String length);

    Long count();

    List<Device> findBySearchParam(Map<String, Object> searchParam);

    Long countBySearchParam(Map<String,Object> searchParam);

    void del(Integer id);

    Device findDeviceById(int deviceId);

    void updateCurrentNum(Device device);
}
