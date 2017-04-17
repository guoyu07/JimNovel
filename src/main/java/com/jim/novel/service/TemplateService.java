/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.jim.novel.service;


import com.jim.novel.constant.ConfigConstant;
import com.jim.novel.constant.SystemConstant;
import com.jim.novel.entity.FolderVo;
import com.jim.novel.exception.FolderNotFoundException;
import com.jim.novel.exception.TemplateNotFoundException;
import com.jim.novel.model.Folder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 模板工具类
 * 
 * @author Herbert
 * 
 */
@Service
public class TemplateService {

	private static String FOLDER_TEMPLATE_PREFIX = "folder";
	private static String FILE_TEMPLATE_PREFIX = "article";
	private static String CHAPTER_TEMPLATE_PREFIX = "chapter";

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ConfigService configService;

	@Autowired
	private FolderService folderService;

	/**
	 * @return
	 */
	public String get404() {
		return this.getTemplatePath("404");
	}

	/**
	 * @return
	 */
	public String get500() {
		return this.getTemplatePath("500");
	}

	public String getAdminTemplate(String template){
		return this.getAdminTemplatePath(template);
	}

	/**
	 * 得到首页（默认页）模板
	 * 
	 * @return
	 * @throws TemplateNotFoundException
	 */
	public String getDefaultTemplate() throws TemplateNotFoundException {
		List<String> themeOrderList = new ArrayList<String>();
		themeOrderList.add("index");
		themeOrderList.add(FOLDER_TEMPLATE_PREFIX);
		themeOrderList.add(FILE_TEMPLATE_PREFIX);
		themeOrderList.add(CHAPTER_TEMPLATE_PREFIX);
		for (String theme : themeOrderList) {
			if (this.isExist(theme)) {
				return this.getTemplatePath(theme);
			}
		}
		throw new TemplateNotFoundException("模板文件：index 不存在！！");
	}



	/**
	 * 得到x目录视图模板
	 * 
	 * @param folderId
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws FolderNotFoundException
	 */
	public String getFolderTemplate(int folderId)
			throws TemplateNotFoundException, FolderNotFoundException {
		List<FolderVo> folderPathList = folderService
				.getFolderPathListByFolderId(folderId);
		List<String> themeOrderList = new ArrayList<String>();
		themeOrderList.add(FOLDER_TEMPLATE_PREFIX);
		String themeString = FOLDER_TEMPLATE_PREFIX;
		for (Folder folder : folderPathList) {
			themeString = themeString + "-" + folder.getEname();
			themeOrderList.add(themeString);
		}
		// 模板顺序反转
		Collections.reverse(themeOrderList);
		for (String theme : themeOrderList) {
			if (this.isExist(theme)) {
				return this.getTemplatePath(theme);
			}
		}
		throw new TemplateNotFoundException("模板文件："
				+ this.getTemplatePath(FOLDER_TEMPLATE_PREFIX) + ".html"
				+ " 不存在！！");
	}

	/**
	 * 得到文件模板
	 * 
	 * @param folderPathList
	 * @param articleId
	 * @return
	 * @throws TemplateNotFoundException
	 * @throws FolderNotFoundException
	 */
	public String getArticleTemplate(int folderId, int articleId)
			throws TemplateNotFoundException, FolderNotFoundException {
		List<FolderVo> folderPathList = folderService
				.getFolderPathListByFolderId(folderId);
		List<String> themeOrderList = new ArrayList<String>();
		themeOrderList.add(FILE_TEMPLATE_PREFIX);
		String themeString = FILE_TEMPLATE_PREFIX;
		for (Folder folder : folderPathList) {
			themeString = themeString + "-" + folder.getEname();
			themeOrderList.add(themeString);
		}
		themeOrderList.add(themeString + "-" + articleId);


		// 模板顺序反转
		Collections.reverse(themeOrderList);
		for (String theme : themeOrderList) {
			if (this.isExist(theme)) {
				return this.getTemplatePath(theme);
			}
		}
		throw new TemplateNotFoundException("模板文件："
				+ this.getTemplatePath(FILE_TEMPLATE_PREFIX) + " 不存在！！");
	}


	public String getChapterTemplate()
			throws TemplateNotFoundException, FolderNotFoundException {
//		List<FolderVo> folderPathList = folderService
//				.getFolderPathListByFolderId(folderId);
//		List<String> themeOrderList = new ArrayList<String>();
//		themeOrderList.add(CHAPTER_TEMPLATE_PREFIX);
//		String themeString = CHAPTER_TEMPLATE_PREFIX;
//		for (Folder folder : folderPathList) {
//			themeString = themeString + "-" + folder.getEname();
//			themeOrderList.add(themeString);
//		}
//		themeOrderList.add(themeString + "-" + articleId);
//
//		// 模板顺序反转
//		Collections.reverse(themeOrderList);
//		for (String theme : themeOrderList) {
//			if (this.isExist(theme)) {
//				return this.getTemplatePath(theme);
//			}
//		}
		return this.getTemplatePath("chapter");
//		throw new TemplateNotFoundException("模板文件："
//				+ this.getTemplatePath(CHAPTER_TEMPLATE_PREFIX) + " 不存在！！");
	}

	/**
	 * 得到当前请求需要渲染的模板相对路径
	 * 
	 * @param theme
	 * @return
	 */
	private String getTemplatePath(String template) {
		return "/template/"
				+ configService.getStringByKey(ConfigConstant.NOVEL_TEMPLATE)
				+ "/" + template;
	}

	/**得到管理后台的模板相对路径
	 *
	 * @param template
	 * @return
	 */
	private String getAdminTemplatePath(String template) {
		return "/template/"
				+ configService.getStringByKey(ConfigConstant.ADMIN_TEMPLATE)
				+ "/" + template;
	}




	/**
	 * 模板物理地址是否存在
	 * 
	 * @param theme
	 * @return
	 */
	@Cacheable("default")
	public Boolean isExist(String theme) {
		String themePath = "/WEB-INF/static/template/"
				+ configService.getStringByKey(ConfigConstant.NOVEL_TEMPLATE)
				+ "/" + theme + ".html";
		File file = new File(SystemConstant.JIM_NOVEL_ROOT + themePath);
		if (file.exists()) {
			logger.info("尝试使用模板：" + themePath+"【存在】");
			return true;
		} else {
			logger.info("尝试使用模板：" + themePath+"【不存在】");
			return false;
		}
	}

}
