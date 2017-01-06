package book.util;




import book.exception.DataExpaction;
import org.apache.commons.dbcp2.BasicDataSource;


import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Acer on 2016/11/30.
 */
public class DataConnetion {
    //静态常量必须是有值得所有将final去掉
    private static  String DRIVER ;//= "com.mysql.jdbc.Driver";
    private static  String URL;// = "jdbc:mysql:///db_1";
    private static  String USER;// = "root";
    private static  String PASSWORD;//= "root";
    //来源于dbcp项目
    private static BasicDataSource basicDataSource = new BasicDataSource();
    //静态代码块只执行一次数据库连接池只能是一个并且只需要执行一次所有放在静态代码块中
    static {
        //加载并读取config配置文件
        Properties properties = new Properties();//通过Properties类的对象读取配置文件
        //获取源代码根目录文件
        try {
            properties.load(DataConnetion.class.getClassLoader().getResourceAsStream("config.properties"));
            DRIVER = properties.getProperty("jdbc.driver");
            USER = properties.getProperty("jdbc.user");
            PASSWORD = properties.getProperty("jdbc.password");
            URL = properties.getProperty("jdbc.url");
        } catch (IOException e) {
            throw new DataExpaction("读取config文件异常",e);
        }
        basicDataSource.setDriverClassName(DRIVER);
        basicDataSource.setUrl(URL);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setUsername(USER);

        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxWaitMillis(5000);
    }

    //获取数据库连接池
    public static DataSource getDataSource(){
        return basicDataSource;
    }
    public static Connection getConnection(){
        Connection connection = null;
        try {
            //从数据库池中获取一个连接，不能再向数据库要了
            connection = basicDataSource.getConnection();
        } catch (Exception e) {
           throw new DataExpaction("获取数据库连接异常",e);
        }
        return  connection;
    }

}
