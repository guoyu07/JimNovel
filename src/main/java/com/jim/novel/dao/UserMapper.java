package com.jim.novel.dao;

import com.jim.novel.entity.UserVo;
import com.jim.novel.model.User;
import com.jim.novel.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    UserVo selectByPrimaryKey(Integer id);

    String selectNameById(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}