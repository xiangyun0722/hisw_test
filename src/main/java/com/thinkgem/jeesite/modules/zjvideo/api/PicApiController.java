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
import com.thinkgem.jeesite.modules.zjvideo.entity.Pic;
import com.thinkgem.jeesite.modules.zjvideo.service.PicService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片表ApiController
 * @author lyy
 * @version 2020-02-06
 */
@Controller
@RequestMapping(value = "api/pic")
@CrossOrigin
public class PicApiController extends BaseController {

	@Autowired
	private PicService picService;

	@RequestMapping(value ="/upload")
	@ResponseBody
	public RestResponse upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file){
		RestResponse restResponse = new RestResponse();
		try{
			Pic pic = picService.upload(file);
			restResponse.setData(pic);
		}catch (Exception ex){
			restResponse= ExceptionUtil.dealException(ex);
		}
		return restResponse;
	}
	
	@RequestMapping(value ="/get")
    @ResponseBody
	public RestResponse get(HttpServletRequest request, HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
        try {
			String id = request.getParameter("id");
			Pic pic = picService.getDetail(id);
			Assert.notNull(pic, "id错误");
			restResponse.setData(pic);
		} catch (Exception ex) {
	    	restResponse= ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	

	@RequestMapping(value ="/delete")
	@ResponseBody
	public RestResponse delete(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		RestResponse restResponse = new RestResponse();
        try {
        	Pic picModel=RequestUtil.form(Pic.class, request);
        	picService.delete(picModel);
        	return new RestResponse(RestErrorCode.SUCESS_CODE, "删除成功");
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
}