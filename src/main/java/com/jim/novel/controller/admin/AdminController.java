package com.jim.novel.controller.admin;

import com.jim.novel.controller.BaseController;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.entity.UserFolderVo;
import com.jim.novel.exception.ArticleNotFoundException;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Article;
import com.jim.novel.model.Base;
import com.jim.novel.model.Chapter;
import com.jim.novel.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
public class AdminController extends BaseController{

    @Autowired
    private TemplateService templateService;

    @RequestMapping("/main.htm")
    public String mains(){
        return templateService.getAdminTemplate("main");
    }

    @RequestMapping("/index.htm")
    public String index(){
        return templateService.getAdminTemplate("index");
    }

    @RequestMapping("/article.htm")
    public String article(){
        return templateService.getAdminTemplate("article");
    }


    @RequestMapping("/admin_article.htm")
    public String adminArticle(){
        return templateService.getAdminTemplate("admin_article");
    }

    @RequestMapping("/admin_folder.htm")
    public String adminFolder( ModelMap modelMap){
        List<FolderVo> folderAll = folderService.getAllFolderList();
        modelMap.put("folderAll", folderAll);
        return templateService.getAdminTemplate("admin_folder");
    }

    @RequestMapping("/blog.htm")
    public String blog(Integer chapterId,ModelMap modelMap){
        ArticleVo articleVo = null;
        Chapter chapter = chapterService.getChapterDetail(chapterId);
        try {
            articleVo = articleService.getArticleById(chapter.getArticleId());
        } catch (ArticleNotFoundException e) {
            e.printStackTrace();
        } catch (FolderNotFoundException e) {
            e.printStackTrace();
        }
        modelMap.put("chapter",chapter);
        modelMap.put("article",articleVo);
        return templateService.getAdminTemplate("blog");
    }


    @RequestMapping("/chapter.htm")
    public String chapter(ModelMap modelMap,String articleId,String title){
        modelMap.put("articleId",articleId);
        String articleTitle = "《"+title+"》";
        modelMap.put("articleTitle",articleTitle);
        return templateService.getAdminTemplate("chapter");
    }


    @RequestMapping("/chapter_detail.htm")
    public String chapterDetail(ModelMap modelMap,String articleId,String title){
        modelMap.put("articleId",articleId);
        String articleTitle = "《"+title+"》";
        modelMap.put("articleTitle",articleTitle);
        return templateService.getAdminTemplate("chapter_detail");
    }

    @RequestMapping("/editChapter.htm")
    public String editChapter(ModelMap modelMap,String chapterId){


        return templateService.getAdminTemplate("edit");
    }


    @RequestMapping("/login.htm")
    public String login(){
        return templateService.getAdminTemplate("login");
    }

    @RequestMapping("/edit.htm")
    public String exit(Integer chapterId,ModelMap modelMap){
        ArticleVo articleVo = null;
        Chapter chapter = chapterService.getChapterDetail(chapterId);
        try {
            articleVo = articleService.getArticleById(chapter.getArticleId());
        } catch (ArticleNotFoundException e) {
            e.printStackTrace();
        } catch (FolderNotFoundException e) {
            e.printStackTrace();
        }
        modelMap.put("chapter",chapter);
        modelMap.put("article",articleVo);
        return templateService.getAdminTemplate("edit");
    }



    @RequestMapping("/add.htm")
    public String add(String articleId,ModelMap modelMap)
    {
        if(articleId!=null){
            ArticleVo articleVo = null;
            try {
                articleVo = articleService.getArticleById(Integer.parseInt(articleId));
                logger.info(articleVo);
            } catch (ArticleNotFoundException e) {
                e.printStackTrace();
            } catch (FolderNotFoundException e) {
                e.printStackTrace();
            }
            modelMap.put("article",articleVo);
        }
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
