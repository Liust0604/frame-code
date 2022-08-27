package com.mori.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器,必须实现HandlerExceptionResolver接口
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    /**
     * controller抛出异常到前端控制器，前端控制器会调用异常处理器
     * 需要异常处理器中编写处理异常的业务逻辑
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //1、获取异常对象
        SysException e = null;
        if (ex instanceof SysException) {
            e = (SysException) ex;
        } else {
            e = new SysException("系统正在维护…");
        }
        //2、创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg", e.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
