/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.ProgramGroup;

/**
 * 课程组权限DAO接口
 * @author j.feng
 * @version 2015-05-08
 */
@MyBatisDao
public interface ProgramGroupDao extends CrudDao<ProgramGroup> {
	/**
	 * 查询权限表中是否已经存在记录
	 * @param groupId
	 * @param programId
	 * @return
	 */
	List<ProgramGroup> queryByGroupIdProgramId(@Param("groupId")int groupId, @Param("programId")int programId);
	
	/**
	 * 数据库删除权限组信息
	 * @param programGroup
	 */
	void deleteProgramGroup(ProgramGroup programGroup);
}