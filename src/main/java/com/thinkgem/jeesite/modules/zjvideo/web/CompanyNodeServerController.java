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
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.zjvideo.entity.CompanyNodeServer;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeServer;
import com.thinkgem.jeesite.modules.zjvideo.service.CompanyNodeServerService;
import com.thinkgem.jeesite.modules.zjvideo.service.NodeServerService;

/**
 * 单位节点Controller
 * @author j.feng
 * @version 2015-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/companyNodeServer")
public class CompanyNodeServerController extends BaseController {

	@Autowired
	private CompanyNodeServerService companyNodeServerService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private NodeServerService nodeServerService;	
	
	@ModelAttribute
	public CompanyNodeServer get(@RequestParam(required=false) String id) {
		CompanyNodeServer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = companyNodeServerService.get(id);
		}
		if (entity == null){
			entity = new CompanyNodeServer();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:companyNodeServer:view")
	@RequestMapping(value = {"list", ""})
	public String list(CompanyNodeServer companyNodeServer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CompanyNodeServer> page = companyNodeServerService.findPage(new Page<CompanyNodeServer>(request, response), companyNodeServer); 
		model.addAttribute("page", page);
		return "modules/zjvideo/companyNodeServerList";
	}

	@RequiresPermissions("zjvideo:companyNodeServer:view")
	@RequestMapping(value = "form")
	public String form(CompanyNodeServer companyNodeServer, Model model) {
		if(StringUtils.isNoneBlank(companyNodeServer.getId())){
			//单位，服务器名称前台显示
			Office office = officeService.get(companyNodeServer.getCompanyid());
			companyNodeServer.setOffice(office);
			NodeServer nodeServer = nodeServerService.get(companyNodeServer.getNodeserverid());
			companyNodeServer.setNodeServer(nodeServer);
		}
		model.addAttribute("companyNodeServer", companyNodeServer);
		return "modules/zjvideo/companyNodeServerForm";
	}

	@RequiresPermissions("zjvideo:companyNodeServer:edit")
	@RequestMapping(value = "save")
	public String save(CompanyNodeServer companyNodeServer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, companyNodeServer)){
			return form(companyNodeServer, model);
		}
		companyNodeServerService.save(companyNodeServer);
		addMessage(redirectAttributes, "保存单位节点成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/companyNodeServer/?repage";
	}
	
	@RequiresPermissions("zjvideo:companyNodeServer:edit")
	@RequestMapping(value = "delete")
	public String delete(CompanyNodeServer companyNodeServer, RedirectAttributes redirectAttributes) {
		companyNodeServerService.delete(companyNodeServer);
		addMessage(redirectAttributes, "删除单位节点成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/companyNodeServer/?repage";
	}

}