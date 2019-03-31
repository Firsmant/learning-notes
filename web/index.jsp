<%@ page import="wangcf.bean.User" %><%--
  User: wangcf
  Date: 2019/3/30 10:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<%
    Object userInfo = (User)session.getAttribute("userInfo");
    if (userInfo != null) {
        out.write("欢迎您：" + ((User) userInfo).getUsernam());
    } else {
        out.append("<a href=\"login.jsp\">请登录</a>");
    }
%>

</body>
</html>
