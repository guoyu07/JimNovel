package com.jim.novel.controller;

import com.jim.novel.entity.ArticleVo;
import com.jim.novel.model.Folder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**小说控制器
 * Created by run on 17/3/13.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @RequestMapping(value = "/{articleId}.htm", method = RequestMethod.GET)
    public String article(@PathVariable int articleId,
                          ModelMap modelMap) {
        try {
            ArticleVo article = articleService.getArticleWithAutorName(articleId);
            Folder folder = folderService.getFolderById(article.getFolderId());
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
