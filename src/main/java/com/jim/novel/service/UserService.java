package com.jim.novel.service;

import com.jim.novel.dao.UserMapper;
import com.jim.novel.entity.UserVo;
import com.jim.novel.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by run on 17/3/22.
 */
@Service
public class UserService {
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserMapper userDao;

    public String getAuthorNmaeByUserId(int id){
        UserVo user =  userDao.selectByPrimaryKey(id);
        return user.getName();
    }

}
