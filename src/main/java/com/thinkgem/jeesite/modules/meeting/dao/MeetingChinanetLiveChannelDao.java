/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingChinanetLiveChannel;

/**
 * 中国电信企业直播频道DAO接口
 * @author tanwenkai
 * @version 2016-09-20
 */
@MyBatisDao
public interface MeetingChinanetLiveChannelDao extends CrudDao<MeetingChinanetLiveChannel> {

	MeetingChinanetLiveChannel getByRelId(MeetingChinanetLiveChannel meetingChinanetLiveChannel);
	
}