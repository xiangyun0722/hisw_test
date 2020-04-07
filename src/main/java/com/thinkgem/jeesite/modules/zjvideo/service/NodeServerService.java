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
import com.thinkgem.jeesite.modules.zjvideo.dao.NodeServerDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeServer;

/**
 * 节点服务器Service
 * @author j.feng
 * @version 2015-07-16
 */
@Service
@Transactional(readOnly = true)
public class NodeServerService extends CrudService<NodeServerDao, NodeServer> {

	@Autowired
	private NodeServerDao nodeServerDao;
	
	public NodeServer get(String id) {
		return super.get(id);
	}
	
	public List<NodeServer> findList(NodeServer nodeServer) {
		return super.findList(nodeServer);
	}
	
	public Page<NodeServer> findPage(Page<NodeServer> page, NodeServer nodeServer) {
		return super.findPage(page, nodeServer);
	}
	
	@Transactional(readOnly = false)
	public void save(NodeServer nodeServer) {
		super.save(nodeServer);
	}
	
	@Transactional(readOnly = false)
	public void delete(NodeServer nodeServer) {
		super.delete(nodeServer);
	}
	/**
	 * 查询所有节点服务器
	 * @return
	 */
	public List<NodeServer> getAllNodeServer(){	
		return nodeServerDao.getAllNodeServer();
	}	
}