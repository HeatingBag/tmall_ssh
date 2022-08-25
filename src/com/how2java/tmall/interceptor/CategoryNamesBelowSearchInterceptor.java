/**
* @author 作者 E-mail:
* @version 创建时间：2022年6月2日 上午10:57:58
*/
package com.how2java.tmall.interceptor;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CategoryNamesBelowSearchInterceptor extends AbstractInterceptor {

	@Autowired
	CategoryService categoryService;

	/**
	 * 1. 如果发现是以/fore开头的访问就进行处理
	 * 2. 取出所有的分类对象
	 * 3. 放在session的"cs"中
	 * 4. 在search.jsp，simpleSearch.jsp中进行显示
	 * */

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		String result = "";

		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		ServletContext servletContext = (ServletContext) ctx.get(StrutsStatics.SERVLET_CONTEXT);

		String contextPath = servletContext.getContextPath();
		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		if(uri.startsWith("/fore")) {
			List<Category> cs = categoryService.list();
			ctx.getSession().put("cs", cs);
		}
		result = invocation.invoke();
		return result;
	}

}
