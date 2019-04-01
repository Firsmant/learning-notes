package wangcf.util;


import javax.servlet.http.Cookie;

/**
 * @Author: wangcf
 * @Date: 2019/3/31 17:27
 */
public class CookieUtil {
    public static Cookie findCookie(Cookie[] cookies,String cookieName){
        Cookie cookie = null;
        if(cookies!=null){
            for(Cookie coo:cookies){
              if(cookieName.equals(coo.getName()) )  {
                  cookie = coo;
                  break;
              }
            }
        }
        return cookie;
    }
}
