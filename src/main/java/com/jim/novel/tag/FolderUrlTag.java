/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.jim.novel.tag;

import com.jim.novel.plugins.TagPlugin;
import com.jim.novel.service.FolderService;
import com.jim.novel.utils.HttpUtils;
import com.jim.novel.utils.PropertyUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * folder标签
 * 
 * @author lqq
 * 
 */
@Service
public class FolderUrlTag extends TagPlugin {
	@Autowired
	private FolderService folderService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String folderId = params.get("folderId").toString();
		String basePath = HttpUtils.getBasePath(request);
		if (Boolean.getBoolean(PropertyUtils.getValue("jim.static"))) {
			env.getOut().write(basePath + "/html/folder/" + folderId + ".html");
		} else {
			env.getOut().write(basePath + "/folder/" + folderId + ".htm");
		}
	}

}
