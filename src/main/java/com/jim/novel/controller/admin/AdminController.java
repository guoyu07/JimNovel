package com.jim.novel.controller.admin;

import com.jim.novel.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1.管理后台不采用freemarker渲染的方式
 * 2.将用户中心和网站管理后台合并处理：
 *   a.普通用户登录将展示基本菜单(小说管理/我的上传记录/评论管理)
 *   b.站点管理员登录将展示a.中的菜单之外，增加(分类管理/权限管理/用户管理)
 *
 * @author run
 * @create 2017-04-09
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TemplateService templateService;

    @RequestMapping("/index.htm")
    public String index(){
        return templateService.getAdminTemplate("index");
    }

    @RequestMapping("/article.htm")
    public String article(){
        return templateService.getAdminTemplate("article");
    }

    @RequestMapping("/login.htm")
    public String login(){
        return templateService.getAdminTemplate("login");
    }

    @RequestMapping("/add.htm")
    public String add(){
        return templateService.getAdminTemplate("add");
    }

    @RequestMapping("/comment.htm")
    public String comment(){
        return templateService.getAdminTemplate("comment");
    }

    @RequestMapping("/profile.htm")
    public String profile(){
        return templateService.getAdminTemplate("profile");
    }

    @RequestMapping("/history.htm")
    public String history(){
        return templateService.getAdminTemplate("history");
    }

    @RequestMapping("/form_avatar.htm")
    public String avater(){
        return templateService.getAdminTemplate("form_avatar");
    }

    @RequestMapping("/change_info.htm")
    public String change_info(){
        return templateService.getAdminTemplate("change_info");
    }

    @RequestMapping("/change_pwd.htm")
    public String change_pwd(){
        return templateService.getAdminTemplate("change_pwd");
    }

    //***static/template/admin/index.html这个页面中的iframe需要通过这个控制器路由，注意后缀是.htm，freemarker会自动找到static/template/admin下的xxx.html进行渲染
}
