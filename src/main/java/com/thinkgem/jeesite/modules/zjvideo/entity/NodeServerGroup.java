/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 节点服务器组Entity
 * @author j.feng
 * @version 2015-07-16
 */
public class NodeServerGroup extends DataEntity<NodeServerGroup> {
	
	private static final long serialVersionUID = 1L;
	private String groupname;		// 组名称
	
	private NodeServer nodeServer;									//节点服务器对象
	private List<Integer> nodeServierid;							//节点服务器ids
	private List<NodeServer> nodeServerList = Lists.newArrayList(); //节点服务器列表	
	private String stationFlag;	    // 原站标识 0.普通节点 1.原站节点
	
	public NodeServerGroup() {
		super();
	}

	public NodeServerGroup(String id){
		super(id);
	}

	@Length(min=0, max=255, message="组名称长度必须介于 0 和 255 之间")
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public NodeServer getNodeServer() {
		return nodeServer;
	}

	public void setNodeServer(NodeServer nodeServer) {
		this.nodeServer = nodeServer;
	}

	public List<Integer> getNodeServierid() {
		return nodeServierid;
	}

	public void setNodeServierid(List<Integer> nodeServierid) {
		this.nodeServierid = nodeServierid;
	}

	public List<NodeServer> getNodeServerList() {
		return nodeServerList;
	}

	public void setNodeServerList(List<NodeServer> nodeServerList) {
		this.nodeServerList = nodeServerList;
	}

	public String getStationFlag() {
		return stationFlag;
	}

	public void setStationFlag(String stationFlag) {
		this.stationFlag = stationFlag;
	}
}