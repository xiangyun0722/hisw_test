/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户群组Entity
 * @author j.feng
 * @version 2015-05-15
 */
public class StudentGroup extends DataEntity<StudentGroup> {
	
	private static final long serialVersionUID = 1L;
	private String groupid;		// 群组id
	private String userid;		// 用户id
	private Date addtime;		// 增加时间
	private Date edittime;		// 最后修改时间
	private String slock;		// 状态标志位0:正常，1：锁定
	
	public StudentGroup() {
		super();
	}

	public StudentGroup(String id){
		super(id);
	}

	@Length(min=0, max=11, message="群组id长度必须介于 0 和 11 之间")
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	@Length(min=0, max=11, message="用户id长度必须介于 0 和 11 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEdittime() {
		return edittime;
	}

	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	
	@Length(min=0, max=1, message="状态标志位0:正常，1：锁定长度必须介于 0 和 1 之间")
	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}
	
}