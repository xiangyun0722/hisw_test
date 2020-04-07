/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingChinanetLiveChannel;
import com.thinkgem.jeesite.modules.meeting.dao.MeetingChinanetLiveChannelDao;

/**
 * 中国电信企业直播频道Service
 * @author tanwenkai
 * @version 2016-09-20
 */
@Service
@Transactional(readOnly = true)
public class MeetingChinanetLiveChannelService extends CrudService<MeetingChinanetLiveChannelDao, MeetingChinanetLiveChannel> {

	public MeetingChinanetLiveChannel get(String id) {
		return super.get(id);
	}
	
	public List<MeetingChinanetLiveChannel> findList(MeetingChinanetLiveChannel meetingChinanetLiveChannel) {
		return super.findList(meetingChinanetLiveChannel);
	}
	
	public Page<MeetingChinanetLiveChannel> findPage(Page<MeetingChinanetLiveChannel> page, MeetingChinanetLiveChannel meetingChinanetLiveChannel) {
		return super.findPage(page, meetingChinanetLiveChannel);
	}
	
	@Transactional(readOnly = false)
	public void save(MeetingChinanetLiveChannel meetingChinanetLiveChannel) {
		super.save(meetingChinanetLiveChannel);
	}
	
	@Transactional(readOnly = false)
	public void delete(MeetingChinanetLiveChannel meetingChinanetLiveChannel) {
		super.delete(meetingChinanetLiveChannel);
	}

	@Transactional(readOnly = false)
	public MeetingChinanetLiveChannel getByRelId(MeetingChinanetLiveChannel meetingChinanetLiveChannel) {
		return dao.getByRelId(meetingChinanetLiveChannel);
	}
	
}