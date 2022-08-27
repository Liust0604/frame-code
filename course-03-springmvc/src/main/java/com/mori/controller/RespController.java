package com.mori.controller;

import com.mori.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/resp")
public class RespController {

    /**
     * 返回字符串
     *
     * @param model
     * @return
     */
    @RequestMapping("/testString")
    public String testString(Model model) {
        User user = new User();
        user.setUname("小明");
        user.setPassword("123");
        user.setAge(18);
        model.addAttribute("user", user);
        return "success";
    }

    /**
     * 返回void
     */
    @RequestMapping("/testVoid") //默认使用视图解析器。查找 /WEB-INF/pages/testVoid.jsp
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //自定义跳转，不经过视图解析器
        //方式一：请求转发。内部转发，不用加虚拟路径(路径填写，根路径是WEB-INF所在目录)
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
        //方式二：重定向。
        // 服务器再次发送请求，要加虚拟路径；外部请求不能直接访问WEB-INF，只能访问webapp以内的资源(可以通过webapp的页面间接访问里面的)
        //response.sendRedirect(request.getContextPath() + "/index.jsp");
        //方式三：直接响应。
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("你好!");
        return; //不执行后面内容，直接结束
    }

    /**
     * 返回ModelAndView对象
     *
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        //保存数据到mv对象中，同Model一样保存到request域中
        User user = new User();
        user.setUname("小明");
        user.setPassword("123");
        user.setAge(18);
        mv.addObject("user", user);
        //指定跳转页面，使用视图解析器
        mv.setViewName("success");
        return mv;
    }

    /**
     * 请求转发关键字
     *
     * @return
     */
    @RequestMapping("/testForward")
    public String testForward() {
        //请求转发关键字 forward:
        //内部转发，不需要虚拟路径
        return "forward:/WEB-INF/pages/success.jsp";
    }

    /**
     * 重定向关键字
     *
     * @return
     */
    @RequestMapping("/testRedirect")
    public String testRedirect() {
        //重定向关键字 redirect:
        //外部请求，底层会给在前面加上虚拟路径，外部不能直接访问WEB-INF
        return "redirect:/index.jsp";
    }

    /**
     * 响应Ajax异步请求
     *
     * @return
     */
    //@ResponseBody 将返回对象转成json，需要jackson依赖
    @RequestMapping("/testAjax")
    public @ResponseBody
    User testAjax(@RequestBody User user) {
        System.out.println(user); //获取整个请求体,底层封装成对象
        user.setUname("呼呼");
        user.setPassword("ccc");
        user.setAge(12);
        return user; //返还一个对象，底层转换成响应体
    }
}
