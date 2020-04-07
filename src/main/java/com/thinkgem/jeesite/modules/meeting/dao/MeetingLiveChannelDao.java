/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingLiveChannel;

/**
 * 直播频道DAO接口
 * @author j.feng
 * @version 2015-08-14
 */
@MyBatisDao
public interface MeetingLiveChannelDao extends CrudDao<MeetingLiveChannel> {
	
}