/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videotranscodingtask;
import com.thinkgem.jeesite.modules.zjvideo.service.VideotranscodingtaskService;

/**
 * 视频模板中间表Controller
 * @author j.feng
 * @version 2015-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/videotemplate")
public class VideotranscodingtaskController extends BaseController {

	@Autowired
	private VideotranscodingtaskService videotemplateService;
	
	@ModelAttribute
	public Videotranscodingtask get(@RequestParam(required=false) String id) {
		Videotranscodingtask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = videotemplateService.get(id);
		}
		if (entity == null){
			entity = new Videotranscodingtask();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:videotemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(Videotranscodingtask videotemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println(request.getRemoteHost());
		System.out.println(request.getServerName());
		Page<Videotranscodingtask> page = videotemplateService.findPage(new Page<Videotranscodingtask>(request, response), videotemplate); 
		model.addAttribute("page", page);
		return "modules/zjvideo/videotemplateList";
	}

	@RequiresPermissions("zjvideo:videotemplate:view")
	@RequestMapping(value = "form")
	public String form(Videotranscodingtask videotemplate, Model model) {
		model.addAttribute("videotemplate", videotemplate);
		return "modules/zjvideo/videotemplateForm";
	}

	@RequiresPermissions("zjvideo:videotemplate:edit")
	@RequestMapping(value = "save")
	public String save(Videotranscodingtask videotemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, videotemplate)){
			return form(videotemplate, model);
		}
		videotemplateService.save(videotemplate);
		addMessage(redirectAttributes, "保存视频模板中间表成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videotemplate/?repage";
	}
	
	@RequiresPermissions("zjvideo:videotemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(Videotranscodingtask videotemplate, RedirectAttributes redirectAttributes) {
		videotemplateService.delete(videotemplate);
		addMessage(redirectAttributes, "删除视频模板中间表成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videotemplate/?repage";
	}

	/**
	 * 数据库删除转码任务
	 * @param videotemplate
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "task/delete")
	public String deleteVideotranscodingtask(Videotranscodingtask videotranscodingtask, RedirectAttributes redirectAttributes) {
		videotemplateService.deleteVideotranscodingtask(videotranscodingtask);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/transcodetask/list?repage";
	}

	/**
	 * 数据库重置转码任务
	 * @param videotemplate
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "task/reset")
	public String resetVideotranscodingtask(Videotranscodingtask videotranscodingtask, RedirectAttributes redirectAttributes) {
		videotemplateService.resetVideotranscodingtask(videotranscodingtask);
		addMessage(redirectAttributes, "重置成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/transcodetask/list?repage";
	}

	/***
	 * 刷新缓存信息。
	 * @param documentTranscodingTask
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:videotemplate:view")
	@ResponseBody
	@RequestMapping(value = {"refreshList"})
	public List<Videotranscodingtask> refreshList(HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询指定的几个
		String ids = request.getParameter("ids");
		ids = StringUtils.removeStart(ids, ",");
		List<Videotranscodingtask> lists = new ArrayList<Videotranscodingtask>();
		if(org.apache.commons.lang.StringUtils.isNotBlank(ids)){
			lists = videotemplateService.findListByIds(ids);	
		}
		return lists;
	}		
}