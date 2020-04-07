/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 参会用户Entity
 * @author j.feng
 * @version 2015-08-14
 */
public class MeetingJoinUser extends DataEntity<MeetingJoinUser> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String ip;		// ip
	private Date upOperationTime;		// 上次操作时间
	private Date operationTime;		// 操作时间
	private String pageIndex;		// 页号信息
	private String countries;		// 国家
	private String provinces;		// 省市
	private String city;		// 区/县
	private String network;		// 网络运营商
	private Date startTime;		// 参会时间
	private Date endTime;		// 离开时间
	private String sessionid;		// sessionid
	
	public MeetingJoinUser() {
		super();
	}

	public MeetingJoinUser(String id){
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
	
	@Length(min=0, max=64, message="国家长度必须介于 0 和 64 之间")
	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}
	
	@Length(min=0, max=64, message="省市长度必须介于 0 和 64 之间")
	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}
	
	@Length(min=0, max=64, message="区/县长度必须介于 0 和 64 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=64, message="网络运营商长度必须介于 0 和 64 之间")
	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=64, message="sessionid长度必须介于 0 和 64 之间")
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	
}