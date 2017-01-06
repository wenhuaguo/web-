package com.kaishengit.pojo;

/**
 * Created by Acer on 2017/1/4.
 */
public class Student {
    private Integer id;
    private String name;
    private String password;
    private Integer schooleid;
    private Academic academic;

    public Academic getAcademic() {
        return academic;
    }

    public void setAcademic(Academic academic) {
        this.academic = academic;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", schooleid=" + schooleid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSchooleid() {
        return schooleid;
    }

    public void setSchooleid(Integer schooleid) {
        this.schooleid = schooleid;
    }
}
