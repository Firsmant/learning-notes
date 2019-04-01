<%--
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
    Object message = session.getAttribute("message");
    if (message != null) {
        out.append(message.toString());
    }%>
<form action="login" method="post">
    账号：<input type="text" name="userid"/><br>
    密码：<input type="password" name="password"/><br>
    记住密码：<input type="checkbox" value="yes"/><br>
    <input type="submit" value="登录"/>
    <a href="register.jsp">注册</a>
</form>
</body>
</html>
