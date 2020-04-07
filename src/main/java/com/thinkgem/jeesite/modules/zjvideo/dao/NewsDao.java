/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.News;

/**
 * 资讯管理DAO接口
 * @author j.feng
 * @version 2015-05-04
 */
@MyBatisDao
public interface NewsDao extends CrudDao<News> {
	/**
	 * 数据库删除资讯信息
	 * @param videos
	 */
	void deleteNews(News news);
}