/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.dao.VideotranscodingtaskDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videotranscodingtask;

/**
 * 视频模板中间表Service
 * @author j.feng
 * @version 2015-05-21
 */
@Service
@Transactional(readOnly = true)
public class VideotranscodingtaskService extends CrudService<VideotranscodingtaskDao, Videotranscodingtask> {

	@Autowired
	private VideotranscodingtaskDao videotranscodingtaskDao;
	
	public Videotranscodingtask get(String id) {
		return super.get(id);
	}
	
	public List<Videotranscodingtask> findList(Videotranscodingtask videotemplate) {
		return super.findList(videotemplate);
	}
	
	public Page<Videotranscodingtask> findPage(Page<Videotranscodingtask> page, Videotranscodingtask videotemplate) {
		videotemplate.getSqlMap().put("dsf", dataScopeFilter(videotemplate.getCurrentUser(), "o", "u"));		
		return super.findPage(page, videotemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(Videotranscodingtask videotemplate) {
		super.save(videotemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(Videotranscodingtask videotemplate) {
		super.delete(videotemplate);
	}
	
	/**
	 * 数据库删除转码任务
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public void deleteVideotranscodingtask(Videotranscodingtask videotranscodingtask){
		videotranscodingtaskDao.deleteVideotranscodingtask(videotranscodingtask);
	}
	
	/**
	 * 刷新转码任务信息
	 * j.feng 2015年9月11日 上午11:34:23
	 * @param ids
	 * @return
	 */
	public List<Videotranscodingtask> findListByIds(String ids) {
		return videotranscodingtaskDao.findListByIds(ids);
	}

	@Transactional(readOnly = false)
	public void resetVideotranscodingtask(Videotranscodingtask videotranscodingtask) {
		videotranscodingtask.setConvertstatus(Templates.convertstatus_wait);
		videotranscodingtaskDao.updateStatus(videotranscodingtask);

	}
}