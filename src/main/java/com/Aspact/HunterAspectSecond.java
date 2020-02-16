package com.Aspact;

import com.Bean.Introducer;
import com.Bean.IntroducerBean;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Create by Joyyue sheting on 2020/2/16
 */
@Aspect
@EnableAspectJAutoProxy
@Component
@Order(2)
public class HunterAspectSecond {

    @Before("execution(* com.Controller.*.*(..))")
    public void doBefore() {
        System.out.println("=============med!!!");
    }

    @DeclareParents(value = "com.Controller.*", defaultImpl = IntroducerBean.class)
    public Introducer internType;

}
