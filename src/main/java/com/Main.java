package com;

import com.Bean.MyBean;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Create by Joyyue sheting on 2020/4/25
 */
//@DependsOn("ApplicationContext")
@ComponentScan("com.Bean")
public class Main {

//    @Autowired
    private ApplicationContext context;

//    private Main() {
//        context = new AnnotationConfigApplicationContext();
//    }

    public void TestMyBean() {
        context = new FileSystemXmlApplicationContext("F:\\bounce12\\src\\main\\resources\\xml\\application.xml");
        MyBean myBean1 = (MyBean) context.getBean("myBean");
        System.out.println(myBean1.getMessage());
        MyBean myBean2 = (MyBean) context.getBean("&mybean");
        System.out.println(myBean2.getMessage());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.TestMyBean();
    }

}
