<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>首页</title>
    </head>
    <body>
        <a href="account/findAll">测试</a><br/>

        <form action="account/saveAccount" method="post">
            姓名：<input type="text" value="ccc" name="name"><br/>
            金额：<input type="text" value="100" name="money">
            <input type="submit" value="保存">
        </form>
    </body>
</html>
