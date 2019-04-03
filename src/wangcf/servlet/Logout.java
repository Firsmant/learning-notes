package wangcf.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //session销毁
        req.getSession().invalidate();
        //销毁cookie；暂时不考虑销毁cookie
//        Cookie[] cookies = req.getCookies();
//        if (cookies != null) {
//            for (Cookie coo : cookies) {
//                coo.setMaxAge(0);
//            }
//        }
        //返回当前页面
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
