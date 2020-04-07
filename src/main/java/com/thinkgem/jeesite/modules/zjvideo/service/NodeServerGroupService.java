/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.dao.NodeServerGroupDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeServerGroup;

/**
 * 节点服务器组Service
 * @author j.feng
 * @version 2015-07-16
 */
@Service
@Transactional(readOnly = true)
public class NodeServerGroupService extends CrudService<NodeServerGroupDao, NodeServerGroup> {
	@Autowired
	private NodeServerGroupDao nodeServerGroupDao;
	
	public NodeServerGroup get(String id) {
		return super.get(id);
	}
	
	public List<NodeServerGroup> findList(NodeServerGroup nodeServerGroup) {
		return super.findList(nodeServerGroup);
	}
	
	public Page<NodeServerGroup> findPage(Page<NodeServerGroup> page, NodeServerGroup nodeServerGroup) {
		return super.findPage(page, nodeServerGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(NodeServerGroup nodeServerGroup) {
		super.save(nodeServerGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(NodeServerGroup nodeServerGroup) {
		super.delete(nodeServerGroup);
	}
	
	/**
	 * 删除节点组关联数据
	 * @param nodeServerGroup
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteNodeGroup(NodeServerGroup nodeServerGroup){
		return nodeServerGroupDao.deleteNodeGroup(nodeServerGroup);
	}
	
	/**
	 * 插入节点组关联数据
	 * @param nodeServerGroup
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insertNodeGroup(NodeServerGroup nodeServerGroup){
		return nodeServerGroupDao.insertNodeGroup(nodeServerGroup);
	}
	
	/**
	 * 查询组下面的服务器列表
	 * @param program
	 * @return
	 */
	public List<NodeServerGroup> queryOwnNodeServerList(NodeServerGroup nodeServerGroup){
		return nodeServerGroupDao.queryOwnNodeServerList(nodeServerGroup);
	}	
}