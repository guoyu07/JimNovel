package com.jim.novel.controller.admin;

import com.jim.novel.constant.enums.ArticleStatus;
import com.jim.novel.constant.enums.FolderDisplay;
import com.jim.novel.controller.BaseController;
import com.jim.novel.entity.ArticleVo;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Article;
import com.jim.novel.model.Response;
import com.jim.novel.model.User;
import com.jim.novel.service.FolderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Response response = new Response(Response.OK_200,"ok",list);
        return response.toJsonString();
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

}
