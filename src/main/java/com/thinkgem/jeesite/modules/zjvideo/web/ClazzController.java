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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Clazz;
import com.thinkgem.jeesite.modules.zjvideo.service.ClazzService;

/**
 * 二级分类Controller
 * @author j.feng
 * @version 2015-08-06
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/clazz")
public class ClazzController extends BaseController {

	@Autowired
	private ClazzService clazzService;
	
	@ModelAttribute
	public Clazz get(@RequestParam(required=false) String id) {
		Clazz entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = clazzService.get(id);
		}
		if (entity == null){
			entity = new Clazz();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:clazz:view")
	@RequestMapping(value = {"list", ""})
	public String list(Clazz clazz, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Clazz> list = clazzService.findList(clazz); 
		model.addAttribute("list", list);
		return "modules/zjvideo/clazzList";
	}

	@RequiresPermissions("zjvideo:clazz:view")
	@RequestMapping(value = "form")
	public String form(Clazz clazz, Model model) {
		if (clazz.getParent()!=null && StringUtils.isNotBlank(clazz.getParent().getId())){
			clazz.setParent(clazzService.get(clazz.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(clazz.getId())){
				Clazz clazzChild = new Clazz();
				clazzChild.setParent(new Clazz(clazz.getParent().getId()));
				List<Clazz> list = clazzService.findList(clazz); 
				if (list.size() > 0){
					clazz.setSort(list.get(list.size()-1).getSort());
					if (clazz.getSort() != null){
						clazz.setSort(clazz.getSort() + 30);
					}
				}
			}
		}
		if (clazz.getSort() == null){
			clazz.setSort(30);
		}
		model.addAttribute("clazz", clazz);
		return "modules/zjvideo/clazzForm";
	}

	@RequiresPermissions("zjvideo:clazz:edit")
	@RequestMapping(value = "save")
	public String save(Clazz clazz, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, clazz)){
			return form(clazz, model);
		}
		clazzService.save(clazz);
		addMessage(redirectAttributes, "保存二级分类成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/clazz/?repage";
	}
	
	@RequiresPermissions("zjvideo:clazz:edit")
	@RequestMapping(value = "delete")
	public String delete(Clazz clazz, RedirectAttributes redirectAttributes) {
		clazzService.delete(clazz);
		addMessage(redirectAttributes, "删除二级分类成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/clazz/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Clazz> list = clazzService.findList(new Clazz());
		for (int i=0; i<list.size(); i++){
			Clazz e = list.get(i);
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