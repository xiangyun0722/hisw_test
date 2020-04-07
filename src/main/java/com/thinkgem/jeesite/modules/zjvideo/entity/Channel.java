/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.List;

/**
 * 频道Entity
 * @author lyy
 * @version 2020-02-06
 */
public class Channel extends DataEntity<Channel> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 频道名称
	private Integer picid;		// 水印图片id
	private String pushUrlOne;		// 推流地址1
	private String playUrlOne;		// 播放地址1
	private String pushUrlTwo;		// 推流地址2
	private String playUrlTwo;		// 播放地址2
	private String pushUrlThree;		// 推流地址3
	private String playUrlThree;		// 播放地址3

	private String picHttpUrl; //水印图片url
	
	private String templateid;  //模板id

	private List<ScheduleProgram> programList;
	
	public Channel() {
		super();
	}

	public Channel(String id){
		super(id);
	}

	@Length(min=0, max=255, message="频道名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPicid() {
		return picid;
	}

	public void setPicid(Integer picid) {
		this.picid = picid;
	}
	
	@Length(min=0, max=255, message="推流地址1长度必须介于 0 和 255 之间")
	public String getPushUrlOne() {
		return pushUrlOne;
	}

	public void setPushUrlOne(String pushUrlOne) {
		this.pushUrlOne = pushUrlOne;
	}
	
	@Length(min=0, max=255, message="播放地址1长度必须介于 0 和 255 之间")
	public String getPlayUrlOne() {
		return playUrlOne;
	}

	public void setPlayUrlOne(String playUrlOne) {
		this.playUrlOne = playUrlOne;
	}
	
	@Length(min=0, max=255, message="推流地址2长度必须介于 0 和 255 之间")
	public String getPushUrlTwo() {
		return pushUrlTwo;
	}

	public void setPushUrlTwo(String pushUrlTwo) {
		this.pushUrlTwo = pushUrlTwo;
	}
	
	@Length(min=0, max=255, message="播放地址2长度必须介于 0 和 255 之间")
	public String getPlayUrlTwo() {
		return playUrlTwo;
	}

	public void setPlayUrlTwo(String playUrlTwo) {
		this.playUrlTwo = playUrlTwo;
	}
	
	@Length(min=0, max=255, message="推流地址3长度必须介于 0 和 255 之间")
	public String getPushUrlThree() {
		return pushUrlThree;
	}

	public void setPushUrlThree(String pushUrlThree) {
		this.pushUrlThree = pushUrlThree;
	}
	
	@Length(min=0, max=255, message="播放地址3长度必须介于 0 和 255 之间")
	public String getPlayUrlThree() {
		return playUrlThree;
	}

	public void setPlayUrlThree(String playUrlThree) {
		this.playUrlThree = playUrlThree;
	}

	public List<ScheduleProgram> getProgramList() {
		return programList;
	}

	public void setProgramList(List<ScheduleProgram> programList) {
		this.programList = programList;
	}

	public String getPicHttpUrl() {
		return picHttpUrl;
	}

	public void setPicHttpUrl(String picHttpUrl) {
		this.picHttpUrl = picHttpUrl;
	}
	
	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
}