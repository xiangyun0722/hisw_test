/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Classroom;

/**
 * 教室DAO接口
 * @author j.feng
 * @version 2015-06-10
 */
@MyBatisDao
public interface ClassroomDao extends CrudDao<Classroom> {
	
}