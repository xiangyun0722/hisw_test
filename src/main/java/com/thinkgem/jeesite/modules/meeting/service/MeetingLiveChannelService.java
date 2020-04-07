/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingLiveChannel;
import com.thinkgem.jeesite.modules.meeting.dao.MeetingLiveChannelDao;

/**
 * 直播频道Service
 * @author j.feng
 * @version 2015-08-14
 */
@Service
@Transactional(readOnly = true)
public class MeetingLiveChannelService extends CrudService<MeetingLiveChannelDao, MeetingLiveChannel> {

	public MeetingLiveChannel get(String id) {
		return super.get(id);
	}
	
	public List<MeetingLiveChannel> findList(MeetingLiveChannel meetingLiveChannel) {
		return super.findList(meetingLiveChannel);
	}
	
	public Page<MeetingLiveChannel> findPage(Page<MeetingLiveChannel> page, MeetingLiveChannel meetingLiveChannel) {
		return super.findPage(page, meetingLiveChannel);
	}
	
	@Transactional(readOnly = false)
	public void save(MeetingLiveChannel meetingLiveChannel) {
		super.save(meetingLiveChannel);
	}
	
	@Transactional(readOnly = false)
	public void delete(MeetingLiveChannel meetingLiveChannel) {
		super.delete(meetingLiveChannel);
	}
	
}