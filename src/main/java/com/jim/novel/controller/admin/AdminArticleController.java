package com.jim.novel.controller.admin;

import com.jim.novel.constant.enums.ArticleStatus;
import com.jim.novel.controller.BaseController;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Article;
import com.jim.novel.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author run
 * @create 2017-04-17
 **/
@Controller
@RequestMapping("/ar")
public class AdminArticleController extends BaseController {

    @RequestMapping("/update")
    @ResponseBody
    public String update(Integer status, Integer articleId){
        articleService.updateStatus(status,articleId);
        return renderSuccess(null);
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
            @RequestParam("title") String title, @RequestParam("keyword")String keyword, @RequestParam(value = "fileBig", required = false) MultipartFile fileBig, @RequestParam(value = "fileSmall", required = false) MultipartFile fileSmall){
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

    @ResponseBody
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.GET)
    public String deteleArticle(Integer articleId){
        articleService.deleteArticle(articleId);
        return renderSuccess(null);
    }
}
