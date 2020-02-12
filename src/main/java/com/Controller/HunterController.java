package com.Controller;

import com.Entity.Impl.HunterImpl;
import com.Service.HunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * Create by Joyyue sheting on 2018/8/25
 */
@Profile("dev")
@RestController
public class HunterController {

    @Autowired
    private HunterService hunterService;

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

}
