package ajax.servlet.ajax.entity;

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
@WebServlet("/checkemail")
public class EmailServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println("Email:" +email);

        PrintWriter out = resp.getWriter();
        if("123@qq.com".equals(email)){
            out.print("false");
        }else {
            out.print("true");
        }
        out.flush();
        out.close();
    }
}
