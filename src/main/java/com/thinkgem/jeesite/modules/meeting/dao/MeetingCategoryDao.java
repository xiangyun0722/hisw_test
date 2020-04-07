/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingCategory;

/**
 * 会议分类DAO接口
 * @author j.feng
 * @version 2015-08-14
 */
@MyBatisDao
public interface MeetingCategoryDao extends TreeDao<MeetingCategory> {
	
}