package com.jim.novel.tag;

import com.jim.novel.entity.ArticleVo;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.plugins.TagPlugin;
import com.jim.novel.service.ArticleService;
import com.jim.novel.service.FolderService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

/**
 * 当前用户的小说创建列表
 *
 * @author run
 * @create 2017-04-15
 **/
@Service
public class MyArticleListTag extends TagPlugin {
    @Autowired
    private ArticleService articleService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        // 获取页面的参数
        Integer userId = Integer.parseInt(params.get("userId").toString());
        List<ArticleVo> articlelist = new ArrayList<>();
        // 获取文件的分页
        try {
              articlelist = articleService.getArticleListByOwnerId(userId);
              env.setVariable("tag_article_list", BEANS_WRAPPER.wrap(articlelist));

        } catch (FolderNotFoundException e) {
            env.setVariable("tag_article_list", BEANS_WRAPPER.wrap(null));
        }
        body.render(env.getOut());
    }

}
