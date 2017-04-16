package com.jim.novel.controller.admin;

import com.jim.novel.controller.BaseController;
import com.jim.novel.model.Chapter;
import com.jim.novel.utils.SSUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 添加新的章节
     * @param articleId
     * @param title
     * @param content
     * @param createTime
     * @param sort
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addChapter", method = RequestMethod.POST)
    public String addChapter(
            @RequestParam(value = "articleId", required = true) Integer articleId,
            @RequestParam("title") String title, @RequestParam("content")String content, @RequestParam(value = "createTime", required = false)String createTime, @RequestParam(value = "sort", required = false)Integer sort){

        Chapter chapter = chapterService.addChapter(SSUtils.toText(title.trim()), articleId, SSUtils.toText(content), createTime, sort);

        return renderSuccess(chapter);

    }

    /**
     * 更新指定章节信息
     * @param chapterId
     * @param articleId
     * @param title
     * @param content
     * @param createTime
     * @param sort
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateChapter", method = RequestMethod.POST)
    public String updateChapter( @RequestParam(value = "chapterId")Integer chapterId,@RequestParam(value = "articleId", required = false) Integer articleId,
                                 @RequestParam("title") String title, @RequestParam("content")String content, @RequestParam(value = "createTime", required = false)String createTime, @RequestParam(value = "sort", required = false)Integer sort){
        int success = chapterService.updateChapter(chapterId, title, articleId, SSUtils.toText(title.trim()),sort);
        return renderSuccess(success);

    }

    /**
     * 删除指定章节
     * @param chapterId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteChapter", method = RequestMethod.GET)
    public String updateChapter(Integer chapterId){
        int success = chapterService.delete(chapterId);
        return renderSuccess(null);

    }


}
