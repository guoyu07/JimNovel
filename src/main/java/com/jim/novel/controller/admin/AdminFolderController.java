package com.jim.novel.controller.admin;

import com.jim.novel.constant.ArticleConstant;
import com.jim.novel.constant.enums.ArticleStatus;
import com.jim.novel.constant.enums.FolderDisplay;
import com.jim.novel.controller.BaseController;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Article;
import com.jim.novel.model.Chapter;
import com.jim.novel.model.Response;
import com.jim.novel.model.User;
import com.jim.novel.service.FolderService;
import com.jim.novel.utils.SSUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 小说分类信息控制器
 *
 * @author run
 * @create 2017-04-12
 **/
@Controller
@RequestMapping("/fo")
public class AdminFolderController extends BaseController {

    @Autowired
    private FolderService folderService;


    /**
     * 小说分类
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String getFolderList(){
        User me = me();
        List<FolderVo> list = folderService.getAllFolderList(me.getId());

        //返回的json数据统一封装到Response这个对象中 具体包括code,msg,data
        //ajax异步请求的时候，需要从data取出对应的对象
       return renderSuccess(list);
    }


    /**
     * 指定用户在当前目录下的小说记录
     * @return
     */
    @RequestMapping("/articleList")
    @ResponseBody
    public String getPersonlArticleList(Integer folderId){
        try {
            User me = me();
            List<ArticleVo> list = articleService.getArticleListByOwnerIdAndFolderId(me.getId(),folderId, ArticleStatus.ORIGIN);
            return renderSuccess(list);
        } catch (FolderNotFoundException e) {
            e.printStackTrace();
            return renderError(null);
        }

    }

    /**
     * 指定用户在当前目录下的小说记录
     * @return
     */
    @RequestMapping("/chapterList")
    @ResponseBody
    public String getPersonlChapterList(Integer articleId){
        List<Chapter> list = chapterService.getChapterList(articleId);
        return renderSuccess(list);
    }


    /**
     * 预添加小说
     * @param folderId
     * @param title
     * @param createTime
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public String addArticle(
            @RequestParam("folderId") int folderId,
            @RequestParam("title") String title,@RequestParam("keyword")String keyword,@RequestParam(value = "fileBig", required = false) MultipartFile fileBig,@RequestParam(value = "fileSmall", required = false) MultipartFile fileSmall){
        Article article = null;
        try {
             article = articleService.addArticle(folderId, me().getId(),title,keyword,fileBig,fileSmall);
        } catch (FolderNotFoundException e) {
            e.printStackTrace();
            return renderError("添加失败");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return renderSuccess(article);

    }




}
