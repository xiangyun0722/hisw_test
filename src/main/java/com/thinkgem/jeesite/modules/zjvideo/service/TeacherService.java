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
import com.thinkgem.jeesite.modules.zjvideo.entity.Teacher;
import com.thinkgem.jeesite.modules.zjvideo.dao.TeacherDao;
import com.thinkgem.jeesite.modules.zjvideo.dao.VideosDao;

/**
 * 教师信息Service
 * @author j.feng
 * @version 2015-05-04
 */
@Service
@Transactional(readOnly = true)
public class TeacherService extends CrudService<TeacherDao, Teacher> {

	
	@Autowired
	private TeacherDao teacherDao;
	
	public Teacher get(String id) {
		return super.get(id);
	}
	
	public List<Teacher> findList(Teacher teacher) {
		return super.findList(teacher);
	}
	
	public Page<Teacher> findPage(Page<Teacher> page, Teacher teacher) {
		return super.findPage(page, teacher);
	}
	
	@Transactional(readOnly = false)
	public void save(Teacher teacher) {
		super.save(teacher);
	}
	
	@Transactional(readOnly = false)
	public void delete(Teacher teacher) {
		super.delete(teacher);
	}
	
	/**
	 * 查询主讲人信息列表
	 * @return
	 */
	public List<Teacher> queryTeacherList(){
		return teacherDao.queryTeacherList();
	}
}