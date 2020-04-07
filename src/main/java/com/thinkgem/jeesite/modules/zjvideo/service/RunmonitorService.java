/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.Runmonitor;
import com.thinkgem.jeesite.modules.zjvideo.dao.RunmonitorDao;

/**
 * 监控表Service
 * @author lyy
 * @version 2020-02-18
 */
@Service
@Transactional(readOnly = true)
public class RunmonitorService extends CrudService<RunmonitorDao, Runmonitor> {

	public Runmonitor get(String id) {
		return super.get(id);
	}
	
	public List<Runmonitor> findList(Runmonitor runmonitor) {
		return super.findList(runmonitor);
	}
	
	public Page<Runmonitor> findPage(Page<Runmonitor> page, Runmonitor runmonitor) {
		return super.findPage(page, runmonitor);
	}
	
	@Transactional(readOnly = false)
	public void save(Runmonitor runmonitor) {
		super.save(runmonitor);
	}
	
	@Transactional(readOnly = false)
	public void delete(Runmonitor runmonitor) {
		super.delete(runmonitor);
	}
	
}