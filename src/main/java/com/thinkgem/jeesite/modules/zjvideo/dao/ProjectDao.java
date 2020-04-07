/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Project;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;

/**
 * 项目信息表DAO接口
 * @author j.feng
 * @version 2015-07-09
 */
@MyBatisDao
public interface ProjectDao extends TreeDao<Project> {

	void deleteTemplates(Project project);

	int insertTemplates(Project project);
	 
	
}