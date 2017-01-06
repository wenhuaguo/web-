package com.kaishengit;

import com.kaishengit.mapper.AccountMapper;
import com.kaishengit.pojo.Account;
import com.kaishengit.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/1/5.
 */
public class AccountMapperTestCase {
    @Test
    public void save(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accounts = accountMapper.findAll();
        for(Account account : accounts){
            System.out.println(account);
        }
        //accountMapper.del(8);
//        Account account = accountMapper.findById(13);
//        System.out.println(account);
//        account.setName("王五");
//        accountMapper.save(account);
//        Account account = new Account();
//        account.setName("李四");
//        account.setMoney(100);
//        accountMapper.save(account);
        sqlSession.commit();
        System.out.println("存储成功");
        sqlSession.close();
    }
    @Test
    public void findPage(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accounts = accountMapper.findPage(0,5);
        for(Account account : accounts){
            System.out.println(account);
        }
        sqlSession.close();
    }
    @Test
    public void findByparam(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Map<String,Object> param = new HashMap<String,Object>();
        //param.put("name","王五");
        param.put("money",100);
        List<Account> accounts = accountMapper.aa(param);
        for(Account account: accounts){
            System.out.println(account);
        }
        sqlSession.close();
    }
}
