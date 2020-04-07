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
import com.thinkgem.jeesite.modules.zjvideo.entity.Server;
import com.thinkgem.jeesite.modules.zjvideo.service.ServerService;

/**
 * 视频服务器管理Controller
 * @author j.feng
 * @version 2015-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/server")
public class ServerController extends BaseController {

	@Autowired
	private ServerService serverService;
	
	@ModelAttribute
	public Server get(@RequestParam(required=false) String id) {
		Server entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = serverService.get(id);
		}
		if (entity == null){
			entity = new Server();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:server:view")
	@RequestMapping(value = {"list", ""})
	public String list(Server server, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Server> page = serverService.findPage(new Page<Server>(request, response), server); 
		model.addAttribute("page", page);
		return "modules/zjvideo/serverList";
	}

	@RequiresPermissions("zjvideo:server:view")
	@RequestMapping(value = "form")
	public String form(Server server, Model model) {
		model.addAttribute("server", server);
		return "modules/zjvideo/serverForm";
	}

	@RequiresPermissions("zjvideo:server:edit")
	@RequestMapping(value = "save")
	public String save(Server server, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, server)){
			return form(server, model);
		}
		serverService.save(server);
		addMessage(redirectAttributes, "保存视频服务器管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/server/?repage";
	}
	
	@RequiresPermissions("zjvideo:server:edit")
	@RequestMapping(value = "delete")
	public String delete(Server server, RedirectAttributes redirectAttributes) {
		serverService.delete(server);
		addMessage(redirectAttributes, "删除视频服务器管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/server/?repage";
	}

}