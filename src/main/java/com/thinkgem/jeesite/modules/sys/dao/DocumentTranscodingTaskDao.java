/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.DocumentTranscodingTask;

/**
 * 系统文档转码功能DAO接口
 * @author twk
 * @version 2015-08-10
 */
@MyBatisDao
public interface DocumentTranscodingTaskDao extends CrudDao<DocumentTranscodingTask> {

	DocumentTranscodingTask findOneByBusinessId(DocumentTranscodingTask documentTranscodingTask);

	List<DocumentTranscodingTask> findListByIds(@Param("ids") String ids);
	
}