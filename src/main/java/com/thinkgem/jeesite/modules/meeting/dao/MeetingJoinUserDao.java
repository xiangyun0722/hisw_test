/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingJoinUser;

/**
 * 参会用户DAO接口
 * @author j.feng
 * @version 2015-08-14
 */
@MyBatisDao
public interface MeetingJoinUserDao extends CrudDao<MeetingJoinUser> {
	
}