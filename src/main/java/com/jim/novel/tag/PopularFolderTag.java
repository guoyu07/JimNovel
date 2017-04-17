/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.jim.novel.tag;

import com.jim.novel.constant.enums.FolderDisplay;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.model.Folder;
import com.jim.novel.plugins.TagPlugin;
import com.jim.novel.service.FolderService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
*
* @author run
*
**/
@Service
public class PopularFolderTag extends TagPlugin {
	@Autowired
	private FolderService folderService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {


		List<FolderVo> list = new ArrayList<FolderVo>();

		list = folderService.getPopularFolderList();

		env.setVariable("tag_popular_folder_list", DEFAULT_WRAPPER.wrap(list));
		body.render(env.getOut());
	}

}
