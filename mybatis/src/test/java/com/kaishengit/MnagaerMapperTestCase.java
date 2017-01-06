package com.kaishengit;

import com.kaishengit.mapper.ManagerMapper;
import com.kaishengit.pojo.Manager;
import com.kaishengit.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Acer on 2017/1/4.
 */
public class MnagaerMapperTestCase {
    private SqlSession sqlSession;
    private ManagerMapper managerMapper;
    @Before
    public void setup(){
        sqlSession = SqlSessionFactoryUtil.getSqlSession();
        managerMapper = sqlSession.getMapper(ManagerMapper.class);
    }
    @After
    public void  close(){
        sqlSession.close();
    }
    @Test
    public void findManagerById(){
        //Logger logger = LoggerFactory.getLogger();
        //获得SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        //动态代理模式自动产生一个ManagerMapper接口的实现类
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        Manager manager = managerMapper.findManagerById(1);
        System.out.println(manager);
        //logger.debug("{}",manager);
        //释放资源
        sqlSession.close();
    }
    @Test
    public void save(){
        //获取SqlSession对象并设定为自动提交事务
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        //创建一个pojo对象
        Manager manager = new Manager();
        manager.setName("rose");
        manager.setPassword("123123");
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        managerMapper.save(manager);
        //释放资源
        sqlSession.close();
    }
    @Test
    public void update(){
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        //创建ManagerMapper接口的对象动态代理模式
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        Manager manager = managerMapper.findManagerById(3);
        manager.setPassword("123");
        manager.setName("aa");
        managerMapper.update(manager);
        //事务回滚如果出错误不进行提交
        sqlSession.rollback();
        //手动提交事务才可以改变数据库
        //sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
    @Test
    public void findAll(){
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        //动态代理模式myBatis自动创建ManagerMapper接口的实现类
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        List<Manager> managerList = managerMapper.findAll();
        for (Manager manager: managerList){
            System.out.println(manager);
        }
        //释放资源
        sqlSession.close();
    }
    @Test
    public void findByNameAndPassword(){
        //多个查询参数的问题解决方式1利用map集合
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("name","wangwu");
        param.put("password","123456");
        Manager manager = managerMapper.findByNameAndPassword(param);
        System.out.println(manager);
        sqlSession.close();
    }
    @Test
    public void findByparam(){
        //多参数查询语句
        Map<String,Object> param = new HashMap<String,Object>();
        //param.put("name","wangwu");
        param.put("password","123456");
        Manager manager = managerMapper.findByParam(param);
        System.out.println(manager);
    }

    @Test
    public void findByIds(){
        //批量查询数据
//        List<Integer> integerList = new ArrayList<Integer>();
//        integerList.add(1);
//        integerList.add(2);
//        integerList.add(3);
        List<Manager> managerList = managerMapper.findByIds(Arrays.asList(1,3,4,5,7,8));
        for(Manager manager : managerList){
            System.out.println(manager);
        }
    }
    @Test
    public void batchSave(){
        //批量提交数据
        List<Manager> managerList = new ArrayList<Manager>();
        managerList.add(new Manager("haha","123123"));
        managerList.add(new Manager("heihie","123123"));
        managerList.add(new Manager("nihao","123123"));
        managerMapper.batchSave(managerList);
        //手动提交事务
        sqlSession.commit();
    }

    @Test
    public void findByCatch(){
        //一级缓存同一个SqlSession中默认开启
        Manager manager = managerMapper.findManagerById(1);
        manager = managerMapper.findManagerById(1);
        manager = managerMapper.findManagerById(1);
        System.out.println(manager);
    }

    @Test
    public void findByCatch2(){
        //二级缓存（默认关闭）同一个SqlSessionFactory中
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        Manager manager = managerMapper.findManagerById(1);
        System.out.println(manager);
        sqlSession.close();
        SqlSession sqlSession1 = SqlSessionFactoryUtil.getSqlSession();
        ManagerMapper managerMapper1 = sqlSession1.getMapper(ManagerMapper.class);
        Manager manager1 = managerMapper1.findManagerById(1);
        System.out.println(manager1);
        sqlSession1.close();
    }
    @Test
    public void findByParams(){
        Manager manager =managerMapper.findByNameAndPassword("wangwu","123456");
        System.out.println(manager);

    }
    @Test
    public void findByparams(){
        Manager manager = managerMapper.findParams("wangwu","123");
        System.out.println(manager);
    }
}
