/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 配置管理Entity
 * @author j.feng
 * @version 2015-05-05
 */
public class Config extends DataEntity<Config> {
	
	private static final long serialVersionUID = 1L;
	private String configExplain;		// 配置解释
	private String configkey;			// 配置key
	private String configKey;			// 配置key
	private String configValue;			// 配置value
	private String display;				// display
	private String configexplain;		// configexplain
	private Date addtime;				// addtime
	private Date edittime;				// edittime
	private String slock;				// slock
	private String remark;				// remark
	
	/*关联对象查询*/
	private Liveing liveing;			//在线直播
	
	public Liveing getLiveing() {
		return liveing;
	}

	public void setLiveing(Liveing liveing) {
		this.liveing = liveing;
	}

	public Config() {
		super();
	}

	public Config(String id){
		super(id);
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	@Length(min=0, max=255, message="配置解释长度必须介于 0 和 255 之间")
	public String getConfigExplain() {
		return configExplain;
	}

	public void setConfigExplain(String configExplain) {
		this.configExplain = configExplain;
	}
	@Length(min=0, max=255, message="配置value长度必须介于 0 和 255 之间")
	public String getConfigValue() {
		return configValue;
	}

	public String getConfigkey() {
		return configkey;
	}

	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	@Length(min=0, max=11, message="display长度必须介于 0 和 11 之间")
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
	
	@Length(min=0, max=255, message="configexplain长度必须介于 0 和 255 之间")
	public String getConfigexplain() {
		return configexplain;
	}

	public void setConfigexplain(String configexplain) {
		this.configexplain = configexplain;
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
	
	@Length(min=0, max=1, message="slock长度必须介于 0 和 1 之间")
	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}