package book.service;

import book.dao.BookDao;
import book.entity.Book;

import java.util.List;

/**
 * Created by Acer on 2016/12/7.
 */
public class BookService {
    private BookDao book = new BookDao();
    /**
     * 显示所有书籍
     */
    public List<Book> showAll() {
        return book.findAll();
    }
}
