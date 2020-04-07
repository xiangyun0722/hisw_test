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
import com.thinkgem.jeesite.modules.zjvideo.dao.ClassTypeDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.ClassType;

/**
 * 一级分类Service
 * @author j.feng
 * @version 2015-05-04
 */
@Service
@Transactional(readOnly = true)
public class ClassTypeService extends CrudService<ClassTypeDao, ClassType> {

	@Autowired
	private ClassTypeDao classTypeDao;
	
	public ClassType get(String id) {
		return super.get(id);
	}
	
	public List<ClassType> findList(ClassType classType) {
		return super.findList(classType);
	}
	
	public Page<ClassType> findPage(Page<ClassType> page, ClassType classType) {
		return super.findPage(page, classType);
	}
	
	@Transactional(readOnly = false)
	public void save(ClassType classType) {
		super.save(classType);
	}
	
	@Transactional(readOnly = false)
	public void delete(ClassType classType) {
		super.delete(classType);
	}
	
	/**
	 * 查询所有一级分类信息
	 * @return
	 */
	public List<ClassType> getAllClzzType(){
		return classTypeDao.getAllClzzType();
	}
}