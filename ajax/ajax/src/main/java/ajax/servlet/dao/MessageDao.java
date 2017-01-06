package ajax.servlet.dao;

import ajax.servlet.entity.Student;
import book.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by Acer on 2016/12/9.
 */
public class MessageDao {
    public List<Student> findAll(){
        String sql = "select * from student order by id desc";
        return Dbhelp.query(sql,new BeanListHandler<>(Student.class));
    }

    public List<Student> findById(int id) {
        String sql = "select * from student where id > ? order by id desc";
        return Dbhelp.query(sql,new BeanListHandler<>(Student.class),id);
    }
}
