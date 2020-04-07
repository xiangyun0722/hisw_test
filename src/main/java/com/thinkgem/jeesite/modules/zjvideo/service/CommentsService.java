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
import com.thinkgem.jeesite.modules.zjvideo.entity.Comments;
import com.thinkgem.jeesite.modules.zjvideo.dao.CommentsDao;

/**
 * 评论信息Service
 * @author j.feng
 * @version 2015-05-07
 */
@Service
@Transactional(readOnly = true)
public class CommentsService extends CrudService<CommentsDao, Comments> {

	@Autowired
	private CommentsDao commentsDao;
	
	public Comments get(String id) {
		return super.get(id);
	}
	
	public List<Comments> findList(Comments comments) {
		return super.findList(comments);
	}
	
	public Page<Comments> findPage(Page<Comments> page, Comments comments) {
		return super.findPage(page, comments);
	}
	
	@Transactional(readOnly = false)
	public void save(Comments comments) {
		super.save(comments);
	}
	
	@Transactional(readOnly = false)
	public void delete(Comments comments) {
		super.delete(comments);
	}
	
	/**
	 * 数据库删除敏感评论内容
	 * @param comments
	 */
	@Transactional(readOnly = false)
	public void deleteComment(Comments comments){
		commentsDao.deleteComment(comments);
	}
}