package com.kaishengit.dao;

import com.kaishengit.entity.Admin;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Acer on 2016/12/28.
 */
public class AdminDao {
    //根据名查找对应的账号
    public Admin findByName(String adminname) {
        String sql = "select * from t_admin where adminname=?";
        return Dbhelp.query(sql,new BeanHandler<>(Admin.class),adminname);
    }
}
