/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.jim.novel.constant;


import com.jim.novel.utils.PropertyUtils;

/**
 * 系统常量
 * 
 * @author Herbert
 * 
 */
public class SystemConstant {

	/**
	 * 应用部署路径的KEY
	 */
	public static String JIM_NOVEL_ROOT = PropertyUtils.getRoot();

	/**
	 * 上传图片文件夹
	 */
	public static String UPLOAD_FOLDER = "/upload/";

	/**
	 * 备份文件夹
	 */
	public static String BACKUP_FOLDER = "/WEB-INF/backup";

	/**
	 * Session中的管理员Key
	 */
	public static final String SESSION_ADMIN = "SESSION_ADMIN";

	public static final String SESSION_USER = "SESSION_USER";

	/**
	 * 头像URL 180x180
	 */
	public static final String FACE_URL = "http://faceurl.shishuo.com/face";

	/**
	 * 
	 */
	public static final String LANGUAGE = "language";

}
