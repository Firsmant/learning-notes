<%@ page import="wangcf.bean.User" %><%--
  User: wangcf
  Date: 2019/3/30 11:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%
    Object userInfo = (User)session.getAttribute("userInfo");
    if (userInfo != null) {
        out.write("欢迎您：" + ((User) userInfo).getUsername());
        out.append("<a href=\"logout\">退出</a>");
    } else {
        out.append("<a href=\"login.jsp\">请登录</a>");
    }
%>

<%
    Object message = session.getAttribute("message");
    if (message != null) {
        out.append(message.toString());
    }%>
<form action="login" method="post">
    账号：<input type="text" name="userid"/><br>
    密码：<input type="password" name="password"/><br>
    记住密码：<input type="checkbox" name="autoLogin" value="yes"/><br>
    <input type="submit" value="登录"/>
    <a href="register.jsp">注册</a>
</form>
</body>
</html>
