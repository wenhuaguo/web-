package com.kaishengit.util.readconfig;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Acer on 2016/12/15.
 */
public class ReadConfig {
    private static Properties properties = new Properties();
    static {

        try {
            properties.load(ReadConfig.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String get(String str){
        return properties.getProperty(str);
    }

}
