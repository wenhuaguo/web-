package ajax.test;


import ajax.servlet.entity.Student;

import book.util.Dbhelp;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;


/**
 * Created by Acer on 2016/12/9.
 */
public class TestStudent {
    public static void main(String[] args) {
        String sql = "select * from student where id > ? order by id desc";
        List<Student> student =  Dbhelp.query(sql,new BeanListHandler<>(Student.class),4);
        System.out.println(student.size());

    }
}
