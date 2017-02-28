package com.kaishengit.mapper;

import com.kaishengit.pojo.LabourType;

import java.util.List;

/**
 * Created by Acer on 2017/2/18.
 */
public interface LabourTypeMapper {
    List<LabourType> findAll();

    LabourType findLabourById(Integer id);

    void updateLabourTypeCurrentNum(LabourType labourType);
}
