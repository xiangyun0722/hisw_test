/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图片Entity
 * @author lyy
 * @version 2020-02-06
 */
public class Pic extends DataEntity<Pic> {
	
	private static final long serialVersionUID = 1L;
	private String source;		// 保存路径
	private String url;		// 访问url

	private String httpUrl; //完整访问url
	
	public Pic() {
		super();
	}

	public Pic(String id){
		super(id);
	}

	@Length(min=0, max=255, message="保存路径长度必须介于 0 和 255 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=255, message="访问url长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}
}