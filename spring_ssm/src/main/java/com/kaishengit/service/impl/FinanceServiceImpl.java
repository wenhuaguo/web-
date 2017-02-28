package com.kaishengit.service.impl;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.FinanceMapper;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import com.kaishengit.shiro.ShiroUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/2/23.
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceMapper financeMapper;

    @Override
    public List<Finance> findFinanceByQueryParam(Map<String, String> queryParam) {
        List<Finance> financeList = financeMapper.findFinanceByQueryParam(queryParam);
        return financeList;
    }

    @Override
    public Long count() {
        Long count = financeMapper.count();
        return count;
    }

    @Override
    public Long filterCount(Map<String, String> queryParam) {
        return financeMapper.filterCount(queryParam);
    }

    @Override
    public void changeFinanceStateById(Integer id) {
        Finance finance = financeMapper.findFinanceById(id);
        //是否需要判断该财务流水存在
        finance.setState(Finance.STATE_OK);
        finance.setConfirmUser(ShiroUtil.getCurrentUserName());
        finance.setConfirmDate(DateTime.now().toString("yyyy-MM-dd"));
        System.out.println(DateTime.now().toString("yyyy-MM-dd"));
        financeMapper.updateState(finance);

    }

    @Override
    public List<Finance> findFinanceByCreateDate(String date) {

        return financeMapper.findFinanceByCreateDate(date);
    }

    @Override
    public List<Map<String, Object>> findPieDtataByDate(String date,String type) {
        System.out.println("tyep:"+ type + "date:" + date);
        return financeMapper.findNameAndValueByDate(date,type);
    }

}
