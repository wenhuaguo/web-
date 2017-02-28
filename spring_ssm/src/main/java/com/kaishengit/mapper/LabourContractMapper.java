package com.kaishengit.mapper;

import com.kaishengit.pojo.LabourContract;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/2/20.
 */
public interface LabourContractMapper {
    void save(LabourContract companyContract);

    void updateCost(@Param("totalPrice") Float totalPrice,@Param("preCost") Float preCost,@Param("lastCost") Float lastCost,@Param("id") Integer id);

    LabourContract findCompanyContractBySerialNum(String id);

    List<LabourContract> findLabourContractByQueryParam(Map<String, String> queryParam);

    Long count();

    LabourContract findLabourContractById(Integer id);

    void updateState(LabourContract labourContract);
}
