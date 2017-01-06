package ajax.jquery;

import ajax.util.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Acer on 2016/12/8.
 */
@WebServlet("/data.xml")
public class XmlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        System.out.println(url);
        String xml = HttpUtils.httpCilentServlet(url);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(xml);
        out.flush();
        out.close();
    }
}
