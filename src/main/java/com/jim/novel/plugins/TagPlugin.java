package com.jim.novel.plugins;

import com.jim.novel.utils.SSUtils;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义freemarker标签，避免通过重复controller返回对象进行渲染页面
 */
@Service
public abstract class TagPlugin extends ApplicationObjectSupport implements
		TemplateDirectiveModel, Plugin {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@PostConstruct
	public void init() throws TemplateModelException {
		String className = this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf(".") + 1);
		String beanName = StringUtils.uncapitalize(className);
		String tagName = "shishuo_" + SSUtils.toUnderline(beanName);
		logger.info(tagName);
		//注册的对象能够被所有的freemarker模板访问到
		freeMarkerConfigurer.getConfiguration().setSharedVariable(tagName,
				this.getApplicationContext().getBean(beanName));
	}


}
