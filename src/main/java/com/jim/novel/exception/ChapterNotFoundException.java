

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
