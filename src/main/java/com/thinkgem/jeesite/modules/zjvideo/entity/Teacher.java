/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 教师信息Entity
 * @author j.feng
 * @version 2015-05-04
 */
public class Teacher extends DataEntity<Teacher> {
	
	private static final long serialVersionUID = 1L;
	private String teacherno;		// 教师工号
	private String password;		// 密码
	private String realname;		// 教师姓名
	private String telphone;		// 电话
	private String mobile;			// 手机号码
	private String webchat;			// 微信
	private String college;			// 隶属学院
	private String fasten;			// 隶属系
	private String detail;			// 教师简介
	private String picurl;			// 头像地址
	private String logintimes;		// 登录次数
	private Date lastlogintime;		// 最后登录时间
	private String lastloginip;		// 最后登录ip
	private String coursenum;		// 课程数量
	private String recomment;		// 是否推荐   0：不是，1：是
	private Date addtime;			// 增加时间
	private Date edittime;			// 最后修改时间
	private String slock;			// 状态标志位0:正常，1：锁定
	private String status;			// 0：默认，1：上线
	private String adflag;			// adflag
	private String title;			// 教师职称
	private String profession;		// 职业
	private String signature;		// 签名
	private String nickname;		// 昵称
	private String sex;				// 性别
	private String district;		// 地区
	private String mailbox;			// 邮箱账号
	
	/*激活挂起鉴权标志*/
	private Boolean suspended;
	
	public Boolean getSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	public Teacher() {
		super();
	}

	public Teacher(String id){
		super(id);
	}

	@Length(min=0, max=50, message="teacherno长度必须介于 0 和 50 之间")
	public String getTeacherno() {
		return teacherno;
	}

	public void setTeacherno(String teacherno) {
		this.teacherno = teacherno;
	}
	
	@Length(min=0, max=50, message="password长度必须介于 0 和 50 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=50, message="realname长度必须介于 0 和 50 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=50, message="telphone长度必须介于 0 和 50 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Length(min=0, max=50, message="mobile长度必须介于 0 和 50 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=255, message="webchat长度必须介于 0 和 255 之间")
	public String getWebchat() {
		return webchat;
	}

	public void setWebchat(String webchat) {
		this.webchat = webchat;
	}
	
	@Length(min=0, max=50, message="隶属学院长度必须介于 0 和 50 之间")
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	@Length(min=0, max=50, message="隶属系长度必须介于 0 和 50 之间")
	public String getFasten() {
		return fasten;
	}

	public void setFasten(String fasten) {
		this.fasten = fasten;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=255, message="头像地址长度必须介于 0 和 255 之间")
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	@Length(min=0, max=11, message="登录次数长度必须介于 0 和 11 之间")
	public String getLogintimes() {
		return logintimes;
	}

	public void setLogintimes(String logintimes) {
		this.logintimes = logintimes;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	
	@Length(min=0, max=50, message="最后登录ip长度必须介于 0 和 50 之间")
	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	
	@Length(min=0, max=11, message="课程数量长度必须介于 0 和 11 之间")
	public String getCoursenum() {
		return coursenum;
	}

	public void setCoursenum(String coursenum) {
		this.coursenum = coursenum;
	}
	
	@Length(min=0, max=1, message="是否推荐   0：不是，1：是长度必须介于 0 和 1 之间")
	public String getRecomment() {
		return recomment;
	}

	public void setRecomment(String recomment) {
		this.recomment = recomment;
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
	
	@Length(min=0, max=1, message="0：默认，1：上线长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=11, message="adflag长度必须介于 0 和 11 之间")
	public String getAdflag() {
		return adflag;
	}

	public void setAdflag(String adflag) {
		this.adflag = adflag;
	}
	
	@Length(min=0, max=255, message="教师职称长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="职业长度必须介于 0 和 255 之间")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@Length(min=0, max=255, message="签名长度必须介于 0 和 255 之间")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Length(min=0, max=255, message="昵称长度必须介于 0 和 255 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=255, message="性别长度必须介于 0 和 255 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=255, message="地区长度必须介于 0 和 255 之间")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	@Length(min=0, max=255, message="邮箱账号长度必须介于 0 和 255 之间")
	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	
}