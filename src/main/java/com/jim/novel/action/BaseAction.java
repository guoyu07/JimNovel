package com.jim.novel.action;

import com.jim.novel.service.ArticleService;
import com.jim.novel.service.FolderService;
import com.jim.novel.service.TemplateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**基础控制器
 * Created by run on 17/3/13.
 */
public class BaseAction {
    @Autowired
    protected FolderService folderService;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected TemplateService themeService;


    protected final Logger logger = Logger.getLogger(this.getClass());
}
