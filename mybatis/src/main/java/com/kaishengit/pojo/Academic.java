package com.kaishengit.pojo;

import java.util.List;

/**
 * Created by Acer on 2017/1/4.
 */
public class Academic {
    private Integer id;
    private String academic;
    List<Student> studentList;

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    @Override
    public String toString() {
        return "Academic{" +
                "id=" + id +
                ", academic='" + academic + '\'' +
                '}';
    }
}
