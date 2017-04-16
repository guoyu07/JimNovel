package com.jim.novel.controller.admin;

import com.jim.novel.constant.enums.ArticleStatus;
import com.jim.novel.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
