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
import com.thinkgem.jeesite.modules.zjvideo.entity.SoftUpdate;
import com.thinkgem.jeesite.modules.zjvideo.service.SoftUpdateService;

/**
 * 软件更新Controller
 * @author j.feng
 * @version 2015-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/softUpdate")
public class SoftUpdateController extends BaseController {

	@Autowired
	private SoftUpdateService softUpdateService;
	
	@ModelAttribute
	public SoftUpdate get(@RequestParam(required=false) String id) {
		SoftUpdate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = softUpdateService.get(id);
		}
		if (entity == null){
			entity = new SoftUpdate();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:softUpdate:view")
	@RequestMapping(value = {"list", ""})
	public String list(SoftUpdate softUpdate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SoftUpdate> page = softUpdateService.findPage(new Page<SoftUpdate>(request, response), softUpdate); 
		model.addAttribute("page", page);
		return "modules/zjvideo/softUpdateList";
	}

	@RequiresPermissions("zjvideo:softUpdate:view")
	@RequestMapping(value = "form")
	public String form(SoftUpdate softUpdate, Model model) {
		model.addAttribute("softUpdate", softUpdate);
		return "modules/zjvideo/softUpdateForm";
	}

	@RequiresPermissions("zjvideo:softUpdate:edit")
	@RequestMapping(value = "save")
	public String save(SoftUpdate softUpdate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, softUpdate)){
			return form(softUpdate, model);
		}
		softUpdateService.save(softUpdate);
		addMessage(redirectAttributes, "保存软件更新成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/softUpdate/?repage";
	}
	
	@RequiresPermissions("zjvideo:softUpdate:edit")
	@RequestMapping(value = "delete")
	public String delete(SoftUpdate softUpdate, RedirectAttributes redirectAttributes) {
		softUpdateService.delete(softUpdate);
		addMessage(redirectAttributes, "删除软件更新成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/softUpdate/?repage";
	}

}