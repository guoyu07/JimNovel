package com.jim.novel.service;

import com.jim.novel.dao.UserCollectMapper;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.entity.UserCollectVo;
import com.jim.novel.model.User;
import com.jim.novel.model.UserCollect;
import org.apache.ibatis.annotations.Arg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-03-29 23:48
 **/
@Service
public class UserCollectService {

    @Autowired
    UserCollectMapper userCollectDao;

    @Autowired
    ArticleService articleService;

    public void add(User user, Integer articleId){
        Integer userId = user.getId();
        UserCollect uc = new UserCollect();
        uc.setArticleId(articleId);
        uc.setUserId(userId);
        uc.setCreateTime(new Date());
        uc.setModifyTime(new Date());
        userCollectDao.insertSelective(uc);
    }

    public boolean isCollected(User user,Integer articleId){
        Integer userId = user.getId();
        if(userCollectDao.isExist(userId,articleId)>0){
            return true;
        }
        return false;
    }

    public List<UserCollectVo> getAllCollectOfUser(Integer userId){
        List<UserCollectVo> articleIds = userCollectDao.getAllCollectsByUserId(userId);
        return articleIds;
    }

    public boolean remove(Integer id){
        return userCollectDao.updateCollectById(id);
    }
}
