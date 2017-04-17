package com.jim.novel.controller;

import com.jim.novel.constant.SystemConstant;
import com.jim.novel.model.Admin;
import com.jim.novel.model.Base;
import com.jim.novel.model.Response;
import com.jim.novel.model.User;
import com.jim.novel.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**基础控制器
 * Created by run on 17/3/13.
 */
public class BaseController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    protected FolderService folderService;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected TemplateService themeService;

    @Autowired
    protected ChapterService chapterService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserFolderService userFolderService;

    @Autowired
    protected UserCollectService userCollectService;


    //通过session获取当前用户信息(带密码)
    protected User me(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        User me = (User)request.getSession().getAttribute(SystemConstant.SESSION_USER);

        return userService.getUserInfo(me.getId());
    }

    //通过session获取当前管理员信息(带密码)
    protected Admin admin(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Admin me = (Admin)request.getSession().getAttribute(SystemConstant.SESSION_ADMIN);

        return userService.getAdminInfo(me.getId());
    }

    protected void SessionedUser(User user){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.getSession().setAttribute(SystemConstant.SESSION_USER,user);
    }

    protected void SessionedAdmin(Admin user){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.getSession().setAttribute(SystemConstant.SESSION_ADMIN,user);
    }

    protected String renderSuccess(Object data){
        return Response.successResponseJson(data);
    }

    protected String renderError(String msg){
        if(msg==null){
            msg = "操作失败！";
        }
        return Response.errorResponseJson(msg);
    }
}
