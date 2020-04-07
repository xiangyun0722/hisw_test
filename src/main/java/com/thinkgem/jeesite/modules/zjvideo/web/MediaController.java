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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;
import com.thinkgem.jeesite.modules.zjvideo.service.MediaService;

/**
 * 媒资信息Controller
 * @author j.feng
 * @version 2015-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/media")
public class MediaController extends BaseController {

	@Autowired
	private MediaService mediaService;
	
	@ModelAttribute
	public Media get(@RequestParam(required=false) String id) {
		Media entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mediaService.get(id);
		}
		if (entity == null){
			entity = new Media();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:media:view")
	@RequestMapping(value = {"list", ""})
	public String list(Media media, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Media> page = mediaService.findPage(new Page<Media>(request, response), media); 
		model.addAttribute("page", page);
		return "modules/zjvideo/mediaList";
	}

	@RequiresPermissions("zjvideo:media:view")
	@RequestMapping(value = "form")
	public String form(Media media, Model model) {
		model.addAttribute("media", media);
		return "modules/zjvideo/mediaForm";
	}

	@RequiresPermissions("zjvideo:media:edit")
	@RequestMapping(value = "save")
	public String save(Media media, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, media)){
			return form(media, model);
		}
		mediaService.save(media);
		addMessage(redirectAttributes, "保存媒资信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/media/?repage";
	}
	
	@RequiresPermissions("zjvideo:media:edit")
	@RequestMapping(value = "delete")
	public String delete(Media media, RedirectAttributes redirectAttributes) {
		mediaService.delete(media);
		addMessage(redirectAttributes, "删除媒资视频成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/media/?repage";
	}

}