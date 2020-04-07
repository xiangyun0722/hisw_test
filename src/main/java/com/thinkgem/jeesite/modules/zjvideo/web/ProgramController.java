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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.Group;
import com.thinkgem.jeesite.modules.zjvideo.entity.Program;
import com.thinkgem.jeesite.modules.zjvideo.entity.ProgramGroup;
import com.thinkgem.jeesite.modules.zjvideo.entity.Teacher;
import com.thinkgem.jeesite.modules.zjvideo.service.GroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.ProgramGroupService;
import com.thinkgem.jeesite.modules.zjvideo.service.ProgramService;
import com.thinkgem.jeesite.modules.zjvideo.service.ProgramVolumeService;
import com.thinkgem.jeesite.modules.zjvideo.service.TeacherService;

/**
 * 课程管理Controller
 * @author j.feng
 * @version 2015-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/program")
public class ProgramController extends BaseController {

	@Autowired
	private ProgramService programService;

	@Autowired
	private TeacherService teacherService;	
	
	@Autowired
	private GroupService groupService;	
	
	@Autowired
	private ProgramGroupService programGroupService;	
	
	@Autowired
	private ProgramVolumeService programVolumeService;
	
	@ModelAttribute
	public Program get(@RequestParam(required=false) String id) {
		Program entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = programService.get(id);
		}
		if (entity == null){
			entity = new Program();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:program:view")
	@RequestMapping(value = {"list", ""})
	public String list(Program program, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Program> page = programService.findPage(new Page<Program>(request, response), program); 
		/*添加用户权限组处理信息*/
		List<Program> programs = page.getList();
		for(Program pro : programs){
			List<Program> progroup= programService.findOwnGroupList(pro);
			String ownGroupName ="";
			if(progroup != null && progroup.size() >= 1){
				for (int i = 0; i < progroup.size(); i++) {
					if(i==0){
						ownGroupName=ownGroupName+progroup.get(i).getOwnGroupName();
					}else{
						ownGroupName=ownGroupName+","+progroup.get(i).getOwnGroupName();	
					}
				}
			}
			pro.setOwnGroupName(ownGroupName);
		}
		model.addAttribute("page", page);
		return "modules/zjvideo/programList";
	}

	@RequiresPermissions("zjvideo:program:view")
	@RequestMapping(value = "form")
	public String form(Program program, Model model) {
		List<Teacher> teachers =teacherService.queryTeacherList();
		List<Group> groups =groupService.queryGroupList();
		model.addAttribute("program", program);
		model.addAttribute("teacherList", teachers);
		model.addAttribute("groupList", groups);
		return "modules/zjvideo/programForm";
	}

	@RequiresPermissions("zjvideo:program:view")
	@RequestMapping(value = "form/update")
	public String formUpdate(Program program, Model model) {
		List<Teacher> teachers =teacherService.queryTeacherList();
		/*取所有的组信息*/
		List<Group> grous = groupService.queryGroupList();
		List<Integer> groupIds = Lists.newArrayList();	//用户权限组ids
		/*取课程原来的组信息*/
		if(program.getId() != null && program.getId().length() >= 1){
			List<Program> progroup= programService.findOwnGroupList(program);
			if(progroup != null && progroup.size() >= 1){
				for (int i = 0; i < progroup.size(); i++) {
					groupIds.add(progroup.get(i).getGroupIds());
				}
			}
		}
		program.setGroupId(groupIds);
		model.addAttribute("groupList", grous);
		/*model.addAttribute("groupInfo", groups);*/
		model.addAttribute("program", program);
		model.addAttribute("teacherList", teachers);
		return "modules/zjvideo/programUpdateForm";
	}
	
	@RequiresPermissions("zjvideo:program:edit")
	@RequestMapping(value = "save")
	public String save(Program program, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, program)){
			return form(program, model);
		}
		/*删除原有用户权限信息*/
		ProgramGroup programGroups = new ProgramGroup();
		if(program.getId() != null && program.getId().length() >= 1){
			List<Program> progroup= programService.findOwnGroupList(program);
			for(Program pro : progroup){
				programGroups.setId(String.valueOf(pro.getProgramGroupId()));
				programGroupService.deleteProgramGroup(programGroups);
			}
		}
		programService.save(program);
		/*用户组权限添加*/
		if(program.getGroupId()!=null && program.getGroupId().size()>=1){
			for (int id : program.getGroupId()) {
				ProgramGroup programGroup = new ProgramGroup();
				programGroup.setProgramid(program.getId());
				programGroup.setGroupid(String.valueOf(id));
				programGroup.setAddtime(new Date());
				programGroupService.save(programGroup);//添加权限到表中去。
			}
		}
		addMessage(redirectAttributes, "保存课程管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/program/?repage";
	}
	
	@RequiresPermissions("zjvideo:program:edit")
	@RequestMapping(value = "delete")
	public String delete(Program program, RedirectAttributes redirectAttributes) {
		programService.deleteProgram(program);
		addMessage(redirectAttributes, "删除课程成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/program/?repage";
	}
	
}