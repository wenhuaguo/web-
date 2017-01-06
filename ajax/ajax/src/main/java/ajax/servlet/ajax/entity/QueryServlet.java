package ajax.servlet.ajax.entity;

import ajax.util.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Acer on 2016/12/6.
 */
@WebServlet("/query")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        name = new String(name.getBytes("ISO8859-1"),"UTF-8");
        String url = "http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&key=1587754017&type=data&doctype=xml&version=1.1&q=" + name;
        String query = HttpUtils.httpCilentServlet(url);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml; charset=UTF-8");
        PrintWriter out = resp.getWriter();//获取响应输出流
        out.print(query);
        out.flush();
        out.close();
    }
}
