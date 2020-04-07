/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Config;
import com.thinkgem.jeesite.modules.zjvideo.entity.Liveing;
import com.thinkgem.jeesite.modules.zjvideo.service.ConfigService;
import com.thinkgem.jeesite.modules.zjvideo.service.LiveingService;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;

/**
 * 配置管理Controller
 * @author j.feng
 * @version 2015-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/config")
public class ConfigController extends BaseController {

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private LiveingService liveingService;	
	
	@ModelAttribute
	public Config get(@RequestParam(required=false) String id) {
		Config entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = configService.get(id);
		}
		if (entity == null){
			entity = new Config();
		}
		return entity;
	}
	/**
	 * 首页配置
	 * @param config
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:config:view")
	@RequestMapping(value = "config/portal")
	public String portalList(Config config, HttpServletRequest request, HttpServletResponse response, Model model) {
		config.setConfigkey(ConstantUtils.MARQUEE_MESSAGE);
		Page<Config> page = configService.findPortalPage(new Page<Config>(request, response), config); 
		model.addAttribute("page", page);
		return "modules/zjvideo/configPortalList";
	}

	/**
	 * 机顶盒首页直播配置
	 * @param config
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:config:view")
	@RequestMapping(value = "config/living")
	public String livingList(Config config, HttpServletRequest request, HttpServletResponse response, Model model) {
		config.setConfigkey(ConstantUtils.INDEX_PLAY_MOVIES);
		Page<Config> page = configService.findPage(new Page<Config>(request, response), config); 
		model.addAttribute("page", page);
		return "modules/zjvideo/configLivingList";
	}
	
	@RequiresPermissions("zjvideo:config:view")
	@RequestMapping(value = {"list", ""})
	public String list(Config config, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Config> page = configService.findPage(new Page<Config>(request, response), config); 
		model.addAttribute("page", page);
		return "modules/zjvideo/configList";
	}
	
	@RequiresPermissions("zjvideo:config:view")
	@RequestMapping(value = "form")
	public String form(Config config, Model model) {
		model.addAttribute("config", config);
		return "modules/zjvideo/configForm";
	}
	
	/**
	 * 首页配置form
	 * @param config
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:config:edit")
	@RequestMapping(value = "portal/form")
	public String portalForm(Config config, Model model) {
		model.addAttribute("config", config);
		return "modules/zjvideo/configPortalForm";
	}
	
	@RequiresPermissions("zjvideo:config:edit")
	@RequestMapping(value = "living/form")
	public String livingForm(Config config, Model model) {
		List<Liveing> living = liveingService.getAllLiveing();
		model.addAttribute("config", config);
		model.addAttribute("livings", living);
		return "modules/zjvideo/configLivingForm";
	}	

	@RequiresPermissions("zjvideo:config:edit")
	@RequestMapping(value = "save")
	public String save(Config config, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, config)){
			return form(config, model);
		}
		configService.save(config);
		addMessage(redirectAttributes, "保存配置成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/config/?repage";
	}
	
	/**
	 * 首页配置保存
	 * @param config
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("zjvideo:config:edit")
	@RequestMapping(value = "portal/save")
	public String savePortal(Config config, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, config)){
			return form(config, model);
		}
		configService.save(config);
		addMessage(redirectAttributes, "保存配置成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/config/config/portal?repage";
	}
	
	/**
	 * 首页直播保存信息
	 * @param config
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("zjvideo:config:edit")
	@RequestMapping(value = "living/save")
	public String saveLiving(Config config, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, config)){
			return form(config, model);
		}
		configService.save(config);
		addMessage(redirectAttributes, "保存配置成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/config/config/living?repage";
	}
	
	@RequiresPermissions("zjvideo:config:edit")
	@RequestMapping(value = "delete")
	public String delete(Config config, RedirectAttributes redirectAttributes) {
		configService.delete(config);
		addMessage(redirectAttributes, "删除配置管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/config/?repage";
	}
}