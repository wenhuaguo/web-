package com.kaishengit;

import com.kaishengit.mapper.AcademicMapper;
import com.kaishengit.pojo.Academic;
import com.kaishengit.pojo.Student;
import com.kaishengit.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by Acer on 2017/1/4.
 */
public class AcademicMapperTestCase {
    @Test
    public void findById(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AcademicMapper academicMapper = sqlSession.getMapper(AcademicMapper.class);
        Academic academic = academicMapper.findById(1);
        List<Student> students = academic.getStudentList();
        System.out.println(academic);
        for(Student student :students){
            System.out.println(student);
        }
        sqlSession.close();
    }
    @Test
    public void findAll(){
        //数据库查询的N+1此问题
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AcademicMapper academicMapper = sqlSession.getMapper(AcademicMapper.class);
        List<Academic> academics = academicMapper.findAll();

        for(Academic academic : academics){
            System.out.println(academic);
            //之前讲Student 对象封装到 Academic实体类中作为属性，是1对多的体现
            List<Student> students = academic.getStudentList();
            for(Student student : students){
                System.out.println(student);
            }
            System.out.println("............................................");
        }
        sqlSession.close();
    }
    @Test
    public void findByParams(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        AcademicMapper academicMapper = sqlSession.getMapper(AcademicMapper.class);
        Academic academic = academicMapper.findByidParam(1);
        System.out.println(academic);
        List<Student> students = academic.getStudentList();
        for(Student student :students){
            System.out.println(student);
        }
    }
}
