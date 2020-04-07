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
import com.thinkgem.jeesite.modules.zjvideo.entity.Group;
import com.thinkgem.jeesite.modules.zjvideo.service.GroupService;

/**
 * 组管理Controller
 * @author j.feng
 * @version 2015-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/group")
public class GroupController extends BaseController {

	@Autowired
	private GroupService groupService;
	
	@ModelAttribute
	public Group get(@RequestParam(required=false) String id) {
		Group entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = groupService.get(id);
		}
		if (entity == null){
			entity = new Group();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:group:view")
	@RequestMapping(value = {"list", ""})
	public String list(Group group, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Group> page = groupService.findPage(new Page<Group>(request, response), group); 
		model.addAttribute("page", page);
		return "modules/zjvideo/groupList";
	}

	@RequiresPermissions("zjvideo:group:view")
	@RequestMapping(value = "form")
	public String form(Group group, Model model) {
		model.addAttribute("group", group);
		return "modules/zjvideo/groupForm";
	}

	@RequiresPermissions("zjvideo:group:edit")
	@RequestMapping(value = "save")
	public String save(Group group, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, group)){
			return form(group, model);
		}
		groupService.save(group);
		addMessage(redirectAttributes, "保存组管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/group/?repage";
	}
	
	@RequiresPermissions("zjvideo:group:edit")
	@RequestMapping(value = "delete")
	public String delete(Group group, RedirectAttributes redirectAttributes) {
		groupService.delete(group);
		addMessage(redirectAttributes, "删除组管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/group/?repage";
	}

}