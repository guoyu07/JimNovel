package com.jim.novel.dao;

import com.jim.novel.model.UserCollect;
import org.apache.ibatis.annotations.Param;

/**
 * 用户收藏
 *
 * @author
 * @create 2017-03-29 23:50
 **/
public interface UserCollectMapper {

    int insertSelective(UserCollect uc);

    Integer isExist(@Param("userId")Integer userId,@Param("articleId")Integer articleId);
}
