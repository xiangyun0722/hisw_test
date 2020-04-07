/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Config;

/**
 * 配置管理DAO接口
 * @author j.feng
 * @version 2015-05-05
 */
@MyBatisDao
public interface ConfigDao extends CrudDao<Config> {
	/**
	 * 根据key查询配置信息
	 * @param configkey
	 * @return
	 */
	List<Config> findByConfigKey(Config config);
}