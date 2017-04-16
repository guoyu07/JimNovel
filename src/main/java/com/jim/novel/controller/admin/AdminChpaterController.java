package com.jim.novel.controller.admin;

import com.jim.novel.controller.BaseController;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 小说章节创建控制器
 *
 * @author run
 * @create 2017-04-16
 **/
@Controller
@RequestMapping("/ch")
public class AdminChpaterController extends BaseController
{

    /**
     * 创建新章节
     * @param folderId
     * @param title
     * @param createTime
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addChapter", method = RequestMethod.POST)
    public String addChapter(
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
     * 预添加小说
     * @param folderId
     * @param title
     * @param createTime
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateChapter", method = RequestMethod.POST)
    public String updateChapter(
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
}
