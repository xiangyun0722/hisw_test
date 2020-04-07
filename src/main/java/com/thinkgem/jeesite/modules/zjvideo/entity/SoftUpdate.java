/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 软件更新Entity
 * @author j.feng
 * @version 2015-05-05
 */
public class SoftUpdate extends DataEntity<SoftUpdate> {
	
	private static final long serialVersionUID = 1L;
	private String platform;		// 1、Android Phone，2、ios 3：Android TV
	private String externalurl;		// externalurl
	private String filename;		// filename
	private String forceflag;		// 0：不强制，1，强制升级
	private String mainversion;		// mainversion
	private String minorversion;	// minorversion
	private String androidversion;	// androidversion
	private String detail;			// detail
	private String fileurl;			// fileurl
	private Date addtime;			// 增加时间
	private Date edittime;			// 最后修改 时间
	private String slock;			// 状态标志位0:正常，1：锁定
	
	public SoftUpdate() {
		super();
	}

	public SoftUpdate(String id){
		super(id);
	}

	@Length(min=0, max=4, message="1、Android Phone，2、ios 3：Android TV长度必须介于 0 和 4 之间")
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	@Length(min=0, max=255, message="externalurl长度必须介于 0 和 255 之间")
	public String getExternalurl() {
		return externalurl;
	}

	public void setExternalurl(String externalurl) {
		this.externalurl = externalurl;
	}
	
	@Length(min=0, max=50, message="filename长度必须介于 0 和 50 之间")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Length(min=0, max=1, message="0：不强制，1，强制升级长度必须介于 0 和 1 之间")
	public String getForceflag() {
		return forceflag;
	}

	public void setForceflag(String forceflag) {
		this.forceflag = forceflag;
	}
	
	@Length(min=0, max=4, message="mainversion长度必须介于 0 和 4 之间")
	public String getMainversion() {
		return mainversion;
	}

	public void setMainversion(String mainversion) {
		this.mainversion = mainversion;
	}
	
	@Length(min=0, max=4, message="minorversion长度必须介于 0 和 4 之间")
	public String getMinorversion() {
		return minorversion;
	}

	public void setMinorversion(String minorversion) {
		this.minorversion = minorversion;
	}
	
	@Length(min=0, max=11, message="androidversion长度必须介于 0 和 11 之间")
	public String getAndroidversion() {
		return androidversion;
	}

	public void setAndroidversion(String androidversion) {
		this.androidversion = androidversion;
	}
	
	@Length(min=0, max=2000, message="detail长度必须介于 0 和 2000 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=255, message="fileurl长度必须介于 0 和 255 之间")
	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
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