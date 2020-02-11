package com.Mapper;

import com.Entity.Impl.HunterImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by Joyyue sheting on 2018/8/25
 */
@Repository
public interface HunterMapper {

    int Insert(HunterImpl hunter);

    HunterImpl userQuery(int hunterID);

    List<HunterImpl> queryAll();

}
