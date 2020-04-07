/**
 * 
 */
package com.thinkgem.jeesite.modules.live.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.live.entity.ChannelFiles;
import com.thinkgem.jeesite.modules.live.dao.ChannelFilesDao;

/**
 * 直播频道录制文件Service
 * @author tanwenkai@qq.com
 * @version 2016-12-12
 */
@Service
@Transactional(readOnly = true)
public class ChannelFilesService extends CrudService<ChannelFilesDao, ChannelFiles> {
	
	public ChannelFiles get(String id) {
		return super.get(id);
	}
	
	public List<ChannelFiles> findList(ChannelFiles channelFiles) {
		return super.findList(channelFiles);
	}
	
	public Page<ChannelFiles> findPage(Page<ChannelFiles> page, ChannelFiles channelFiles) {
		return super.findPage(page, channelFiles);
	}
	
	@Transactional(readOnly = false)
	public void save(ChannelFiles channelFiles) {
		super.save(channelFiles);
	}
	
	@Transactional(readOnly = false)
	public void delete(ChannelFiles channelFiles) {
		super.delete(channelFiles);
	}
 
}