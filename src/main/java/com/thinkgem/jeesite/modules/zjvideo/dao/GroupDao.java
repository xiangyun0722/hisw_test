/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Group;

/**
 * 组管理DAO接口
 * @author j.feng
 * @version 2015-05-04
 */
@MyBatisDao
public interface GroupDao extends CrudDao<Group> {
	/**
	 * 查询组成员列表信息
	 * @return
	 */
	List<Group> queryGroupList();
	
	/**
	 * 根据组名查询组成员列表信息
	 * @return
	 */
	List<Group> findByGroupName(@Param("groupname") String groupname);
}