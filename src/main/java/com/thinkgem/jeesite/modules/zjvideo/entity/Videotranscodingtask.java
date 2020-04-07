/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 视频转码任务表Entity
 * @author j.feng
 * @version 2015-05-21
 */
public class Videotranscodingtask extends DataEntity<Videotranscodingtask> {
	
	private static final long serialVersionUID = 1L;
	private String templateid;		// 模板ID
	private String videoid;			// 视频ID
	private String convertstatus; 	// 转码状态(-1等待转码,0：转码中，1转码成功，2转码失败）
	private String convertmsg;		// 转码描述
	private Long convertusetime;	// 转码所用时间（秒）
	private Date convertStartTime;	// 转码开始时间
	private Date convertEndTime;	// 转码结束时间
	private String projectid;    	// 项目ID
	private String companyid;    	// 公司ID
	
	//关联信息查询
	private Videos video;			//视频实体
	private Templates template;		//模板实体
	
	public Videotranscodingtask() {
		super();
	}

	public Videotranscodingtask(String id){
		super(id);
	}

	@Length(min=0, max=50, message="模板ID长度必须介于 0 和 50 之间")
	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	
	@Length(min=0, max=11, message="视频ID长度必须介于 0 和 11 之间")
	public String getVideoid() {
		return videoid;
	}

	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}

	public String getConvertstatus() {
		return convertstatus;
	}

	public void setConvertstatus(String convertstatus) {
		this.convertstatus = convertstatus;
	}

	public String getConvertmsg() {
		return convertmsg;
	}

	public void setConvertmsg(String convertmsg) {
		this.convertmsg = convertmsg;
	}

	public Long getConvertusetime() {
		return convertusetime;
	}

	public void setConvertusetime(Long convertusetime) {
		this.convertusetime = convertusetime;
	}

	public Date getConvertStartTime() {
		return convertStartTime;
	}

	public void setConvertStartTime(Date convertStartTime) {
		this.convertStartTime = convertStartTime;
	}

	public Date getConvertEndTime() {
		return convertEndTime;
	}

	public void setConvertEndTime(Date convertEndTime) {
		this.convertEndTime = convertEndTime;
	}

	public Videos getVideo() {
		return video;
	}

	public void setVideo(Videos video) {
		this.video = video;
	}

	public Templates getTemplate() {
		return template;
	}

	public void setTemplate(Templates template) {
		this.template = template;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
}