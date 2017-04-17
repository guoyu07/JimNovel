/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

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
import java.util.List;
import java.util.Map;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

/**
 * @author Herbert
 * 
 */
@Service
public class ArticleListTag extends TagPlugin {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private FolderService folderService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		Integer folderId = Integer.parseInt(params.get("folderId").toString());
		Integer rows = Integer.parseInt(params.get("rows").toString());
		// 获取文件的分页
		try {

			FolderVo folder = folderService.getFolderById(folderId);
			List<ArticleVo> articlelist = articleService.getArticleListOfDisplayByPath(
					folder.getPath(), 0, rows);
			env.setVariable("tag_article_list", BEANS_WRAPPER.wrap(articlelist));
		} catch (FolderNotFoundException e) {
			env.setVariable("tag_article_list", BEANS_WRAPPER.wrap(null));
		}

		body.render(env.getOut());
	}

}
