package com.thinkgem.jeesite.modules.sys.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.annotation.ApiAuth;
import com.thinkgem.jeesite.common.api.RestErrorCode;
import com.thinkgem.jeesite.common.api.RestResponse;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.live.dao.BusinessSystemDao;
import com.thinkgem.jeesite.modules.live.entity.BusinessSystem;

/**
 * api token 拦截器, 拦截 api 的用户信息。并且
 * @author twk
 * @version 2015年8月15日
 */
public class ApiTokenInterceptor extends BaseService implements HandlerInterceptor {
	
	private static BusinessSystemDao businessSystemDao = SpringContextHolder.getBean(BusinessSystemDao.class);
	
	@Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
	        Method method = handlerMethod.getMethod();
	        ApiAuth apiAuth = method.getAnnotation(ApiAuth.class);
	        if (apiAuth != null) {
	           String  publicKey   = request.getParameter("publicKey");
	           boolean isok = false;
	           if(StringUtils.isNotBlank(publicKey) ){
	        	   //校验publicKey
	        	   //BusinessSystemUtils.getByPublicKey(publicKey);
	        	   BusinessSystem businessSystem = businessSystemDao.getByPublicKey(publicKey);
	        	   if(businessSystem!=null && StringUtils.isNotBlank(businessSystem.getId())){
	        		   isok = true ;
	        	   }
	           }else{//需要重新登录。
	        	   isok = false;
	           }
	           if(!isok){
	        	   RestResponse rsp = new RestResponse(RestErrorCode.AUTHENTICATION_FAILED_CODE,RestErrorCode.AUTHENTICATION_FAILED);
	        	   //输出必须要登录的提示。
	        	   response.setContentType("application/json;charset=UTF-8");
	        	   response.getWriter().write(JSONObject.toJSONString(rsp));
	           }
	           return isok;
	        }else{
	        	return true;
	        }
		}else{
			// 没有注解通过拦截
	        return true;	
		}
    }
	
	public static void main(String[] args) {
		try {
			RestResponse response = new RestResponse(RestErrorCode.AUTHENTICATION_FAILED_CODE,RestErrorCode.AUTHENTICATION_FAILED);
			System.out.println(JSONObject.toJSONString(response));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		if (modelAndView != null){
//			// 如果是手机或平板访问的话，则跳转到手机视图页面。
//			if(UserAgentUtils.isMobileOrTablet(request) && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")){
//				modelAndView.setViewName("mobile/" + modelAndView.getViewName());
//			}
//		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		
	}

}
