package com.mori.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器 【必须实现 HandlerInterceptor 接口】
 */
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 预处理
     *
     * @param request
     * @param response
     * @param handler
     * @return true放行，有下一个拦截器则执行，没有则执行controller方法。
     * false 不放行，可以不执行controller，直接使用request、response跳转页面。
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器===预处理");
        return true;
    }

    /**
     * 后处理 controller方法执行后，success.jsp执行前
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器===后处理");
        //若在这里请求转发其他页面，则不会再打开 success.jsp
    }

    /**
     * success.jsp执行后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器===最后");
    }
}
