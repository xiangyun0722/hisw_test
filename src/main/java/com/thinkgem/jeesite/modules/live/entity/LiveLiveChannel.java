/**
 * 
 */
package com.thinkgem.jeesite.modules.live.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直播频道Entity
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
public class LiveLiveChannel extends DataEntity<LiveLiveChannel> {
	
	private static final long serialVersionUID = 1L;
	
	private String livePlatform;//直播平台（0：（默认，腾讯直播。） 1：（自研直播））
	
	public static String  LIVE_PLATFORM_QCLOUD="0"; //腾讯直播  qcloud
	public static String  LIVE_PLATFORM_LOCAL="1";  //本地直播（直播服务器在本地服务器）
	
	private String name;		// 名称
	private BusinessSystem businessSystem;//所属业务系统
	private String relId;		// 外部系统关联id
	private String channelSecretKey;// 频道秘钥
	//private Integer checkChannelSecretKeyFlag;//是否需要检查 频道秘钥
	private String pushRtmpUrl;		// 推送rtmp流地址
	private String transcodingParas;// 转码参数
	private String logo;		// 频道logo
	private String rtmpUrl;		// rtmp播放地址
	private String hlsUrl;		// hls播放地址
	private String status;		// 状态(上线、下线)
	private String streamId ;	// 流名称。
	
	public LiveLiveChannel() {
		super();
	}

	public LiveLiveChannel(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="外部系统关联id长度必须介于 0 和 256 之间")
	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}
	
	@Length(min=0, max=64, message="频道秘钥长度必须介于 0 和 64 之间")
	public String getChannelSecretKey() {
		return channelSecretKey;
	}

	public void setChannelSecretKey(String channelSecretKey) {
		this.channelSecretKey = channelSecretKey;
	}
	
	@Length(min=0, max=256, message="推送rtmp流地址长度必须介于 0 和 256 之间")
	public String getPushRtmpUrl() {
		return pushRtmpUrl;
	}

	public void setPushRtmpUrl(String pushRtmpUrl) {
		this.pushRtmpUrl = pushRtmpUrl;
	}
	
	public String getTranscodingParas() {
		return transcodingParas;
	}

	public void setTranscodingParas(String transcodingParas) {
		this.transcodingParas = transcodingParas;
	}
	
	@Length(min=0, max=256, message="频道logo长度必须介于 0 和 256 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Length(min=0, max=256, message="rtmp播放地址长度必须介于 0 和 256 之间")
	public String getRtmpUrl() {
		return rtmpUrl;
	}

	public void setRtmpUrl(String rtmpUrl) {
		this.rtmpUrl = rtmpUrl;
	}
	
	@Length(min=0, max=256, message="hls播放地址长度必须介于 0 和 256 之间")
	public String getHlsUrl() {
		return hlsUrl;
	}

	public void setHlsUrl(String hlsUrl) {
		this.hlsUrl = hlsUrl;
	}
	
	@Length(min=0, max=256, message="状态(上线、下线)长度必须介于 0 和 256 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BusinessSystem getBusinessSystem() {
		return businessSystem;
	}

	public void setBusinessSystem(BusinessSystem businessSystem) {
		this.businessSystem = businessSystem;
	}

	public String getLivePlatform() {
		return livePlatform;
	}

	public void setLivePlatform(String livePlatform) {
		this.livePlatform = livePlatform;
	}

	public String getStreamId() {
		return streamId;
	}

	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	
}