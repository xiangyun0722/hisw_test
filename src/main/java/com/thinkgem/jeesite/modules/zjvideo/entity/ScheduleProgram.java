/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 排片表Entity
 * @author lyy
 * @version 2020-02-06
 */
public class ScheduleProgram extends DataEntity<ScheduleProgram> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 课程名称
	private Integer videoid;		// 视频id
	private Integer channelId;      //频道id
	private String startTime;		// 开始时间
	private String endTime;  //结束时间
	private Integer status;  //直播状态 3直播中，4已直播
	private Date date;		// 排片日期

	/**业务相关展示字段*/
	private String convertstatus; //转码任务状态
	private String coverHttpUrl; //封面图片访问地址
	private String playHttpUrl; // 视频点播地址
	private Integer length; //持续时长
	private String videoStatus; //视频状态
	private Integer fileSize; //文件大小

	private String format;//文件类型
	private String bitRate; //码率信息
	private String resolution; //分辨率
	private String picurl; //封面图url
	private String path; //视频地址url
	
	public ScheduleProgram() {
		super();
	}

	public ScheduleProgram(String id){
		super(id);
	}

	@Length(min=0, max=255, message="课程名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getVideoid() {
		return videoid;
	}

	public void setVideoid(Integer videoid) {
		this.videoid = videoid;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCoverHttpUrl() {
		return coverHttpUrl;
	}

	public void setCoverHttpUrl(String coverHttpUrl) {
		this.coverHttpUrl = coverHttpUrl;
	}

	public String getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(String videoStatus) {
		this.videoStatus = videoStatus;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getConvertstatus() {
		return convertstatus;
	}

	public void setConvertstatus(String convertstatus) {
		this.convertstatus = convertstatus;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPlayHttpUrl() {
		return playHttpUrl;
	}

	public void setPlayHttpUrl(String playHttpUrl) {
		this.playHttpUrl = playHttpUrl;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getBitRate() {
		return bitRate;
	}

	public void setBitRate(String bitRate) {
		this.bitRate = bitRate;
	}
}