/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Program;

/**
 * 课程管理DAO接口
 * @author j.feng
 * @version 2015-05-06
 */
@MyBatisDao
public interface ProgramDao extends CrudDao<Program> {
	/**
	 * 查询用户可观看视频权限列表
	 * @param program
	 * @return
	 */
	List<Program> findOwnGroupList(Program program);
	
	/**
	 * 数据库删除课程信息
	 * @param program
	 */
	void deleteProgram(Program program);
}