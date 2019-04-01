package wangcf.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: wangcf
 * @Date: 2019/3/31 16:14
 */
public class C3P0Test {
    @Test
    public void testC3P0Test() throws PropertyVetoException, SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        //可以将连接数据库信息配置到c3p0-config.xml
//        cpds.setDriverClass("com.mysql.jdbc.Driver");
//        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/reward");
//        cpds.setUser("root");
//        cpds.setPassword("root");
        Connection connection = cpds.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sys_user");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
        }

        connection.close();
        statement.close();
        resultSet.close();
    }
}
