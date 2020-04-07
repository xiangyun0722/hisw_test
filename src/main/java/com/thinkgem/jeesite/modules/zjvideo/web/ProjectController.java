/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Project;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;
import com.thinkgem.jeesite.modules.zjvideo.service.ProjectService;
import com.thinkgem.jeesite.modules.zjvideo.service.TemplatesService;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;

/**
 * 项目信息表Controller
 * @author j.feng
 * @version 2015-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private TemplatesService templateService;
	
	@ModelAttribute
	public Project get(@RequestParam(required=false) String id) {
		Project entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectService.get(id);
		}
		if (entity == null){
			entity = new Project();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:project:view")
	@RequestMapping(value = {"list", ""})
	public String list(Project project, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Project> list = projectService.findList(project); 
		model.addAttribute("list", list);
		return "modules/zjvideo/projectList";
	}

	@RequiresPermissions("zjvideo:project:view")
	@RequestMapping(value = "form")
	public String form(Project project, Model model) {
		if (project.getParent()!=null && StringUtils.isNotBlank(project.getParent().getId())){
			project.setParent(projectService.get(project.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(project.getId())){
				Project projectChild = new Project();
				projectChild.setParent(new Project(project.getParent().getId()));
				List<Project> list = projectService.findList(project); 
				if (list.size() > 0){
					project.setSort(list.get(list.size()-1).getSort());
					if (project.getSort() != null){
						project.setSort(project.getSort() + 30);
					}
				}
			}
		}
		if (project.getSort() == null){
			project.setSort(30);
		}
		if(!StringUtils.isBlank(project.getCompanyid())){
			Office office = officeService.get(project.getCompanyid());
			project.setOffice(office);
		}
		if(project.getCreateBy() != null && StringUtils.isNoneBlank(project.getCreateBy().getId())){
			User user = systemService.getUser(project.getCreateBy().getId());
			project.setCreateBy(user);
		}		
		//查询全部的模板出来。
		List<Templates>  templatesList= templateService.getAllTemplates();
		model.addAttribute("templatesList", templatesList);
		//查询拥有的 模板信息。
		//组下面的服务器列表
		List<Integer> templatesIds = Lists.newArrayList();
		List<Templates>  templatesListTemp = templateService.getOwnTemplates(project);
		for (Templates templates : templatesListTemp) {
			if(org.apache.commons.lang.StringUtils.isNotBlank(templates.getId())){
				templatesIds.add(Integer.parseInt(templates.getId()));	
			}
		}
		project.setTemplatesids(templatesIds);
		model.addAttribute("project", project);
		return "modules/zjvideo/projectForm";
	}

	@RequiresPermissions("zjvideo:project:edit")
	@RequestMapping(value = "save")
	public String save(Project project, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, project)){
			return form(project, model);
		}
		//删除项目原来的全部模板信息。
		if(org.apache.commons.lang.StringUtils.isNotBlank(project.getId())){
			projectService.deleteTemplates(project);
		}
		String ftpPath = DictUtils.getDictValue("FTP_DIRECTORIES", "sys_config", "");
		String webPath = DictUtils.getDictValue("WEB_DIRECTORIES", "sys_config", "");
		String repositoryPath = DictUtils.getDictValue("REPOSITORY_DIRECTORIES", "sys_config", "");
		
		String transcodeFtpPath = new String();
		String transcodeWebPath = new String();
		String transcoderepositoryPath = new String();
		
		projectService.save(project);
		transcodeFtpPath=ftpPath + ConstantUtils.TRANCCODE_PATH_OFFICE + project.getCompanyid() + "/"+ ConstantUtils.TRANCCODE_PATH_COMPANY + project.getId() + "/" + ConstantUtils.TRANCCODE_PATH_FTP + "/";
		transcodeWebPath=webPath + ConstantUtils.TRANCCODE_PATH_OFFICE + project.getCompanyid() + "/"+ ConstantUtils.TRANCCODE_PATH_COMPANY + project.getId() + "/" + ConstantUtils.TRANCCODE_PATH_WEB + "/";
		transcoderepositoryPath=repositoryPath + ConstantUtils.TRANCCODE_PATH_OFFICE + project.getCompanyid() + "/"+ ConstantUtils.TRANCCODE_PATH_COMPANY + project.getId() + "/";
		
		project.setFtpautopath(transcodeFtpPath);
		project.setFtpmanupath(transcodeWebPath);
		project.setRepositorypath(transcoderepositoryPath);
		
		projectService.save(project);
		//保存模板到中间表中去。
		int row = projectService.insertTemplates(project);
		addMessage(redirectAttributes, "保存项目信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/project/?repage";
	}
	
	@RequiresPermissions("zjvideo:project:edit")
	@RequestMapping(value = "delete")
	public String delete(Project project, RedirectAttributes redirectAttributes) {
		projectService.delete(project);
		addMessage(redirectAttributes, "删除项目信息表成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/project/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Project> list = projectService.findList(new Project());
		for (int i=0; i<list.size(); i++){
			Project e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}