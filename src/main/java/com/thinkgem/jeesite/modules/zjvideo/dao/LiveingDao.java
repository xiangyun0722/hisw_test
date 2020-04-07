/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Liveing;

/**
 * 频道管理DAO接口
 * @author j.feng
 * @version 2015-05-06
 */
@MyBatisDao
public interface LiveingDao extends CrudDao<Liveing> {
	/**
	 * 查询所有直播在线信息
	 * @return
	 */
	List<Liveing> getAllLiveing();
}