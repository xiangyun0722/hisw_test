package com.thinkgem.jeesite.common.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 打印访问路径的过滤器 在项目开发中用于帮助调试 在web.xml配置文件中
 * @author libo
 */
public class PrintURLFilter implements Filter {
	
	Logger logger = Logger.getLogger(PrintURLFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String urlstr = getFullURL((HttpServletRequest)request);
		if(!urlstr.endsWith(".js") &&  StringUtils.indexOf(urlstr, "static")<0){
			logger.info(urlstr);
		}
		chain.doFilter(request, response);
	}

	public static String getFullURL(HttpServletRequest request) {

		StringBuffer urlstrbuf = request.getRequestURL();

		if (request.getQueryString() != null) {
			urlstrbuf.append("?");
			urlstrbuf.append(request.getQueryString());
		}

		return urlstrbuf.toString();
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
