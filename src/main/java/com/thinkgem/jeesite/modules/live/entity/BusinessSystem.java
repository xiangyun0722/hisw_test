/**
 * 
 */
package com.thinkgem.jeesite.modules.live.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直播业务系统Entity
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
public class BusinessSystem extends DataEntity<BusinessSystem> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 接入系统名称
	private String logo;		// 图标
	private String publicKey;		// 公共key
	
	public BusinessSystem() {
		super();
	}

	public BusinessSystem(String id){
		super(id);
	}

	@Length(min=0, max=64, message="接入系统名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="图标长度必须介于 0 和 256 之间")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Length(min=0, max=64, message="公共key长度必须介于 0 和 64 之间")
	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
}