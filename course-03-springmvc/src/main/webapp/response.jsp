<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>

        <script src="js/jquery.min.js"></script>

        <script>
            $(function () {
                $("#btn").click(function () {
                    //发送ajax异步请求
                    $.ajax({
                        type: "GET",
                        url: "resp/testAjax",
                        contentType: "application/json;charset=utf-8", //发送数据类型为json
                        data: {
                            'uname': '小红',
                            'password': 'abc',
                            'age': '23',
                        },
                        dataType: "json", //预期返回数据类型为json
                        //回调函数
                        success: function (data) {
                            //返回的是json对象，可以直接获取属性
                            alert(data);
                            alert(data.uname + "," + data.password + data.age);
                        }
                    });
                });
            });
        </script>
    </head>
    <body>

        <h4>响应的三种方式</h4>

        <a href="resp/testString">testString</a><br/>

        <a href="resp/testVoid">testVoid</a><br/>

        <a href="resp/testModelAndView">testModelAndView</a><br/>

        <h4>请求转发和重定向 关键字</h4>

        <a href="resp/testForward">testForward</a><br/>

        <a href="resp/testRedirect">testRedirect</a><br/>

        <hr/>
        <h4>响应json数据</h4>

        <button id="btn">发送ajax请求</button>

    </body>
</html>
