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
import com.thinkgem.jeesite.modules.zjvideo.entity.ProgramGroup;
import com.thinkgem.jeesite.modules.zjvideo.service.ProgramGroupService;

/**
 * 课程组权限Controller
 * @author j.feng
 * @version 2015-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/programGroup")
public class ProgramGroupController extends BaseController {

	@Autowired
	private ProgramGroupService programGroupService;
	
	@ModelAttribute
	public ProgramGroup get(@RequestParam(required=false) String id) {
		ProgramGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = programGroupService.get(id);
		}
		if (entity == null){
			entity = new ProgramGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:programGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProgramGroup programGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProgramGroup> page = programGroupService.findPage(new Page<ProgramGroup>(request, response), programGroup); 
		model.addAttribute("page", page);
		return "modules/zjvideo/programGroupList";
	}

	@RequiresPermissions("zjvideo:programGroup:view")
	@RequestMapping(value = "form")
	public String form(ProgramGroup programGroup, Model model) {
		model.addAttribute("programGroup", programGroup);
		return "modules/zjvideo/programGroupForm";
	}

	@RequiresPermissions("zjvideo:programGroup:edit")
	@RequestMapping(value = "save")
	public String save(ProgramGroup programGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, programGroup)){
			return form(programGroup, model);
		}
		programGroupService.save(programGroup);
		addMessage(redirectAttributes, "保存课程组权限成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/programGroup/?repage";
	}
	
	@RequiresPermissions("zjvideo:programGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(ProgramGroup programGroup, RedirectAttributes redirectAttributes) {
		programGroupService.delete(programGroup);
		addMessage(redirectAttributes, "删除课程组权限成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/programGroup/?repage";
	}

}