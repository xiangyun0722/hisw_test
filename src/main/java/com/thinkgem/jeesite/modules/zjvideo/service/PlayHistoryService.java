/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.dao.PlayHistoryDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.PlayHistory;

/**
 * 播放历史信息Service
 * @author j.feng
 * @version 2015-05-07
 */
@Service
@Transactional(readOnly = true)
public class PlayHistoryService extends CrudService<PlayHistoryDao, PlayHistory> {

	@Autowired
	private PlayHistoryDao playHistoryDao;
	
	public PlayHistory get(String id) {
		return super.get(id);
	}
	
	public List<PlayHistory> findList(PlayHistory playHistory) {
		return super.findList(playHistory);
	}
	
	public Page<PlayHistory> findPage(Page<PlayHistory> page, PlayHistory playHistory) {
		return super.findPage(page, playHistory);
	}
	
	@Transactional(readOnly = false)
	public void save(PlayHistory playHistory) {
		super.save(playHistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlayHistory playHistory) {
		super.delete(playHistory);
	}
	
	/**
	 * 点播课程统计
	 * @param page
	 * @param collections
	 * @return
	 */
	public Page<PlayHistory>  findStatisticsPlayHistoryPage(Page<PlayHistory> page,PlayHistory playHistory){
		playHistory.setPage(page);
		page.setList(playHistoryDao.findStatisticsPlayHistoryList(playHistory));
		return page;
	}	
	
	/**
	 * 学生收藏点播统计
	 * @param playHistory
	 * @return
	 */
	public Page<PlayHistory>  findStatisticsStudentPlayHistoryPage(Page<PlayHistory> page,PlayHistory playHistory){
		playHistory.setPage(page);
		page.setList(playHistoryDao.findStatisticsStudentPlayHistoryList(playHistory));
		return page;
	}
	
	/**
	 * 老师收藏点播统计
	 * @param playHistory
	 * @return
	 */
	public Page<PlayHistory>  findStatisticsTeacherPlayHistoryPage(Page<PlayHistory> page,PlayHistory playHistory){
		playHistory.setPage(page);
		page.setList(playHistoryDao.findStatisticsTeacherPlayHistoryList(playHistory));
		return page;
	}	
}