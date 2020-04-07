/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.SoftUpdate;

/**
 * 软件更新DAO接口
 * @author j.feng
 * @version 2015-05-05
 */
@MyBatisDao
public interface SoftUpdateDao extends CrudDao<SoftUpdate> {
	
}