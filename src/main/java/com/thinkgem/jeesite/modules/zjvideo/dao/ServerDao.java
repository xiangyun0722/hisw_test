/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Server;

/**
 * 视频服务器管理DAO接口
 * @author j.feng
 * @version 2015-05-06
 */
@MyBatisDao
public interface ServerDao extends CrudDao<Server> {
	
}