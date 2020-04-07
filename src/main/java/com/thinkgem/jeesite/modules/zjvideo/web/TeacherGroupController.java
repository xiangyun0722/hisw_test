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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.TeacherGroup;
import com.thinkgem.jeesite.modules.zjvideo.service.GroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.TeacherGroupService;

/**
 * 教师群组Controller
 * @author j.feng
 * @version 2015-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/teacherGroup")
public class TeacherGroupController extends BaseController {

	@Autowired
	private TeacherGroupService teacherGroupService;
	
	@Autowired
	private GroupService groupService;		
	
	@ModelAttribute
	public TeacherGroup get(@RequestParam(required=false) String id) {
		TeacherGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = teacherGroupService.get(id);
		}
		if (entity == null){
			entity = new TeacherGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:teacherGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(TeacherGroup teacherGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeacherGroup> page = teacherGroupService.findPage(new Page<TeacherGroup>(request, response), teacherGroup); 
		model.addAttribute("page", page);
		return "modules/zjvideo/teacherGroupList";
	}

	@RequiresPermissions("zjvideo:teacherGroup:view")
	@RequestMapping(value = "form")
	public String form(TeacherGroup teacherGroup, Model model) {
		model.addAttribute("teacherGroup", teacherGroup);
		return "modules/zjvideo/teacherGroupForm";
	}

	@RequiresPermissions("zjvideo:teacherGroup:edit")
	@RequestMapping(value = "save")
	public String save(TeacherGroup teacherGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, teacherGroup)){
			return form(teacherGroup, model);
		}
		teacherGroupService.save(teacherGroup);
		addMessage(redirectAttributes, "保存教师群组成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/teacherGroup/?repage";
	}
	
	@RequiresPermissions("zjvideo:teacherGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(TeacherGroup teacherGroup, RedirectAttributes redirectAttributes) {
		teacherGroupService.delete(teacherGroup);
		addMessage(redirectAttributes, "删除教师群组成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/teacherGroup/?repage";
	}
}