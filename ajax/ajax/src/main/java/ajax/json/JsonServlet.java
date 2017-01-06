package ajax.json;

import ajax.servlet.ajax.entity.User;
import com.google.gson.Gson;

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
 * Created by Acer on 2016/12/6.
 */
@WebServlet("/data.json")
public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        User user = new User(1,"张三","北京");
        User user1 = new User(2,"李四","天津");
        User user2 = new User(3,"王五","河南");
        //language=JSON
       // String json = ""
        //将所有对象放到List集合中通过Arrays的asList()方法
        List<User> userList = Arrays.asList(user,user1,user2);

        //利用第三方库，Google-json，创建json对象
        Gson gson = new Gson();
        //将数据转换为json数据格式
        String json = gson.toJson(userList);
        //输出json,创建响应输出流
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
