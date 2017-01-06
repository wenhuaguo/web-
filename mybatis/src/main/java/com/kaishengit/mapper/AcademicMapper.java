package com.kaishengit.mapper;

import com.kaishengit.pojo.Academic;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Acer on 2017/1/4.
 */
public interface AcademicMapper {
    Academic findById(Integer id);
    List<Academic> findAll();
    //一对多的注解形式
    @Select("select * from academic where id=#{id}")
    @Results(value ={
            @Result(property = "id",column = "id"),
            @Result(property = "academic",column = "academic"),
            @Result(property = "studentList",javaType = List.class,column = "id",many = @Many(select = "com.kaishengit.mapper.StudentMapper.findBySchooleid"))
    })
    Academic findByidParam(Integer id);
}
