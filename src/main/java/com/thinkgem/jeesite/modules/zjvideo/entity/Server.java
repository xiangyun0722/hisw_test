/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 视频服务器管理Entity
 * @author j.feng
 * @version 2015-05-06
 */
public class Server extends DataEntity<Server> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 服务器名称
	private String url;		    // url前缀地址
	private String ipaddress;	// ip地址
	private Date addtine;		// 添加时间
	private Date edittime;		// 修改时间
	private String slock;		// slock
	private String servertype;	// 服务器类型
	private String status;		// 服务器状态
	private String rtmpsd;		// pc标清地址
	private String rtmpnd;		// pc普清地址
	private String rtmphd;		// pc高清地址
	private String rtmpvn;		// pc声音地址
	private String m3u8sd;		// ios标清地址
	private String m3u8nd;		// ios普清地址
	private String m3u8hd;		// ios高清地址
	private String m3u8vn;		// ios声音地址
	
	public Server() {
		super();
	}

	public Server(String id){
		super(id);
	}

	@Length(min=0, max=50, message="服务器名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="url前缀地址长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=50, message="ip地址长度必须介于 0 和 50 之间")
	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtine() {
		return addtine;
	}

	public void setAddtine(Date addtine) {
		this.addtine = addtine;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEdittime() {
		return edittime;
	}

	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	
	@Length(min=0, max=1, message="slock长度必须介于 0 和 1 之间")
	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}
	
	@Length(min=0, max=1, message="服务器类型长度必须介于 0 和 1 之间")
	public String getServertype() {
		return servertype;
	}

	public void setServertype(String servertype) {
		this.servertype = servertype;
	}
	
	@Length(min=0, max=1, message="服务器状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="pc标清地址长度必须介于 0 和 255 之间")
	public String getRtmpsd() {
		return rtmpsd;
	}

	public void setRtmpsd(String rtmpsd) {
		this.rtmpsd = rtmpsd;
	}
	
	@Length(min=0, max=255, message="pc普清地址长度必须介于 0 和 255 之间")
	public String getRtmpnd() {
		return rtmpnd;
	}

	public void setRtmpnd(String rtmpnd) {
		this.rtmpnd = rtmpnd;
	}
	
	@Length(min=0, max=255, message="pc高清地址长度必须介于 0 和 255 之间")
	public String getRtmphd() {
		return rtmphd;
	}

	public void setRtmphd(String rtmphd) {
		this.rtmphd = rtmphd;
	}
	
	@Length(min=0, max=255, message="pc声音地址长度必须介于 0 和 255 之间")
	public String getRtmpvn() {
		return rtmpvn;
	}

	public void setRtmpvn(String rtmpvn) {
		this.rtmpvn = rtmpvn;
	}
	
	@Length(min=0, max=255, message="ios标清地址长度必须介于 0 和 255 之间")
	public String getM3u8sd() {
		return m3u8sd;
	}

	public void setM3u8sd(String m3u8sd) {
		this.m3u8sd = m3u8sd;
	}
	
	@Length(min=0, max=255, message="ios普清地址长度必须介于 0 和 255 之间")
	public String getM3u8nd() {
		return m3u8nd;
	}

	public void setM3u8nd(String m3u8nd) {
		this.m3u8nd = m3u8nd;
	}
	
	@Length(min=0, max=255, message="ios高清地址长度必须介于 0 和 255 之间")
	public String getM3u8hd() {
		return m3u8hd;
	}

	public void setM3u8hd(String m3u8hd) {
		this.m3u8hd = m3u8hd;
	}
	
	@Length(min=0, max=255, message="ios声音地址长度必须介于 0 和 255 之间")
	public String getM3u8vn() {
		return m3u8vn;
	}

	public void setM3u8vn(String m3u8vn) {
		this.m3u8vn = m3u8vn;
	}
	
}