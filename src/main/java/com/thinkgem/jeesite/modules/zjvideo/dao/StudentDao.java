/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Student;

/**
 * 学生信息DAO接口
 * @author j.feng
 * @version 2015-05-04
 */
@MyBatisDao
public interface StudentDao extends CrudDao<Student> {
	
}