/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.TreeEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 项目信息表Entity
 * @author j.feng
 * @version 2015-07-09
 */
public class Project extends TreeEntity<Project> {
	
	private static final long serialVersionUID = 1L;
	private String companyid;	  // 单位ID
	private String code;		  // 项目编码
	private String type;		  // 项目类型
	private Office office;		  // 归属单位
	private String ftpautopath;	  // ftp自动转码目录
	private String ftpmanupath;   // web手动转码目录
	private String repositorypath;// 资源库目录
	private String loginName;	  //登陆名
	
	
	
	private List<Integer> templatesids;//项目拥有的全部模板信息。
	
	private List<Templates> templatesList;//所属模板信息。
	
	public Project() {
		super();
		this.sort = 30;
	}

	public Project(String id){
		super(id);
	}

	@Length(min=0, max=11, message="单位ID长度必须介于 0 和 11 之间")
	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
	@Length(min=0, max=100, message="项目编码长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=1, message="项目类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Project getParent() {
		return parent;
	}

	public void setParent(Project parent) {
		this.parent = parent;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getFtpautopath() {
		return ftpautopath;
	}

	public void setFtpautopath(String ftpautopath) {
		this.ftpautopath = ftpautopath;
	}

	public String getFtpmanupath() {
		return ftpmanupath;
	}

	public void setFtpmanupath(String ftpmanupath) {
		this.ftpmanupath = ftpmanupath;
	}

	public String getRepositorypath() {
		return repositorypath;
	}

	public void setRepositorypath(String repositorypath) {
		this.repositorypath = repositorypath;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<Integer> getTemplatesids() {
		return templatesids;
	}

	public void setTemplatesids(List<Integer> templatesids) {
		this.templatesids = templatesids;
	}

	public List<Templates> getTemplatesList() {
		return templatesList;
	}

	public void setTemplatesList(List<Templates> templatesList) {
		this.templatesList = templatesList;
	}

	
}