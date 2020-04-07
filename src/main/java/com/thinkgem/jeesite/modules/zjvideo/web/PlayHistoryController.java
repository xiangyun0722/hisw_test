/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

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
import com.thinkgem.jeesite.modules.zjvideo.entity.PlayHistory;
import com.thinkgem.jeesite.modules.zjvideo.service.CollectionsService;
import com.thinkgem.jeesite.modules.zjvideo.service.PlayHistoryService;

/**
 * 播放历史信息Controller
 * @author j.feng
 * @version 2015-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/playHistory")
public class PlayHistoryController extends BaseController {

	@Autowired
	private PlayHistoryService playHistoryService;
	
	@Autowired
	private CollectionsService collectionService;
	
	@ModelAttribute
	public PlayHistory get(@RequestParam(required=false) String id) {
		PlayHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = playHistoryService.get(id);
		}
		if (entity == null){
			entity = new PlayHistory();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:playHistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlayHistory playHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlayHistory> page = playHistoryService.findPage(new Page<PlayHistory>(request, response), playHistory); 
		model.addAttribute("page", page);
		return "modules/zjvideo/playHistoryList";
	}

	@RequiresPermissions("zjvideo:playHistory:view")
	@RequestMapping(value = "/statics/history")
	public String staticsList(PlayHistory playHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlayHistory> page = playHistoryService.findStatisticsPlayHistoryPage(new Page<PlayHistory>(request, response), playHistory); 
		model.addAttribute("page", page);
		return "modules/zjvideo/playStaticsHistoryList";
	}
	
	@RequiresPermissions("zjvideo:playHistory:view")
	@RequestMapping(value = "/statics/student/history")
	public String staticsStudentHistoryList(PlayHistory playHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(PlayHistory.collectionTypeTeacher == playHistory.getStaticsflag()){
			return "redirect:" + adminPath + "/zjvideo/collection/statics/studnet/collection";
		}
		Page<PlayHistory> page = playHistoryService.findStatisticsStudentPlayHistoryPage(new Page<PlayHistory>(request, response), playHistory); 
		model.addAttribute("page", page);
		return "modules/zjvideo/StaticsStudentHistoryList";
	}
	
	@RequiresPermissions("zjvideo:playHistory:view")
	@RequestMapping(value = "/statics/teacher/history")
	public String staticsTeacherHistoryList(PlayHistory playHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(PlayHistory.collectionTypeTeacher == playHistory.getStaticsflag()){
			return "redirect:" + adminPath + "/zjvideo/collection/statics/teacher/collection";
		}		
		Page<PlayHistory> page = playHistoryService.findStatisticsTeacherPlayHistoryPage(new Page<PlayHistory>(request, response), playHistory); 
		model.addAttribute("page", page);
		return "modules/zjvideo/StaticsTeacherHistoryList";
	}
		
	
	@RequiresPermissions("zjvideo:playHistory:view")
	@RequestMapping(value = "form")
	public String form(PlayHistory playHistory, Model model) {
		model.addAttribute("playHistory", playHistory);
		return "modules/zjvideo/playHistoryForm";
	}

	@RequiresPermissions("zjvideo:playHistory:edit")
	@RequestMapping(value = "save")
	public String save(PlayHistory playHistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, playHistory)){
			return form(playHistory, model);
		}
		playHistoryService.save(playHistory);
		addMessage(redirectAttributes, "保存播放历史信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/playHistory/?repage";
	}
	
	@RequiresPermissions("zjvideo:playHistory:edit")
	@RequestMapping(value = "delete")
	public String delete(PlayHistory playHistory, RedirectAttributes redirectAttributes) {
		playHistoryService.delete(playHistory);
		addMessage(redirectAttributes, "删除播放历史信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/playHistory/?repage";
	}

}