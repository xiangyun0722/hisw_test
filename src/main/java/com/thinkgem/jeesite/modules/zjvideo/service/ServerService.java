/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.Server;
import com.thinkgem.jeesite.modules.zjvideo.dao.ServerDao;

/**
 * 视频服务器管理Service
 * @author j.feng
 * @version 2015-05-06
 */
@Service
@Transactional(readOnly = true)
public class ServerService extends CrudService<ServerDao, Server> {

	public Server get(String id) {
		return super.get(id);
	}
	
	public List<Server> findList(Server server) {
		return super.findList(server);
	}
	
	public Page<Server> findPage(Page<Server> page, Server server) {
		return super.findPage(page, server);
	}
	
	@Transactional(readOnly = false)
	public void save(Server server) {
		super.save(server);
	}
	
	@Transactional(readOnly = false)
	public void delete(Server server) {
		super.delete(server);
	}
	
}