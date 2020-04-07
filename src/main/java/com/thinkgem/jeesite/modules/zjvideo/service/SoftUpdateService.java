/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.SoftUpdate;
import com.thinkgem.jeesite.modules.zjvideo.dao.SoftUpdateDao;

/**
 * 软件更新Service
 * @author j.feng
 * @version 2015-05-05
 */
@Service
@Transactional(readOnly = true)
public class SoftUpdateService extends CrudService<SoftUpdateDao, SoftUpdate> {

	public SoftUpdate get(String id) {
		return super.get(id);
	}
	
	public List<SoftUpdate> findList(SoftUpdate softUpdate) {
		return super.findList(softUpdate);
	}
	
	public Page<SoftUpdate> findPage(Page<SoftUpdate> page, SoftUpdate softUpdate) {
		return super.findPage(page, softUpdate);
	}
	
	@Transactional(readOnly = false)
	public void save(SoftUpdate softUpdate) {
		super.save(softUpdate);
	}
	
	@Transactional(readOnly = false)
	public void delete(SoftUpdate softUpdate) {
		super.delete(softUpdate);
	}
	
}