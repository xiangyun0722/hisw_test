/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Channel;

/**
 * 频道DAO接口
 * @author lyy
 * @version 2020-02-06
 */
@MyBatisDao
public interface ChannelDao extends CrudDao<Channel> {
	
}