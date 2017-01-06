package book.util;




import book.exception.DataExpaction;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Acer on 2016/11/30.
 * 有了数据库连接池使用DbHelp更加方便来了
 */
public class Dbhelp {

    private static Connection GetConnection(){
        return DataConnetion.getConnection();
    }
    public static void update(String sql,Object... params) throws DataExpaction {


        try {
            QueryRunner queryRunner = new QueryRunner(DataConnetion.getDataSource());
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new DataExpaction("执行" + sql + "异常",e);
        }
    }
    public static <T> T query(String sql,ResultSetHandler<T> rsh,Object... params) throws DataExpaction{
        //使用开源库项目（Dbutils）中的QueryRunner对象的update方法执行（增删改）和query方法分别执行select查询
        //不用关自己会关数据库连接
        QueryRunner queryRunner = new QueryRunner(DataConnetion.getDataSource());
        try {
            T t =queryRunner.query(sql,rsh,params);
            System.out.println("sql:" + sql + t);
            return t;
        } catch (SQLException e) {
            throw new DataExpaction("执行" + sql + "异常",e);
        }
    }

    //关闭数据库连接
    private static void close(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                throw new DataExpaction("关闭Connection异常",e);
            }
        }
    }

}
