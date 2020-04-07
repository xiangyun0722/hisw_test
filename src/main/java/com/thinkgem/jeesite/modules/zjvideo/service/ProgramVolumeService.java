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
import com.thinkgem.jeesite.modules.zjvideo.dao.ProgramVolumeDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.ProgramVolume;

/**
 * 章节信息Service
 * @author j.feng
 * @version 2015-05-07
 */
@Service
@Transactional(readOnly = true)
public class ProgramVolumeService extends CrudService<ProgramVolumeDao, ProgramVolume> {

	@Autowired
	private ProgramVolumeDao programVolumeDao;
	
	public ProgramVolume get(String id) {
		return super.get(id);
	}
	
	public List<ProgramVolume> findList(ProgramVolume programVolume) {
		return super.findList(programVolume);
	}
	
	public Page<ProgramVolume> findPage(Page<ProgramVolume> page, ProgramVolume programVolume) {
		return super.findPage(page, programVolume);
	}
	
	@Transactional(readOnly = false)
	public void save(ProgramVolume programVolume) {
		super.save(programVolume);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProgramVolume programVolume) {
		super.delete(programVolume);
	}
	
	/**
	 * 根据课程ID查询关联的所有剧集信息
	 * @param programVolume
	 * @return
	 */
	public Page<ProgramVolume>  findProgramVolumeList(Page<ProgramVolume> page,ProgramVolume programVolume){
		programVolume.setPage(page);
		page.setList(programVolumeDao.findProgramVolumeList(programVolume));
		return page;
	}	
	
	/**
	 * 数据库删除
	 * @param programVolume
	 */
	@Transactional(readOnly = false)
	public void deleteProgramVolume(ProgramVolume programVolume){	
		programVolumeDao.deleteProgramVolume(programVolume);
	}
}