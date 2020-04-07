/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeServer;

/**
 * 节点服务器DAO接口
 * @author j.feng
 * @version 2015-07-16
 */
@MyBatisDao
public interface NodeServerDao extends CrudDao<NodeServer> {
	/**
	 * 查询所有节点服务器
	 * @return
	 */
	List<NodeServer> getAllNodeServer();	
}