package com.thinkgem.jeesite.common.api;


import org.apache.log4j.Logger;
import org.springframework.web.bind.MissingServletRequestParameterException;

/***
 * 统一异常处理类
 * @author twk
 */
public class ExceptionUtil {
	
	static Logger logger =Logger.getLogger(ExceptionUtil.class);
	
	/***
	 * 处理异常类
	 * @param ex
	 * @return
	*/
	public static RestResponse dealException(Exception ex){
		RestResponse response =null ;
	     if (ex instanceof RestException) {
	          RestException rex = (RestException) ex;
	          response = new RestResponse(rex.getErrorCode(),ex.getMessage());
	    } else  if (ex instanceof IllegalArgumentException || ex instanceof MissingServletRequestParameterException ) {
			response = new RestResponse(RestErrorCode.BIZ_ARGUMENT_INVALID,ex.getMessage());
	    }
	    /*else if ( ex instanceof AuthorizationException){
	    	response = new RestResponse(RestErrorCode.AUTHENTICATION_FAILED,ex.getMessage());
	    } */
	     else {
	    	logger.error(ex, ex);
	    	response = new RestResponse(RestErrorCode.SYSTEM_ERROR_CODE, RestErrorCode.SYSTEM_ERROR);
	    	//response = new RestResponse(RestErrorCode.SYSTEM_ERROR_CODE, ex.getMessage());
	    }
	    return response;
	} 
}
