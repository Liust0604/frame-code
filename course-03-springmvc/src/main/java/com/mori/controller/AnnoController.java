package com.mori.controller;

import com.mori.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@RequestMapping(path = "/anno")
@SessionAttributes(value = {"msg"}) //把msg=消息存入到session域对象
public class AnnoController {

    /**
     * 参数赋值
     *
     * @param name
     * @return
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "username") String name) {
        System.err.println("name：" + name);
        return "success";
    }

    /**
     * 获取请求头内容
     *
     * @param header
     * @return
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header) {
        System.err.println("请求头：" + header);
        return "success";
    }

    /**
     * 获取请求体内容，get不能用
     *
     * @param body
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.err.println("请求体：" + body);
        return "success";
    }

    /**
     * 绑定url占位符，用于实现restful编程风格
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/testPathVariable/{uid}", method = RequestMethod.GET)
    //path占位符名要和name属性名一致，形参名随意，请求时不需要带任何占位符名
    public String testPathVariable(@PathVariable(name = "uid") String id) {
        System.err.println("占位符 id：" + id);
        return "success";
    }

    /**
     * 获取指定cookie的值
     *
     * @param cookieValue
     * @return
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String cookieValue) {
        System.err.println("cookie值：" + cookieValue);
        return "success";
    }

    /**
     * Model接口存数据到request域中
     * SessionAttributes 注解再把数据存入session中
     *
     * @param model
     * @return
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Model model) {
        //底层会存储到request域对象中
        model.addAttribute("msg", "消息");
        return "success";
    }

    /**
     * 测试 session 数据共享
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap) {
        //modelMap 是 Model 的实现类
        String msg = (String) modelMap.get("msg");
        System.out.println("session中取出数据：" + msg);
        return "success";
    }

    /**
     * 测试 删除session
     *
     * @param status
     * @return
     */
    @RequestMapping("/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status) {
        status.setComplete(); //session会话完成，会清除session
        return "success";
    }

    /**
     * ModelAttribute 注解在 方法上(有返回值)
     *
     * @return
     */
/*    @RequestMapping("/testModelAttributeFun")
    public String testModelAttributeFun(Account account) {
        System.err.println("testModelAttributeFun 执行了……");
        System.out.println(account);
        return "success";
    }*/

    /**
     * @ModelAttribute 注解的方法先执行 (有返回值)
     */
  /*   @ModelAttribute
   public Account showUser(String username, String password) {
        System.err.println("showUser 执行了……");
        //（模拟）通过用户名到数据库查找账户
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setBalance(22.5f); //通过数据库的数据补全实体对象
        return account;
    }*/

    /**
     * ModelAttribute 注解在 方法上(无返回值)
     *
     * @return
     */
    @RequestMapping("/testModelAttributeFun")
    public String testModelAttributeFun(@ModelAttribute("abc") Account account) { //通过map的key取出实体对象
        System.err.println("testModelAttributeFun 执行了……");
        System.out.println(account);
        return "success";
    }

    /**
     * @ModelAttribute 注解的方法先执行 (无返回值)
     */
    @ModelAttribute
    public void showUser(String username, String password, Map<String, Account> map) {
        System.err.println("showUser 执行了……");
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setBalance(22.5f); //数据库数据补全实体对象
        map.put("abc", account); //实体对象存入map集合
    }
}
