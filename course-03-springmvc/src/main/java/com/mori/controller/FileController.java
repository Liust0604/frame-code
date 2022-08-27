package com.mori.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/file")
public class FileController {

    /**
     * 传统方式文件上传
     */
    @RequestMapping("/fileupload")
    public String fileupload(HttpServletRequest request) throws Exception {
        ServletContext ctx = request.getSession().getServletContext(); //最大域对象
        //1、指定上传的位置
        String path = ctx.getRealPath("/uploads/"); //最终目录为 : target/course-03-springmvc/uploads/
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        //2、fileupload组件解析request对象，获取文件项目
        DiskFileItemFactory factory = new DiskFileItemFactory(); //磁盘文件项目
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request，获取所有文件项
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem item : fileItems) {
            if (item.isFormField()) {
                //普通表单项
            } else {
                //3、上传文件项
                //获取上传文件名
                String filename = item.getName() + "_" + UUID.randomUUID().toString().replace("-", "");
                //上传文件
                item.write(new File(path, filename)); //文件项写为指定路径的指定文件
                //删除临时文件
                item.delete();
            }
        }
        return "fileupload";
    }

    /**
     * springMVC方式文件上传
     */
    @RequestMapping("/fileuploadMVC")
    public String fileuploadMVC(HttpServletRequest request, MultipartFile upload) throws Exception {
        ServletContext ctx = request.getSession().getServletContext(); //最大域对象
        //1、指定上传的位置
        String path = ctx.getRealPath("/uploads/"); //最终目录为 : target/course-03-springmvc/uploads/
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        //2、上传文件项
        //获取上传文件名
        String filename = upload.getOriginalFilename() + "_" + UUID.randomUUID().toString().replace("-", "");
        //文件上传
        upload.transferTo(new File(path, filename));
        return "fileupload";
    }

    /**
     * springMVC方式文件上传（跨服务器）
     */
    @RequestMapping("/fileuploadServer")
    public String fileuploadServer(MultipartFile upload) throws Exception {
        //1、定义上传文件服务器的路径
        String path = "http://localhost:8081/fileupload/uploads/";

        //2、jersey组件创建客户端对象
        Client client = Client.create();

        //3、当前客户端和文件服务器进行连接
        String filename = upload.getOriginalFilename() + "_" + UUID.randomUUID().toString().replace("-", "");
        WebResource resource = client.resource(path + filename);
        //虚拟路径：http://localhost:8081/fileupload/ -> target/项目名/
        //文件目录：http://localhost:8081/fileupload/uploads/ -> target/项目名/uploads/（文件服务器提前创建好）
        //文件：http://localhost:8081/fileupload/uploads/a.jpg -> target/项目名/uploads/a.jpg

        //4、上传文件
        resource.put(upload.getBytes()); //把文件发送到文件服务器

        return "fileupload";
    }
}
