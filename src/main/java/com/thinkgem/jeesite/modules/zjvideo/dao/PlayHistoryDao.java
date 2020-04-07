/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.PlayHistory;

/**
 * 播放历史信息DAO接口
 * @author j.feng
 * @version 2015-05-07
 */
@MyBatisDao
public interface PlayHistoryDao extends CrudDao<PlayHistory> {
	/**
	 * 点播课程统计
	 * @param playHistory
	 * @return
	 */
	List<PlayHistory> findStatisticsPlayHistoryList(PlayHistory playHistory);
	
	/**
	 * 学生收藏点播统计
	 * @param playHistory
	 * @return
	 */
	List<PlayHistory> findStatisticsStudentPlayHistoryList(PlayHistory playHistory);
	
	/**
	 * 老师收藏点播统计
	 * @param playHistory
	 * @return
	 */
	List<PlayHistory> findStatisticsTeacherPlayHistoryList(PlayHistory playHistory);
}