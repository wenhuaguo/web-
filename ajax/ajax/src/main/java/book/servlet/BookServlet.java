package book.servlet;

import book.entity.Book;
import book.service.BookService;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Acer on 2016/12/7.
 */
@WebServlet("/list")
public class BookServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setCharacterEncoding("UTF-8");
        //resp.setContentType("application/json;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        BookService bookService = new BookService();
         List<Book> list =bookService.showAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("listBook.jsp").forward(req,resp);
        //Gson json = new Gson();
        //String listBook = json.toJson(list);
        //创建输出流对象
        //PrintWriter out = resp.getWriter();
        //out.print(listBook);
        //out.flush();
        //out.close();
    }
}
