/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 学生信息Entity
 * @author j.feng
 * @version 2015-05-04
 */
public class Student extends DataEntity<Student> {
	
	private static final long serialVersionUID = 1L;
	private String studentno;		// 学生学号
	private String realname;		// 学生名称
	private String passwod;			// 学生密码
	private String sex;				// 性别
	private String age;				// 年龄
	private String picurl;			// 头像
	private String major;			// 专业
	private String mobile;			// 手机
	private String address;			// 联系地址
	private String logintimes;		// 登录次数
	private Date lastlogintime;		// 最后登录时间
	private String lastloginip;		// 最后登录ip
	private String recommend;		// 推荐学员标志            0:不是 1：是
	private Date addtime;			// 增加时间
	private Date edittime;			// 最后修改时间
	private String slock;			// 状态标志位0:正常，1：锁定
	private String studentid;		// studentid
	private String adflag;			// adflag
	private String detail;			// 学生简介
	private String count;			// 收藏课程个数
	private String profession;		// 职业
	private String district;		// 地区
	private String signature;		// 签名
	private String nickname;		// 昵称
	private String mailbox;			// 邮箱账号
	
	/*激活挂起鉴权标志*/
	private Boolean suspended;
	
	public static String isSlock = "0";//正常
	public static String noSlock = "1";//锁定
	
	public Boolean getSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	public Student() {
		super();
	}

	public Student(String id){
		super(id);
	}

	@Length(min=0, max=50, message="学生学号长度必须介于 0 和 50 之间")
	public String getStudentno() {
		return studentno;
	}

	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}
	
	@Length(min=0, max=50, message="学生名称长度必须介于 0 和 50 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=50, message="学生密码长度必须介于 0 和 50 之间")
	public String getPasswod() {
		return passwod;
	}

	public void setPasswod(String passwod) {
		this.passwod = passwod;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=4, message="年龄长度必须介于 0 和 4 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=255, message="picurl长度必须介于 0 和 255 之间")
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	@Length(min=0, max=50, message="专业长度必须介于 0 和 50 之间")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Length(min=0, max=50, message="手机长度必须介于 0 和 50 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=50, message="联系地址长度必须介于 0 和 50 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	@Length(min=0, max=1, message="推荐学员标志            0:不是 1：是长度必须介于 0 和 1 之间")
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
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
	
	@Length(min=0, max=255, message="studentid长度必须介于 0 和 255 之间")
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	
	@Length(min=1, max=11, message="adflag长度必须介于 1 和 11 之间")
	public String getAdflag() {
		return adflag;
	}

	public void setAdflag(String adflag) {
		this.adflag = adflag;
	}
	
	@Length(min=0, max=255, message="学生简介长度必须介于 0 和 255 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=11, message="收藏课程个数长度必须介于 0 和 11 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=255, message="职业长度必须介于 0 和 255 之间")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@Length(min=0, max=255, message="地区长度必须介于 0 和 255 之间")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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
	
	@Length(min=0, max=255, message="邮箱账号长度必须介于 0 和 255 之间")
	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	
}