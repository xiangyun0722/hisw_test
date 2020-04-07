/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 收藏信息Entity
 * @author j.feng
 * @version 2015-05-07
 */
public class Collections extends DataEntity<Collections> {
	
	private static final long serialVersionUID = 1L;
	private String studentid;	// 学生/老师ID
	private String programid;	// 课程编号ID
	private Date addtime;		// 增加时间
	private Date edittime;		// 最后修改时间
	private String slock;		// 状态标志位0:正常，1：锁定
	private String type;		// 用户类型
	
	/*关联信息对象查询*/
	private Program program;	// 课程实体
	private Student student;	// 学生实体
	private Teacher teacher;	// 教师实体

	private int count;			// 收藏数量
	private int staticsflag;		//统计类型	1:点播	2:收藏
	
	public static int collectionTypeStudent = 1;//学生收藏点播类型
	public static int collectionTypeTeacher = 2;//老师收藏点播类型	
	
	public int getStaticsflag() {
		return staticsflag;
	}

	public void setStaticsflag(int staticsflag) {
		this.staticsflag = staticsflag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Collections() {
		super();
	}

	public Collections(String id){
		super(id);
	}

	@Length(min=0, max=11, message="学生/老师ID长度必须介于 0 和 11 之间")
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	
	@Length(min=0, max=11, message="课程编号ID长度必须介于 0 和 11 之间")
	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
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
	
	@Length(min=0, max=2, message="用户类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}