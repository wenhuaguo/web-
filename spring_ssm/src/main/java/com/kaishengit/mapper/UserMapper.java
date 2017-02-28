package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import com.kaishengit.utile.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2017/1/12.
 */
public interface UserMapper {
    void add(User user);
    List<User> findAll();

    void del(Integer userId);

    User findById(Integer id);

    void update(User user);

    Long totals(@Param("queryName") String queryName,@Param("queryRole") String queryRole);

    List<User> findUserBySearchParam(@Param("queryName") String queryName,
                                     @Param("queryRole") String queryRole,
                                     @Param("pageStar") Integer pageStar,
                                     @Param("pageSize") Integer pageSize);

    User findByUserName(String userName);
}
