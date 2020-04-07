/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.entity;

import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.hisw.core.utils.DateFormatter;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 系统文档转码功能Entity
 * @author twk
 * @version 2015-08-10
 */
public class DocumentTranscodingTask extends DataEntity<DocumentTranscodingTask> {
	
	private static final long serialVersionUID = 1L;
	private String dealHostIp;		// 处理主机IP
	private String businessId;		// 业务id
	private String originalName;	// 原始名称
	private String filepath;		// 文件地址
	private Long  length ;			//文件大小
	private String fileJson;		//文件json信息。
	private Integer status;		// 转码状态
	private Integer totalPageNumber;		// 总页数
	private Integer onePageOneSwfFlag;		// 一个页面一个swf标示(0：一个文件一个swf，1：一个页面一个swf文件）
	private String swfsPath;		// swfs文件地址
	private String swfPath;		// 单个swf文件地址
	private String imagesPath;		// 图片地址
	private String jsPath;		// js文件
	private String childThreadTotal;		// 子线程总数
	private String childThreadSuccess;		// 子线程成功数
	private String childThreadFaill;		// 子线程失败数
	
	private String keywords;	//关键字。
	
	/***
	 * 初始化全部的路径信息。 
	 */
	public void initPathInfo(){
//		swf的目录：转码后的文件根目录/         doc/年/月/日/uuid/swf/文件名称.swf
//		多个swf的目录：转码后的文件根目录/      doc/年/月/日/uuid/swf/文件名称_%d.swf
//		img的目录：转码后的文件根目录/         doc/年/月/日/uuid/images/文件名称_%d.png
//		js 的目录：转码后的文件根目录/         doc/年/月/日/uuid/images/文件名称.js
		String path="doc/"+DateFormatter.shortDirDate(new Date())+"/"+getId()+"/";
		String baseName =StringUtils.remove(FilenameUtils.getBaseName(filepath), " ") ;
		swfsPath=path+"swf/"+baseName+"_%.swf";		// swf文件的路径
		swfPath=path+"swf/"+baseName+".swf";
		imagesPath=path+"images/"+baseName+"_%d.png";
		jsPath=path+"images/"+baseName+".js";		// js文件地址(移动端）
	}
	
	
	public DocumentTranscodingTask() {
		super();
	}

	public DocumentTranscodingTask(String id){
		super(id);
	}

	@Length(min=0, max=255, message="处理主机IP长度必须介于 0 和 255 之间")
	public String getDealHostIp() {
		return dealHostIp;
	}

	public void setDealHostIp(String dealHostIp) {
		this.dealHostIp = dealHostIp;
	}
	
	@Length(min=1, max=64, message="业务id长度必须介于 1 和 64 之间")
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	@Length(min=1, max=255, message="原始名称长度必须介于 1 和 255 之间")
	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	//@Length(min=1, max=255, message="文件地址长度必须介于 1 和 255 之间")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setTotalPageNumber(Integer totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}
	
	public Integer getOnePageOneSwfFlag() {
		return onePageOneSwfFlag;
	}

	public void setOnePageOneSwfFlag(Integer onePageOneSwfFlag) {
		this.onePageOneSwfFlag = onePageOneSwfFlag;
	}
	
	@Length(min=1, max=255, message="swfs文件地址长度必须介于 1 和 255 之间")
	public String getSwfsPath() {
		return swfsPath;
	}

	public void setSwfsPath(String swfsPath) {
		this.swfsPath = swfsPath;
	}
	
	@Length(min=1, max=255, message="单个swf文件地址长度必须介于 1 和 255 之间")
	public String getSwfPath() {
		return swfPath;
	}

	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}
	
	@Length(min=1, max=255, message="图片地址长度必须介于 1 和 255 之间")
	public String getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}
	
	@Length(min=1, max=255, message="js文件长度必须介于 1 和 255 之间")
	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}
	
	@Length(min=1, max=2, message="子线程总数长度必须介于 1 和 2 之间")
	public String getChildThreadTotal() {
		return childThreadTotal;
	}

	public void setChildThreadTotal(String childThreadTotal) {
		this.childThreadTotal = childThreadTotal;
	}
	
	@Length(min=1, max=2, message="子线程成功数长度必须介于 1 和 2 之间")
	public String getChildThreadSuccess() {
		return childThreadSuccess;
	}

	public void setChildThreadSuccess(String childThreadSuccess) {
		this.childThreadSuccess = childThreadSuccess;
	}
	
	@Length(min=1, max=2, message="子线程失败数长度必须介于 1 和 2 之间")
	public String getChildThreadFaill() {
		return childThreadFaill;
	}

	public void setChildThreadFaill(String childThreadFaill) {
		this.childThreadFaill = childThreadFaill;
	}


	public Long getLength() {
		return length;
	}


	public void setLength(Long length) {
		this.length = length;
	}


	public String getFileJson() {
		return fileJson;
	}


	public void setFileJson(String fileJson) {
		this.fileJson = fileJson;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}