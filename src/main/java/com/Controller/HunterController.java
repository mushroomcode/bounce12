package com.Controller;

import com.Bean.HunterCarYellow1;
import com.Entity.Impl.HunterImpl;
import com.Service.HunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Create by Joyyue sheting on 2018/8/25
 */

//@Profile("dev")
@RestController
@PropertySource( name="jdbc-bainuo-dev.properties",value= {"classpath:/properties/hunter.properties"},
        ignoreResourceNotFound = false,encoding="UTF-8")
@ConfigurationProperties(prefix = "hunter")
public class HunterController  {

    @Autowired
    private HunterService hunterService;

//    @ConfigurationProperties()
    private HunterCarYellow1 hunterCarYellow1;

    @Value("${driver-username}")
    private String username1;

    @Value("${driver-class-name}")
    private String dnc;

    private String counts;

    private String hunterName;

//    @Value("${beans}")
//    private String beans;

//    @RequestMapping(value = "/hunter", method = RequestMethod.POST)
//    public String hunterAdd(HunterImpl hunter)  {
//        return hunterService.Insert(hunter) != 0 ? "success" : "fail";
//    }

    @RequestMapping("/hunterQuery/{hunterID}")
    @ResponseBody
    public String hunterQuery (@PathVariable int hunterID) {
//        System.out.println(hunterId);
        return hunterService.userQuery(hunterID);
    }

    @ResponseBody
    @RequestMapping("/allHunters")
    public List<HunterImpl> queryAll() {
        return hunterService.queryAll();
    }


    @ResponseBody
    @RequestMapping("/showAttr")
    public String showProperties() {
        System.out.println(hunterName);
        System.out.println(counts);
        return "driver-class-name: " + dnc + " driver-username: " + username1;
    }

}
