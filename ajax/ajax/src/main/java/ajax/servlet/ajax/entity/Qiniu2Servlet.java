package ajax.servlet.ajax.entity;

import ajax.util.SendHttpServlet;
import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/12.
 */
@WebServlet("/upload11")
@MultipartConfig
public class Qiniu2Servlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //初始化和七牛云存储进行连接需要一对有效的access key 和 secret key
        String ak = "r-bFs81SQP2jFGhkSo3s4IkxEECeWw3Gs-Ov2vtn";

        String sk = "25EThnRaXIL3xmBSp8v6y5R_e0IFeMJLykdZdWrb";
        String bucket = "storage"; //文件上传存储的空间名
        //通过ak和sk进行密匙配置
        Auth auth = Auth.create(ak,sk);
        //产生上传的token值通过密匙配置产生的对象
        String token = auth.uploadToken(bucket);
        req.setAttribute("token",token);
        req.setAttribute("id",123123);
        req.getRequestDispatcher("qiniu2.jsp").forward(req,resp);

    }
}
