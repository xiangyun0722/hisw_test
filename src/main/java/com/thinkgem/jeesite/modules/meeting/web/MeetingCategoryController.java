/**
 * 
 */
package com.thinkgem.jeesite.modules.meeting.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingCategory;
import com.thinkgem.jeesite.modules.meeting.service.MeetingCategoryService;

/**
 * 会议分类Controller
 * @author j.feng
 * @version 2015-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/meeting/meetingCategory")
public class MeetingCategoryController extends BaseController {

	@Autowired
	private MeetingCategoryService meetingCategoryService;
	
	@ModelAttribute
	public MeetingCategory get(@RequestParam(required=false) String id) {
		MeetingCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = meetingCategoryService.get(id);
		}
		if (entity == null){
			entity = new MeetingCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("meeting:meetingCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(MeetingCategory meetingCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<MeetingCategory> list = meetingCategoryService.findList(meetingCategory); 
		model.addAttribute("list", list);
		return "modules/meeting/meetingCategoryList";
	}

	@RequiresPermissions("meeting:meetingCategory:view")
	@RequestMapping(value = "form")
	public String form(MeetingCategory meetingCategory, Model model) {
		if (meetingCategory.getParent()!=null && StringUtils.isNotBlank(meetingCategory.getParent().getId())){
			meetingCategory.setParent(meetingCategoryService.get(meetingCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(meetingCategory.getId())){
				MeetingCategory meetingCategoryChild = new MeetingCategory();
				meetingCategoryChild.setParent(new MeetingCategory(meetingCategory.getParent().getId()));
				List<MeetingCategory> list = meetingCategoryService.findList(meetingCategory); 
				if (list.size() > 0){
					meetingCategory.setSort(list.get(list.size()-1).getSort());
					if (meetingCategory.getSort() != null){
						meetingCategory.setSort(meetingCategory.getSort() + 30);
					}
				}
			}
		}
		if (meetingCategory.getSort() == null){
			meetingCategory.setSort(30);
		}
		model.addAttribute("meetingCategory", meetingCategory);
		return "modules/meeting/meetingCategoryForm";
	}

	@RequiresPermissions("meeting:meetingCategory:edit")
	@RequestMapping(value = "save")
	public String save(MeetingCategory meetingCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, meetingCategory)){
			return form(meetingCategory, model);
		}
		meetingCategoryService.save(meetingCategory);
		addMessage(redirectAttributes, "保存会议分类成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingCategory/?repage";
	}
	
	@RequiresPermissions("meeting:meetingCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(MeetingCategory meetingCategory, RedirectAttributes redirectAttributes) {
		meetingCategoryService.delete(meetingCategory);
		addMessage(redirectAttributes, "删除会议分类成功");
		return "redirect:"+Global.getAdminPath()+"/meeting/meetingCategory/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<MeetingCategory> list = meetingCategoryService.findList(new MeetingCategory());
		for (int i=0; i<list.size(); i++){
			MeetingCategory e = list.get(i);
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