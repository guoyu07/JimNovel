package com.jim.novel.dao;

import com.jim.novel.model.Config;
import com.jim.novel.model.ConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {

    int deleteByPrimaryKey(String keymap);

    int insert(Config record);

    Config selectByPrimaryKey(String keymap);

    int updateByPrimaryKey(Config record);
}