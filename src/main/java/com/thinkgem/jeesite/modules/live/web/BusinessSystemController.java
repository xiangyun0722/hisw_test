/**
 * 
 */
package com.thinkgem.jeesite.modules.live.web;

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
import com.thinkgem.jeesite.modules.live.entity.BusinessSystem;
import com.thinkgem.jeesite.modules.live.service.BusinessSystemService;

/**
 * 直播业务系统Controller
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
@Controller
@RequestMapping(value = "${adminPath}/live/businessSystem")
public class BusinessSystemController extends BaseController {

	@Autowired
	private BusinessSystemService businessSystemService;
	
	@ModelAttribute
	public BusinessSystem get(@RequestParam(required=false) String id) {
		BusinessSystem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = businessSystemService.get(id);
		}
		if (entity == null){
			entity = new BusinessSystem();
		}
		return entity;
	}
	
	@RequiresPermissions("live:businessSystem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusinessSystem businessSystem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusinessSystem> page = businessSystemService.findPage(new Page<BusinessSystem>(request, response), businessSystem); 
		model.addAttribute("page", page);
		return "modules/live/businessSystemList";
	}

	@RequiresPermissions("live:businessSystem:view")
	@RequestMapping(value = "form")
	public String form(BusinessSystem businessSystem, Model model) {
		model.addAttribute("businessSystem", businessSystem);
		return "modules/live/businessSystemForm";
	}
	
	@RequiresPermissions("live:businessSystem:view")
	@RequestMapping(value = "view")
	public String view(BusinessSystem businessSystem, Model model) {
		model.addAttribute("businessSystem", businessSystem);
		return "modules/live/businessSystemView";
	}
	
	@RequiresPermissions("live:businessSystem:edit")
	@RequestMapping(value = "save")
	public String save(BusinessSystem businessSystem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, businessSystem)){
			return form(businessSystem, model);
		}
		businessSystemService.save(businessSystem);
		addMessage(redirectAttributes, "保存直播业务系统成功");
		return "redirect:"+Global.getAdminPath()+"/live/businessSystem/?repage";
	}
	
	@RequiresPermissions("live:businessSystem:edit")
	@RequestMapping(value = "delete")
	public String delete(BusinessSystem businessSystem, RedirectAttributes redirectAttributes) {
		businessSystemService.delete(businessSystem);
		addMessage(redirectAttributes, "删除直播业务系统成功");
		return "redirect:"+Global.getAdminPath()+"/live/businessSystem/?repage";
	}

}