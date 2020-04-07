/**
 * 
 */
package com.thinkgem.jeesite.modules.live.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.live.entity.ChannelFiles;

/**
 * 直播频道录制文件DAO接口
 * @author tanwenkai@qq.com
 * @version 2016-12-12
 */
@MyBatisDao
public interface ChannelFilesDao extends CrudDao<ChannelFiles> {

	ChannelFiles getByChannelSecretKey(ChannelFiles channelFiles);
	
}