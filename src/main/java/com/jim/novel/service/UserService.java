package com.jim.novel.service;

import com.jim.novel.constant.SystemConstant;
import com.jim.novel.dao.AdminMapper;
import com.jim.novel.dao.UserMapper;
import com.jim.novel.entity.UserVo;
import com.jim.novel.exception.AuthException;
import com.jim.novel.exception.MyRuntimeException;
import com.jim.novel.model.Admin;
import com.jim.novel.model.AdminExample;
import com.jim.novel.model.User;
import com.jim.novel.model.UserExample;
import com.jim.novel.utils.AuthUtils;
import com.jim.novel.utils.PropertyUtils;
import com.jim.novel.utils.SSUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by run on 17/3/22.
 */
@Service
public class UserService {
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserMapper userDao;

    @Autowired
    private AdminMapper adminDao;

    public String getAuthorNmaeByUserId(int id){
        UserVo user =  userDao.selectByPrimaryKey(id);
        return user.getName();
    }

    public User getUserInfo(Integer userId){
        UserExample userExample = new UserExample();
        userExample.or().andIdEqualTo(userId);
        return userDao.selectByExample(userExample).get(0);
    }

    public Admin getAdminInfo(Integer userId){
        AdminExample adminExample = new AdminExample();
        adminExample.or().andIdEqualTo(userId);
        return adminDao.selectByExample(adminExample).get(0);
    }

    public boolean updateUserInfoById(User user){
        return  userDao.updateByPrimaryKey(user)==1?true:false;
    }

    public boolean updateAdminInfoById(Admin user){
        return  adminDao.updateByPrimaryKey(user)==1?true:false;
    }

    /**
     * 判断管理员是否存在
     * @param name
     * @param pwd
     * @return
     */
    public Admin adminIsExist(String name){
        AdminExample adminExample = new AdminExample();
        adminExample.or().andNameEqualTo(name);
        List<Admin> admin = adminDao.selectByExample(adminExample);
        if (admin.isEmpty()){
            return null;
        }
        return admin.get(0);
    }

    /**
     * 判断管理员是否存在
     * @param name
     * @param pwd
     * @return
     */
    public User userIsExist(String email){
        UserExample userExample = new UserExample();
        userExample.or().andEmailEqualTo(email);
        List<User> user = userDao.selectByExample(userExample);
        if (user.isEmpty()){
            return null;
        }
        return user.get(0);
    }

    public boolean checkPwd(String name,String pwd){
        AdminExample adminExample = new AdminExample();
        adminExample.or().andNameEqualTo(name).andPasswordEqualTo(pwd);
        List<Admin> admin = adminDao.selectByExample(adminExample);
        if (admin.isEmpty()){
            return false;
        }
        return true;
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
       String loginPassword = AuthUtils.getPassword(password);
   //     String loginPassword = password;
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
    @Transactional(rollbackFor = MyRuntimeException.class)
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
        newUser.setName("");
        newUser.setOpenId("0");
        newUser.setCreateTime(new Date());
        newUser.setModifyTime(new Date());
        try {
            userDao.insertSelective(newUser);
            newUser.setName(SSUtils.randomNickName(newUser.getId()));
            userDao.updateByPrimaryKey(newUser);
            return true;
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
       return false;


    }

}
