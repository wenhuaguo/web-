package ajax.servlet.ajax.entity;

import ajax.util.SendHttpServlet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/10.
 */
@WebServlet("/qiniu")
public class QiniuServlet extends SendHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //要接入云存储需要一对有效的ak和sk进行签名认证
        //产生token
        //2.设置AK和SK,设置存储空间的我名称3.设置回调（303）URL
        String ak = "r-bFs81SQP2jFGhkSo3s4IkxEECeWw3Gs-Ov2vtn";
        String  sk = "25EThnRaXIL3xmBSp8v6y5R_e0IFeMJLykdZdWrb";
        String bucketName = "storage";//要上传的空间
        Auth auth = Auth.create(ak,sk);//密匙的配置
        //计算上传文件的token
        StringMap stringMap = new StringMap();
        stringMap.put("returnUrl","http://localhost:8080/qiniucallback");
        //3600指的是token过期时间，单位是秒
        String token = auth.uploadToken(bucketName,null,3600,stringMap);
        req.setAttribute("token",token);
        req.getRequestDispatcher("qiniu.jsp").forward(req,resp);

    }
}
