package com.jim.novel.service;

import com.jim.novel.constant.SystemConstant;
import com.jim.novel.dao.UserMapper;
import com.jim.novel.entity.UserVo;
import com.jim.novel.exception.AuthException;
import com.jim.novel.model.User;
import com.jim.novel.utils.AuthUtils;
import com.jim.novel.utils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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

    /**
     * 登陆
     *
     * @param email
     * @param password
     * @param request
     * @throws IOException
     */
    public void Authlogin(String email, String password,
                           HttpServletRequest request) throws AuthException,
            IOException {
        UserVo user = userDao.getUserByEmail(email);
        if (user == null) {
            throw new AuthException("邮箱或密码错误");
        }
//        String loginPassword = AuthUtils.getPassword(password);
        String loginPassword = password;
        if (loginPassword.equals(user.getPassword())) {
            HttpSession session = request.getSession();
            user.setPassword("");
            session.setAttribute(SystemConstant.SESSION_USER,
                    user);
        } else {
            throw new AuthException("邮箱或密码错误");
        }
    }

    /**
     * 注册账户
     * @param email
     * @param password
     */
    public boolean register(String email,String password){
        UserVo user = userDao.getUserByEmail(email);
        if (user != null) {
           return false;
        }
        String loginPassword = AuthUtils.getPassword(password);
        User newUser = new User();
        newUser.setPassword(loginPassword);
        newUser.setEmail(email);
        newUser.setType(0);
        newUser.setName("未命名");
        newUser.setOpenId("0");
        newUser.setCreateTime(new Date());
        newUser.setModifyTime(new Date());
        if(userDao.insertSelective(newUser)==1){
            return true;
        }
        return false;
    }

}
