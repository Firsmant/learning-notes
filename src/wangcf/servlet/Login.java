package wangcf.servlet;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import wangcf.bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: wangcf
 * @Date: 2019/3/30 11:37
 */
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "text/html;charset=UTF-8");
        //自动登录值
        String AUTO_LOGIN = "yes";

        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String autoLogin = req.getParameter("autoLogin");
        //DBUtiles和C3P0结合使用
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from sys_user where userid = ? and password = ? ";
        try {
            User userInfo = qr.query(sql, new BeanHandler<User>(User.class), userid, password);

            if (userInfo != null) {
                //如果选择自动登录，需要记住cookie 60*60*27*7
                //在下次访问其他页面的时候，直接使用cookie中保存的账号密码登录
                if (AUTO_LOGIN.equals(autoLogin)) {
                    Cookie cookie = new Cookie("autoLogin", userInfo.getUserid() + "#" + userInfo.getPassword());
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    cookie.setPath("/login");
                    resp.addCookie(cookie);
                }
                //登录成功，设置session并且跳转到首页
                req.getSession().setAttribute("userInfo", userInfo);
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
