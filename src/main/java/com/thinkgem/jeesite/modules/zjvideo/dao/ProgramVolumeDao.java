/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.ProgramVolume;

/**
 * 章节信息DAO接口
 * @author j.feng
 * @version 2015-05-07
 */
@MyBatisDao
public interface ProgramVolumeDao extends CrudDao<ProgramVolume> {
	/**
	 * 根据课程ID查询关联的所有剧集信息
	 * @param programVolume
	 * @return
	 */
	List<ProgramVolume> findProgramVolumeList(ProgramVolume programVolume);
	
	/**
	 * 数据库删除
	 * @param programVolume
	 */
	void deleteProgramVolume(ProgramVolume programVolume);	
}