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

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeGroup;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeServer;
import com.thinkgem.jeesite.modules.zjvideo.service.NodeGroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.NodeServerService;

/**
 * 节点组Controller
 * @author j.feng
 * @version 2015-07-21
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/nodeGroup")
public class NodeGroupController extends BaseController {

	@Autowired
	private NodeGroupService nodeGroupService;
	
	@ModelAttribute
	public NodeGroup get(@RequestParam(required=false) String id) {
		NodeGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nodeGroupService.get(id);
		}
		if (entity == null){
			entity = new NodeGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:nodeGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(NodeGroup nodeGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NodeGroup> page = nodeGroupService.findPage(new Page<NodeGroup>(request, response), nodeGroup); 
		model.addAttribute("page", page);
		return "modules/zjvideo/nodeGroupList";
	}

	@RequiresPermissions("zjvideo:nodeGroup:view")
	@RequestMapping(value = "form")
	public String form(NodeGroup nodeGroup, Model model) {
		model.addAttribute("nodeGroup", nodeGroup);
		return "modules/zjvideo/nodeGroupForm";
	}

	@RequiresPermissions("zjvideo:nodeGroup:edit")
	@RequestMapping(value = "save")
	public String save(NodeGroup nodeGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nodeGroup)){
			return form(nodeGroup, model);
		}
		nodeGroupService.save(nodeGroup);
		addMessage(redirectAttributes, "保存节点组成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/nodeGroup/?repage";
	}
	
	@RequiresPermissions("zjvideo:nodeGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(NodeGroup nodeGroup, RedirectAttributes redirectAttributes) {
		nodeGroupService.delete(nodeGroup);
		addMessage(redirectAttributes, "删除节点组成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/nodeGroup/?repage";
	}

}