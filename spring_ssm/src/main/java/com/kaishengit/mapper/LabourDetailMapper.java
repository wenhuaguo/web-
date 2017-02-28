package com.kaishengit.mapper;

import com.kaishengit.pojo.LabourDetail;

import java.util.List;

/**
 * Created by Acer on 2017/2/19.
 */
public interface LabourDetailMapper {
    void save(List<LabourDetail> labourDetailList);

    List<LabourDetail> findLabourRentDetail(Integer id);
}
