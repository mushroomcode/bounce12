package com.Service;

import com.Entity.Impl.HunterImpl;
import com.Mapper.HunterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by Joyyue sheting on 2018/8/25
 */
@Service
public class HunterService {

    @Autowired
    private HunterMapper hunterMapper;

    @Cacheable(cacheNames = "Hunter", key = "#hunterID")
    public String userQuery(int hunterID) {
        return hunterMapper.userQuery(hunterID).toString();
    }

    public int Insert(HunterImpl hunter) {
        return hunterMapper.Insert(hunter);
    }

    public List<HunterImpl> queryAll() {
        return hunterMapper.queryAll();
    }


    @Transactional
    public void updateTrans() {
        return ;
    }

    @Transactional
    public void queryTrans() {
        List<HunterImpl> list = hunterMapper.queryAll();
        for(int i = 0;i < list.size();i += 1) {
            System.out.println(list.get(i).getHunterID() + ": " +list.get(i).getHunterName());
        }
    }

}
