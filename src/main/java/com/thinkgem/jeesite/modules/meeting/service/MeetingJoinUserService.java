/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingJoinUser;
import com.thinkgem.jeesite.modules.meeting.dao.MeetingJoinUserDao;

/**
 * 参会用户Service
 * @author j.feng
 * @version 2015-08-14
 */
@Service
@Transactional(readOnly = true)
public class MeetingJoinUserService extends CrudService<MeetingJoinUserDao, MeetingJoinUser> {

	public MeetingJoinUser get(String id) {
		return super.get(id);
	}
	
	public List<MeetingJoinUser> findList(MeetingJoinUser meetingJoinUser) {
		return super.findList(meetingJoinUser);
	}
	
	public Page<MeetingJoinUser> findPage(Page<MeetingJoinUser> page, MeetingJoinUser meetingJoinUser) {
		return super.findPage(page, meetingJoinUser);
	}
	
	@Transactional(readOnly = false)
	public void save(MeetingJoinUser meetingJoinUser) {
		super.save(meetingJoinUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(MeetingJoinUser meetingJoinUser) {
		super.delete(meetingJoinUser);
	}
	
}