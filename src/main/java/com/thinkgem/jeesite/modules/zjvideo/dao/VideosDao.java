/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videos;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videotranscodingtask;

/**
 * 视频管理DAO接口
 * @author j.feng
 * @version 2015-05-06
 */
@MyBatisDao
public interface VideosDao extends CrudDao<Videos> {
	/**
	 * 数据库删除视频信息
	 * @param videos
	 */
	void deleteVideo(Videos videos);
	
	/**
	 * 查询所有的未使用视频列表
	 * @param programvolumeId
	 * @return
	 */
	List<Videos> queryByProgramVolumeId(Videos videos);	
	
	/**
	 * 根据剧集ID查询视频列表
	 * @param programvolumeId
	 * @return
	 */
	List<Videos> findByProgramVolumeId(@Param("programvolumeId") String programvolumeId);	
	
	/**
	 * 删除视频模板关联数据
	 * @param user
	 * @return
	 */
	int deleteVideosTemplates(Videos videos);
	
	/**
	 * 插入转码任务
	 * @param user
	 * @return
	 */
	int insertVideoTranscodingTask(Videos videos);	
	
	/**
	 * 插入转码任务
	 * @param user
	 * @return
	 */
	int insertVideoPushcdnTask(Videos videos);	
	
	/**
	 * 查询视频所有的模板列表
	 * @param program
	 * @return
	 */
	List<Videos> queryOwnTemplatesList(Videos videos);	
	
	/**
	 * 查询未处理媒资
	 * @param program
	 * @return
	 */
	List<Videos> queryQueueVideo(Videos videos);	
	
	/**
	 * 查询正式媒资
	 * @param program
	 * @return
	 */
	List<Videos> queryVideoMedia(Videos videos);
	
	
	/**
	 * 查询某个公司下的正式媒资
	 * @param program
	 * @return
	 */
	List<Videos> queryVideoMediaByCompany(Videos videos);
	
	
	/**
	 * 查询正式媒资下的视频列表
	 * @param program
	 * @return
	 */
	List<Media> queryMediaVideos(Media media);	
	
	/**
	 * 转码任务列表信息
	 * @param videotranscodingtask
	 * @return
	 */
	List<Videotranscodingtask> queryVideotranscodingtask(Videotranscodingtask videotranscodingtask);		
	
	/**
	 * 批量删除码流信息
	 * @param videos
	 */
	void batchDeleteTranscodeInfo(Videos videos);
	
	/**
	 * 
	 * @Title: queryVideosList 
	 * @Description: 根据公司Id查询视频列表
	 * @param @param videos
	 * @param @return  参数说明 
	 * @throws
	 */
	List<Videos> queryVideosList(Videos videos);	
}