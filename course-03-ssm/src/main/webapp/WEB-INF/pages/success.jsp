<%--
  Created by IntelliJ IDEA.
  User: LiuShitian
  Date: 2022/8/28
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>成功页面</title>
    </head>
    <body>
        <h1>跳转成功！</h1>

        <c:forEach items="${accounts}" var="account">
            ${account.name}
        </c:forEach>
    </body>
</html>
