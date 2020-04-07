/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Project;

/**
 * 系统上传文件Entity
 * @author twk
 * @version 2015-07-27
 */
public class Uploadfile extends DataEntity<Uploadfile> {
	
	private static final long serialVersionUID = 1L;
	private String filename;	// 文件名
	private String filepath;	//上传后的文件路径
	private Long filesize;		// 文件大小
	private Integer schunk;		// 已上传数
	private Integer schunks;	// 总分割数
	private Project project; 	//所属项目
	private User uploadBy;		//上传人
	
	
	public Uploadfile() {
		super();
		//设置默认上传用户。
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.uploadBy = user;
		}
	}

	public Uploadfile(String id){
		super(id);
	}

	@Length(min=1, max=255, message="文件名长度必须介于 1 和 255 之间")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Length(min=1, max=14, message="文件大小长度必须介于 1 和 14 之间")
	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	
	@Length(min=1, max=11, message="已上传数长度必须介于 1 和 11 之间")
	public Integer getSchunk() {
		return schunk;
	}

	public void setSchunk(Integer schunk) {
		this.schunk = schunk;
	}
	
	@Length(min=1, max=11, message="总分割数长度必须介于 1 和 11 之间")
	public Integer getSchunks() {
		return schunks;
	}

	public void setSchunks(Integer schunks) {
		this.schunks = schunks;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(User uploadBy) {
		this.uploadBy = uploadBy;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}