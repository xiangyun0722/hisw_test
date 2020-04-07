/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeServerGroup;

/**
 * 节点服务器组DAO接口
 * @author j.feng
 * @version 2015-07-16
 */
@MyBatisDao
public interface NodeServerGroupDao extends CrudDao<NodeServerGroup> {
	/**
	 * 删除节点组关联数据
	 * @param nodeServerGroup
	 * @return
	 */
	int deleteNodeGroup(NodeServerGroup nodeServerGroup);
	
	/**
	 * 插入节点组关联数据
	 * @param nodeServerGroup
	 * @return
	 */
	int insertNodeGroup(NodeServerGroup nodeServerGroup);	
	
	/**
	 * 查询组下面的服务器列表
	 * @param program
	 * @return
	 */
	List<NodeServerGroup> queryOwnNodeServerList(NodeServerGroup nodeServerGroup);		
}