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
import com.thinkgem.jeesite.modules.meeting.entity.MeetingLiveChannel;
import com.thinkgem.jeesite.modules.meeting.service.MeetingLiveChannelService;

/**
 * 直播频道Controller
 * @author j.feng
 * @version 2015-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meetingLiveChannel")
public class MeetingLiveChannelController extends BaseController {

	@Autowired
	private MeetingLiveChannelService meetingLiveChannelService;
	
	@ModelAttribute
	public MeetingLiveChannel get(@RequestParam(required=false) String id) {
		MeetingLiveChannel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = meetingLiveChannelService.get(id);
		}
		if (entity == null){
			entity = new MeetingLiveChannel();
		}
		return entity;
	}
	
	@RequiresPermissions("meeting:meetingLiveChannel:view")
	@RequestMapping(value = {"list", ""})
	public String list(MeetingLiveChannel meetingLiveChannel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MeetingLiveChannel> page = meetingLiveChannelService.findPage(new Page<MeetingLiveChannel>(request, response), meetingLiveChannel); 
		model.addAttribute("page", page);
		return "modules/meeting/meetingLiveChannelList";
	}

	@RequiresPermissions("meeting:meetingLiveChannel:view")
	@RequestMapping(value = "form")
	public String form(MeetingLiveChannel meetingLiveChannel, Model model) {
		model.addAttribute("meetingLiveChannel", meetingLiveChannel);
		return "modules/meeting/meetingLiveChannelForm";
	}

	@RequiresPermissions("meeting:meetingLiveChannel:edit")
	@RequestMapping(value = "save")
	public String save(MeetingLiveChannel meetingLiveChannel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, meetingLiveChannel)){
			return form(meetingLiveChannel, model);
		}
		meetingLiveChannelService.save(meetingLiveChannel);
		addMessage(redirectAttributes, "保存直播频道成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingLiveChannel/?repage";
	}
	
	@RequiresPermissions("meeting:meetingLiveChannel:edit")
	@RequestMapping(value = "delete")
	public String delete(MeetingLiveChannel meetingLiveChannel, RedirectAttributes redirectAttributes) {
		meetingLiveChannelService.delete(meetingLiveChannel);
		addMessage(redirectAttributes, "删除直播频道成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingLiveChannel/?repage";
	}

}