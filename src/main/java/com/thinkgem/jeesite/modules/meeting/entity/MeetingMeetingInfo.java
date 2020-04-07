/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会议操作记录Entity
 * @author j.feng
 * @version 2015-08-14
 */
public class MeetingMeetingInfo extends DataEntity<MeetingMeetingInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 会议名称
	private String content;		// 简介
	private String present;		// 主讲人
	private Integer length;		// 会议时长（分钟）
	private String img;		// 封面
	private String imgs;		// 图片介绍
	private String ppt;		// 讲稿
	private String pptUuid;		// 讲稿UUID
	private String video;		// 录播视频
	private Integer type;		// 会议类型(直播/点播)
	private MeetingLiveChannel meetingLiveChannel;// 所属频道
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Integer maxNumber;		// 参会人数上限
	private Integer loginType;		// 登录类型（不需要登录/需要登录/需要报名/需要报名并且审核）
	
	public MeetingMeetingInfo() {
		super();
	}

	public MeetingMeetingInfo(String id){
		super(id);
	}

	@Length(min=0, max=100, message="会议名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="主讲人长度必须介于 0 和 64 之间")
	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}
	
	//@Length(min=0, max=6, message="会议时长（分钟）长度必须介于 0 和 6 之间")
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	@Length(min=0, max=256, message="封面长度必须介于 0 和 256 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	//@Length(min=0, max=256, message="讲稿长度必须介于 0 和 256 之间")
	public String getPpt() {
		return ppt;
	}

	public void setPpt(String ppt) {
		this.ppt = ppt;
	}
	
	@Length(min=0, max=64, message="讲稿UUID长度必须介于 0 和 64 之间")
	public String getPptUuid() {
		return pptUuid;
	}

	public void setPptUuid(String pptUuid) {
		this.pptUuid = pptUuid;
	}
	
	//@Length(min=0, max=256, message="录播视频长度必须介于 0 和 256 之间")
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	
//	@Length(min=0, max=64, message="所属频道长度必须介于 0 和 64 之间")
//	public String getChannelId() {
//		return channelId;
//	}
//
//	public void setChannelId(String channelId) {
//		this.channelId = channelId;
//	}
	
	public MeetingLiveChannel getMeetingLiveChannel() {
		return meetingLiveChannel;
	}

	public void setMeetingLiveChannel(MeetingLiveChannel meetingLiveChannel) {
		this.meetingLiveChannel = meetingLiveChannel;
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
	
	//@Length(min=0, max=4, message="参会人数上限长度必须介于 0 和 4 之间")
	public Integer getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}
	
	//@Length(min=0, max=1, message="登录类型（不需要登录/需要登录/需要报名/需要报名并且审核）长度必须介于 0 和 1 之间")
	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	
}