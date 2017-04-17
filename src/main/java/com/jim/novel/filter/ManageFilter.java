/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.jim.novel.filter;


import com.jim.novel.constant.SystemConstant;
import com.jim.novel.model.Admin;
import com.jim.novel.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 管理过滤器
 * 
 * @author run
 * 
 */
public class ManageFilter implements Filter {

	/**
	 * 需要排除的页面
	 */

	private String excludedUrl;
	private String[] excludedPageArray;

	protected final Logger logger = Logger.getLogger(this.getClass());

	public void init(FilterConfig fConfig) throws ServletException {
		excludedUrl = fConfig.getInitParameter("excludedUrl");
		if(StringUtils.isNotEmpty(excludedUrl)) {
			excludedPageArray = excludedUrl.split(",");
		}
		return;
	}


	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		Boolean isExcludedPage = false;
		for (String page : excludedPageArray) {//判断是否在过滤url之外
			if(request.getRequestURI().equals(page)){

				isExcludedPage = true;
				break;
			}
		}
		if(isExcludedPage){
			chain.doFilter(request, response);
		}else{
			Admin admin = (Admin) request.getSession().getAttribute(
					SystemConstant.SESSION_ADMIN);
			User user = (User) request.getSession().getAttribute(SystemConstant.SESSION_USER);
			if (admin == null && user == null) {
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":" + request.getServerPort()
						+ path;
				response.sendRedirect(basePath + "/admin/login.htm");
			} else {
				chain.doFilter(request, response);
			}
		}


	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
