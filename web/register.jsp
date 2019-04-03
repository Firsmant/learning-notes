<%@ page import="wangcf.bean.User" %><%--
  User: wangcf
  Date: 2019/3/30 11:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

注册页面

</body>
</html>
