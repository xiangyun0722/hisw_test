/**
 * 
 */
package com.thinkgem.jeesite.modules.live.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直播频道录制文件Entity
 * @author tanwenkai@qq.com
 * @version 2016-12-12
 */
public class ChannelFiles extends DataEntity<ChannelFiles> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 文件名
	private LiveLiveChannel liveChannel;		// 直播频道id
	private Long fileSize;		// 文件大小
	private String filePath;		// 文件地址
	private String downloadUrl;		// 下载地址
	private Date startDatetime;		// 开始录制时间
	private Date endDatetime;		// 结束录制时间
	private String playTime;		// 总时长
	
	public ChannelFiles() {
		super();
	}

	public ChannelFiles(String id){
		super(id);
	}

	@Length(min=0, max=64, message="文件名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LiveLiveChannel getLiveChannel() {
		return liveChannel;
	}

	public void setLiveChannel(LiveLiveChannel liveChannel) {
		this.liveChannel = liveChannel;
	}
	
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Length(min=0, max=255, message="文件地址长度必须介于 0 和 255 之间")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Length(min=0, max=256, message="下载地址长度必须介于 0 和 256 之间")
	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
	
	@Length(min=0, max=64, message="总时长长度必须介于 0 和 64 之间")
	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	
}