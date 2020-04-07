/**
 * 
 */
package com.thinkgem.jeesite.modules.live.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.live.entity.LiveLiveChannel;

/**
 * 直播频道DAO接口
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
@MyBatisDao
public interface LiveLiveChannelDao extends CrudDao<LiveLiveChannel> {
	
	LiveLiveChannel getByRelId(LiveLiveChannel liveLiveChannel);

	LiveLiveChannel getByChannelSecretKey(LiveLiveChannel liveLiveChannel);
	
}