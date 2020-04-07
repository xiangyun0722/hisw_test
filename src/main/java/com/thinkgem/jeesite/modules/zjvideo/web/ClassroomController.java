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
import com.thinkgem.jeesite.modules.zjvideo.entity.Classroom;
import com.thinkgem.jeesite.modules.zjvideo.service.ClassroomService;

/**
 * 教室Controller
 * @author j.feng
 * @version 2015-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/classroom")
public class ClassroomController extends BaseController {

	@Autowired
	private ClassroomService classroomService;
	
	@ModelAttribute
	public Classroom get(@RequestParam(required=false) String id) {
		Classroom entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = classroomService.get(id);
		}
		if (entity == null){
			entity = new Classroom();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:classroom:view")
	@RequestMapping(value = {"list", ""})
	public String list(Classroom classroom, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Classroom> page = classroomService.findPage(new Page<Classroom>(request, response), classroom); 
		model.addAttribute("page", page);
		return "modules/zjvideo/classroomList";
	}

	@RequiresPermissions("zjvideo:classroom:view")
	@RequestMapping(value = "form")
	public String form(Classroom classroom, Model model) {
		model.addAttribute("classroom", classroom);
		return "modules/zjvideo/classroomForm";
	}

	@RequiresPermissions("zjvideo:classroom:edit")
	@RequestMapping(value = "save")
	public String save(Classroom classroom, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, classroom)){
			return form(classroom, model);
		}
		classroomService.save(classroom);
		addMessage(redirectAttributes, "保存教室成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/classroom/?repage";
	}
	
	@RequiresPermissions("zjvideo:classroom:edit")
	@RequestMapping(value = "delete")
	public String delete(Classroom classroom, RedirectAttributes redirectAttributes) {
		classroomService.delete(classroom);
		addMessage(redirectAttributes, "删除教室成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/classroom/?repage";
	}

}