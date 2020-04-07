/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeServerGroup;
import com.thinkgem.jeesite.modules.zjvideo.service.NodeServerGroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.NodeServerService;

/**
 * 节点服务器组Controller
 * @author j.feng
 * @version 2015-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/nodeServerGroup")
public class NodeServerGroupController extends BaseController {
	static Logger logger =Logger.getLogger(ProgramController.class);
	
	@Autowired
	private NodeServerGroupService nodeServerGroupService;
	
	@Autowired
	private NodeServerService nodeServerService;	
	
	boolean host_flag = false;				//是否源站标识
	
	@ModelAttribute
	public NodeServerGroup get(@RequestParam(required=false) String id) {
		NodeServerGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nodeServerGroupService.get(id);
		}
		if (entity == null){
			entity = new NodeServerGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:nodeServerGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(NodeServerGroup nodeServerGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NodeServerGroup> page = nodeServerGroupService.findPage(new Page<NodeServerGroup>(request, response), nodeServerGroup); 
		model.addAttribute("page", page);
		return "modules/zjvideo/nodeServerGroupList";
	}

	@RequiresPermissions("zjvideo:nodeServerGroup:view")
	@RequestMapping(value = "form")
	public String form(NodeServerGroup nodeServerGroup, Model model) {
		List<NodeServer> nodeServerList = Lists.newArrayList();
		nodeServerList = nodeServerService.getAllNodeServer();
		//组下面的服务器列表
		List<Integer> nodeServerIds = Lists.newArrayList();	
		if(nodeServerGroup.getId() != null && nodeServerGroup.getId().length() >= 1){
			List<NodeServerGroup> nodeServerGroups= nodeServerGroupService.queryOwnNodeServerList(nodeServerGroup);
				for (NodeServerGroup nodeGroup : nodeServerGroups) {
					nodeServerIds.add(Integer.valueOf(nodeGroup.getNodeServer().getId()));
					if(StringUtils.equalsIgnoreCase(nodeGroup.getStationFlag(),"1")){
						nodeServerGroup.setStationFlag(nodeGroup.getNodeServer().getId());
					}
				}
		}
		nodeServerGroup.setNodeServierid(nodeServerIds);
		model.addAttribute("nodeServerList", nodeServerList);		
		model.addAttribute("nodeServerGroup", nodeServerGroup);
		return "modules/zjvideo/nodeServerGroupForm";
	}

	@RequiresPermissions("zjvideo:nodeServerGroup:edit")
	@RequestMapping(value = "save")
	public String save(NodeServerGroup nodeServerGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nodeServerGroup)){
			return form(nodeServerGroup, model);
		}
		logger.info("nodeServerGroup=======================" + nodeServerGroup.toString());
		//删除原有节点组关联信息
		if(nodeServerGroup.getId() != null && nodeServerGroup.getId().length() >= 1){
			nodeServerGroupService.deleteNodeGroup(nodeServerGroup);
		}
		logger.info("源站======================*********" + nodeServerGroup.getStationFlag());
		nodeServerGroupService.save(nodeServerGroup);
		String stationFlag = nodeServerGroup.getStationFlag();
		//节点组信息添加
		List<Integer> nodeServerids = nodeServerGroup.getNodeServierid();
		List<NodeServer> nodeServerList = Lists.newArrayList();
		for(Integer serverid : nodeServerids){
			NodeServer nodeServer = nodeServerService.get(String.valueOf(serverid));
			if(StringUtils.equalsIgnoreCase(String.valueOf(serverid), stationFlag)){
				host_flag = true;
				nodeServer.setStationFlag("1");//该服务器是源站服务器
			}else{
				nodeServer.setStationFlag("0");//该服务器是普通服务器
			}
			nodeServerList.add(nodeServer);
		}
		nodeServerGroup.setNodeServerList(nodeServerList);
		nodeServerGroupService.insertNodeGroup(nodeServerGroup);
		if(host_flag){
			addMessage(redirectAttributes, "保存节点服务器组成功");
		}else{
			addMessage(redirectAttributes, "保存节点服务器组成功<br>源站服务器选择错了，请您重新选择！");
		}
		return "redirect:"+Global.getAdminPath()+"/zjvideo/nodeServerGroup/?repage";
	}
	
	@RequiresPermissions("zjvideo:nodeServerGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(NodeServerGroup nodeServerGroup, RedirectAttributes redirectAttributes) {
		nodeServerGroupService.delete(nodeServerGroup);
		addMessage(redirectAttributes, "删除节点服务器组成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/nodeServerGroup/?repage";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<NodeServerGroup> list = nodeServerGroupService.findList(new NodeServerGroup());
		for (int i=0; i<list.size(); i++){
			NodeServerGroup e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("name", e.getGroupname());
				mapList.add(map);
			}
		}
		return mapList;
	}
}