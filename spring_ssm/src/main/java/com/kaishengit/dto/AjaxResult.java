package com.kaishengit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Acer on 2017/2/16.
 * 基于注解的get和set方法
 * AllArgsConstructor基于注解的全部构造方法参数
 */
@Data
@AllArgsConstructor
public class AjaxResult {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String status;
    private String message;
    private Object data;

    public AjaxResult(){}

    public AjaxResult(String status,String message) {
        this.status = status;
        this.message = message;
    }

    public AjaxResult(Object data) {
        this.status = SUCCESS;
        this.data = data;
    }

}
