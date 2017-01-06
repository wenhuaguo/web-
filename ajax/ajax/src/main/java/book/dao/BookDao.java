package book.dao;

import book.entity.Book;
import book.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.util.List;

/**
 * Created by Acer on 2016/12/7.
 */
public class BookDao {
    /**
     * 显示所有书籍
     * @return
     */
    public List<Book> findAll() {
        String sql = "select * from t_book";
        return Dbhelp.query(sql,new BeanListHandler<>(Book.class));
    }
}
