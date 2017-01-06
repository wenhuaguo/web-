package com.kaishengit.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by Acer on 2016/12/16.
 */
public class ChangeEcoding extends org.apache.commons.lang3.StringUtils {
    //doGet解决中文乱码的问题，并进行日志记录
    private static Logger logger = LoggerFactory.getLogger(ChangeEcoding.class);
    public static String chageEncoding(String str){
        try {
            return new String(str.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串{}异常",str);
            throw  new RuntimeException("字符串"+str+"异常",e);
        }
    }
}
