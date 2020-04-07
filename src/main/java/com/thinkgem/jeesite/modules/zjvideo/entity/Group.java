/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 组管理Entity
 * @author j.feng
 * @version 2015-05-04
 */
public class Group extends DataEntity<Group> {
	
	private static final long serialVersionUID = 1L;
	private String groupname;		// 组名称
	private String adgroupname;		// AD组名
	private String usercount;		// usercount
	private Date addtime;			// 增加时间
	private Date edittime;			// 最后修改时间
	private String slock;			// 状态标志位0:正常，1：锁定
	
	public Group() {
		super();
	}

	public Group(String id){
		super(id);
	}

	@Length(min=0, max=50, message="组名称长度必须介于 0 和 50 之间")
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	@Length(min=0, max=50, message="AD组名长度必须介于 0 和 50 之间")
	public String getAdgroupname() {
		return adgroupname;
	}

	public void setAdgroupname(String adgroupname) {
		this.adgroupname = adgroupname;
	}
	
	@Length(min=0, max=11, message="usercount长度必须介于 0 和 11 之间")
	public String getUsercount() {
		return usercount;
	}

	public void setUsercount(String usercount) {
		this.usercount = usercount;
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