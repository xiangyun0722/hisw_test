/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.StudentGroup;

/**
 * 用户群组DAO接口
 * @author j.feng
 * @version 2015-05-15
 */
@MyBatisDao
public interface StudentGroupDao extends CrudDao<StudentGroup> {
	/**
	 * 根据用户ID查询用户组信息
	 * @return
	 */
	List<StudentGroup> findByUserid(@Param("userid") String userid);
	
	/**
	 * 数据库删除用户群组信息
	 * @param program
	 */
	void deleteStudentGroup(StudentGroup studentGroup);	
}