package wangcf.test;

import org.junit.Test;
import wangcf.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: wangcf
 * @Date: 2019/3/30 14:40
 */

public class JDBCUtilTest {
    @Test
    public void testJDBCUtil() {
        Connection conn = null;
        Statement stat = null;
        ResultSet st = null;
        try {
            conn = JDBCUtil.getConnection();
            stat = conn.createStatement();
            String sql = "select * from sys_user";
            st = stat.executeQuery(sql);
            while (st.next()) {
                System.out.println("userid：" + st.getInt("userid"));
                System.out.println("username: " + st.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stat);
        }
    }
}
