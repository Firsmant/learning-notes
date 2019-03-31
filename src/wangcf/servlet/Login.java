package wangcf.servlet;

import org.apache.commons.dbutils.QueryRunner;
import wangcf.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: wangcf
 * @Date: 2019/3/30 11:37
 */
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "text/html;charset=UTF-8");

        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = JDBCUtil.getConnection();
        String sql = "select * from sys_user where userid = ? and password = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userid);
            ps.setString(2, password);
            rs = ps.executeQuery();
            //登录成功，设置session，并且跳转到首页
            if (rs.next()) {
                req.getSession().setAttribute("username", rs.getString("username"));
                //重定向方式
                resp.sendRedirect("index.jsp");
            } else {
                //登录失败，提醒重新登录
                //请求转发的方式
                req.getSession().setAttribute("message", "登录失败，请检查账号密码");
                req.getRequestDispatcher("login.jsp").forward(req, resp);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
