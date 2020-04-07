/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.ClassType;

/**
 * 一级分类DAO接口
 * @author j.feng
 * @version 2015-05-04
 */
@MyBatisDao
public interface ClassTypeDao extends CrudDao<ClassType> {
	/**
	 * 查询所有一级分类信息
	 * @return
	 */
	List<ClassType> getAllClzzType();
}