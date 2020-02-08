package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Create by Joyyue sheting on 2020/2/6
 */

@MapperScan("com.Mapper")
@SpringBootApplication
@EnableCaching
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
