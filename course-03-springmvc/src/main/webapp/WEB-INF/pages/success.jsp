<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>成功页面</title>
    </head>
    <body>
        <h3>成功！</h3>

        <hr/>
        request域中取出数据： ${requestScope.msg}
        <br/>
        session域中取出数据： ${sessionScope.get("msg")}

        <hr/>
        ${user.uname}<br/>
        ${user.age}


        <% System.out.println("success.jsp!"); %>
    </body>
</html>
