package com.kaishengit.service;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.readconfig.ReadConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Acer on 2016/12/28.
 */
public class AdminService {
    //管理员登录日志
    Logger logger = LoggerFactory.getLogger(AdminService.class);
    private AdminDao admindao = new AdminDao();
    //管理员进行登录
    public Admin loginByNameAndPassword( String adminname, String password,String ip) {
        Admin admin = admindao.findByName(adminname);
        if(admin != null && admin.getPassword().equals(DigestUtils.md5Hex(ReadConfig.get("user.password.salt") + password))){
            logger.debug("{}管理员登录了管理系统，ip{}为",admin.getAdminname(),ip);
            return admin;
        }else {
            throw new ServiceException("账号或密码错误");
        }
    }
}
