package com.thinkgem.jeesite.common.api;

public class RestErrorCode {
	
	public static final String EMPTY_STRING= "";

    /***
	 * 系统错误
	 */
	public final static int SYSTEM_ERROR_CODE 				 =9999;
	
	public final static String SYSTEM_ERROR = "系统错误,请重试!";
    
    // 客户端错误
    public static final int AUTHENTICATION__ARGUMENT_INVALID = 401;
    
    /***
     * 参数错误
     */
    public static final int BIZ_ARGUMENT_INVALID             = 402;
    
    /***
     * 认证失败
     */
    public static final int     AUTHENTICATION_FAILED_CODE	 = 400;
    
    public  static final String AUTHENTICATION_FAILED ="认证错误,请重新登录";
    
    
    /**
     * 资源找不到
     **/
    public static final int RESOURCE_NOT_FOUND               = 404;
    
    /**
     * 短信发送失败
     **/
    public static final int SEND_SMS_FAILED               = 410;
    
    public final static String SUCESS = "操作成功";
    
	public final static int SUCESS_CODE =0;
}
