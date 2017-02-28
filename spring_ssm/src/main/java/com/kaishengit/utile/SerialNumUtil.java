package com.kaishengit.utile;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;

/**
 * Created by Acer on 2017/2/18.
 * 产生流水号的工具类
 */
public class SerialNumUtil {
    //产生流水号的方法
    public static String getSerialNumber(){
        DateTime now = new DateTime();
        //按照当前时间产生一串数字
        String result = now.toString("YYYYMMDDHHmmss");
        //然后这一串数字+随机产生的四位数字
        result+= RandomStringUtils.randomNumeric(4);
        return result;
    }
}
