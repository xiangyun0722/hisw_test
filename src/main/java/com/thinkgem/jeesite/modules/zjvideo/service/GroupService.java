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
import com.thinkgem.jeesite.modules.zjvideo.dao.GroupDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Group;

/**
 * 组管理Service
 * @author j.feng
 * @version 2015-05-04
 */
@Service
@Transactional(readOnly = true)
public class GroupService extends CrudService<GroupDao, Group> {

	@Autowired
	private GroupDao groupDao;

	public Group get(String id) {
		return super.get(id);
	}
	
	public List<Group> findList(Group group) {
		return super.findList(group);
	}
	
	public Page<Group> findPage(Page<Group> page, Group group) {
		return super.findPage(page, group);
	}
	
	@Transactional(readOnly = false)
	public void save(Group group) {
		super.save(group);
	}
	
	@Transactional(readOnly = false)
	public void delete(Group group) {
		super.delete(group);
	}
	
	/**
	 * 查询组成员列表信息
	 * @return
	 */
	public List<Group> queryGroupList(){
		return groupDao.queryGroupList();
	}
	
	/**
	 * 根据群组名字查询该组信息
	 * @param programvolumeId
	 * @return
	 */
	public Group findByGroupName(String groupname){	
		List<Group> group = groupDao.findByGroupName(groupname);
		if(group != null && group.size() >= 1){
			return group.get(0);
		}
		return null;
	}	
}