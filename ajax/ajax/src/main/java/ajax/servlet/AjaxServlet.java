package ajax.servlet;

import ajax.servlet.ajax.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Acer on 2016/12/5.
 */
@WebServlet("/user.xml")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过改写实体类的构造方法使创建对象简单
        User user1 = new User(1001,"张三","天津");
        User user2 = new User(1002,"李四","北京");
        User user3 = new User(1003,"王五","石家庄");

        List<User> users = Arrays.asList(user1,user2,user3);//将多个对象一次添加到集合中

        //1.设置响应头的字符编码
        resp.setCharacterEncoding("UTF-8");
        //2.设置响应头MIME type类型
        resp.setContentType("text/xml; charset=UTF-8");
        //3.通过PrintWriter对象给客户返回XML文件内容
        PrintWriter out = resp.getWriter();
       out.print("<users>");
        for(User user : users){
            out.print("<user  id=\""+user.getId()+"\">");
            out.print("<name>" + user.getName() + "</name>");
            out.print("<address>" + user.getAddress() + "</address>");
            out.print("</user>");
        }
        out.print("</users>");
        out.flush();
        out.close();
    }
}
