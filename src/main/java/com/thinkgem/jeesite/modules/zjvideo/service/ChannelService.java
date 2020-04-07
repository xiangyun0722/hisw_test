/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.modules.zjvideo.entity.Pic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.Channel;
import com.thinkgem.jeesite.modules.zjvideo.dao.ChannelDao;

/**
 * 频道Service
 * @author lyy
 * @version 2020-02-06
 */
@Service
@Transactional(readOnly = false)
public class ChannelService extends CrudService<ChannelDao, Channel> {

	@Autowired
	private PicService picService;

	public Channel get(String id) {
		return super.get(id);
	}
	
	public List<Channel> findList(Channel channel) {
		return super.findList(channel);
	}
	
	public Page<Channel> findPage(Page<Channel> page, Channel channel) {
		return super.findPage(page, channel);
	}
	
	public void save(Channel channel) {
		dao.insert(channel);
	}

	public void update(Channel channel) {
		channel.setUpdateDate(new Date());
		dao.update(channel);
	}
	
	public void delete(Channel channel) {
		super.delete(channel);
	}

	/**
	 * 获取频道详情
	 * @param id
	 * @return
	 */
	public Channel getDetail(String id) {
		//查询频道信息
		Channel channel = get(id);
		//获取水印图片地址
		if(channel.getPicid() != null){
			Pic pic = picService.getDetail(String.valueOf(channel.getPicid()));
			if(pic != null){
				channel.setPicHttpUrl(pic.getHttpUrl());
			}
		}

		return channel;
	}
}