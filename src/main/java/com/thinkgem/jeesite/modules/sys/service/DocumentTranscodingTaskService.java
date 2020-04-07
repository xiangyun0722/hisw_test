/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.DocumentTranscodingTask;
import com.thinkgem.jeesite.modules.sys.dao.DocumentTranscodingTaskDao;

/**
 * 系统文档转码功能Service
 * @author twk
 * @version 2015-08-10
 */
@Service
@Transactional(readOnly = true)
public class DocumentTranscodingTaskService extends CrudService<DocumentTranscodingTaskDao, DocumentTranscodingTask> {

	
	public DocumentTranscodingTask get(String id) {
		return super.get(id);
	}
	
	public List<DocumentTranscodingTask> findList(DocumentTranscodingTask documentTranscodingTask) {
		return super.findList(documentTranscodingTask);
	}
	
	public DocumentTranscodingTask findOneByBusinessId(String businessId) {
		//DocumentTranscodingTask documentTranscodingTask
		DocumentTranscodingTask documentTranscodingTask  = new DocumentTranscodingTask();
		documentTranscodingTask.setBusinessId(businessId);
		return dao.findOneByBusinessId(documentTranscodingTask);
	}
	
	
	
	public Page<DocumentTranscodingTask> findPage(Page<DocumentTranscodingTask> page, DocumentTranscodingTask documentTranscodingTask) {
		return super.findPage(page, documentTranscodingTask);
	}
	
	@Transactional(readOnly = false)
	public void save(DocumentTranscodingTask entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			entity.initPathInfo();//初始化路径信息。
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DocumentTranscodingTask documentTranscodingTask) {
		super.delete(documentTranscodingTask);
	}
	
	public List<DocumentTranscodingTask> findListByIds(String ids) {
		return dao.findListByIds(ids); 
	}
	
}