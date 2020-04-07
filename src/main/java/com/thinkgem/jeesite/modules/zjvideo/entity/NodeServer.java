/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 节点服务器Entity
 * @author j.feng
 * @version 2015-07-16
 */
public class NodeServer extends DataEntity<NodeServer> {
	
	private static final long serialVersionUID = 1L;
	private Integer loadnum;			 // 网络负载
	private String url;				 // 视频访问前缀
	private String cdnurl;			//cdn视频访问前缀
	private String name;			 // 服务器名称
	private Integer maxload;			 // 负载上限
	private String onlineFlag;		 // 在线状态
	private String ipaddress;		 // IP地址
	private String location;		 // 机房位置 
	private String carrier ;		 // 网络运营商 1.电信 2.网通 3.联通
	
	private String stationFlag;	     // 原站标识 0.普通节点 1.原站节点
	private String optimalConfig ;	 // 节点优选比重参数
	
	public NodeServer() {
		super();
	}

	public NodeServer(String id){
		super(id);
	}

	public Integer getLoadnum() {
		return loadnum;
	}

	public void setLoadnum(Integer loadnum) {
		this.loadnum = loadnum;
	}
	
	@Length(min=0, max=255, message="视频访问前缀长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=255, message="服务器名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getMaxload() {
		return maxload;
	}

	public void setMaxload(Integer maxload) {
		this.maxload = maxload;
	}
	
	@Length(min=0, max=1, message="在线状态长度必须介于 0 和 1 之间")
	public String getOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	
	@Length(min=0, max=255, message="IP地址长度必须介于 0 和 255 之间")
	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getStationFlag() {
		return stationFlag;
	}

	public void setStationFlag(String stationFlag) {
		this.stationFlag = stationFlag;
	}

	public String getOptimalConfig() {
		return optimalConfig;
	}

	public void setOptimalConfig(String optimalConfig) {
		this.optimalConfig = optimalConfig;
	}

	public String getCdnurl() {
		return cdnurl;
	}

	public void setCdnurl(String cdnurl) {
		this.cdnurl = cdnurl;
	}
}