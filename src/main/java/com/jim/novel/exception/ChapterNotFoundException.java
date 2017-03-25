/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.jim.novel.exception;

/**
 * 
 * 章节没有发现异常
 * 
 * @author Herbert
 * 
 */
public class ChapterNotFoundException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ChapterNotFoundException(String msg) {
		super(msg);
	}
}
