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
import com.thinkgem.jeesite.modules.zjvideo.entity.Teacher;
import com.thinkgem.jeesite.modules.zjvideo.entity.TeacherGroup;
import com.thinkgem.jeesite.modules.zjvideo.service.GroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.TeacherGroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.TeacherService;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;

/**
 * 教师信息Controller
 * @author j.feng
 * @version 2015-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/teacher")
public class TeacherController extends BaseController {

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private GroupService groupService;	
	
	@Autowired
	private TeacherGroupService teacherGroupService;	
	
	@ModelAttribute
	public Teacher get(@RequestParam(required=false) String id) {
		Teacher entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = teacherService.get(id);
		}
		if (entity == null){
			entity = new Teacher();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:teacher:view")
	@RequestMapping(value = {"list", ""})
	public String list(Teacher teacher, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Teacher> page = teacherService.findPage(new Page<Teacher>(request, response), teacher); 
		/*如果群组已经存标志出*/
		List<Teacher> teachers= page.getList();
		for(Teacher tea : teachers){
			TeacherGroup teacherGroup = teacherGroupService.findByTeacherid(tea.getId());
			if(null != teacherGroup){
				tea.setSuspended(Boolean.TRUE);
			}else{
				tea.setSuspended(Boolean.FALSE);
			}
		}		
		model.addAttribute("page", page);
		return "modules/zjvideo/teacherList";
	}

	@RequiresPermissions("zjvideo:teacher:view")
	@RequestMapping(value = "form")
	public String form(Teacher teacher, Model model) {
		model.addAttribute("teacher", teacher);
		return "modules/zjvideo/teacherForm";
	}

	@RequiresPermissions("zjvideo:teacher:edit")
	@RequestMapping(value = "save")
	public String save(Teacher teacher, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, teacher)){
			return form(teacher, model);
		}
		teacherService.save(teacher);
		addMessage(redirectAttributes, "保存教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/teacher/?repage";
	}
	
	@RequiresPermissions("zjvideo:teacher:edit")
	@RequestMapping(value = "delete")
	public String delete(Teacher teacher, RedirectAttributes redirectAttributes) {
		teacherService.delete(teacher);
		addMessage(redirectAttributes, "删除教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/teacher/?repage";
	}

	/**
	 * 挂起、激活群组
	 */
	@RequiresPermissions("zjvideo:teacher:edit")
	@RequestMapping(value = "/update/{state}")
	public String updateState(@PathVariable("state") String state,  @RequestParam("id") String teacherid, RedirectAttributes redirectAttributes) {
		String message = null;
		if (state.equals("active")) {
			TeacherGroup teacherGroup = new TeacherGroup();
			Group group = groupService.findByGroupName(ConstantUtils.TEACHER_GROUP_NAME);
			if(null != group){
				teacherGroup.setGroupid(group.getId());
				teacherGroup.setTeacherid(teacherid);;
				teacherGroup.setAddtime(new Date());
				teacherGroup.setSlock(Student.isSlock);
				teacherGroupService.save(teacherGroup);
				message = "激活该教师加入群组。";
			}
			else{
				message = "激活该教师加入群组失败。";
			}
		} else if (state.equals("suspend")) {
			TeacherGroup teacherGroup = teacherGroupService.findByTeacherid(teacherid);
			if(null != teacherGroup){
				teacherGroupService.deleteTeacherGroup(teacherGroup);
				message = "挂起该教师加入群组。";
			}
			else{
				message = "挂起该教师加入群组失败。";
			}
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:" + adminPath + "/zjvideo/teacher";
	}			
}