/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.dao.ConfigDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Config;

/**
 * 配置管理Service
 * @author j.feng
 * @version 2015-05-05
 */
@Service
@Transactional(readOnly = true)
public class ConfigService extends CrudService<ConfigDao, Config> {

	@Autowired
	private ConfigDao configDao;
	
	public Config get(String id) {
		return super.get(id);
	}
	
	public List<Config> findList(Config config) {
		return super.findList(config);
	}
	
	public Page<Config> findPage(Page<Config> page, Config config) {
		return super.findPage(page, config);
	}
	
	@Transactional(readOnly = false)
	public void save(Config config) {
		super.save(config);
	}
	
	@Transactional(readOnly = false)
	public void delete(Config config) {
		super.delete(config);
	}
	
	/**
	 * 根据key查询配置信息
	 * @param configkey
	 * @return
	 */
	public List<Config> findByConfigKey(Config config){	
		return configDao.findByConfigKey(config);
	}
	
	/**
	 * 用户自定义分页查询信息显示
	 */
	public Page<Config> findPortalPage(Page<Config> page, Config config) {
		config.setPage(page);
		page.setList(configDao.findByConfigKey(config));
		return super.findPage(page, config);
	}
}