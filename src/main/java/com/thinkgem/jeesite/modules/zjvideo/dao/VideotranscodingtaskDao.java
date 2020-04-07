/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videotranscodingtask;

/**
 * 视频模板中间表DAO接口
 * @author j.feng
 * @version 2015-05-21
 */
@MyBatisDao
public interface VideotranscodingtaskDao extends CrudDao<Videotranscodingtask> {
	/**
	 * 数据库删除转码任务
	 * @param videos
	 */
	void deleteVideotranscodingtask(Videotranscodingtask videotranscodingtask);
	/**
	 * 刷新转码任务信息
	 * j.feng 2015年9月11日 上午11:35:01
	 * @param ids
	 * @return
	 */
	List<Videotranscodingtask> findListByIds(String ids);

    void updateStatus(Videotranscodingtask videotranscodingtask);
}