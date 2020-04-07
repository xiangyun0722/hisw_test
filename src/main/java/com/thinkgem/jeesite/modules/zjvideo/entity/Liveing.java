/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 频道管理Entity
 * @author j.feng
 * @version 2015-05-06
 */
public class Liveing extends DataEntity<Liveing> {
	
	private static final long serialVersionUID = 1L;
	private String livename;	// 频道名称
	private String livenum;		// 频道号
	private String playurl;		// 播放地址
	private String picurl;		// 频道logo
	private String isonline;	// 在线
	private String viewtimes;	// viewtimes
	private Date addtime;		// 增加时间
	private Date edittime;		// 最后修改时间
	private String slock;		// 状态标志位0:正常，1：锁定
	
	public Liveing() {
		super();
	}

	public Liveing(String id){
		super(id);
	}

	@Length(min=0, max=150, message="频道名称长度必须介于 0 和 150 之间")
	public String getLivename() {
		return livename;
	}

	public void setLivename(String livename) {
		this.livename = livename;
	}
	
	@Length(min=0, max=50, message="频道号长度必须介于 0 和 50 之间")
	public String getLivenum() {
		return livenum;
	}

	public void setLivenum(String livenum) {
		this.livenum = livenum;
	}
	
	@Length(min=0, max=255, message="播放地址长度必须介于 0 和 255 之间")
	public String getPlayurl() {
		return playurl;
	}

	public void setPlayurl(String playurl) {
		this.playurl = playurl;
	}
	
	@Length(min=0, max=255, message="频道logo长度必须介于 0 和 255 之间")
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	@Length(min=0, max=1, message="在线长度必须介于 0 和 1 之间")
	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}
	
	@Length(min=0, max=11, message="viewtimes长度必须介于 0 和 11 之间")
	public String getViewtimes() {
		return viewtimes;
	}

	public void setViewtimes(String viewtimes) {
		this.viewtimes = viewtimes;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEdittime() {
		return edittime;
	}

	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	
	@Length(min=0, max=1, message="状态标志位0:正常，1：锁定长度必须介于 0 和 1 之间")
	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}
	
}