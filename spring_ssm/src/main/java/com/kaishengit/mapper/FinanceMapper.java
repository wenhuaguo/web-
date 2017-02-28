package com.kaishengit.mapper;

import com.kaishengit.pojo.Finance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/2/23.
 */
public interface FinanceMapper {
    void saveFinanceAssembly(Finance finance);

    Finance findFinanceBySerialNum(String serialNum);

    void updateCost(Finance finance);

    List<Finance> findFinanceByQueryParam(Map<String, String> queryParam);

    Long count();

    Long filterCount(Map<String, String> queryParam);

    Finance findFinanceById(Integer id);

    void updateState(Finance finance);

    List<Finance> findFinanceByCreateDate(String date);

    List<Map<String,Object>> findNameAndValueByDate(@Param("date") String date,@Param("type") String type);
}
