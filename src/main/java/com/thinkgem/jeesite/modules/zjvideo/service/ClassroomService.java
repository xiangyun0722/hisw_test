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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Classroom;
import com.thinkgem.jeesite.modules.zjvideo.dao.ClassroomDao;

/**
 * 教室Service
 * @author j.feng
 * @version 2015-06-10
 */
@Service
@Transactional(readOnly = true)
public class ClassroomService extends CrudService<ClassroomDao, Classroom> {

	
	public Classroom get(String id) {
		Classroom classroom = super.get(id);
		return classroom;
	}
	
	public List<Classroom> findList(Classroom classroom) {
		return super.findList(classroom);
	}
	
	public Page<Classroom> findPage(Page<Classroom> page, Classroom classroom) {
		return super.findPage(page, classroom);
	}
	
	@Transactional(readOnly = false)
	public void save(Classroom classroom) {
		super.save(classroom);
	}
	
	@Transactional(readOnly = false)
	public void delete(Classroom classroom) {
		super.delete(classroom);
	}
	
}