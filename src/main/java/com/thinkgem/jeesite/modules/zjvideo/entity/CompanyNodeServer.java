/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 单位节点Entity
 * @author j.feng
 * @version 2015-07-16
 */
public class CompanyNodeServer extends DataEntity<CompanyNodeServer> {
	
	private static final long serialVersionUID = 1L;
	private String nodeserverid;	// 服务器ID
	private String companyid;		// 单位ID
	private String strategyType;	// 策略类型
	private String optimizationNode;// 优选节点相关特征
	
	//关联相关对象
	private NodeServer nodeServer;  //节点服务器
	private Office office;		  	//归属单位
	
	public CompanyNodeServer() {
		super();
	}

	public CompanyNodeServer(String id){
		super(id);
	}

	@Length(min=0, max=11, message="服务器ID长度必须介于 0 和 11 之间")
	public String getNodeserverid() {
		return nodeserverid;
	}

	public void setNodeserverid(String nodeserverid) {
		this.nodeserverid = nodeserverid;
	}
	
	@Length(min=0, max=11, message="单位ID长度必须介于 0 和 11 之间")
	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
	@Length(min=0, max=255, message="策略类型长度必须介于 0 和 255 之间")
	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}
	
	@Length(min=0, max=1, message="优选节点相关特征长度必须介于 0 和 1 之间")
	public String getOptimizationNode() {
		return optimizationNode;
	}

	public void setOptimizationNode(String optimizationNode) {
		this.optimizationNode = optimizationNode;
	}

	public NodeServer getNodeServer() {
		return nodeServer;
	}

	public void setNodeServer(NodeServer nodeServer) {
		this.nodeServer = nodeServer;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
}