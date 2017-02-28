package com.kaishengit.service;

import com.kaishengit.pojo.Finance;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/2/23.
 */
public interface FinanceService {
    List<Finance> findFinanceByQueryParam(Map<String, String> queryParam);

    Long count();

    Long filterCount(Map<String, String> queryParam);

    void changeFinanceStateById(Integer id);

    List<Finance> findFinanceByCreateDate(String date);

    List<Map<String,Object>> findPieDtataByDate(String date, String type);
}
