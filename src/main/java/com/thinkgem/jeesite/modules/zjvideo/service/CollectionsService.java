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
import com.thinkgem.jeesite.modules.zjvideo.dao.CollectionsDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Collections;

/**
 * 收藏信息Service
 * @author j.feng
 * @version 2015-05-07
 */
@Service
@Transactional(readOnly = true)
public class CollectionsService extends CrudService<CollectionsDao, Collections> {

	@Autowired
	private CollectionsDao collectionsDao;
	
	public Collections get(String id) {
		return super.get(id);
	}
	
	public List<Collections> findList(Collections collection) {
		return super.findList(collection);
	}
	
	public Page<Collections> findPage(Page<Collections> page, Collections collection) {
		return super.findPage(page, collection);
	}
	
	@Transactional(readOnly = false)
	public void save(Collections collection) {
		super.save(collection);
	}
	
	@Transactional(readOnly = false)
	public void delete(Collections collection) {
		super.delete(collection);
	}
	
	/**
	 * 统计课程收藏列表
	 * @param collections
	 * @return
	 */
	public Page<Collections>  findStatisticsCollectionPage(Page<Collections> page,Collections collections){
		collections.setPage(page);
		page.setList(collectionsDao.findStatisticsCollectionList(collections));
		return page;
	}
	
	/**
	 * 学生收藏点播统计
	 * @param collections
	 * @return
	 */
	public Page<Collections>  findStatisticsStudentCollectionPage(Page<Collections> page,Collections collections){	
		collections.setPage(page);
		page.setList(collectionsDao.findStatisticsStudentCollectionsList(collections));
		return page;
	}
	
	
	/**
	 * 老师收藏点播统计
	 * @param collections
	 * @return
	 */
	public Page<Collections>  findStatisticsTeacherCollectionPage(Page<Collections> page,Collections collections){
		collections.setPage(page);
		page.setList(collectionsDao.findStatisticsTeacherCollectionsList(collections));
		return page;
	}
}