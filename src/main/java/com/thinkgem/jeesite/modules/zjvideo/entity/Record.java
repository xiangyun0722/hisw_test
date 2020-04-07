/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 录制Entity
 * @author j.feng
 * @version 2015-06-10
 */
public class Record extends DataEntity<Record> {
	
	private static final long serialVersionUID = 1L;
	private Date begintime;		// 开始时间
	private Date endtime;		// 结束时间
	private String name;		// 文件名称
	private String source;		// 文件源地址
	private String programvolumeid;// 章节ID
	private String liveid;		// 录制频道ID
	private String classroomid;	// 教室ID
	
	public Record() {
		super();
	}

	public Record(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@Length(min=0, max=255, message="文件名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="文件源地址长度必须介于 0 和 255 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=11, message="章节ID长度必须介于 0 和 11 之间")
	public String getProgramvolumeid() {
		return programvolumeid;
	}

	public void setProgramvolumeid(String programvolumeid) {
		this.programvolumeid = programvolumeid;
	}
	
	@Length(min=0, max=11, message="录制频道ID长度必须介于 0 和 11 之间")
	public String getLiveid() {
		return liveid;
	}

	public void setLiveid(String liveid) {
		this.liveid = liveid;
	}
	
	@Length(min=0, max=11, message="classroomid长度必须介于 0 和 11 之间")
	public String getClassroomid() {
		return classroomid;
	}

	public void setClassroomid(String classroomid) {
		this.classroomid = classroomid;
	}
	
}