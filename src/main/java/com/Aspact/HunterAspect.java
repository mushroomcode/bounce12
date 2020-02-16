package com.Aspact;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Create by Joyyue sheting on 2020/2/16
 */
@Aspect
@Component
@EnableAspectJAutoProxy
@Order(2)
public class HunterAspect {

    /**
     * 前置通知
     */
    @Before("execution(* com.Controller.*.*(..))")
    public void doBefore() {
        System.out.println("=============before");
    }

    /**
     * 后置通知
     */
    @After("CutPoint()")
    public void doAfter() {
        System.out.println("=============after");
    }

    @Pointcut("execution(* com.Controller.*.*(..))")
    public void CutPoint() { }

}
