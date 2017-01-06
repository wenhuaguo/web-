package com.kaishengit.dao;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Acer on 2016/12/13.
 */
public class Log4jTestCase {
    @Test
    public void TestLog(){
        //创建Logger(记录器)对象
        Logger logger = LoggerFactory.getLogger(Log4jTestCase.class);//参数为当前运行时类
        logger.trace("{}-{} trance message","tom","hello");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        //logger.fatal("fatal message");
    }
}
