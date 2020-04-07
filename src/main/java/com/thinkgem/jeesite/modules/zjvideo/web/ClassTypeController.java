/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.zjvideo.entity.ClassType;
import com.thinkgem.jeesite.modules.zjvideo.service.ClassTypeService;

/**
 * 一级分类Controller
 * @author j.feng
 * @version 2015-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/classType")
public class ClassTypeController extends BaseController {

	@Autowired
	private ClassTypeService classTypeService;
	
	@ModelAttribute
	public ClassType get(@RequestParam(required=false) String id) {
		ClassType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = classTypeService.get(id);
		}
		if (entity == null){
			entity = new ClassType();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:classType:view")
	@RequestMapping(value = {"list", ""})
	public String list(ClassType classType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ClassType> page = classTypeService.findPage(new Page<ClassType>(request, response), classType); 
		model.addAttribute("page", page);
		return "modules/zjvideo/classTypeList";
	}

	@RequiresPermissions("zjvideo:classType:view")
	@RequestMapping(value = "form")
	public String form(ClassType classType, Model model) {
		model.addAttribute("classType", classType);
		return "modules/zjvideo/classTypeForm";
	}

	@RequiresPermissions("zjvideo:classType:edit")
	@RequestMapping(value = "save")
	public String save(ClassType classType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, classType)){
			return form(classType, model);
		}
		classType.setEdittime(new Date());
		classTypeService.save(classType);
		addMessage(redirectAttributes, "保存一级分类成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/classType/?repage";
	}
	
	@RequiresPermissions("zjvideo:classType:edit")
	@RequestMapping(value = "delete")
	public String delete(ClassType classType, RedirectAttributes redirectAttributes) {
		classTypeService.delete(classType);
		addMessage(redirectAttributes, "删除一级分类成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/classType/?repage";
	}

}