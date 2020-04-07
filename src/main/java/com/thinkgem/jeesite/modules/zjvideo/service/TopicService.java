/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.Topic;
import com.thinkgem.jeesite.modules.zjvideo.dao.TopicDao;

/**
 * 话题信息Service
 * @author j.feng
 * @version 2015-05-07
 */
@Service
@Transactional(readOnly = true)
public class TopicService extends CrudService<TopicDao, Topic> {

	public Topic get(String id) {
		return super.get(id);
	}
	
	public List<Topic> findList(Topic topic) {
		return super.findList(topic);
	}
	
	public Page<Topic> findPage(Page<Topic> page, Topic topic) {
		return super.findPage(page, topic);
	}
	
	@Transactional(readOnly = false)
	public void save(Topic topic) {
		super.save(topic);
	}
	
	@Transactional(readOnly = false)
	public void delete(Topic topic) {
		super.delete(topic);
	}
	
}