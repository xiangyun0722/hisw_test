/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直播频道Entity
 * @author j.feng
 * @version 2015-08-14
 */
public class MeetingLiveChannel extends DataEntity<MeetingLiveChannel> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String logo;		// 频道logo
	private String rtmpHd;		// rtmp高清
	private String rtmpSd;		// rtmp标清
	private String rtmpNd;		// rtmp普清
	private String rtmpVn;		// rtmp声音
	private String hlsHd;		// hls高清
	private String hlsSd;		// hls标清
	private String hlsNd;		// hls普清
	private String hlsVn;		// hls声音
	private Integer status;		// 状态(上线、下线)
	
	/***
	 * 上线
	 */
	public static int statusUplines=1;
	
	/***
	 * 下线
	 */
	public static int statusDownlines=0;

	
	public MeetingLiveChannel() {
		super();
	}

	public MeetingLiveChannel(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="频道logo长度必须介于 0 和 256 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Length(min=0, max=256, message="rtmp高清长度必须介于 0 和 256 之间")
	public String getRtmpHd() {
		return rtmpHd;
	}

	public void setRtmpHd(String rtmpHd) {
		this.rtmpHd = rtmpHd;
	}
	
	@Length(min=0, max=256, message="rtmp标清长度必须介于 0 和 256 之间")
	public String getRtmpSd() {
		return rtmpSd;
	}

	public void setRtmpSd(String rtmpSd) {
		this.rtmpSd = rtmpSd;
	}
	
	@Length(min=0, max=256, message="rtmp普清长度必须介于 0 和 256 之间")
	public String getRtmpNd() {
		return rtmpNd;
	}

	public void setRtmpNd(String rtmpNd) {
		this.rtmpNd = rtmpNd;
	}
	
	@Length(min=0, max=256, message="rtmp声音长度必须介于 0 和 256 之间")
	public String getRtmpVn() {
		return rtmpVn;
	}

	public void setRtmpVn(String rtmpVn) {
		this.rtmpVn = rtmpVn;
	}
	
	@Length(min=0, max=256, message="hls高清长度必须介于 0 和 256 之间")
	public String getHlsHd() {
		return hlsHd;
	}

	public void setHlsHd(String hlsHd) {
		this.hlsHd = hlsHd;
	}
	
	@Length(min=0, max=256, message="hls标清长度必须介于 0 和 256 之间")
	public String getHlsSd() {
		return hlsSd;
	}

	public void setHlsSd(String hlsSd) {
		this.hlsSd = hlsSd;
	}
	
	@Length(min=0, max=256, message="hls普清长度必须介于 0 和 256 之间")
	public String getHlsNd() {
		return hlsNd;
	}

	public void setHlsNd(String hlsNd) {
		this.hlsNd = hlsNd;
	}
	
	@Length(min=0, max=256, message="hls声音长度必须介于 0 和 256 之间")
	public String getHlsVn() {
		return hlsVn;
	}

	public void setHlsVn(String hlsVn) {
		this.hlsVn = hlsVn;
	}
	
	//@Length(min=0, max=256, message="状态(上线、下线)长度必须介于 0 和 256 之间")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}