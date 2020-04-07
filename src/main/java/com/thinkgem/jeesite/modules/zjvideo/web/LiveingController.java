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
import com.thinkgem.jeesite.modules.zjvideo.entity.Liveing;
import com.thinkgem.jeesite.modules.zjvideo.service.LiveingService;

/**
 * 频道管理Controller
 * @author j.feng
 * @version 2015-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/liveing")
public class LiveingController extends BaseController {

	@Autowired
	private LiveingService liveingService;
	
	@ModelAttribute
	public Liveing get(@RequestParam(required=false) String id) {
		Liveing entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = liveingService.get(id);
		}
		if (entity == null){
			entity = new Liveing();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:liveing:view")
	@RequestMapping(value = {"list", ""})
	public String list(Liveing liveing, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Liveing> page = liveingService.findPage(new Page<Liveing>(request, response), liveing); 
		model.addAttribute("page", page);
		return "modules/zjvideo/liveingList";
	}

	@RequiresPermissions("zjvideo:liveing:view")
	@RequestMapping(value = "form")
	public String form(Liveing liveing, Model model) {
		model.addAttribute("liveing", liveing);
		return "modules/zjvideo/liveingForm";
	}

	@RequiresPermissions("zjvideo:liveing:edit")
	@RequestMapping(value = "save")
	public String save(Liveing liveing, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, liveing)){
			return form(liveing, model);
		}
		liveingService.save(liveing);
		addMessage(redirectAttributes, "保存频道管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/liveing/?repage";
	}
	
	@RequiresPermissions("zjvideo:liveing:edit")
	@RequestMapping(value = "delete")
	public String delete(Liveing liveing, RedirectAttributes redirectAttributes) {
		liveingService.delete(liveing);
		addMessage(redirectAttributes, "删除频道管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/liveing/?repage";
	}

}