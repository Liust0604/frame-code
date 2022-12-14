<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

        <h3>参数赋值</h3>
        <%--通过反射传入方法参数--%>
        <a href="param?username=aaa&password=123">参数</a>
        <br>
        <%--传Bean类型的参数--%>
        <%--name要和bean类中的属性一样，通过set方法对Bean对象封装--%>
        <form action="paramBean" method="post">
            姓名: <input type="text" name="username" value="aaa"><br/>
            密码: <input type="text" name="password" value="123"><br/>
            金额: <input type="text" name="balance" value="22.5"><br/>
            <%--生日: <input type="text" name="date" value="2022/08/25 20:12:00"><br/>--%>
            生日: <input type="text" name="date" value="2022-08-25 20:12:00"><br/>
            <%--引用类型成员，需要成员类.属性--%>
            用户姓名: <input type="text" name="user.uname" value="小明"><br/>
            用户年龄: <input type="text" name="user.age" value="18"><br/>
            <%--集合类型成员 List 成员类[index].属性--%>
            用户姓名: <input type="text" name="list[0].uname" value="单列集合List"><br/>
            用户年龄: <input type="text" name="list[0].age" value="22"><br/>
            <%--集合类型成员 Map 成员类[key].属性--%>
            用户姓名: <input type="text" name="map['userA'].uname" value="双列集合Map"><br/>
            用户年龄: <input type="text" name="map['userA'].age" value="22"><br/>
            <input type="submit" value="提交">
        </form>
        <hr/>
        <%--测试原生ServletAPI--%>
        <a href="servletAPI?username=aaa&password=123">ServletAPI</a>
        <hr/>

    </body>
</html>
