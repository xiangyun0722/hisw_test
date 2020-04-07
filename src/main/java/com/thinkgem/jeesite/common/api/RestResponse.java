package com.thinkgem.jeesite.common.api;


/**
 * REST 返回格式，这样可以利用@ResponseBody注解。 
 * @author twk
 */
public class RestResponse {
	
    public static final RestResponse SUCCESS       = new RestResponse();

    public static final RestResponse ERROR_UNKNOWN = new RestResponse(RestErrorCode.SYSTEM_ERROR_CODE, "系统异常！");
    
    private int                      code  = 0;//0代码正常
    private String                   msg  = RestErrorCode.EMPTY_STRING;
    private Object                   data;
    
    public RestResponse(Object data) {
    	this.data=data;
    }
    
    public RestResponse() {
    }
    
    /***
     * 成功消息
     * @param successInfo
     */
    public RestResponse(String successInfo) {
    	super();
		this.msg = successInfo;
    }
    
	public RestResponse(int code, String info) {
		super();
		this.code = code;
		this.msg = info;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
