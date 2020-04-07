/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 话题信息Entity
 * @author j.feng
 * @version 2015-05-07
 */
public class Topic extends DataEntity<Topic> {
	
	private static final long serialVersionUID = 1L;
	private Date addtime;		// 添加时间
	private String businessid;		// 业务ID
	private String name;		// 话题名字
	private String people;		// 话题人
	private String status;		// 状态
	private Date edittime;		// 更新时间
	private String type;		// 类型
	private String userid;		// 用户ID
	
	public Topic() {
		super();
	}

	public Topic(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	@Length(min=0, max=255, message="业务ID长度必须介于 0 和 255 之间")
	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}
	
	@Length(min=0, max=255, message="话题名字长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="话题人长度必须介于 0 和 255 之间")
	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEdittime() {
		return edittime;
	}

	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	
	@Length(min=0, max=11, message="类型长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=11, message="用户ID长度必须介于 0 和 11 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}