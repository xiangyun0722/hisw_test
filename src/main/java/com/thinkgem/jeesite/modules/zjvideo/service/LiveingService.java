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
import com.thinkgem.jeesite.modules.zjvideo.dao.LiveingDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Liveing;

/**
 * 频道管理Service
 * @author j.feng
 * @version 2015-05-06
 */
@Service
@Transactional(readOnly = true)
public class LiveingService extends CrudService<LiveingDao, Liveing> {

	@Autowired
	private LiveingDao liveingDao;
	
	public Liveing get(String id) {
		return super.get(id);
	}
	
	public List<Liveing> findList(Liveing liveing) {
		return super.findList(liveing);
	}
	
	public Page<Liveing> findPage(Page<Liveing> page, Liveing liveing) {
		return super.findPage(page, liveing);
	}
	
	@Transactional(readOnly = false)
	public void save(Liveing liveing) {
		super.save(liveing);
	}
	
	@Transactional(readOnly = false)
	public void delete(Liveing liveing) {
		super.delete(liveing);
	}
	
	/**
	 * 查询所有直播在线信息
	 * @return
	 */
	public List<Liveing> getAllLiveing(){
		return liveingDao.getAllLiveing();
	}
}