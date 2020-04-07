/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 播放历史信息Entity
 * @author j.feng
 * @version 2015-05-07
 */
public class PlayHistory extends DataEntity<PlayHistory> {
	
	private static final long serialVersionUID = 1L;
	private String programid;		// 课程ID
	private String programvolumeid;	// 关联视频剧集表
	private String times;			// 单位：秒
	private String studentid;		// 用户ID
	private String nickname;		// 昵称
	private Date addtime;			// 增加时间
	private Date edittime;			// 最后修改时间
	private String slock;			// 锁定标志
	private String realname;		// 姓名
	private String type;			// 用户类型
	
	/*关联信息对象查询*/
	private Program program;				// 课程实体
	private Student student;				// 学生实体
	private Teacher teacher;				// 教师实体
	private ProgramVolume programVolume;	// 章节实体
	
	private int count;				//点播次数
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

	public ProgramVolume getProgramVolume() {
		return programVolume;
	}

	public void setProgramVolume(ProgramVolume programVolume) {
		this.programVolume = programVolume;
	}

	public PlayHistory() {
		super();
	}

	public PlayHistory(String id){
		super(id);
	}

	@Length(min=0, max=11, message="课程ID长度必须介于 0 和 11 之间")
	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}
	
	@Length(min=0, max=11, message="关联视频剧集表长度必须介于 0 和 11 之间")
	public String getProgramvolumeid() {
		return programvolumeid;
	}

	public void setProgramvolumeid(String programvolumeid) {
		this.programvolumeid = programvolumeid;
	}
	
	@Length(min=0, max=11, message="单位：秒长度必须介于 0 和 11 之间")
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
	@Length(min=0, max=11, message="用户ID长度必须介于 0 和 11 之间")
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	
	@Length(min=0, max=50, message="昵称长度必须介于 0 和 50 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	
	@Length(min=0, max=1, message="锁定标志长度必须介于 0 和 1 之间")
	public String getSlock() {
		return slock;
	}

	public void setSlock(String slock) {
		this.slock = slock;
	}
	
	@Length(min=0, max=255, message="realname长度必须介于 0 和 255 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=2, message="用户类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}