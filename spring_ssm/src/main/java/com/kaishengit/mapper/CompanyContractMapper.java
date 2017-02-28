package com.kaishengit.mapper;


import com.kaishengit.pojo.CompanyContract;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/2/17.
 */
public interface CompanyContractMapper {

    void save(CompanyContract companyContract);

    void updateCost(@Param("totalPrice") Float totalPrice,@Param("preCost") Float preCost,@Param("lastCost") Float lastCost,@Param("id") Integer id);

    CompanyContract findCompanyContractBySerialNum(String serialNum);

    CompanyContract findCompanyContractById(Integer companyId);

    List<CompanyContract> findCompanyContractByQueryParam(Map<String, String> queryParam);

    void changeState(CompanyContract companyContract);

    Long count();

    Long filteredCount(Map<String, String> queryParam);
}
