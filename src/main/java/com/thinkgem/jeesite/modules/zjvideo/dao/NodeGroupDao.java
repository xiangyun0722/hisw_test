/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.NodeGroup;

/**
 * 节点组DAO接口
 * @author j.feng
 * @version 2015-07-21
 */
@MyBatisDao
public interface NodeGroupDao extends CrudDao<NodeGroup> {
	
}