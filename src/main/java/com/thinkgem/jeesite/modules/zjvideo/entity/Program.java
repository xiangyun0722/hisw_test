/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 课程管理Entity
 * @author j.feng
 * @version 2015-05-06
 */
public class Program extends DataEntity<Program> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 课程名称
	private String ename;		// 英文名称
	private String clazzid;		// 视频分类
	private String picurl;		// 课程海报
	private String actor;		// 主讲人
	private String director;	// 视频导演
	private String iyear;		// 发行年份
	private String areaid;		// 区域信息
	private String language;	// 语言
	private String detail;		// 简介
	private String keyword;		// 关键字
	private String taglist;		// 标签
	private String viewpoint;	// 视频看点
	private String starlevel;	// 评级级别
	private String volume;		// 总集数
	private String status;		// 视频状态            1:默认            2：审核成功            3：上线            4：连续发布            9：审核失败
	private Date publishtime;	// 上映日期
	private String auditer;		// 审核者
	private Date audittime;		// 审核时间
	private String auditreason;	// 审核失败原因
	private Date addtime;		// 增加时间
	private String adder;		// 增加者
	private Date edittime;		// 最后修改时间
	private String editer;		// 最后修改者
	private String slock;		// 锁定标志
	private String isrecommend;	// 1:推荐 0：不推荐
	private String isnew;		// isnew
	private String playtimes;	// playtimes
	private String viewtimes;	// viewtimes
	private String collectiontimes;	// collectiontimes
	private String teacherno;		// teacherno
	private String hour;		// 课程时长
	private String isonline;	// 1.课堂直播，默认代表其它
	private String serverid;	// 服务器ID
	private String host;		// 主办单位
	private String flag;		// 0.未开始 1.正在在线课堂 2.已经完成
	
	/*查询增加权限成成员显示*/
	private String ownGroupName;	//用户权限归属
	private List<Integer> groupId;	//用户权限组ids
	private Integer groupIds;		//用户权限组id
	private String programGroupId;	//群组课程id
	
	public String getProgramGroupId() {
		return programGroupId;
	}

	public void setProgramGroupId(String programGroupId) {
		this.programGroupId = programGroupId;
	}

	public List<Integer> getGroupId() {
		return groupId;
	}

	public Integer getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(Integer groupIds) {
		this.groupIds = groupIds;
	}

	public void setGroupId(List<Integer> groupId) {
		this.groupId = groupId;
	}

	public String getOwnGroupName() {
		return ownGroupName;
	}
	
	public void setOwnGroupName(String ownGroupName) {
		this.ownGroupName = ownGroupName;
	}
	
	public Program() {
		super();
	}

	public Program(String id){
		super(id);
	}

	@Length(min=0, max=100, message="课程名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=150, message="英文名称长度必须介于 0 和 150 之间")
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	@Length(min=0, max=11, message="视频分类长度必须介于 0 和 11 之间")
	public String getClazzid() {
		return clazzid;
	}

	public void setClazzid(String clazzid) {
		this.clazzid = clazzid;
	}
	
	@Length(min=0, max=255, message="课程海报长度必须介于 0 和 255 之间")
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	@Length(min=0, max=100, message="主讲人长度必须介于 0 和 100 之间")
	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}
	
	@Length(min=0, max=100, message="视频导演长度必须介于 0 和 100 之间")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	@Length(min=0, max=11, message="发行年份长度必须介于 0 和 11 之间")
	public String getIyear() {
		return iyear;
	}

	public void setIyear(String iyear) {
		this.iyear = iyear;
	}
	
	@Length(min=0, max=11, message="区域信息长度必须介于 0 和 11 之间")
	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	
	@Length(min=0, max=11, message="语言长度必须介于 0 和 11 之间")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=255, message="关键字长度必须介于 0 和 255 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=0, max=255, message="标签长度必须介于 0 和 255 之间")
	public String getTaglist() {
		return taglist;
	}

	public void setTaglist(String taglist) {
		this.taglist = taglist;
	}
	
	@Length(min=0, max=2000, message="视频看点长度必须介于 0 和 2000 之间")
	public String getViewpoint() {
		return viewpoint;
	}

	public void setViewpoint(String viewpoint) {
		this.viewpoint = viewpoint;
	}
	
	@Length(min=0, max=10, message="评级级别长度必须介于 0 和 10 之间")
	public String getStarlevel() {
		return starlevel;
	}

	public void setStarlevel(String starlevel) {
		this.starlevel = starlevel;
	}
	
	@Length(min=0, max=11, message="总集数长度必须介于 0 和 11 之间")
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	@Length(min=0, max=4, message="视频状态            1:默认            2：审核成功            3：上线            4：连续发布            9：审核失败长度必须介于 0 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	
	@Length(min=0, max=50, message="审核者长度必须介于 0 和 50 之间")
	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAudittime() {
		return audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}
	
	@Length(min=0, max=200, message="审核失败原因长度必须介于 0 和 200 之间")
	public String getAuditreason() {
		return auditreason;
	}

	public void setAuditreason(String auditreason) {
		this.auditreason = auditreason;
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
	
	@Length(min=0, max=1, message="1:推荐 0：不推荐长度必须介于 0 和 1 之间")
	public String getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(String isrecommend) {
		this.isrecommend = isrecommend;
	}
	
	@Length(min=0, max=1, message="isnew长度必须介于 0 和 1 之间")
	public String getIsnew() {
		return isnew;
	}

	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}
	
	@Length(min=0, max=11, message="playtimes长度必须介于 0 和 11 之间")
	public String getPlaytimes() {
		return playtimes;
	}

	public void setPlaytimes(String playtimes) {
		this.playtimes = playtimes;
	}
	
	@Length(min=0, max=11, message="viewtimes长度必须介于 0 和 11 之间")
	public String getViewtimes() {
		return viewtimes;
	}

	public void setViewtimes(String viewtimes) {
		this.viewtimes = viewtimes;
	}
	
	@Length(min=0, max=11, message="collectiontimes长度必须介于 0 和 11 之间")
	public String getCollectiontimes() {
		return collectiontimes;
	}

	public void setCollectiontimes(String collectiontimes) {
		this.collectiontimes = collectiontimes;
	}
	
	@Length(min=0, max=255, message="teacherno长度必须介于 0 和 255 之间")
	public String getTeacherno() {
		return teacherno;
	}

	public void setTeacherno(String teacherno) {
		this.teacherno = teacherno;
	}
	
	@Length(min=0, max=255, message="课程时长长度必须介于 0 和 255 之间")
	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}
	
	@Length(min=0, max=11, message="服务器ID长度必须介于 0 和 11 之间")
	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}
	
	@Length(min=0, max=255, message="主办单位长度必须介于 0 和 255 之间")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}