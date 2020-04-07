/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingMeetingInfo;
import com.thinkgem.jeesite.modules.meeting.dao.MeetingMeetingInfoDao;

/**
 * 会议操作记录Service
 * @author j.feng
 * @version 2015-08-14
 */
@Service
@Transactional(readOnly = true)
public class MeetingMeetingInfoService extends CrudService<MeetingMeetingInfoDao, MeetingMeetingInfo> {

	public MeetingMeetingInfo get(String id) {
		return super.get(id);
	}
	
	public List<MeetingMeetingInfo> findList(MeetingMeetingInfo meetingMeetingInfo) {
		return super.findList(meetingMeetingInfo);
	}
	
	public Page<MeetingMeetingInfo> findPage(Page<MeetingMeetingInfo> page, MeetingMeetingInfo meetingMeetingInfo) {
		return super.findPage(page, meetingMeetingInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(MeetingMeetingInfo meetingMeetingInfo) {
		super.save(meetingMeetingInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(MeetingMeetingInfo meetingMeetingInfo) {
		super.delete(meetingMeetingInfo);
	}
	
}