/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingCategory;
import com.thinkgem.jeesite.modules.meeting.dao.MeetingCategoryDao;

/**
 * 会议分类Service
 * @author j.feng
 * @version 2015-08-14
 */
@Service
@Transactional(readOnly = true)
public class MeetingCategoryService extends TreeService<MeetingCategoryDao, MeetingCategory> {

	public MeetingCategory get(String id) {
		return super.get(id);
	}
	
	public List<MeetingCategory> findList(MeetingCategory meetingCategory) {
		if (StringUtils.isNotBlank(meetingCategory.getParentIds())){
			meetingCategory.setParentIds(","+meetingCategory.getParentIds()+",");
		}
		return super.findList(meetingCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(MeetingCategory meetingCategory) {
		super.save(meetingCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(MeetingCategory meetingCategory) {
		super.delete(meetingCategory);
	}
	
}