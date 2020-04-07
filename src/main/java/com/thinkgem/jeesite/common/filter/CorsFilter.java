package com.thinkgem.jeesite.common.filter;

/**
 * @Version
 * @Author zcf
 * @Created 2020年02月26  13:40:11
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020年02月26  Zcf  create file
 */

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域拦截器
 */
public class CorsFilter implements Filter {

    Logger logger = Logger.getLogger(Filter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");
        logger.info("========经过cors拦截器");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy(){

    }
}
