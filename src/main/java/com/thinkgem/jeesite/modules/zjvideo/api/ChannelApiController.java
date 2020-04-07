/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.api.ExceptionUtil;
import com.thinkgem.jeesite.common.api.RequestUtil;
import com.thinkgem.jeesite.common.api.RestErrorCode;
import com.thinkgem.jeesite.common.api.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.thinkgem.jeesite.common.annotation.ApiAuth;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.Channel;
import com.thinkgem.jeesite.modules.zjvideo.service.ChannelService;

import java.util.List;

/**
 * 频道ApiController
 * @author lyy
 * @version 2020-02-06
 */
@Controller
@RequestMapping(value = "api/channel")
@CrossOrigin
public class ChannelApiController extends BaseController {

	@Autowired
	private ChannelService channelService;
	
	@RequestMapping(value ="/get")
    @ResponseBody
	public RestResponse get(HttpServletRequest request, HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
        try {
			String id = request.getParameter("id");
			Channel channel = channelService.getDetail(id);
			Assert.notNull(channel, "id错误");
			restResponse.setData(channel);
		} catch (Exception ex) {
	    	restResponse= ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	
	@RequestMapping(value = {"list"})
	@ResponseBody
	public RestResponse list(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResponse restResponse = new RestResponse();
        try {
        	Channel channel= RequestUtil.form(Channel.class, request);
        	//String uid =request.getParameter("uid");					//用户ID
			List<Channel> list = channelService.findList(channel);
			return new RestResponse(list);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
	 
	@RequestMapping(value ="/save")
	@ResponseBody
	public RestResponse save(HttpServletRequest request,HttpServletResponse response) {
		RestResponse restResponse = new RestResponse();
        try {
        	Channel channel=RequestUtil.form(Channel.class, request);
        	System.err.println(channel.getTemplateid());
        	System.err.println("templateid: "+request.getParameter("templateid"));
        	channelService.save(channel);
        	return new RestResponse(channel);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
    }

	@RequestMapping(value ="/update")
	@ResponseBody
	public RestResponse update(HttpServletRequest request,HttpServletResponse response) {
		RestResponse restResponse = new RestResponse();
		try {
			Channel channel=RequestUtil.form(Channel.class, request);
			channelService.update(channel);
			return new RestResponse(channel);
		} catch (Exception ex) {
			restResponse=ExceptionUtil.dealException(ex);
		}
		return restResponse;
	}
	
	@RequestMapping(value ="/delete")
	@ResponseBody
	public RestResponse delete(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		RestResponse restResponse = new RestResponse();
        try {
        	Channel channelModel=RequestUtil.form(Channel.class, request);
        	channelService.delete(channelModel);
        	return new RestResponse(RestErrorCode.SUCESS_CODE, "删除成功");
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
}