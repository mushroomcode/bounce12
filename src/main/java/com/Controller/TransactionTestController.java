package com.Controller;

import com.Entity.Hunter;
import com.Entity.Impl.HunterImpl;
import com.Mapper.HunterMapper;
import com.Service.HunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Joyyue sheting on 2020/3/1
 */
@RestController
@PropertySource( name="jdbc-bainuo-dev.properties",value= {"classpath:/properties/hunter.properties"},
        ignoreResourceNotFound = false,encoding="UTF-8")
//@ConfigurationProperties(prefix = "hunter")
public class TransactionTestController {

    @Autowired
    private HunterService service;

    @RequestMapping("/hunterTrans")
    @ResponseBody
    public void hunterAddAndGet (@Value("${hunter.hunterId}") int hunterId,
                                 @Value("${hunter.hunterName1}") String hunterName) {
        HunterImpl hunter = new HunterImpl();
        hunter.setHunterID(hunterId);
        hunter.setHunterName(hunterName);

        HunterImpl hunter1 = new HunterImpl();
        hunter1.setHunterName("Kera");
        hunter1.setHunterID(4);

        System.out.println(hunter.getHunterName());
        System.out.println(hunter.getHunterID());
        System.out.println(hunter1.getHunterID());
        System.out.println(hunter1.getHunterName());

        this.service.addAndGet(hunter, hunter1);

    }

}
