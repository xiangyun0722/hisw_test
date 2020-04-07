/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Clazz;
import com.thinkgem.jeesite.modules.zjvideo.dao.ClazzDao;

/**
 * 二级分类Service
 * @author j.feng
 * @version 2015-08-06
 */
@Service
@Transactional(readOnly = true)
public class ClazzService extends TreeService<ClazzDao, Clazz> {

	public Clazz get(String id) {
		return super.get(id);
	}
	
	public List<Clazz> findList(Clazz clazz) {
		if (StringUtils.isNotBlank(clazz.getParentIds())){
			clazz.setParentIds(","+clazz.getParentIds()+",");
		}
		return super.findList(clazz);
	}
	
	@Transactional(readOnly = false)
	public void save(Clazz clazz) {
		super.save(clazz);
	}
	
	@Transactional(readOnly = false)
	public void delete(Clazz clazz) {
		super.delete(clazz);
	}
	
}