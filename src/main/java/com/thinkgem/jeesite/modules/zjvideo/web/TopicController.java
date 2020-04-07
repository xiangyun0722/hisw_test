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
import com.thinkgem.jeesite.modules.zjvideo.entity.Topic;
import com.thinkgem.jeesite.modules.zjvideo.service.TopicService;

/**
 * 话题信息Controller
 * @author j.feng
 * @version 2015-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/topic")
public class TopicController extends BaseController {

	@Autowired
	private TopicService topicService;
	
	@ModelAttribute
	public Topic get(@RequestParam(required=false) String id) {
		Topic entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = topicService.get(id);
		}
		if (entity == null){
			entity = new Topic();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:topic:view")
	@RequestMapping(value = {"list", ""})
	public String list(Topic topic, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Topic> page = topicService.findPage(new Page<Topic>(request, response), topic); 
		model.addAttribute("page", page);
		return "modules/zjvideo/topicList";
	}

	@RequiresPermissions("zjvideo:topic:view")
	@RequestMapping(value = "form")
	public String form(Topic topic, Model model) {
		model.addAttribute("topic", topic);
		return "modules/zjvideo/topicForm";
	}

	@RequiresPermissions("zjvideo:topic:edit")
	@RequestMapping(value = "save")
	public String save(Topic topic, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, topic)){
			return form(topic, model);
		}
		topicService.save(topic);
		addMessage(redirectAttributes, "保存话题信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/topic/?repage";
	}
	
	@RequiresPermissions("zjvideo:topic:edit")
	@RequestMapping(value = "delete")
	public String delete(Topic topic, RedirectAttributes redirectAttributes) {
		topicService.delete(topic);
		addMessage(redirectAttributes, "删除话题信息成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/topic/?repage";
	}

}