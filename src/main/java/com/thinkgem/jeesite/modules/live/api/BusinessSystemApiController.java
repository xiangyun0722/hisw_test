/**
 * 
 */
package com.thinkgem.jeesite.modules.live.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.thinkgem.jeesite.common.annotation.ApiAuth;
import com.thinkgem.jeesite.common.api.ExceptionUtil;
import com.thinkgem.jeesite.common.api.RequestUtil;
import com.thinkgem.jeesite.common.api.RestErrorCode;
import com.thinkgem.jeesite.common.api.RestResponse;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.live.entity.BusinessSystem;
import com.thinkgem.jeesite.modules.live.service.BusinessSystemService;

/**
 * 直播业务系统ApiController
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
@Controller
@RequestMapping(value = "api/businessSystem")
public class BusinessSystemApiController extends BaseController {

	@Autowired
	private BusinessSystemService businessSystemService;
	
	@ApiAuth
	@RequestMapping(value ="/get")
    @ResponseBody
	public RestResponse get(HttpServletRequest request,HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
        try {
			String id = request.getParameter("id");
			BusinessSystem businessSystem = businessSystemService.get(id);
			Assert.notNull(businessSystem, "id错误");
			restResponse.setData(businessSystem);
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	
	@ApiAuth //必须登录
	@RequestMapping(value = {"list"})
	@ResponseBody
	public RestResponse list(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResponse restResponse = new RestResponse();
        try {
        	BusinessSystem businessSystem=RequestUtil.form(BusinessSystem.class, request);
        	String uid =request.getParameter("uid");					//用户ID	
			Page<BusinessSystem> page = businessSystemService.findPage(new Page<BusinessSystem>(request, response), businessSystem); 
			page.isWebservicePageFlag = true;
  	      return new RestResponse(page);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
	 
	@ApiAuth
	@RequestMapping(value ="/save")
	@ResponseBody
	public RestResponse save(HttpServletRequest request,HttpServletResponse response) {
		RestResponse restResponse = new RestResponse();
        try {
        	BusinessSystem businessSystem=RequestUtil.form(BusinessSystem.class, request);
        	businessSystemService.save(businessSystem);
        	return new RestResponse(businessSystem);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
    }
	
	@ApiAuth
	@RequestMapping(value ="/delete")
	@ResponseBody
	public RestResponse delete(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		RestResponse restResponse = new RestResponse();
        try {
        	BusinessSystem businessSystemModel=RequestUtil.form(BusinessSystem.class, request);
        	businessSystemService.delete(businessSystemModel);
        	return new RestResponse(RestErrorCode.SUCESS_CODE, "删除成功");
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
}