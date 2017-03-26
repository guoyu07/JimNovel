package com.jim.novel.tag;

import com.jim.novel.plugins.TagPlugin;
import com.jim.novel.utils.HttpUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**章节路径标签
 * Created by run on 17/3/26.
 */
@Service
public class ChapterUrlTag extends TagPlugin{
    @Override
    public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        String chapterId = params.get("chapterId").toString();
        String basePath = HttpUtils.getBasePath(request);
        env.getOut().write(basePath + "/chapter/" + chapterId + ".htm");
    }
}
