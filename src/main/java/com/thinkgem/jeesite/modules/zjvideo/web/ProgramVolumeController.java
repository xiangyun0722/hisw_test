/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.util.Date;
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
import com.thinkgem.jeesite.modules.meeting.entity.MeetingMeetingInfo;
import com.thinkgem.jeesite.modules.sys.entity.DocumentTranscodingTask;
import com.thinkgem.jeesite.modules.sys.service.DocumentTranscodingTaskService;
import com.thinkgem.jeesite.modules.sys.web.UploadFileInfo;
import com.thinkgem.jeesite.modules.zjvideo.dao.VideosDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.ProgramVolume;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videos;
import com.thinkgem.jeesite.modules.zjvideo.service.ProgramVolumeService;
import com.thinkgem.jeesite.modules.zjvideo.service.VideosService;

/**
 * 章节信息Controller
 * @author j.feng
 * @version 2015-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/programVolume")
public class ProgramVolumeController extends BaseController {

	@Autowired
	private ProgramVolumeService programVolumeService;
	private String programid;	// 定义一个全局的课程ID
	
	@Autowired
	private VideosService videosService;
	
	@Autowired
	private VideosDao videosDao;
	
	@Autowired
	private DocumentTranscodingTaskService documentTranscodingTaskService;	
	
	@ModelAttribute
	public ProgramVolume get(@RequestParam(required=false) String id) {
		ProgramVolume entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = programVolumeService.get(id);
		}
		if (entity == null){
			entity = new ProgramVolume();
		}
		return entity;
	}
	
	/*@RequiresPermissions("zjvideo:programVolume:view")*/
	@RequestMapping(value = {"list", ""})
	public String list(ProgramVolume programVolume, HttpServletRequest request, HttpServletResponse response, Model model) {
		programVolume.setProgramid(programid);
		Page<ProgramVolume> page = programVolumeService.findPage(new Page<ProgramVolume>(request, response), programVolume); 
		model.addAttribute("page", page);
		return "modules/zjvideo/programVolumeList";
	}
	/**
	 * 课程管理列表
	 * j.feng 2015年8月24日 下午4:46:54
	 * @param programVolume
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/class/list")
	public String classList(ProgramVolume programVolume, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*programVolume.setProgramid(programid);*/
		Page<ProgramVolume> page = programVolumeService.findPage(new Page<ProgramVolume>(request, response), programVolume); 
		model.addAttribute("page", page);
		return "redirect:"+Global.getAdminPath()+"/zjvideo/programVolume/?repage";
	}	
	/**
	 * 课程下面的所有剧集信息
	 * @param programVolume
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:program:edit")
	@RequestMapping(value = "program/List")
	public String programVolumeList(ProgramVolume programVolume, HttpServletRequest request, HttpServletResponse response, Model model) {
		programid = programVolume.getId();
		Page<ProgramVolume> page = programVolumeService.findProgramVolumeList(new Page<ProgramVolume>(request, response), programVolume); 
		model.addAttribute("page", page);
		return "modules/zjvideo/programVolumeList";
	}
	
	/*@RequiresPermissions("zjvideo:programVolume:view")*/
	@RequestMapping(value = "form")
	public String form(ProgramVolume programVolume, Model model) {
		Videos video = videosService.findByProgramVolumeId(programVolume.getId());
		if(null != video){
			programVolume.setVideoName(video.getName());
			programVolume.setVideoId(video.getId());
		}
		model.addAttribute("programVolume", programVolume);
		return "modules/zjvideo/programVolumeForm";
	}

	/*@RequiresPermissions("zjvideo:programVolume:edit")*/
	@RequestMapping(value = "save")
	public String save(ProgramVolume programVolume, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, programVolume)){
			return form(programVolume, model);
		}
		/**用户PPT上传更新*/
		String url = request.getParameter("url");
		programVolume.setUrl(url);
		ProgramVolume programVolumeItem = programVolumeService.get(programVolume.getId());
		if(programVolumeItem ==null){
			UploadFileInfo  uploadFileInfo = UploadFileInfo.StrToBean(url);
			DocumentTranscodingTask documentTranscodingTask = new DocumentTranscodingTask();
			documentTranscodingTask.setFilepath(uploadFileInfo.getFilePath());
			documentTranscodingTask.setOriginalName(uploadFileInfo.getOldFileName());
			documentTranscodingTask.setStatus(0);
			String uuid= UUID.randomUUID().toString();
			documentTranscodingTask.setBusinessId(uuid);
			documentTranscodingTaskService.save(documentTranscodingTask);
			programVolume.setPptUuid(uuid);
		}else{
			if(!StringUtils.equals(programVolumeItem.getUrl(), url)){
				if(StringUtils.isNotBlank(url)){
					UploadFileInfo  uploadFileInfo = UploadFileInfo.StrToBean(url);
					DocumentTranscodingTask documentTranscodingTask = new DocumentTranscodingTask();
					documentTranscodingTask.setFilepath(uploadFileInfo.getFilePath());
					documentTranscodingTask.setOriginalName(uploadFileInfo.getOldFileName());
					documentTranscodingTask.setStatus(0);
					String uuid= UUID.randomUUID().toString();
					documentTranscodingTask.setBusinessId(uuid);
					documentTranscodingTaskService.save(documentTranscodingTask);
					programVolume.setPptUuid(uuid);
				}
			}
		}
		
		programVolume.setProgramid(programid);
		programVolume.setAddtime(new Date());
		programVolumeService.save(programVolume);
		/**删除原有的视频信息*/
		List<Videos> vid = videosDao.findByProgramVolumeId(programVolume.getId());
		for(Videos video : vid){
			video.setProgramvolumeid(null);
			videosService.save(video);
		}
		/**保存视频信息*/
		Videos video = videosService.get(programVolume.getVideoId());
		if(null != video){
			video.setProgramvolumeid(programVolume.getId());
			videosService.save(video);
		}
		addMessage(redirectAttributes, "保存章节信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/programVolume/?repage";
	}
	
	/*@RequiresPermissions("zjvideo:programVolume:edit")*/
	@RequestMapping(value = "delete")
	public String delete(ProgramVolume programVolume, RedirectAttributes redirectAttributes) {
		/*删除章节，取消关联视频的关系*/
		Videos video = videosService.findByProgramVolumeId(programVolume.getId());
		if(null != video){
			video.setProgramvolumeid(null);
			videosService.save(video);
		}
		/*数据库删除章节信息*/
		programVolumeService.deleteProgramVolume(programVolume);
		addMessage(redirectAttributes, "删除章节信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/programVolume/?repage";
	}

}