/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.entity;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 会议分类Entity
 * @author j.feng
 * @version 2015-08-14
 */
public class MeetingCategory extends TreeEntity<MeetingCategory> {
	
	private static final long serialVersionUID = 1L;
	
	public MeetingCategory() {
		super();
	}

	public MeetingCategory(String id){
		super(id);
	}

	@JsonBackReference
	public MeetingCategory getParent() {
		return parent;
	}

	public void setParent(MeetingCategory parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
}