/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资讯管理Entity
 * @author j.feng
 * @version 2015-05-04
 */
public class News extends DataEntity<News> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 新闻标题
	private String tag;			// 标签
	private String keylist;		// 关键字列表
	private String summary;		// 摘要信息
	private String detail;		// 详情
	private String clazzid;		// 栏目id
	private String collectcount;// 收藏数量
	private String isrecommend;	// 0:不是，1：是
	private String praise;		// 赞数
	private String viewcount;	// 浏览次数
	private String editer;		// 编辑
	private String editername;	// 编辑者名称
	private Date publishtime;	// 发布时间
	private String status;		// 0：默认，1：待审核，2：已经审核，3：预发布，4：发布
	private Date addtime;		// 增加时间
	private Date edittime;		// 最后修改时间
	private String slock;		// 状态标志位0:正常，1：锁定
	private String picurl;		// 机顶盒缩略图
	
	/*关联信息对象查询*/
	private Clazz clazz;		// 所属分类实体
	
	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public News() {
		super();
	}

	public News(String id){
		super(id);
	}

	@Length(min=0, max=255, message="新闻标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="标签长度必须介于 0 和 255 之间")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Length(min=0, max=500, message="关键字列表长度必须介于 0 和 500 之间")
	public String getKeylist() {
		return keylist;
	}

	public void setKeylist(String keylist) {
		this.keylist = keylist;
	}
	
	@Length(min=0, max=2000, message="摘要信息长度必须介于 0 和 2000 之间")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=11, message="栏目id长度必须介于 0 和 11 之间")
	public String getClazzid() {
		return clazzid;
	}

	public void setClazzid(String clazzid) {
		this.clazzid = clazzid;
	}
	
	@Length(min=0, max=11, message="收藏数量长度必须介于 0 和 11 之间")
	public String getCollectcount() {
		return collectcount;
	}

	public void setCollectcount(String collectcount) {
		this.collectcount = collectcount;
	}
	
	@Length(min=0, max=1, message="0:不是，1：是长度必须介于 0 和 1 之间")
	public String getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(String isrecommend) {
		this.isrecommend = isrecommend;
	}
	
	@Length(min=0, max=11, message="赞数长度必须介于 0 和 11 之间")
	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise;
	}
	
	@Length(min=0, max=11, message="浏览次数长度必须介于 0 和 11 之间")
	public String getViewcount() {
		return viewcount;
	}

	public void setViewcount(String viewcount) {
		this.viewcount = viewcount;
	}
	
	@Length(min=0, max=50, message="编辑长度必须介于 0 和 50 之间")
	public String getEditer() {
		return editer;
	}

	public void setEditer(String editer) {
		this.editer = editer;
	}
	
	@Length(min=0, max=50, message="编辑者名称长度必须介于 0 和 50 之间")
	public String getEditername() {
		return editername;
	}

	public void setEditername(String editername) {
		this.editername = editername;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	
	@Length(min=0, max=1, message="0：默认，1：待审核，2：已经审核，3：预发布，4：发布长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	@Length(min=0, max=1, message="状态标志位0:正常，1：锁定长度必须介于 0 和 1 之间")
	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}
	
	@Length(min=0, max=155, message="picurl长度必须介于 0 和 155 之间")
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
}