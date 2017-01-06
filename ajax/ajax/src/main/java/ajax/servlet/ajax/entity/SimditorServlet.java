package ajax.servlet.ajax.entity;

import ajax.util.SendHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Acer on 2016/12/12.
 */
@WebServlet("/send")
public class SimditorServlet extends SendHttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String textarea = req.getParameter("message");
        System.out.println(textarea);
        req.setAttribute("message",textarea);
        req.getRequestDispatcher("show.jsp").forward(req,resp);
    }
}
