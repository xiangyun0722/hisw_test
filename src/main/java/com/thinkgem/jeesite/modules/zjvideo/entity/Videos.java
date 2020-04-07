/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 视频管理Entity
 * @author j.feng
 * @version 2015-05-06
 */
public class Videos extends DataEntity<Videos> {
	
	private static final long serialVersionUID = 1L;
	private String programvolumeid;		// 所属课程
	private String name;				// 视频名称
	private String source;				// 视频源地址
	private String clazzid;				// 分类
	private String taglist;				// 标签
	private String picurl;				// 封面图片
	private String length;				// 时长
	private String videotype;			// 视频类型
	private String path;				// 视频相对路径
	private String videocodec;			// 视频编码格式
	private String fileszie;			// 视频大小
	private String avgrate;				// 码率
	private String format;				// FLV,F4V,MP4,3GP,TS,AAC,MP3,VP6,VP8
	private String videorate;			// 文件码流
	private String fps;					// 帧率
	private String width;				// 宽度
	private String height;				// 高度
	private String audiocodec;			// 声音采样率
	private String audiorate;			// 声音码率
	private String samplingrate;		// 采样率
	private String quality;				// 质量格式
	private String protocol;			// HTTP,RTMP,RTSP,HTTP_STREAM,MMS
	private String terminaltype;		// IPAD,ANDROID3,ANDROID2,ANDROID,ANDROID1,HTML5,FLASH
	private String resolution;			// 视频分辨率
	private String aspectratioid;		// 媒体纵横比
	private String status;				// 状态            1:默认            2：审核成功            9：审核失败
	private String auditinfo;			// 审核失败原因
	private Date addtime;				// 增加时间
	private String adder;				// 增加者
	private Date edittime;				// 最后修改时间
	private String editer;				// 最后修改者
	private String slock;				// 锁定标志
	private String mp4path;				// mp4视频路径
	private String generatetaskflag;    // 生成转码任务状态
	private String projectid;    		// 项目ID
	private String companyid;    		// 公司ID
	private String companyCode;    		// 公司编码
	private String companyName;    		// 公司名称

	/*关联对象查询*/
	private ProgramVolume programVolume;//章节对象
	private Project project;    		//项目对象
	
	private Templates templates;		//模板对象
	private List<Integer> templateId;	//模板ids
	private List<Templates> templatesList = Lists.newArrayList(); // 拥有模板列表
	
	private String type;				// 视频类型:nd.普清 sd.标清 fd.流畅 hd.高清 ed.超清
	private User uploadBy;//上传人。

	private String convertstatus;//转码状态(0：转码中，1转码成功，2转码失败）

	private String cdnPath; //cdn播放路径
	
	
	public List<Integer> getTemplateId() {
		return templateId;
	}

	public void setTemplateId(List<Integer> templateId) {
		this.templateId = templateId;
	}

	public Templates getTemplates() {
		return templates;
	}

	public void setTemplates(Templates templates) {
		this.templates = templates;
	}

	public List<Templates> getTemplatesList() {
		return templatesList;
	}

	public void setTemplatesList(List<Templates> templatesList) {
		this.templatesList = templatesList;
	}
	
	public ProgramVolume getProgramVolume() {
		return programVolume;
	}

	public void setProgramVolume(ProgramVolume programVolume) {
		this.programVolume = programVolume;
	}

	public Videos() {
		super();
	}

	public Videos(String id){
		super(id);
	}

	@Length(min=0, max=11, message="所属课程长度必须介于 0 和 11 之间")
	public String getProgramvolumeid() {
		return programvolumeid;
	}

	public void setProgramvolumeid(String programvolumeid) {
		this.programvolumeid = programvolumeid;
	}
	
	@Length(min=0, max=255, message="视频名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="视频源地址长度必须介于 0 和 255 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=11, message="分类长度必须介于 0 和 11 之间")
	public String getClazzid() {
		return clazzid;
	}

	public void setClazzid(String clazzid) {
		this.clazzid = clazzid;
	}
	
	@Length(min=0, max=255, message="标签长度必须介于 0 和 255 之间")
	public String getTaglist() {
		return taglist;
	}

	public void setTaglist(String taglist) {
		this.taglist = taglist;
	}
	
	@Length(min=0, max=200, message="封面图片长度必须介于 0 和 200 之间")
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	@Length(min=0, max=255, message="时长长度必须介于 0 和 255 之间")
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
	@Length(min=0, max=10, message="视频类型长度必须介于 0 和 10 之间")
	public String getVideotype() {
		return videotype;
	}

