/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 教室Entity
 * @author j.feng
 * @version 2015-06-10
 */
public class Classroom extends DataEntity<Classroom> {
	
	private static final long serialVersionUID = 1L;
	private String classnumber;	// 教室号
	private String spart;		// 校区
	private String rname;		// 教室名称
	private String position;	// 所在位置
	private String type;		// 教室类型
	private String room;		// 容量
	
	public Classroom() {
		super();
	}

	public Classroom(String id){
		super(id);
	}

	@Length(min=0, max=255, message="教室号长度必须介于 0 和 255 之间")
	public String getClassnumber() {
		return classnumber;
	}

	public void setClassnumber(String classnumber) {
		this.classnumber = classnumber;
	}
	
	@Length(min=0, max=255, message="校区长度必须介于 0 和 255 之间")
	public String getSpart() {
		return spart;
	}

	public void setSpart(String spart) {
		this.spart = spart;
	}
	
	@Length(min=0, max=255, message="教室名称长度必须介于 0 和 255 之间")
	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}
	
	@Length(min=0, max=255, message="所在位置长度必须介于 0 和 255 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Length(min=0, max=11, message="教室类型长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=11, message="容量长度必须介于 0 和 11 之间")
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
}