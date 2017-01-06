package ajax.util;



import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Acer on 2016/12/9.
 */
public class SendHttpServlet extends HttpServlet {
    /**
     * 给客户端响应一个json数据
     * @param object
     * @param response
     * @throws IOException
     */
    public void readJson(Object object, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        String json = new Gson().toJson(object);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
