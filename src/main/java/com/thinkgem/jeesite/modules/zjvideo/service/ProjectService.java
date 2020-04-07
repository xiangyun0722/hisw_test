/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.Reflections;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.zjvideo.entity.Project;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;
import com.thinkgem.jeesite.modules.zjvideo.dao.NodeServerGroupDao;
import com.thinkgem.jeesite.modules.zjvideo.dao.ProjectDao;

/**
 * 项目信息表Service
 * @author j.feng
 * @version 2015-07-09
 */
@Service
@Transactional(readOnly = true)
public class ProjectService extends TreeService<ProjectDao, Project> {
	
	@Autowired
	private ProjectDao projectDao;

	public Project get(String id) {
		return super.get(id);
	}
	
	public List<Project> findList(Project project) {
		if (StringUtils.isNotBlank(project.getParentIds())){
			project.setParentIds(","+project.getParentIds()+",");
		}
		return super.findList(project);
	}
	
	@Transactional(readOnly = false)
	public void save(Project entity) {
		@SuppressWarnings("unchecked")
		Class<Project> entityClass = Reflections.getClassGenricType(getClass(), 1);
		
		// 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
		if (entity.getParent() == null || StringUtils.isBlank(entity.getParentId()) 
				|| "0".equals(entity.getParentId())){
			entity.setParent(null);
		}else{
			entity.setParent(super.get(entity.getParentId()));
		}
		if (entity.getParent() == null){
			Project parentEntity = null;
			try {
				parentEntity = entityClass.getConstructor(String.class).newInstance("0");
			} catch (Exception e) {
				throw new ServiceException(e);
			}
			entity.setParent(parentEntity);
			entity.getParent().setParentIds(StringUtils.EMPTY);
		}
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = entity.getParentIds(); 
		
		// 设置新的父节点串
		entity.setParentIds(entity.getParent().getParentIds()+entity.getParent().getId()+",");
		User magrUser = new User();
		if(entity!=null && org.apache.commons.lang.StringUtils.isNotBlank(entity.getCreateBy().getId())){
			String createById = entity.getCreateBy().getId();
			magrUser.setId(createById);
		}
		// 保存或更新实体
		if (entity.getIsNewRecord()){
			entity.preInsert();
			entity.setCreateBy(magrUser);
			dao.insert(entity);
		}else{
			entity.preUpdate();
			entity.setCreateBy(magrUser);
			dao.update(entity);
		}
		// 更新子节点 parentIds
		Project o = null;
		try {
			o = entityClass.newInstance();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		o.setParentIds("%,"+entity.getId()+",%");
		List<Project> list = dao.findByParentIdsLike(o);
		for (Project e : list){
			if (e.getParentIds() != null && oldParentIds != null){
				e.setParentIds(e.getParentIds().replace(oldParentIds, entity.getParentIds()));
				preUpdateChild(entity, e);
				dao.updateParentIds(e);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Project project) {
		super.delete(project);
	}

	@Transactional(readOnly = false)
	public void deleteTemplates(Project project) {
		projectDao.deleteTemplates(project);
	}
	
	@Transactional(readOnly = false)
	public int insertTemplates(Project project) {
		return projectDao.insertTemplates(project);
	}

	
}