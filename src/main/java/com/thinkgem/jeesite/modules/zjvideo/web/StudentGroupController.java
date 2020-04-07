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
import com.thinkgem.jeesite.modules.zjvideo.entity.StudentGroup;
import com.thinkgem.jeesite.modules.zjvideo.service.StudentGroupService;

/**
 * 用户群组Controller
 * @author j.feng
 * @version 2015-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/studentGroup")
public class StudentGroupController extends BaseController {

	@Autowired
	private StudentGroupService studentGroupService;
	
	@ModelAttribute
	public StudentGroup get(@RequestParam(required=false) String id) {
		StudentGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentGroupService.get(id);
		}
		if (entity == null){
			entity = new StudentGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:studentGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(StudentGroup studentGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StudentGroup> page = studentGroupService.findPage(new Page<StudentGroup>(request, response), studentGroup); 
		model.addAttribute("page", page);
		return "modules/zjvideo/studentGroupList";
	}

	@RequiresPermissions("zjvideo:studentGroup:view")
	@RequestMapping(value = "form")
	public String form(StudentGroup studentGroup, Model model) {
		model.addAttribute("studentGroup", studentGroup);
		return "modules/zjvideo/studentGroupForm";
	}

	@RequiresPermissions("zjvideo:studentGroup:edit")
	@RequestMapping(value = "save")
	public String save(StudentGroup studentGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studentGroup)){
			return form(studentGroup, model);
		}
		studentGroupService.save(studentGroup);
		addMessage(redirectAttributes, "保存用户群组成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/studentGroup/?repage";
	}
	
	@RequiresPermissions("zjvideo:studentGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(StudentGroup studentGroup, RedirectAttributes redirectAttributes) {
		studentGroupService.delete(studentGroup);
		addMessage(redirectAttributes, "删除用户群组成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/studentGroup/?repage";
	}

}