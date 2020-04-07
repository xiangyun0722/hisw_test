/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 二级分类Entity
 * @author j.feng
 * @version 2015-08-06
 */
public class Clazz extends TreeEntity<Clazz> {
	
	private static final long serialVersionUID = 1L;
	private String typeid;		// 隶属类型
	private String classFlag;	// class_flag
	private String icount;		// 子类数量
	private String iorder;		// 排序标志
	private Date addtime;		// 增加时间
	private Date edittime;		// 最后修改 时间
	private String slock;		// 状态标志位0:正常，1：锁定
	
	public Clazz() {
		super();
	}

	public Clazz(String id){
		super(id);
	}

	@Length(min=0, max=11, message="隶属类型长度必须介于 0 和 11 之间")
	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	
	@Length(min=0, max=1, message="class_flag长度必须介于 0 和 1 之间")
	public String getClassFlag() {
		return classFlag;
	}

	public void setClassFlag(String classFlag) {
		this.classFlag = classFlag;
	}
	
	@Length(min=0, max=11, message="子类数量长度必须介于 0 和 11 之间")
	public String getIcount() {
		return icount;
	}

	public void setIcount(String icount) {
		this.icount = icount;
	}
	
	@Length(min=0, max=4, message="排序标志长度必须介于 0 和 4 之间")
	public String getIorder() {
		return iorder;
	}

	public void setIorder(String iorder) {
		this.iorder = iorder;
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

	@Override
	public Clazz getParent() {
		return parent;
	}

	@Override
	public void setParent(Clazz parent) {
		this.parent = parent;
	}
}