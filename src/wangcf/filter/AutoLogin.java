package wangcf.filter;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import wangcf.bean.User;
import wangcf.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: wangcf
 * @Date: 2019/3/31 16:50
 */
public class AutoLogin implements Filter {

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        User userInfo = (User) request.getSession().getAttribute("userInfo");
        if (userInfo != null) {
            //已登录,直接进入相应页面
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //未登录，获取cookie中的登录信息，尝试自动登录
            Cookie[] cookies = request.getCookies();
            //cookies中是否有autoLogin信息
            Cookie cookie = CookieUtil.findCookie(cookies, "autoLogin");
            if (cookie == null) {
                //未登录过，不进行自动登录，进入相应页面
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                String userid = cookie.getValue().split("#")[0];
                String password = cookie.getValue().split("#")[1];

                //DBUtiles和C3P0结合使用,此处代码和Login中的登录可以复用
                QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
                String sql = "select * from sys_user where userid = ? and password = ? ";
                //尝试登录
                try {
                    userInfo = qr.query(sql, new BeanHandler<User>(User.class), userid, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //账号密码可用,自动登录成功，设置该用户session
                if (userInfo != null) {
                    request.getSession().setAttribute("userInfo", userInfo);
                }
                filterChain.doFilter(request, servletResponse);
            }
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
