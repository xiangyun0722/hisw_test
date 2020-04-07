/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Project;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;

/**
 * 视频模板DAO接口
 * @author j.feng
 * @version 2015-05-21
 */
@MyBatisDao
public interface TemplatesDao extends CrudDao<Templates> {
	/**
	 * 数据库删除模板信息
	 * @param videos
	 */
	void deleteTemplates(Templates templates);	
	
	/**
	 * 查询所有模板信息
	 * @return
	 */
	List<Templates> getAllTemplates();

	List<Templates> getOwnTemplates(Project project);	
	
	List<Templates> getListByProjectid(Integer projectid);
	
	
}