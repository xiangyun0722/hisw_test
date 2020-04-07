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
import com.thinkgem.jeesite.modules.zjvideo.dao.NewsDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.News;

/**
 * 资讯管理Service
 * @author j.feng
 * @version 2015-05-04
 */
@Service
@Transactional(readOnly = true)
public class NewsService extends CrudService<NewsDao, News> {

	@Autowired
	private NewsDao newsDao;
	
	public News get(String id) {
		return super.get(id);
	}
	
	public List<News> findList(News news) {
		return super.findList(news);
	}
	
	public Page<News> findPage(Page<News> page, News news) {
		return super.findPage(page, news);
	}
	
	@Transactional(readOnly = false)
	public void save(News news) {
		super.save(news);
	}
	
	@Transactional(readOnly = false)
	public void delete(News news) {
		super.delete(news);
	}
	
	/**
	 * 数据库删除资讯信息
	 * @param news
	 */
	@Transactional(readOnly = false)
	public void deleteNews(News news){
		newsDao.deleteNews(news);
	}	
}