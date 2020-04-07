/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.Group;
import com.thinkgem.jeesite.modules.zjvideo.entity.Student;
import com.thinkgem.jeesite.modules.zjvideo.entity.StudentGroup;
import com.thinkgem.jeesite.modules.zjvideo.service.GroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.StudentGroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.StudentService;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;

/**
 * 学生信息Controller
 * @author j.feng
 * @version 2015-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/student")
public class StudentController extends BaseController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentGroupService studentGroupService;
	
	@Autowired
	private GroupService groupService;	
	
	@ModelAttribute
	public Student get(@RequestParam(required=false) String id) {
		Student entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentService.get(id);
		}
		if (entity == null){
			entity = new Student();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:student:view")
	@RequestMapping(value = {"list", ""})
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Student> page = studentService.findPage(new Page<Student>(request, response), student); 
		/*如果群组已经存标志出*/
		List<Student> stu = page.getList();
		for(Student su : stu){
			StudentGroup stuGroup = studentGroupService.findByUserid(su.getId());
			if(null != stuGroup){
				su.setSuspended(Boolean.TRUE);
			}else{
				su.setSuspended(Boolean.FALSE);
			}
		}
		model.addAttribute("page", page);
		return "modules/zjvideo/studentList";
	}

	@RequiresPermissions("zjvideo:student:view")
	@RequestMapping(value = "form")
	public String form(Student student, Model model) {
/*		StudentGroup stuGroup = studentGroupService.findByUserid(student.getId());
		if(null != stuGroup){
			student.setSuspended(Boolean.TRUE);
		}*/
		model.addAttribute("student", student);
		return "modules/zjvideo/studentForm";
	}

	@RequiresPermissions("zjvideo:student:edit")
	@RequestMapping(value = "save")
	public String save(Student student, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, student)){
			return form(student, model);
		}
		studentService.save(student);
		addMessage(redirectAttributes, "保存学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/student/?repage";
	}
	
	@RequiresPermissions("zjvideo:student:edit")
	@RequestMapping(value = "delete")
	public String delete(Student student, RedirectAttributes redirectAttributes) {
		studentService.delete(student);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/student/?repage";
	}
	/**
	 * 挂起、激活群组
	 */
	@RequiresPermissions("zjvideo:student:edit")
	@RequestMapping(value = "/update/{state}")
	public String updateState(@PathVariable("state") String state,  @RequestParam("id") String studentId, RedirectAttributes redirectAttributes) {
		String message = null;
		if (state.equals("active")) {
			StudentGroup studentGroup = new StudentGroup();
			Group group = groupService.findByGroupName(ConstantUtils.STUDENT_GROUP_NAME);
			if(null != group){
				studentGroup.setGroupid(group.getId());
				studentGroup.setUserid(studentId);
				studentGroup.setAddtime(new Date());
				studentGroup.setSlock(Student.isSlock);
				studentGroupService.save(studentGroup);
				message = "激活该学生加入群组。";
			}
			else{
				message = "激活该学生加入群组失败。";
			}
		} else if (state.equals("suspend")) {
			StudentGroup stuGroup = studentGroupService.findByUserid(studentId);
			if(null != stuGroup){
				studentGroupService.deleteStudentGroup(stuGroup);
				message = "挂起该学生加入群组。";
			}
			else{
				message = "挂起该学生加入群组失败。";
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:" + adminPath + "/zjvideo/student";
	}	
}