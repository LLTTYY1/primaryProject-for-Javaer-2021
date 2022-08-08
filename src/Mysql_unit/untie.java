package Mysql_unit;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
/**
 * @author 刘泰源
 * @version 1.8
 * @date 2022
 * 这是一个工具类(负责Mysql的连接)
 */
public class untie {
    //定义四个常用的属性
    private static String user;
    private static String password;
    private static String url;
    private static String driver;
     //用static代码进行初始化
     static {
         Properties properties = new Properties();
         try {
             properties.load(new FileInputStream("src\\mysql.propertise02"));
             properties.getProperty("user");
             properties.getProperty("password");
             properties.getProperty("url");
             properties.getProperty("driver");
         } catch (IOException e) {
             throw new RuntimeException();
         }
     }
     //连接数据库的方法
    public static Connection getconnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            //将编译异常转变成运行时异常
            throw new RuntimeException();
        }
        return connection;
    }
    //要关闭的资源(statement,connection,resultset)
    public static void close(Statement statement, Connection connection, ResultSet resultSet){
        try {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