	public void setVideotype(String videotype) {
		this.videotype = videotype;
	}
	
	@Length(min=0, max=255, message="视频相对路径长度必须介于 0 和 255 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=10, message="视频编码格式长度必须介于 0 和 10 之间")
	public String getVideocodec() {
		return videocodec;
	}

	public void setVideocodec(String videocodec) {
		this.videocodec = videocodec;
	}
	
	@Length(min=0, max=11, message="视频大小长度必须介于 0 和 11 之间")
	public String getFileszie() {
		return fileszie;
	}

	public void setFileszie(String fileszie) {
		this.fileszie = fileszie;
	}
	
	@Length(min=0, max=10, message="码率长度必须介于 0 和 10 之间")
	public String getAvgrate() {
		return avgrate;
	}

	public void setAvgrate(String avgrate) {
		this.avgrate = avgrate;
	}
	
	@Length(min=0, max=10, message="FLV,F4V,MP4,3GP,TS,AAC,MP3,VP6,VP8长度必须介于 0 和 10 之间")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	@Length(min=0, max=10, message="文件码流长度必须介于 0 和 10 之间")
	public String getVideorate() {
		return videorate;
	}

	public void setVideorate(String videorate) {
		this.videorate = videorate;
	}
	
	@Length(min=0, max=10, message="帧率长度必须介于 0 和 10 之间")
	public String getFps() {
		return fps;
	}

	public void setFps(String fps) {
		this.fps = fps;
	}
	
	@Length(min=0, max=6, message="宽度长度必须介于 0 和 6 之间")
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	@Length(min=0, max=6, message="高度长度必须介于 0 和 6 之间")
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	@Length(min=0, max=10, message="声音采样率长度必须介于 0 和 10 之间")
	public String getAudiocodec() {
		return audiocodec;
	}

	public void setAudiocodec(String audiocodec) {
		this.audiocodec = audiocodec;
	}
	
	@Length(min=0, max=10, message="声音码率长度必须介于 0 和 10 之间")
	public String getAudiorate() {
		return audiorate;
	}

	public void setAudiorate(String audiorate) {
		this.audiorate = audiorate;
	}
	
	@Length(min=0, max=10, message="采样率长度必须介于 0 和 10 之间")
	public String getSamplingrate() {
		return samplingrate;
	}

	public void setSamplingrate(String samplingrate) {
		this.samplingrate = samplingrate;
	}
	
	@Length(min=0, max=10, message="质量格式长度必须介于 0 和 10 之间")
	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	@Length(min=0, max=10, message="HTTP,RTMP,RTSP,HTTP_STREAM,MMS长度必须介于 0 和 10 之间")
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	@Length(min=0, max=10, message="IPAD,ANDROID3,ANDROID2,ANDROID,ANDROID1,HTML5,FLASH长度必须介于 0 和 10 之间")
	public String getTerminaltype() {
		return terminaltype;
	}

	public void setTerminaltype(String terminaltype) {
		this.terminaltype = terminaltype;
	}
	
	@Length(min=0, max=10, message="视频分辨率长度必须介于 0 和 10 之间")
	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	
	@Length(min=0, max=20, message="媒体纵横比长度必须介于 0 和 20 之间")
	public String getAspectratioid() {
		return aspectratioid;
	}

	public void setAspectratioid(String aspectratioid) {
		this.aspectratioid = aspectratioid;
	}
	
	@Length(min=0, max=4, message="状态            1:默认            2：审核成功            9：审核失败长度必须介于 0 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=500, message="审核失败原因长度必须介于 0 和 500 之间")
	public String getAuditinfo() {
		return auditinfo;
	}

	public void setAuditinfo(String auditinfo) {
		this.auditinfo = auditinfo;
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
	
	@Length(min=0, max=255, message="mp4视频路径长度必须介于 0 和 255 之间")
	public String getMp4path() {
		return mp4path;
	}

	public void setMp4path(String mp4path) {
		this.mp4path = mp4path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getgeneratetaskflag() {
		return generatetaskflag;
	}

	public void setgeneratetaskflag(String generatetaskflag) {
		this.generatetaskflag = generatetaskflag;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
	@JsonIgnore
	public User getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(User uploadBy) {
		this.uploadBy = uploadBy;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getConvertstatus() {
		return convertstatus;
	}

	public void setConvertstatus(String convertstatus) {
		this.convertstatus = convertstatus;
	}

	public String getCdnPath() {
		return cdnPath;
	}

	public void setCdnPath(String cdnPath) {
		this.cdnPath = cdnPath;
	}

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}