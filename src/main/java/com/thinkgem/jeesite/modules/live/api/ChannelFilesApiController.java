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
import com.thinkgem.jeesite.modules.live.entity.ChannelFiles;
import com.thinkgem.jeesite.modules.live.service.ChannelFilesService;

/**
 * 直播频道录制文件ApiController
 * @author tanwenkai@qq.com
 * @version 2016-12-12
 */
@Controller
@RequestMapping(value = "api/channelFiles")
public class ChannelFilesApiController extends BaseController {

	@Autowired
	private ChannelFilesService channelFilesService;
	
	@ApiAuth
	@RequestMapping(value ="/get")
    @ResponseBody
	public RestResponse get(HttpServletRequest request,HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
        try {
			String id = request.getParameter("id");
			ChannelFiles channelFiles = channelFilesService.get(id);
			Assert.notNull(channelFiles, "id错误");
			restResponse.setData(channelFiles);
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
        	ChannelFiles channelFiles=RequestUtil.form(ChannelFiles.class, request);
        	String uid =request.getParameter("uid");					//用户ID	
			Page<ChannelFiles> page = channelFilesService.findPage(new Page<ChannelFiles>(request, response), channelFiles); 
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
        	ChannelFiles channelFiles=RequestUtil.form(ChannelFiles.class, request);
        	channelFilesService.save(channelFiles);
        	return new RestResponse(channelFiles);
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
        	ChannelFiles channelFilesModel=RequestUtil.form(ChannelFiles.class, request);
        	channelFilesService.delete(channelFilesModel);
        	return new RestResponse(RestErrorCode.SUCESS_CODE, "删除成功");
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
}