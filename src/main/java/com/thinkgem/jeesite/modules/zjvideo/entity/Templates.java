/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 视频模板Entity
 * @author j.feng
 * @version 2015-05-21
 */
public class Templates extends DataEntity<Templates> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 模板名称
	private String cmd;			// 模板命令
	private String format;		// 转换视频格式 1.flv 2.m3u8
	private String type;		// 视频类型:nd.普清 sd.标清 fd.流畅 hd.高清 ed.超清
	private String watermark;		// 水印
	
	private List<Videos> videosList = Lists.newArrayList(); // 拥有视频列表
	private String convertstatus;	// 转码状态(-1等待转码,0：转码中，1转码成功，2转码失败）
	//-1 waiting for transcoding, 0: transcoding, 1 transcoding success, 2 transcoding fails
	public static String convertstatus_wait="-1";
	public static String convertstatus_transcoding="0";
	public static String convertstatus_success="1";
	public static String convertstatus_fails="2";

	public Templates() {
		super();
	}

	public Templates(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Videos> getVideosList() {
		return videosList;
	}

	public void setVideosList(List<Videos> videosList) {
		this.videosList = videosList;
	}

	public String getConvertstatus() {
		return convertstatus;
	}

	public void setConvertstatus(String convertstatus) {
		this.convertstatus = convertstatus;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}
	
}