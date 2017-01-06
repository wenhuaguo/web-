package com.kaishengit.dto;

/**
 * Created by Acer on 2016/12/19.
 */
public class JsonResult {
    //state的状态就两个所有写成常量设置为共有属性变量
    public static final String SUCCESS="success";
    public static final String ERROR="error";
    private String state;
    private String message;
    private Object data;
    //同时一定要重载原始的构造方法
    public JsonResult() {
    }

    //写对应的不同的构造方法进行使用
    public JsonResult(String state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }
    //如果状态是success给客户端一个成功的数据
    public JsonResult(Object data) {
        this.state=SUCCESS;
        this.data = data;
    }
    //如果状态是error那么就返回给客户端一个信息
    public JsonResult(String message) {
        this.state=ERROR;
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
