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
import com.thinkgem.jeesite.modules.zjvideo.dao.ProgramDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Program;

/**
 * 课程管理Service
 * @author j.feng
 * @version 2015-05-06
 */
@Service
@Transactional(readOnly = true)
public class ProgramService extends CrudService<ProgramDao, Program> {

	@Autowired
	private ProgramDao programDao;
	
	public Program get(String id) {
		return super.get(id);
	}
	
	public List<Program> findList(Program program) {
		return super.findList(program);
	}
	
	public Page<Program> findPage(Page<Program> page, Program program) {
		return super.findPage(page, program);
	}
	
	@Transactional(readOnly = false)
	public void save(Program program) {
		super.save(program);
	}
	
	@Transactional(readOnly = false)
	public void delete(Program program) {
		super.delete(program);
	}
	
	/**
	 * 查询用户观看视频权限列表
	 * @param program
	 * @return
	 */
	public List<Program> findOwnGroupList(Program program){
		return programDao.findOwnGroupList(program);
	}
	
	/**
	 * 数据库删除课程信息
	 * @param program
	 */
	@Transactional(readOnly = false)
	public void deleteProgram(Program program){	
		programDao.deleteProgram(program);
	}
}