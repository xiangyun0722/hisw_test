/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingJoinUser;
import com.thinkgem.jeesite.modules.meeting.service.MeetingJoinUserService;

/**
 * 参会用户Controller
 * @author j.feng
 * @version 2015-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meetingJoinUser")
public class MeetingJoinUserController extends BaseController {

	@Autowired
	private MeetingJoinUserService meetingJoinUserService;
	
	@ModelAttribute
	public MeetingJoinUser get(@RequestParam(required=false) String id) {
		MeetingJoinUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = meetingJoinUserService.get(id);
		}
		if (entity == null){
			entity = new MeetingJoinUser();
		}
		return entity;
	}
	
	@RequiresPermissions("meeting:meetingJoinUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(MeetingJoinUser meetingJoinUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MeetingJoinUser> page = meetingJoinUserService.findPage(new Page<MeetingJoinUser>(request, response), meetingJoinUser); 
		model.addAttribute("page", page);
		return "modules/meeting/meetingJoinUserList";
	}

	@RequiresPermissions("meeting:meetingJoinUser:view")
	@RequestMapping(value = "form")
	public String form(MeetingJoinUser meetingJoinUser, Model model) {
		model.addAttribute("meetingJoinUser", meetingJoinUser);
		return "modules/meeting/meetingJoinUserForm";
	}

	@RequiresPermissions("meeting:meetingJoinUser:edit")
	@RequestMapping(value = "save")
	public String save(MeetingJoinUser meetingJoinUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, meetingJoinUser)){
			return form(meetingJoinUser, model);
		}
		meetingJoinUserService.save(meetingJoinUser);
		addMessage(redirectAttributes, "保存参会用户成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingJoinUser/?repage";
	}
	
	@RequiresPermissions("meeting:meetingJoinUser:edit")
	@RequestMapping(value = "delete")
	public String delete(MeetingJoinUser meetingJoinUser, RedirectAttributes redirectAttributes) {
		meetingJoinUserService.delete(meetingJoinUser);
		addMessage(redirectAttributes, "删除参会用户成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingJoinUser/?repage";
	}

}