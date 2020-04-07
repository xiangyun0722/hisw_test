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
import com.thinkgem.jeesite.modules.zjvideo.entity.ScheduleProgram;
import com.thinkgem.jeesite.modules.zjvideo.service.ScheduleProgramService;

import java.util.List;
import java.util.Map;

/**
 * 排片表ApiController
 * @author lyy
 * @version 2020-02-06
 */
@Controller
@RequestMapping(value = "api/scheduleProgram")
@CrossOrigin
public class ScheduleProgramApiController extends BaseController {

	@Autowired
	private ScheduleProgramService scheduleProgramService;
	
	@RequestMapping(value ="/get")
    @ResponseBody
	public RestResponse get(HttpServletRequest request, HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
        try {
			String id = request.getParameter("id");
			ScheduleProgram scheduleProgram = scheduleProgramService.getDetail(id);
			Assert.notNull(scheduleProgram, "id错误");
			restResponse.setData(scheduleProgram);
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
        	ScheduleProgram scheduleProgram= RequestUtil.form(ScheduleProgram.class, request);
        	String uid =request.getParameter("uid");					//用户ID

			/*Page<ScheduleProgram> page = scheduleProgramService.findPage(new Page<ScheduleProgram>(request, response), scheduleProgram);
			page.isWebservicePageFlag = true;*/
			List<ScheduleProgram> list = scheduleProgramService.findShowList(scheduleProgram);

			return new RestResponse(list);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}

	/**
	 * 获取正在直播课程信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/getLivingProgram")
	@ResponseBody
	public RestResponse getLivingProgram(HttpServletRequest request, HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
		try {
			Map<String,Object> map = scheduleProgramService.getLivingProgram();
			restResponse.setData(map);
		} catch (Exception ex) {
			restResponse= ExceptionUtil.dealException(ex);
		}
		return restResponse;
	}
	 
	@RequestMapping(value ="/save")
	@ResponseBody
	public RestResponse save(HttpServletRequest request,HttpServletResponse response) {
		RestResponse restResponse = new RestResponse();
        try {
        	ScheduleProgram scheduleProgram=RequestUtil.form(ScheduleProgram.class, request);
        	scheduleProgramService.save(scheduleProgram);
        	return new RestResponse(scheduleProgram);
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
			ScheduleProgram scheduleProgram=RequestUtil.form(ScheduleProgram.class, request);
			scheduleProgramService.update(scheduleProgram);
			return new RestResponse(scheduleProgram);
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
        	ScheduleProgram scheduleProgramModel=RequestUtil.form(ScheduleProgram.class, request);
        	scheduleProgramService.delete(scheduleProgramModel);
        	return new RestResponse(RestErrorCode.SUCESS_CODE, "删除成功");
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
}