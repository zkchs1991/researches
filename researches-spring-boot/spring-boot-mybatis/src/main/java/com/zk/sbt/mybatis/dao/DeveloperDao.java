package com.zk.sbt.mybatis.dao;

import com.zk.sbt.entity.Developer;
import com.zk.sbt.mybatis.mapper.DeveloperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zk_chs on 4/22/17.
 */
@Repository
public class DeveloperDao {

    @Autowired
    private DeveloperMapper developerMapper;

    public Developer findById (Long developerId){
        return developerMapper.findById(developerId);
    }

}
