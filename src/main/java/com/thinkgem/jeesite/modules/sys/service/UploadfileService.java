/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.Uploadfile;
import com.thinkgem.jeesite.modules.sys.dao.UploadfileDao;

/**
 * 系统上传文件Service
 * @author twk
 * @version 2015-07-27
 */
@Service
@Transactional(readOnly = true)
public class UploadfileService extends CrudService<UploadfileDao, Uploadfile> {
	
	public Uploadfile get(String id) {
		return super.get(id);
	}
	
	public List<Uploadfile> findList(Uploadfile uploadfile) {
		return super.findList(uploadfile);
	}
	
	public Page<Uploadfile> findPage(Page<Uploadfile> page, Uploadfile uploadfile) {
		return super.findPage(page, uploadfile);
	}
	
	@Transactional(readOnly = false)
	public void save(Uploadfile uploadfile) {
		super.save(uploadfile);
	}
	
	@Transactional(readOnly = false)
	public void delete(Uploadfile uploadfile) {
		super.delete(uploadfile);
	}
	
}