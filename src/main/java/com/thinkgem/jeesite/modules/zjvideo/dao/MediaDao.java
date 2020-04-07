/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;
import org.apache.ibatis.annotations.Param;

/**
 * 媒资信息DAO接口
 * @author j.feng
 * @version 2015-06-03
 */
@MyBatisDao
public interface MediaDao extends CrudDao<Media> {
	/**
	 * 数据库删除媒资视频信息
	 * @param media
	 */
	void deleteMedia(Media media);
	
	/**
	 * 按照文件名查询可播放视频
	 * @param program
	 * @return
	 */
	List<Media> mediaQueryByName(Media media);

	/**
	 * 查询时间后新增的视频
	 * @param date
	 * @return
	 */
	List<Media> selectAfterTime(@Param("date") String date);
}