package com.jim.novel.dao;

import com.jim.novel.model.Config;
import com.jim.novel.model.ConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {
    int countByExample(ConfigExample example);

    int deleteByExample(ConfigExample example);

    int deleteByPrimaryKey(String keymap);

    int insert(Config record);

    int insertSelective(Config record);

    List<Config> selectByExampleWithBLOBs(ConfigExample example);

    List<Config> selectByExample(ConfigExample example);

    Config selectByPrimaryKey(String keymap);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKeyWithBLOBs(Config record);

    int updateByPrimaryKey(Config record);
}