package com.kaishengit.entity;

/**
 * Created by Acer on 2016/12/20.
 */
public class Node {
    private Integer id;
    private String nodename;
    private Integer nodecount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Integer getNodecount() {
        return nodecount;
    }

    public void setNodecount(Integer nodecount) {
        this.nodecount = nodecount;
    }
}
