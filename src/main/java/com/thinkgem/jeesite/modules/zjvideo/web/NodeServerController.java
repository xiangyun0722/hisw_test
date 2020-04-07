/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeServer;
import com.thinkgem.jeesite.modules.zjvideo.service.NodeServerGroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.NodeServerService;

/**
 * 节点服务器Controller
 * @author j.feng
 * @version 2015-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/nodeServer")
public class NodeServerController extends BaseController {

	@Autowired
	private NodeServerService nodeServerService;
	
	@Autowired
	private NodeServerGroupService nodeServerGroupService;
	
	@ModelAttribute
	public NodeServer get(@RequestParam(required=false) String id) {
		NodeServer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nodeServerService.get(id);
		}
		if (entity == null){
			entity = new NodeServer();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:nodeServer:view")
	@RequestMapping(value = {"list", ""})
	public String list(NodeServer nodeServer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NodeServer> page = nodeServerService.findPage(new Page<NodeServer>(request, response), nodeServer); 
		model.addAttribute("page", page);
		return "modules/zjvideo/nodeServerList";
	}

	@RequiresPermissions("zjvideo:nodeServer:view")
	@RequestMapping(value = "form")
	public String form(NodeServer nodeServer, Model model) {
		model.addAttribute("nodeServer", nodeServer);
		return "modules/zjvideo/nodeServerForm";
	}

	@RequiresPermissions("zjvideo:nodeServer:edit")
	@RequestMapping(value = "save")
	public String save(NodeServer nodeServer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nodeServer)){
			return form(nodeServer, model);
		}
		nodeServerService.save(nodeServer);
		addMessage(redirectAttributes, "保存节点服务器成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/nodeServer/?repage";
	}
	
	@RequiresPermissions("zjvideo:nodeServer:edit")
	@RequestMapping(value = "delete")
	public String delete(NodeServer nodeServer, RedirectAttributes redirectAttributes) {
		nodeServerService.delete(nodeServer);
		addMessage(redirectAttributes, "删除节点服务器成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/nodeServer/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<NodeServer > list = nodeServerService.findList(new NodeServer ());
		for (int i=0; i<list.size(); i++){
			NodeServer  e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}	
}