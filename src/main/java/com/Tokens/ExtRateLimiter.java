package com.Tokens;

import java.lang.annotation.*;

/**
 * Create by Joyyue sheting on 2020/6/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ExtRateLimiter {

    double value();

    // 规定的毫秒内如果没有得到令牌那么就走降级服务
    long timeOut();

}
