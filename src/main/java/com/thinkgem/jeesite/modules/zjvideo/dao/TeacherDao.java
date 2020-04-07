/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Teacher;

/**
 * 教师信息DAO接口
 * @author j.feng
 * @version 2015-05-04
 */
@MyBatisDao
public interface TeacherDao extends CrudDao<Teacher> {
	/**
	 * 查询主讲人列表信息
	 * @return
	 */
	List<Teacher> queryTeacherList();
}