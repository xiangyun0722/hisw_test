/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 一级分类Entity
 * @author j.feng
 * @version 2015-05-04
 */
public class ClassType extends DataEntity<ClassType> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 类别名称
	private String counts;		// 子分类数量
	private Date addtime;		// 增加时间
	private Date edittime;		// 最后修改 时间
	private String slock;		// 状态标志位0:正常，1：锁定
	
	public ClassType() {
		super();
	}

	public ClassType(String id){
		super(id);
	}

	@Length(min=0, max=50, message="类别名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="子分类数量长度必须介于 0 和 11 之间")
	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
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