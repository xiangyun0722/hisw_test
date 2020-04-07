/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 监控表Entity
 * @author lyy
 * @version 2020-02-18
 */
public class Runmonitor extends DataEntity<Runmonitor> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 程序名称
	private String code;		// 程序代码
	private Date addtime;		// 添加时间
	private Date lastupdate;		// 最后次更新时间
	
	public Runmonitor() {
		super();
	}

	public Runmonitor(String id){
		super(id);
	}

	@Length(min=0, max=50, message="程序名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="程序代码长度必须介于 0 和 50 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}
	
}