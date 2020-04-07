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
import com.thinkgem.jeesite.modules.zjvideo.dao.StudentGroupDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.StudentGroup;

/**
 * 用户群组Service
 * @author j.feng
 * @version 2015-05-15
 */
@Service
@Transactional(readOnly = true)
public class StudentGroupService extends CrudService<StudentGroupDao, StudentGroup> {

	@Autowired
	private StudentGroupDao studentGroupDao;
	
	public StudentGroup get(String id) {
		return super.get(id);
	}
	
	public List<StudentGroup> findList(StudentGroup studentGroup) {
		return super.findList(studentGroup);
	}
	
	public Page<StudentGroup> findPage(Page<StudentGroup> page, StudentGroup studentGroup) {
		return super.findPage(page, studentGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(StudentGroup studentGroup) {
		super.save(studentGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(StudentGroup studentGroup) {
		super.delete(studentGroup);
	}
	
	/**
	 * 根据用户ID查询用户组信息
	 * @param programvolumeId
	 * @return
	 */
	public StudentGroup findByUserid(String userid){	
		List<StudentGroup> studentGroup = studentGroupDao.findByUserid(userid);
		if(studentGroup != null && studentGroup.size() >= 1){
			return studentGroup.get(0);
		}
		return null;
	}	
	
	/**
	 * 数据库删除用户群组信息
	 * @param programGroup
	 */
	@Transactional(readOnly = false)
	public void deleteStudentGroup(StudentGroup studentGroup){
		studentGroupDao.deleteStudentGroup(studentGroup);
	}	
}