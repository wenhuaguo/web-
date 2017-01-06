package com.kaishengit.web.user;

import com.kaishengit.entity.User;
import com.kaishengit.service.RegService;
import com.kaishengit.util.ChangeEcoding;
import com.kaishengit.web.SendHttpServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/16.
 */
@WebServlet("/user/validate/email")
public class EmailServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String type = req.getParameter("type");
        //对登录过的用户修改设置资料邮箱进行验证

        HttpSession session = req.getSession();
        User currUser = (User)session.getAttribute("curr_user");//从session空间中获取该用户
        //1.首先判断type是否为空和值是否是1如果是1表示是从setServlet程序中过来的否则是重注册页面过来
        if(currUser != null && "1".equals(type)) {
            //2.然后判断根据session取出来的对象是否为空不为空继续下去为空则不管
            if(currUser != null) {
                //3.判断输入的邮箱是否和之前邮箱相同
                if (email.equals(currUser.getEmail())) {
                    readText("true", resp);
                    return;//return;不带返回值表示终止此方法之后的程序不再运行
                }
            }
        }
        System.out.println("email" + email);
        //该方法解决doGet传递数据中文乱码的问题
        email = ChangeEcoding.chageEncoding(email);
        RegService reg = new RegService();
        User user = reg.findByEamil(email);
        if(user == null){
            readText("true",resp);
        }else {
            readText("false",resp);
        }
    }
}
