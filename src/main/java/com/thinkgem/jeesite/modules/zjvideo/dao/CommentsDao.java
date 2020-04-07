/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Comments;

/**
 * 评论信息DAO接口
 * @author j.feng
 * @version 2015-05-07
 */
@MyBatisDao
public interface CommentsDao extends CrudDao<Comments> {
	/**
	 * 数据库删除敏感评论内容
	 * @param comments
	 */
	void deleteComment(Comments comments);
}