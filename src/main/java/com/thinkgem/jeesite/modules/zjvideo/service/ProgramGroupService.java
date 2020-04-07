/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.ProgramGroup;
import com.thinkgem.jeesite.modules.zjvideo.dao.ProgramGroupDao;
import com.thinkgem.jeesite.modules.zjvideo.dao.VideosDao;

/**
 * 课程组权限Service
 * @author j.feng
 * @version 2015-05-08
 */
@Service
@Transactional(readOnly = true)
public class ProgramGroupService extends CrudService<ProgramGroupDao, ProgramGroup> {

	@Autowired
	private ProgramGroupDao programGroupDao;
	
	public ProgramGroup get(String id) {
		return super.get(id);
	}
	
	public List<ProgramGroup> findList(ProgramGroup programGroup) {
		return super.findList(programGroup);
	}
	
	public Page<ProgramGroup> findPage(Page<ProgramGroup> page, ProgramGroup programGroup) {
		return super.findPage(page, programGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(ProgramGroup programGroup) {
		super.save(programGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProgramGroup programGroup) {
		super.delete(programGroup);
	}
	
	/**
	 * 查询权限表中是否已经存在记录
	 * @param groupId
	 * @param programId
	 * @return
	 */
	public List<ProgramGroup> queryByGroupIdProgramId(@Param("groupId")int groupId, @Param("programId")int programId){
		return programGroupDao.queryByGroupIdProgramId(groupId, programId);
	}
	
	
	/**
	 * 数据库删除权限组信息
	 * @param programGroup
	 */
	@Transactional(readOnly = false)
	public void deleteProgramGroup(ProgramGroup programGroup){
		programGroupDao.deleteProgramGroup(programGroup);
	}
}