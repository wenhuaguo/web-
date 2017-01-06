package com.kaishengit.mapper;

import com.kaishengit.pojo.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/1/4.
 * 创建一个Mapper接口
 */
public interface ManagerMapper {
    Manager findManagerById(Integer id);
    void save(Manager manager);
    void del(Integer id);
    void update(Manager manager);
    List<Manager> findAll();
    //多个查询参数问题
    Manager findByNameAndPassword(Map<String,Object> param);
    //动态sql查询
    Manager findByParam(Map<String,Object> param);
    List<Manager> findByIds(List<Integer> idList);
    void batchSave(List<Manager> managerList);
    Manager findByNameAndPassword(@Param("name") String name,@Param("password") String password);
    Manager findParams(String name,String password);
}
