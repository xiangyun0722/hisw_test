/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 节点组Entity
 * @author j.feng
 * @version 2015-07-21
 */
public class NodeGroup extends DataEntity<NodeGroup> {
	
	private static final long serialVersionUID = 1L;
	private String nodeid;		// 节点服务器ID
	private String groupid;		// 服务器组ID
	private String stationFlag;	// 原站标识 0.普通节点 1.原站节点
	
	public NodeGroup() {
		super();
	}

	public NodeGroup(String id){
		super(id);
	}

	@Length(min=0, max=11, message="节点服务器ID长度必须介于 0 和 11 之间")
	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	
	@Length(min=0, max=11, message="服务器组ID长度必须介于 0 和 11 之间")
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getStationFlag() {
		return stationFlag;
	}

	public void setStationFlag(String stationFlag) {
		this.stationFlag = stationFlag;
	}
}