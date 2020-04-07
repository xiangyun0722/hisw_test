/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.TeacherGroup;

/**
 * 教师群组DAO接口
 * @author j.feng
 * @version 2015-05-18
 */
@MyBatisDao
public interface TeacherGroupDao extends CrudDao<TeacherGroup> {
	/**
	 * 根据教师ID查询教师组信息
	 * @return
	 */
	List<TeacherGroup> findByTeacherid(@Param("teacherid") String teacherid);
	
	/**
	 * 数据库删除教师群组信息
	 * @param program
	 */
	void deleteTeacherGroup(TeacherGroup teacherGroup);		
}