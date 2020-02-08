package com.Service;

import com.Entity.Impl.HunterImpl;
import com.Mapper.HunterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by Joyyue sheting on 2020/2/6
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

}
