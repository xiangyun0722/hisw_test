/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Topic;

/**
 * 话题信息DAO接口
 * @author j.feng
 * @version 2015-05-07
 */
@MyBatisDao
public interface TopicDao extends CrudDao<Topic> {
	
}