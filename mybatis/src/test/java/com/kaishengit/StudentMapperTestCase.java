package com.kaishengit;

import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.pojo.Academic;
import com.kaishengit.pojo.Student;
import com.kaishengit.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Acer on 2017/1/4.
 */
public class StudentMapperTestCase {
    @Test
    public void findById(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.findById(1);
        Academic academic = student.getAcademic();
        System.out.println(student);
        System.out.println(academic);
        sqlSession.close();
    }
    @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.findAll();
        for(Student student : students){
            System.out.println(student);
            Academic academic = student.getAcademic();
            System.out.println(academic);
        }
        sqlSession.close();
    }
    @Test
    public void findByparams(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.findByIds(1);
        System.out.println(student);
        Academic academic = student.getAcademic();
        System.out.println(academic);
        sqlSession.close();
    }
}
