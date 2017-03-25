package com.jim.novel.controller;

import com.jim.novel.entity.ArticleVo;
import com.jim.novel.model.Folder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 小说章节控制器
 *
 * @author
 * @create 2017-03-25 22:40
 **/
@Controller
@RequestMapping("/chapter")
public class ChapterController extends BaseController{
    @RequestMapping(value = "/{chapter}.htm", method = RequestMethod.GET)
    public String chapter(@PathVariable int articleId,
                          @RequestParam(value = "p", defaultValue = "1") int p,
                          ModelMap modelMap) {
        try {
            ArticleVo article = articleService.getArticleWithAutorName(articleId);
            Folder folder = folderService.getFolderById(article.getFolderId());
            modelMap.addAttribute("p", p);
            modelMap.addAttribute("folder", folder);
            modelMap.addAttribute("article", article);
            modelMap.addAttribute("g_folderId", folderService.firstFolderId(folder.getFolderId()));
            return themeService.getArticleTemplate(article.getFolderId(),
                    articleId);
        } catch (Exception e) {
            modelMap.addAttribute("g_folderId", 0);
            return themeService.get404();
        }
    }
}
