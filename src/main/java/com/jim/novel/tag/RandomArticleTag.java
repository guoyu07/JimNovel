package com.jim.novel.tag;

import com.jim.novel.entity.ArticleVo;
import com.jim.novel.entity.PageVo;
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
import java.util.Map;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

/**热门小说推荐
 * Created by run on 17/3/20.
 */
@Service
public class RandomArticleTag extends TagPlugin{
    @Autowired
    private ArticleService articleService;

    @Autowired
    private FolderService folderService;
    @Override
    public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody body ) throws TemplateException, IOException {
// 获取页面的参数
       // Integer folderId = Integer.parseInt(params.get("folderId").toString());
        Integer p = Integer.parseInt(params.get("p").toString());
        Integer rows = Integer.parseInt(params.get("rows").toString());
        Integer count = Integer.parseInt(params.get("count").toString());
        // 获取文件的分页
        try {
            PageVo<ArticleVo> pageVo = articleService.getPopularArticle(p, rows, count);
            env.setVariable("tag_random_article", BEANS_WRAPPER.wrap(pageVo));
        } catch (FolderNotFoundException e) {
            env.setVariable("tag_random_article", BEANS_WRAPPER.wrap(null));
        }

        body.render(env.getOut());
    }
}

