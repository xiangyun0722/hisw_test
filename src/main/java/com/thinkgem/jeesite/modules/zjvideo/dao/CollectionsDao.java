/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Collections;

/**
 * 收藏信息DAO接口
 * @author j.feng
 * @version 2015-05-07
 */
@MyBatisDao
public interface CollectionsDao extends CrudDao<Collections> {
	/**
	 * 查询统计收藏列表信息
	 * @param collections
	 * @return
	 */
	List<Collections> findStatisticsCollectionList(Collections collections);
	

	/**
	 * 学生收藏点播统计
	 * @param collections
	 * @return
	 */
	List<Collections> findStatisticsStudentCollectionsList(Collections collections);
	
	/**
	 * 老师收藏点播统计
	 * @param collections
	 * @return
	 */
	List<Collections> findStatisticsTeacherCollectionsList(Collections collections);	
}