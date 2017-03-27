package com.jim.novel.controller;

import com.jim.novel.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**基础控制器
 * Created by run on 17/3/13.
 */
public class BaseController {
    @Autowired
    protected FolderService folderService;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected TemplateService themeService;

    @Autowired
    protected ChapterService chapterService;

    @Autowired
    protected UserService userService;

    protected final Logger logger = Logger.getLogger(this.getClass());
}
