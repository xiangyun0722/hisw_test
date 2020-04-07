/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeGroup;
import com.thinkgem.jeesite.modules.zjvideo.dao.NodeGroupDao;

/**
 * 节点组Service
 * @author j.feng
 * @version 2015-07-21
 */
@Service
@Transactional(readOnly = true)
public class NodeGroupService extends CrudService<NodeGroupDao, NodeGroup> {

	public NodeGroup get(String id) {
		return super.get(id);
	}
	
	public List<NodeGroup> findList(NodeGroup nodeGroup) {
		return super.findList(nodeGroup);
	}
	
	public Page<NodeGroup> findPage(Page<NodeGroup> page, NodeGroup nodeGroup) {
		return super.findPage(page, nodeGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(NodeGroup nodeGroup) {
		super.save(nodeGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(NodeGroup nodeGroup) {
		super.delete(nodeGroup);
	}
	
}