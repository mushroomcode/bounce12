package com.Tokens;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by Joyyue sheting on 2020/6/25
 */
@Controller
public class HunterTransportController {

    @RequestMapping("limiter/{i}")
    @ResponseBody
    @ExtRateLimiter(value = 1000, timeOut = 500)
    public String rateLimiterControll (@PathVariable int i) {
        System.out.println("请求中...");
        return Integer.toString(i);
    }

}
