/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程组权限Entity
 * @author j.feng
 * @version 2015-05-08
 */
public class ProgramGroup extends DataEntity<ProgramGroup> {
	
	private static final long serialVersionUID = 1L;
	private String programid;	// 课程编号
	private String groupid;		// 群组ID
	private Date addtime;		// 增加时间
	private Date edittime;		// 最后修改时间
	private String slock;		// 状态标志位
	
	public ProgramGroup() {
		super();
	}

	public ProgramGroup(String id){
		super(id);
	}

	@Length(min=0, max=11, message="课程编号长度必须介于 0 和 11 之间")
	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}
	
	@Length(min=0, max=11, message="群组ID长度必须介于 0 和 11 之间")
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
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
	
	@Length(min=0, max=1, message="状态标志位长度必须介于 0 和 1 之间")
	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}
	
}