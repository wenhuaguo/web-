package ajax.servlet.ajax.entity;

import ajax.servlet.entity.Student;
import ajax.servlet.service.MessageService;
import ajax.util.SendHttpServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Acer on 2016/12/9.
 */
@WebServlet("/timeline")
public class TimeLineServlet extends SendHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String madId = req.getParameter("maxId");
        int id = 0;
        if(StringUtils.isNumeric(madId)){
            id = Integer.parseInt(madId);
        }
        MessageService messageService = new MessageService();
        List<Student> list = messageService.findById(id);

        readJson(list,resp);

    }
}
