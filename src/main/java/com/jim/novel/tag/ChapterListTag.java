package com.jim.novel.tag;

import com.jim.novel.entity.ArticleVo;
import com.jim.novel.model.Chapter;
import com.jim.novel.plugins.TagPlugin;
import com.jim.novel.service.ArticleService;
import com.jim.novel.service.ChapterService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

/**小说章节控制器
 * Created by run on 17/3/25.
 */
@Service
public class ChapterListTag extends TagPlugin {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ArticleService articleService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        // 获取页面的参数
        Integer articleId = Integer.parseInt(params.get("articleId").toString());
        // 获取文件的分页

        List<Chapter> list = chapterService.getChapterList(articleId);
        ArticleVo articleVo = articleService.getArticleWithAutorName(articleId);
        env.setVariable("tag_chapter_list", BEANS_WRAPPER.wrap(list));
        body.render(env.getOut());
    }
}
