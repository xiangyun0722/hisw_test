/**
 * 
 */
package com.thinkgem.jeesite.modules.live.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.live.entity.LiveLiveChannel;
import com.thinkgem.jeesite.modules.live.dao.LiveLiveChannelDao;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingChinanetLiveChannel;

/**
 * 直播频道Service
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
@Service
@Transactional(readOnly = true)
public class LiveLiveChannelService extends CrudService<LiveLiveChannelDao, LiveLiveChannel> {

	public LiveLiveChannel get(String id) {
		return super.get(id);
	}
	
	public List<LiveLiveChannel> findList(LiveLiveChannel liveLiveChannel) {
		return super.findList(liveLiveChannel);
	}
	
	public Page<LiveLiveChannel> findPage(Page<LiveLiveChannel> page, LiveLiveChannel liveLiveChannel) {
		return super.findPage(page, liveLiveChannel);
	}
	
	@Transactional(readOnly = false)
	public void save(LiveLiveChannel liveLiveChannel) {
		super.save(liveLiveChannel);
	}
	
	@Transactional(readOnly = false)
	public void delete(LiveLiveChannel liveLiveChannel) {
		super.delete(liveLiveChannel);
	}

	@Transactional(readOnly = false)
	public LiveLiveChannel getByRelId(LiveLiveChannel liveLiveChannel) {
		return dao.getByRelId(liveLiveChannel);
	}

	public LiveLiveChannel getByChannelSecretKey(LiveLiveChannel liveLiveChannel) {
		return dao.getByChannelSecretKey(liveLiveChannel);
	}
	
}