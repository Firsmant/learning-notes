package wangcf.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author: wangcf
 * @Date: 2019/3/30 14:21
 */
public class JDBCUtil {

    //    private static String className = "com.mysql.jdbc.Driver";
    //    private static String url = "jdbc:mysql://localhost:3306/reward";
    //    private static String user = "root";
    //    private static String password = "root";
    //通过properties加载数据库连接信息
    private static String className;
    private static String url;
    private static String user;
    private static String password;

    /**
     * 加载数据库连接信息
     */
    static {
        try {
            Properties properties = new Properties();
            //使用类加载器读取src目录下的文件
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            className = properties.getProperty("className");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            Class.forName(className);
            //释放资源
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     *
     * @param conn
     * @param stat
     * @param rs
     */
    public static void close(Connection conn, Statement stat, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(stat);
        closeConnection(conn);
    }

    /**
     * 释放资源
     *
     * @param conn
     * @param stat
     */
    public static void close(Connection conn, Statement stat) {
        closeStatement(stat);
        closeConnection(conn);
    }

    /**
     * 释放资源
     *
     * @param conn
     */
    private static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    /**
     * 释放资源
     *
     * @param rs
     */
    private static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }
    }

    /**
     * 释放资源
     *
     * @param state
     */
    private static void closeStatement(Statement state) {
        try {
            if (state != null) {
                state.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            state = null;
        }
    }


}
