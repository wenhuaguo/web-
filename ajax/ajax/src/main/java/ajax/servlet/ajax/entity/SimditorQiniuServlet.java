package ajax.servlet.ajax.entity;

import ajax.util.SendHttpServlet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/12.
 */
@WebServlet("/uploadSim")
public class SimditorQiniuServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //初始化和七牛云存储进行连接需要一对有效的access key 和 secret key
        String ak = "r-bFs81SQP2jFGhkSo3s4IkxEECeWw3Gs-Ov2vtn";

        String sk = "25EThnRaXIL3xmBSp8v6y5R_e0IFeMJLykdZdWrb";
        String bucket = "storage"; //文件上传存储的空间名
        //通过ak和sk进行密匙配置创建Auth对象
        Auth auth = Auth.create(ak,sk);
        String returnBody = "{\"success\":true,\"file_path\":\"http://ohwsqq8z2.bkt.clouddn.com/${key}\"}";//自定义响应内容实际上是用户定义的反馈信息模板内容必须为json格式表达式
        StringMap stringMap = new StringMap();
        stringMap.put("returnBody",returnBody);
        //通过Auth对象创建token值
        String token = auth.uploadToken(bucket,null,3600,stringMap);
        req.setAttribute("token",token);
        req.getRequestDispatcher("wysiwyg.jsp").forward(req,resp);
    }
}
