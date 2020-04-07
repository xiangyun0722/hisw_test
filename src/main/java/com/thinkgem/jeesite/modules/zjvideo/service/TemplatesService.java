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
import com.thinkgem.jeesite.modules.zjvideo.dao.TemplatesDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Project;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;

/**
 * 视频模板Service
 * @author j.feng
 * @version 2015-05-21
 */
@Service
@Transactional(readOnly = true)
public class TemplatesService extends CrudService<TemplatesDao, Templates> {
	@Autowired
	private TemplatesDao templatesDao;
	
	public Templates get(String id) {
		return super.get(id);
	}
	
	public List<Templates> findList(Templates template) {
		return super.findList(template);
	}
	
	public Page<Templates> findPage(Page<Templates> page, Templates template) {
		template.getSqlMap().put("dsf", dataScopeFilter(template.getCurrentUser(), "o", "u"));		
		return super.findPage(page, template);
	}
	
	@Transactional(readOnly = false)
	public void save(Templates template) {
		super.save(template);
	}
	
	@Transactional(readOnly = false)
	public void delete(Templates template) {
		super.delete(template);
	}
	
	/**
	 * 数据库删除模板信息
	 * @param videos
	 */
	@Transactional(readOnly = false)
	public void deleteTemplates(Templates templates){
		templatesDao.deleteTemplates(templates);
	}
	
	
	/**
	 * 查询所有模板信息
	 * @return
	 */
	public List<Templates> getAllTemplates(){	
		return templatesDao.getAllTemplates();
	}
	

	public List<Templates> getOwnTemplates(Project project) {
		return templatesDao.getOwnTemplates(project);
	}
	
	
	public List<Templates> getListByProjectid(Integer projectid) {
		return templatesDao.getListByProjectid(projectid);
	}
}