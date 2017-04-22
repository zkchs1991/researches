package com.zk.sbt.mybatis.mapper;

import com.zk.sbt.entity.Developer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zk_chs on 4/22/17.
 */
@Mapper
public interface DeveloperMapper {

    @Select("SELECT * FROM developer WHERE id = #{developerId}")
    Developer findById(Long developerId);

}
