package com.kaishengit.mapper;

import com.kaishengit.pojo.Student;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Acer on 2017/1/4.
 */
public interface StudentMapper {
    Student findById(Integer id);
    List<Student> findBySchooleid(Integer schooleid);
    List<Student> findAll();
    //多对一注解写
    @Select("select * from student where id = #{id}")
    @Results(value= {
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "password",property = "password"),
            @Result(column = "schooleid",property = "schooleid"),
            @Result(property = "academic",column = "schooleid",one = @One(select = "com.kaishengit.mapper.AcademicMapper.findById"))
    }
    )
    Student findByIds(Integer id);
}
