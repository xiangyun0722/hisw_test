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
import com.thinkgem.jeesite.modules.meeting.entity.MeetingPptOperationRecords;
import com.thinkgem.jeesite.modules.meeting.service.MeetingPptOperationRecordsService;

/**
 * 会议操作记录Controller
 * @author j.feng
 * @version 2015-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meetingPptOperationRecords")
public class MeetingPptOperationRecordsController extends BaseController {

	@Autowired
	private MeetingPptOperationRecordsService meetingPptOperationRecordsService;
	
	@ModelAttribute
	public MeetingPptOperationRecords get(@RequestParam(required=false) String id) {
		MeetingPptOperationRecords entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = meetingPptOperationRecordsService.get(id);
		}
		if (entity == null){
			entity = new MeetingPptOperationRecords();
		}
		return entity;
	}
	
	@RequiresPermissions("meeting:meetingPptOperationRecords:view")
	@RequestMapping(value = {"list", ""})
	public String list(MeetingPptOperationRecords meetingPptOperationRecords, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MeetingPptOperationRecords> page = meetingPptOperationRecordsService.findPage(new Page<MeetingPptOperationRecords>(request, response), meetingPptOperationRecords); 
		model.addAttribute("page", page);
		return "modules/meeting/meetingPptOperationRecordsList";
	}

	@RequiresPermissions("meeting:meetingPptOperationRecords:view")
	@RequestMapping(value = "form")
	public String form(MeetingPptOperationRecords meetingPptOperationRecords, Model model) {
		model.addAttribute("meetingPptOperationRecords", meetingPptOperationRecords);
		return "modules/meeting/meetingPptOperationRecordsForm";
	}

	@RequiresPermissions("meeting:meetingPptOperationRecords:edit")
	@RequestMapping(value = "save")
	public String save(MeetingPptOperationRecords meetingPptOperationRecords, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, meetingPptOperationRecords)){
			return form(meetingPptOperationRecords, model);
		}
		meetingPptOperationRecordsService.save(meetingPptOperationRecords);
		addMessage(redirectAttributes, "保存会议操作记录成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingPptOperationRecords/?repage";
	}
	
	@RequiresPermissions("meeting:meetingPptOperationRecords:edit")
	@RequestMapping(value = "delete")
	public String delete(MeetingPptOperationRecords meetingPptOperationRecords, RedirectAttributes redirectAttributes) {
		meetingPptOperationRecordsService.delete(meetingPptOperationRecords);
		addMessage(redirectAttributes, "删除会议操作记录成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingPptOperationRecords/?repage";
	}

}