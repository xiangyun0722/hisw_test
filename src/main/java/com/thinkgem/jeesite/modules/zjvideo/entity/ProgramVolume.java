/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 章节信息Entity
 * @author j.feng
 * @version 2015-05-07
 */
public class ProgramVolume extends DataEntity<ProgramVolume> {
	
	private static final long serialVersionUID = 1L;
	private String programid;	// 课程ID
	private String name;		// 章节名称
	private String volume;		// 章节序号
	private String picurl;		// 章节海报
	private String detail;		// 章节描述
	private String palytimes;	// 播放次数
	private Date addtime;		// 增加时间
	private String adder;		// 增加者
	private Date edittime;		// 最后修改时间
	private String editer;		// 最后修改者
	private String slock;		// 锁定标志
	private String title;		// 教师职称
	private String realname;	// 教师姓名
	private String profile;		// 教师简介
	private String url;			// 课件访问地址
	private String pptUuid;		// 课件唯一标识
	private String prefix;		// 文件前缀
	private String suffix;		// 文件后缀
	private String pagesize;	// ppt页面个数
	private String exercise;	// 练习题目访问地址
	
	/*关联对象查询*/
	private Program program;    //课程对象
	private String videoId;     //视频ID
	private String videoName;   //视频名字
	
	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public ProgramVolume() {
		super();
	}

	public ProgramVolume(String id){
		super(id);
	}

	@Length(min=0, max=11, message="课程ID长度必须介于 0 和 11 之间")
	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}
	
	@Length(min=0, max=100, message="章节名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=6, message="章节序号长度必须介于 0 和 6 之间")
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=11, message="播放次数长度必须介于 0 和 11 之间")
	public String getPalytimes() {
		return palytimes;
	}

	public void setPalytimes(String palytimes) {
		this.palytimes = palytimes;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	@Length(min=0, max=50, message="增加者长度必须介于 0 和 50 之间")
	public String getAdder() {
		return adder;
	}

	public void setAdder(String adder) {
		this.adder = adder;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEdittime() {
		return edittime;
	}

	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	
	@Length(min=0, max=50, message="最后修改者长度必须介于 0 和 50 之间")
	public String getEditer() {
		return editer;
	}

	public void setEditer(String editer) {
		this.editer = editer;
	}
	
	@Length(min=0, max=1, message="锁定标志长度必须介于 0 和 1 之间")
	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}
	
	@Length(min=0, max=255, message="教师职称长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="教师姓名长度必须介于 0 和 255 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=255, message="教师简介长度必须介于 0 和 255 之间")
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=255, message="文件前缀长度必须介于 0 和 255 之间")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Length(min=0, max=255, message="文件后缀长度必须介于 0 和 255 之间")
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	@Length(min=0, max=255, message="ppt页面个数长度必须介于 0 和 255 之间")
	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	
	@Length(min=0, max=255, message="练习题目访问地址长度必须介于 0 和 255 之间")
	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public String getPptUuid() {
		return pptUuid;
	}

	public void setPptUuid(String pptUuid) {
		this.pptUuid = pptUuid;
	}
}