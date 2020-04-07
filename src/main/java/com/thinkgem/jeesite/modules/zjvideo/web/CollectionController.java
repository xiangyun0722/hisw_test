/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.Collections;
import com.thinkgem.jeesite.modules.zjvideo.service.CollectionsService;

/**
 * 收藏信息Controller
 * @author j.feng
 * @version 2015-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/collection")
public class CollectionController extends BaseController {

	@Autowired
	private CollectionsService collectionService;
	
	@ModelAttribute
	public Collections get(@RequestParam(required=false) String id) {
		Collections entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = collectionService.get(id);
		}
		if (entity == null){
			entity = new Collections();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:collection:view")
	@RequestMapping(value = {"list", ""})
	public String list(Collections collection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Collections> page = collectionService.findPage(new Page<Collections>(request, response), collection); 
		model.addAttribute("page", page);
		return "modules/zjvideo/collectionList";
	}

	@RequiresPermissions("zjvideo:collection:view")
	@RequestMapping(value = "/statics/collection")
	public String staticsCollectList(Collections collection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Collections> page = collectionService.findStatisticsCollectionPage(new Page<Collections>(request, response), collection); 
		model.addAttribute("page", page);
		return "modules/zjvideo/staticsCollectionList";
	}	
	
	/*@RequiresPermissions("zjvideo:collection:view")*/
	@RequestMapping(value = "/statics/studnet/collection")
	public String staticsStudentCollectList(Collections collection, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(Collections.collectionTypeStudent == collection.getStaticsflag()){
			return "redirect:" + adminPath + "/zjvideo/playHistory/statics/student/history";
		}
		Page<Collections> page = collectionService.findStatisticsStudentCollectionPage(new Page<Collections>(request, response), collection); 
		model.addAttribute("page", page);
		return "modules/zjvideo/StaticsStudentCollectionList";
	}
	
	/*@RequiresPermissions("zjvideo:collection:view")*/
	@RequestMapping(value = "/statics/teacher/collection")
	public String staticsTeacherCollectList(Collections collection, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(Collections.collectionTypeStudent == collection.getStaticsflag()){
			return "redirect:" + adminPath + "/zjvideo/playHistory/statics/teacher/history";
		}
		Page<Collections> page = collectionService.findStatisticsTeacherCollectionPage(new Page<Collections>(request, response), collection); 
		model.addAttribute("page", page);
		return "modules/zjvideo/StaticsTeacherCollectionList";
	}	
	
	@RequiresPermissions("zjvideo:collection:view")
	@RequestMapping(value = "form")
	public String form(Collections collection, Model model) {
		model.addAttribute("collection", collection);
		return "modules/zjvideo/collectionForm";
	}

	@RequiresPermissions("zjvideo:collection:edit")
	@RequestMapping(value = "save")
	public String save(Collections collection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, collection)){
			return form(collection, model);
		}
		collectionService.save(collection);
		addMessage(redirectAttributes, "保存收藏信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/collection/?repage";
	}
	
	@RequiresPermissions("zjvideo:collection:edit")
	@RequestMapping(value = "delete")
	public String delete(Collections collection, RedirectAttributes redirectAttributes) {
		collectionService.delete(collection);
		addMessage(redirectAttributes, "删除收藏信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/collection/?repage";
	}

}