package com.Controller;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 实现重定向，短链接服务
 * Create by Joyyue sheting on 2018/8/25
 */

@Controller
@RequestMapping("/hunter")
public class HunterLong2ShortController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public String getShortUrl(@PathVariable String id) {
        String url = redisTemplate.opsForValue().get(id);
        System.out.println(url);
        return url;
    }

    @PostMapping
    @ResponseBody
    public String create(@RequestBody String url) {
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if(urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            redisTemplate.opsForValue().set(id, url);
            return id;
        }

        throw new RuntimeException("URL Invalid: " + url);
    }

    @GetMapping(value = "/access/web")
    public String redirect() {
        return "redirect:/hunter/index/realweb?parameter=coming";
    }

    @GetMapping(value = "/access/redirect")
    public String redirect2(HttpServletResponse response) {
        try {
            response.sendRedirect("/hunter/index/realweb?parameter=coming");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return "";
        }
    }

    /**
     * 重定向后的地址
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/index/realweb")
    public String real(HttpServletRequest request) {
        return "redirect happened: " + JSON.toJSONString(request.getParameterMap());
    }


    /**
     * 转发后响应的方法，但是不会显示此URL
     * @return
     */
    @RequestMapping("/index/forward")
    @ResponseBody
    public String toForward() {
        return "this, is the forward, not redirect!!";
    }

    /**
     * 转发，和转发后的地址一样
     * @param request
     * @return
     */
    //    @ResponseBody
    @GetMapping(value = "/forward")
    public String requestForward(HttpServletRequest request) {
        return "forward:/hunter/index/forward";
    }

}


