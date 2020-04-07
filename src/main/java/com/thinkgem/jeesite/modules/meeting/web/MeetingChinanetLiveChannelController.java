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
import com.thinkgem.jeesite.modules.meeting.entity.MeetingChinanetLiveChannel;
import com.thinkgem.jeesite.modules.meeting.service.MeetingChinanetLiveChannelService;

/**
 * 中国电信企业直播频道Controller
 * @author tanwenkai
 * @version 2016-09-20
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meetingChinanetLiveChannel")
public class MeetingChinanetLiveChannelController extends BaseController {

	@Autowired
	private MeetingChinanetLiveChannelService meetingChinanetLiveChannelService;
	
	@ModelAttribute
	public MeetingChinanetLiveChannel get(@RequestParam(required=false) String id) {
		MeetingChinanetLiveChannel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = meetingChinanetLiveChannelService.get(id);
		}
		if (entity == null){
			entity = new MeetingChinanetLiveChannel();
		}
		return entity;
	}
	
	@RequiresPermissions("meeting:meetingChinanetLiveChannel:view")
	@RequestMapping(value = {"list", ""})
	public String list(MeetingChinanetLiveChannel meetingChinanetLiveChannel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MeetingChinanetLiveChannel> page = meetingChinanetLiveChannelService.findPage(new Page<MeetingChinanetLiveChannel>(request, response), meetingChinanetLiveChannel); 
		model.addAttribute("page", page);
		return "modules/meeting/meetingChinanetLiveChannelList";
	}

	@RequiresPermissions("meeting:meetingChinanetLiveChannel:view")
	@RequestMapping(value = "form")
	public String form(MeetingChinanetLiveChannel meetingChinanetLiveChannel, Model model) {
		model.addAttribute("meetingChinanetLiveChannel", meetingChinanetLiveChannel);
		return "modules/meeting/meetingChinanetLiveChannelForm";
	}

	@RequiresPermissions("meeting:meetingChinanetLiveChannel:edit")
	@RequestMapping(value = "save")
	public String save(MeetingChinanetLiveChannel meetingChinanetLiveChannel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, meetingChinanetLiveChannel)){
			return form(meetingChinanetLiveChannel, model);
		}
		meetingChinanetLiveChannelService.save(meetingChinanetLiveChannel);
		addMessage(redirectAttributes, "保存直播频道成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingChinanetLiveChannel/?repage";
	}
	
	@RequiresPermissions("meeting:meetingChinanetLiveChannel:edit")
	@RequestMapping(value = "delete")
	public String delete(MeetingChinanetLiveChannel meetingChinanetLiveChannel, RedirectAttributes redirectAttributes) {
		meetingChinanetLiveChannelService.delete(meetingChinanetLiveChannel);
		addMessage(redirectAttributes, "删除直播频道成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingChinanetLiveChannel/?repage";
	}

}