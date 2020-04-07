/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.web;

import java.util.List;
import java.util.UUID;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingLiveChannel;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingMeetingInfo;
import com.thinkgem.jeesite.modules.meeting.service.MeetingLiveChannelService;
import com.thinkgem.jeesite.modules.meeting.service.MeetingMeetingInfoService;
import com.thinkgem.jeesite.modules.sys.entity.DocumentTranscodingTask;
import com.thinkgem.jeesite.modules.sys.service.DocumentTranscodingTaskService;
import com.thinkgem.jeesite.modules.sys.web.UploadFileInfo;

/**
 * 会议操作记录Controller
 * @author j.feng
 * @version 2015-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meetingMeetingInfo")
public class MeetingMeetingInfoController extends BaseController {

	@Autowired
	private MeetingMeetingInfoService meetingMeetingInfoService;
	
	@Autowired
	private MeetingLiveChannelService meetingLiveChannelService;
	
	@Autowired
	private DocumentTranscodingTaskService documentTranscodingTaskService;
	
	@ModelAttribute
	public MeetingMeetingInfo get(@RequestParam(required=false) String id) {
		MeetingMeetingInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = meetingMeetingInfoService.get(id);
		}
		if (entity == null){
			entity = new MeetingMeetingInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("meeting:meetingMeetingInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MeetingMeetingInfo meetingMeetingInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MeetingMeetingInfo> page = meetingMeetingInfoService.findPage(new Page<MeetingMeetingInfo>(request, response), meetingMeetingInfo); 
		model.addAttribute("page", page);
		return "modules/meeting/meetingMeetingInfoList";
	}

	@RequiresPermissions("meeting:meetingMeetingInfo:view")
	@RequestMapping(value = "form")
	public String form(MeetingMeetingInfo meetingMeetingInfo, Model model) {
		model.addAttribute("meetingMeetingInfo", meetingMeetingInfo);
		//查询全部的频道。
		MeetingLiveChannel meetingLiveChannel = new MeetingLiveChannel();
		meetingLiveChannel.setStatus(MeetingLiveChannel.statusUplines);//上线可用的频道
		List<MeetingLiveChannel> meetingLiveChannelList = meetingLiveChannelService.findList(meetingLiveChannel);
		model.addAttribute("meetingLiveChannelList", meetingLiveChannelList);
		return "modules/meeting/meetingMeetingInfoForm";
	}

	@RequiresPermissions("meeting:meetingMeetingInfo:edit")
	@RequestMapping(value = "save")
	public String save(MeetingMeetingInfo meetingMeetingInfo, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, meetingMeetingInfo)){
			return form(meetingMeetingInfo, model);
		}
		String ppt = request.getParameter("ppt");
		meetingMeetingInfo.setPpt(ppt);
		MeetingMeetingInfo meetingMeetingInfoTemp = meetingMeetingInfoService.get(meetingMeetingInfo.getId());
		if(meetingMeetingInfoTemp ==null){
			UploadFileInfo  uploadFileInfo = UploadFileInfo.StrToBean(ppt);
			DocumentTranscodingTask documentTranscodingTask = new DocumentTranscodingTask();
			documentTranscodingTask.setFilepath(uploadFileInfo.getFilePath());
			documentTranscodingTask.setOriginalName(uploadFileInfo.getOldFileName());
			documentTranscodingTask.setStatus(0);
			String uuid= UUID.randomUUID().toString();
			documentTranscodingTask.setBusinessId(uuid);
			documentTranscodingTaskService.save(documentTranscodingTask);
			meetingMeetingInfo.setPptUuid(uuid);
		}else{
			if(!StringUtils.equals(meetingMeetingInfoTemp.getPpt(), ppt)){
				if(StringUtils.isNotBlank(ppt)){
					UploadFileInfo  uploadFileInfo = UploadFileInfo.StrToBean(ppt);
					DocumentTranscodingTask documentTranscodingTask = new DocumentTranscodingTask();
					documentTranscodingTask.setFilepath(uploadFileInfo.getFilePath());
					documentTranscodingTask.setOriginalName(uploadFileInfo.getOldFileName());
					documentTranscodingTask.setStatus(0);
					String uuid= UUID.randomUUID().toString();
					documentTranscodingTask.setBusinessId(uuid);
					documentTranscodingTaskService.save(documentTranscodingTask);
					meetingMeetingInfo.setPptUuid(uuid);
				}
			}
		}
		meetingMeetingInfoService.save(meetingMeetingInfo);
		addMessage(redirectAttributes, "保存会议操作记录成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingMeetingInfo/?repage";
	}
	
	@RequiresPermissions("meeting:meetingMeetingInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MeetingMeetingInfo meetingMeetingInfo, RedirectAttributes redirectAttributes) {
		meetingMeetingInfoService.delete(meetingMeetingInfo);
		addMessage(redirectAttributes, "删除会议操作记录成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingMeetingInfo/?repage";
	}

}