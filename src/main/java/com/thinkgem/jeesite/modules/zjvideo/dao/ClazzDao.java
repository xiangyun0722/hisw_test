/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Clazz;

/**
 * 二级分类DAO接口
 * @author j.feng
 * @version 2015-08-06
 */
@MyBatisDao
public interface ClazzDao extends TreeDao<Clazz> {
	
}