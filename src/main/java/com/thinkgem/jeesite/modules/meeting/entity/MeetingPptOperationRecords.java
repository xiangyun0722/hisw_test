/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会议操作记录Entity
 * @author j.feng
 * @version 2015-08-14
 */
public class MeetingPptOperationRecords extends DataEntity<MeetingPptOperationRecords> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String ip;		// ip
	private Date upOperationTime;		// 上次操作时间
	private Date operationTime;		// 操作时间
	private String pageIndex;		// 页号信息
	
	public MeetingPptOperationRecords() {
		super();
	}

	public MeetingPptOperationRecords(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="ip长度必须介于 0 和 256 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpOperationTime() {
		return upOperationTime;
	}

	public void setUpOperationTime(Date upOperationTime) {
		this.upOperationTime = upOperationTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	@Length(min=0, max=4, message="页号信息长度必须介于 0 和 4 之间")
	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	
}