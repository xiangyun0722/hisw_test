/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingPptOperationRecords;
import com.thinkgem.jeesite.modules.meeting.dao.MeetingPptOperationRecordsDao;

/**
 * 会议操作记录Service
 * @author j.feng
 * @version 2015-08-14
 */
@Service
@Transactional(readOnly = true)
public class MeetingPptOperationRecordsService extends CrudService<MeetingPptOperationRecordsDao, MeetingPptOperationRecords> {

	public MeetingPptOperationRecords get(String id) {
		return super.get(id);
	}
	
	public List<MeetingPptOperationRecords> findList(MeetingPptOperationRecords meetingPptOperationRecords) {
		return super.findList(meetingPptOperationRecords);
	}
	
	public Page<MeetingPptOperationRecords> findPage(Page<MeetingPptOperationRecords> page, MeetingPptOperationRecords meetingPptOperationRecords) {
		return super.findPage(page, meetingPptOperationRecords);
	}
	
	@Transactional(readOnly = false)
	public void save(MeetingPptOperationRecords meetingPptOperationRecords) {
		super.save(meetingPptOperationRecords);
	}
	
	@Transactional(readOnly = false)
	public void delete(MeetingPptOperationRecords meetingPptOperationRecords) {
		super.delete(meetingPptOperationRecords);
	}
	
}