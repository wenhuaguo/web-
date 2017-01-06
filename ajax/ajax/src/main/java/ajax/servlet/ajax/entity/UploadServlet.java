package ajax.servlet.ajax.entity;

import ajax.util.SendHttpServlet;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Acer on 2016/12/9.
 * 需要添加注解利用Servlet3处理文件上传
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends SendHttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Post提交方式设置字符集为UTF-8处理中文乱码的问题
        req.setCharacterEncoding("UTF-8");
        //确定文件上传后存储的路径
        File file = new File("D:/upfile");
        //判断是否存在如果不存在则创建出来
        if(!file.exists()){
            file.mkdir();
        }
        //通过上传控件name属性的值获取相应的part对象
        Part part = req.getPart("file");
        String fileName = part.getHeader("content-disposition");

        System.out.println(fileName);
        String exe = fileName.substring(fileName.lastIndexOf("."),fileName.length()-1);
        System.out.println(exe);
        String contentType = part.getContentType();
        if(contentType.startsWith("image/")){

        }
        System.out.println(part.getName());
        System.out.println(part.getContentType());
        System.out.println(part.getSize());
//        Map<String,Object> result = new HashMap<>();
//        result.put("state","success");
//        result.put("data","1234");
//        readJson(result,resp);


//        System.out.println(part.getSubmittedFileName());
        //System.out.println(part.getSubmittedFileName());
        //获取文件上传的名称
       // String fileName = part.getSubmittedFileName();
        //System.out.println(fileName);
        //通过UUID算法产生一个唯一名称避免在服务端重名
        String newFileName = UUID.randomUUID() + file.getName();
        //获取上传文件的输入流
        InputStream inputStream = part.getInputStream();
        //文件的输出流
        FileOutputStream out = new FileOutputStream(new File(file,newFileName));
        //通过IOutils工具类的copy()方法进行拷贝
        IOUtils.copy(inputStream,out);
        //关闭输入流和输出流
        out.flush();//关闭之前刷新下缓存区1
        out.close();
        inputStream.close();
    }
}
