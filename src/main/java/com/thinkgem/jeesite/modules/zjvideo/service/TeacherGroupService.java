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
import com.thinkgem.jeesite.modules.zjvideo.dao.TeacherGroupDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.TeacherGroup;

/**
 * 教师群组Service
 * @author j.feng
 * @version 2015-05-18
 */
@Service
@Transactional(readOnly = true)
public class TeacherGroupService extends CrudService<TeacherGroupDao, TeacherGroup> {

	@Autowired
	private TeacherGroupDao teacherGroupDao;
	
	public TeacherGroup get(String id) {
		return super.get(id);
	}
	
	public List<TeacherGroup> findList(TeacherGroup teacherGroup) {
		return super.findList(teacherGroup);
	}
	
	public Page<TeacherGroup> findPage(Page<TeacherGroup> page, TeacherGroup teacherGroup) {
		return super.findPage(page, teacherGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(TeacherGroup teacherGroup) {
		super.save(teacherGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(TeacherGroup teacherGroup) {
		super.delete(teacherGroup);
	}
	
	/**
	 * 根据教师ID查询教师组信息
	 * @return
	 */
	public TeacherGroup findByTeacherid(@Param("teacherid") String teacherid){
		List<TeacherGroup> teacherGroup = teacherGroupDao.findByTeacherid(teacherid);
		if(teacherGroup != null && teacherGroup.size() >= 1){
			return teacherGroup.get(0);
		}
		return null;
	}
	
	/**
	 * 数据库删除教师群组信息
	 * @param program
	 */
	@Transactional(readOnly = false)
	public void deleteTeacherGroup(TeacherGroup teacherGroup){
		teacherGroupDao.deleteTeacherGroup(teacherGroup);
	}
}