<%--
  Created by IntelliJ IDEA.
  User: LiuShitian
  Date: 2022/8/27
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h3>传统方式上传</h3>
        <form action="file/fileupload" method="post" enctype="multipart/form-data">
            选择文件：<input type="file" name="file"> <br/>
            <input type="submit" value="上传">
        </form>

        <hr/>

        <h3>springMVC方式上传</h3>
        <form action="file/fileuploadMVC" method="post" enctype="multipart/form-data">
            选择文件：<input type="file" name="upload"> <br/> <%--注意name属性--%>
            <input type="submit" value="上传">
        </form>


        <hr/>

        <h3>springMVC方式上传（跨服务器）</h3>
        <form action="file/fileuploadServer" method="post" enctype="multipart/form-data">
            选择文件：<input type="file" name="upload"> <br/> <%--注意name属性--%>
            <input type="submit" value="上传">
        </form>

    </body>
</html>
