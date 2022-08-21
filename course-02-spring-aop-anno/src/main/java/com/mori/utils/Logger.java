package com.mori.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 【通知类】
 * 用于记录日志的工具类
 */
@Component("logger")
@Aspect //表示当前类是一个切面类
public class Logger {

    //指定切入点表达式
    @Pointcut("execution(* com.mori.service.impl.*.*(..))")
    private void pt1() {
    }

    /**
     * 前置通知
     */
    //@Before("pt1()")
    public void beforePrintLog() {
        System.out.println("前置通知：Logger类beforePrintLog方法开始记录日志了……");
    }

    /**
     * 后置通知
     */
    //@AfterReturning("pt1()")
    public void afterReturningPrintLog() {
        System.out.println("后置通知：Logger类afterPrintLog方法开始记录日志了……");
    }

    /**
     * 异常通知
     */
    //@AfterThrowing("pt1()")
    public void afterThrowingPrintLog() {
        System.out.println("异常通知：Logger类afterThrowingPrintLog方法开始记录日志了……");
    }

    /**
     * 最终通知
     */
    //@After("pt1()")
    public void afterPrintLog() {
        System.out.println("最终通知：Logger类afterPrintLog方法开始记录日志了……");
    }

    /**
     * 环绕通知：可以通过代码确定通知何时执行
     * <p>
     * 正确使用方式：使用spring提供的ProceedingJoinPoint.proceed(args)接口方法，明确切入点调用。
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        try {
            System.out.println("环绕通知：前置");
            Object[] args = pjp.getArgs(); //获取切入点方法所需参数
            Object rtValue = pjp.proceed(args);//明确切入点调用
            System.out.println("环绕通知：后置");
            return rtValue;
        } catch (Throwable t) {  //必须Throwable
            System.out.println("环绕通知：异常");
            t.printStackTrace();
            throw new RuntimeException(t);
        } finally {
            System.out.println("环绕通知：最终");
        }
    }
}
