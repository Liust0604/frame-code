<%--
  Created by IntelliJ IDEA.
  User: LiuShitian
  Date: 2022/8/27
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h3>常用注解</h3>
        <a href="anno/testRequestParam?username=小明&password=123">@RequestParam</a>
        <br>
        <hr/>
        <a href="anno/testRequestHeader?username=小明&password=123">@RequestHeader</a>
        <br>
        <form action="anno/testRequestBody" method="post">
            姓名: <input type="text" name="username" value="aaa"><br/>
            密码: <input type="text" name="password" value="123"><br/>
            金额: <input type="text" name="balance" value="22.5"><br/>
            <input type="submit" value="@RequestBody">
        </form>
        <hr/>
        <a href="anno/testPathVariable/10">@PathVariable</a><br/>
        <a href="anno/testCookieValue">@CookieValue</a><br/>
        <a href="anno/testSessionAttributes">@SessionAttributes</a><br/>
        <a href="anno/getSessionAttributes">getSessionAttributes</a><br/>
        <a href="anno/delSessionAttributes">delSessionAttributes</a><br/>
        <hr/>
        <form action="anno/testModelAttributeFun" method="post">
            姓名: <input type="text" name="username" value="aaa"><br/>
            密码: <input type="text" name="password" value="123"><br/>
            <input type="submit" value="@ModelAttributeFun">
        </form>
    </body>
</html>
