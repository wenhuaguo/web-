package com.kaishengit.mapper;

import com.kaishengit.pojo.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/1/5.
 * 加在接口上面表示开启二级缓存如果没有配置文件的话如果有最好写在配置文件中
 */
//@CacheNamespace
public interface  AccountMapper {
    //通过注解的形式不用进行写Mapper接口的配置文件对应某些语句对应动态Sql语句必须写配置文件
    //增加，获得主动增长主键的值,不刷新缓存
    @Insert("insert into account(name,money) values(#{name},#{money})")
    @Options(useGeneratedKeys = true,keyProperty = "id",flushCache = false)
    void save(Account account);

    //删除
    @Delete("delete from account where id=#{id}")
    void del(Integer id);

    //修改
    @Update("update from account set name=#{name},money=#{money} where id = #{id}")
    void update(Account account);

    //查询单个查询结果不放入缓存
    @Select("select * from account where id = #{id}")
    @Options(useCache = false)
    Account findById(Integer id);

    //查询全部所有
    @Select("select * from account")
    List<Account> findAll();
//    $和#区别是$不加单引号，#加单引号，在显示几条数据的时候应该用$
    @Select("select * from account limit ${star},${size}")
    List<Account> findPage(@Param("star") int star,
                           @Param("size") int size);

    List<Account> aa(Map<String,Object> param);
}
