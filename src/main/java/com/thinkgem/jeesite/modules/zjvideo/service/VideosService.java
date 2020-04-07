/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.zjvideo.dao.MediaDao;
import com.thinkgem.jeesite.modules.zjvideo.dao.VideosDao;
import com.thinkgem.jeesite.modules.zjvideo.dao.VideotranscodingtaskDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videos;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videotranscodingtask;

/**
 * 视频管理Service
 * @author j.feng
 * @version 2015-05-06
 */
@Service
@Transactional(readOnly = true)
public class VideosService extends CrudService<VideosDao, Videos> {

	@Autowired
	private VideosDao videosDao;
	
	@Autowired
	private VideotranscodingtaskDao videotranscodingtaskDao;
	
	@Autowired
	private MediaDao mediaDao;
	
	public Videos get(String id) {
		return super.get(id);
	}
	
	public List<Videos> findList(Videos videos) {
		return super.findList(videos);
	}
	
	public Page<Videos> findPage(Page<Videos> page, Videos videos) {
		videos.getSqlMap().put("dsf", dataScopeFilter(videos.getCurrentUser(), "o", "u"));		
		return super.findPage(page, videos);
	}
	
	@Transactional(readOnly = false)
	public void save(Videos entity) {
		if (entity.getIsNewRecord()){
			User createUser =null;
			if(entity.getCreateBy()!=null && StringUtils.isNotBlank(entity.getCreateBy().getId())){
				createUser = new User();
				createUser.setId(entity.getCreateBy().getId());
			}
			entity.preInsert();
			if(createUser != null){
				entity.setCreateBy(createUser);
			}
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Videos videos) {
		super.delete(videos);
	}
	
	/**
	 * 数据库删除视频信息
	 * @param videos
	 */
	@Transactional(readOnly = false)
	public void deleteVideo(Videos videos){
		videosDao.deleteVideo(videos);
	}
	
	/**
	 * 查询所有的未使用视频列表
	 * @param programvolumeId
	 * @return
	 */
	public List<Videos> queryByProgramVolumeId(){
		return null;
	}
	
	/**
	 * 根据课程ID分页查询关联的所有剧集信息
	 * @param programVolume
	 * @return
	 */
	public Page<Videos>  findProgramVolumeList(Page<Videos> page,Videos videos){
		videos.setPage(page);
		videos.getSqlMap().put("dsf", dataScopeFilter(videos.getCurrentUser(), "o", "u"));
		page.setList(videosDao.queryByProgramVolumeId(videos));
		return page;
	}	
	
	/**
	 * 根据剧集ID查询视频列表
	 * @param programvolumeId
	 * @return
	 */
	public Videos findByProgramVolumeId(String programvolumeId){	
		List<Videos> videos = videosDao.findByProgramVolumeId(programvolumeId);
		if(videos != null && videos.size() >= 1){
			return videos.get(0);
		}
		return null;
	}
	
	/**
	 * 删除视频模板关联数据
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteVideosTemplates(Videos videos){
		return videosDao.deleteVideosTemplates(videos);
	}
	
	/**
	 * 插入转码任务数据
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insertVideoTranscodingTask(Videos videos){		
		return videosDao.insertVideoTranscodingTask(videos);
	}
	
	
	/**
	 * 插入推cdn任务数据
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insertVideoPushcdnTask(Videos videos){		
		return videosDao.insertVideoPushcdnTask(videos);
	}
	
	/**
	 * 查询视频所有的模板列表
	 * @param program
	 * @return
	 */
	public List<Videos> queryOwnTemplatesList(Videos videos){
		return videosDao.queryOwnTemplatesList(videos);
	}
	
	/**
	 * 查询未处理媒资
	 * @param program
	 * @return
	 */
	public Page<Videos>  queryQueueVideo(Page<Videos> page,Videos videos){
		videos.setPage(page);
		videos.getSqlMap().put("dsf", dataScopeFilter(videos.getCurrentUser(), "o", "u"));		
		page.setList(videosDao.queryQueueVideo(videos));
		return page;
	}
	
	/**
	 * 查询正式媒资
	 * @param program
	 * @return
	 */
	public Page<Videos>  queryVideoMedia(Page<Videos> page,Videos videos){
		videos.setPage(page);
		User user=videos.getCurrentUser();
		if(user.isAdmin()){
			videos.getSqlMap().put("dsf", dataScopeFilter(videos.getCurrentUser(), "o", "u"));		
			page.setList(videosDao.queryVideoMedia(videos));
		}else{
			Role r =user.getRoleList().get(0);
			if(Role.DATA_SCOPE_COMPANY.equals(r.getDataScope())){
				videos.setCompanyid(user.getCompany().getId());
				page.setList(videosDao.queryVideoMediaByCompany(videos));
			}else{
				videos.getSqlMap().put("dsf", dataScopeFilter(videos.getCurrentUser(), "o", "u"));		
				page.setList(videosDao.queryVideoMedia(videos));
			}
			
		}		
		return page;
	}	
	
	/**
	 * 查询正式媒资下的视频列表
	 * @param program
	 * @return
	 */
	public Page<Media>  queryMediaVideos(Page<Media> page,Media media){
		media.setPage(page);
		media.getSqlMap().put("dsf", dataScopeFilter(media.getCurrentUser(), "o", "u"));		
		page.setList(videosDao.queryMediaVideos(media));
		return page;
	}	
	
	/**
	 * 转码任务列表信息
	 * @param page
	 * @param media
	 * @return
	 */
	public Page<Videotranscodingtask>  queryVideotranscodingtask(Page<Videotranscodingtask> page,Videotranscodingtask videotranscodingtask){
		videotranscodingtask.setPage(page);
		videotranscodingtask.getSqlMap().put("dsf", dataScopeFilter(videotranscodingtask.getCurrentUser(), "o", "u"));
		page.setList(videosDao.queryVideotranscodingtask(videotranscodingtask));
		return page;
	}	
	
	/**
	 * 批量删除码流信息
	 * @param videos
	 */
	@Transactional(readOnly = false)
	public void batchDeleteTranscodeInfo(Videos videos){
		videosDao.batchDeleteTranscodeInfo(videos);
	}

	/**
	 * 
	 * @Title: queryVideosList 
	 * @Description: 根据公司Id查询视频列表
	 * @param @param page
	 * @param @param videos
	 * @param @return  参数说明 
	 * @throws
	 */
	public Page<Videos> queryVideosList(Page<Videos> page, Videos videos) {
		videos.setPage(page);
		page.setList(videosDao.queryVideosList(videos));
		return page;
	}

	public Page<Media> apiQueryMediaVideos(Page<Media> page, Media media) {
		media.setPage(page);
		page.setList(videosDao.queryMediaVideos(media));
		return page;
	}
}