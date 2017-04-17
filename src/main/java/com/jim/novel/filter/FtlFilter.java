/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.jim.novel.filter;


import com.jim.novel.utils.HttpUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * Ftl文件安全过滤器
 * 
 * @author Herbert
 * 
 */
public class FtlFilter implements Filter {

	protected final Logger logger = Logger.getLogger(this.getClass());

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.sendRedirect(HttpUtils.getBasePath(request) + "/404.htm");
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
