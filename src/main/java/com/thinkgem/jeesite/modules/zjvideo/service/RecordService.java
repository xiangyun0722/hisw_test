/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.Record;
import com.thinkgem.jeesite.modules.zjvideo.dao.RecordDao;

/**
 * 录制Service
 * @author j.feng
 * @version 2015-06-10
 */
@Service
@Transactional(readOnly = true)
public class RecordService extends CrudService<RecordDao, Record> {

	public Record get(String id) {
		return super.get(id);
	}
	
	public List<Record> findList(Record record) {
		return super.findList(record);
	}
	
	public Page<Record> findPage(Page<Record> page, Record record) {
		return super.findPage(page, record);
	}
	
	@Transactional(readOnly = false)
	public void save(Record record) {
		super.save(record);
	}
	
	@Transactional(readOnly = false)
	public void delete(Record record) {
		super.delete(record);
	}
	
}