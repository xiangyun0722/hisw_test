/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.ClassType;
import com.thinkgem.jeesite.modules.zjvideo.entity.News;
import com.thinkgem.jeesite.modules.zjvideo.service.ClassTypeService;
import com.thinkgem.jeesite.modules.zjvideo.service.ClazzService;
import com.thinkgem.jeesite.modules.zjvideo.service.NewsService;

/**
 * 资讯管理Controller
 * @author j.feng
 * @version 2015-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/news")
public class NewsController extends BaseController {

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ClazzService clazzService;
	
	@Autowired
	private ClassTypeService classTypeService;
	
	@ModelAttribute
	public News get(@RequestParam(required=false) String id) {
		News entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = newsService.get(id);
		}
		if (entity == null){
			entity = new News();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:news:view")
	@RequestMapping(value = {"list", ""})
	public String list(News news, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<News> page = newsService.findPage(new Page<News>(request, response), news); 
		model.addAttribute("page", page);
		return "modules/zjvideo/newsList";
	}

	@RequiresPermissions("zjvideo:news:view")
	@RequestMapping(value = "form")
	public String form(News news, Model model) {
		model.addAttribute("news", news);
		List<ClassType> clazzType = classTypeService.getAllClzzType();
		model.addAttribute("clazzs", clazzType);
		return "modules/zjvideo/newsForm";
	}

	@RequiresPermissions("zjvideo:news:edit")
	@RequestMapping(value = "save")
	public String save(News news, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, news)){
			return form(news, model);
		}
		newsService.save(news);
		addMessage(redirectAttributes, "保存资讯管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/news/?repage";
	}
	
	@RequiresPermissions("zjvideo:news:edit")
	@RequestMapping(value = "delete")
	public String delete(News news, RedirectAttributes redirectAttributes) {
		newsService.delete(news);
		addMessage(redirectAttributes, "删除资讯管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/news/?repage";
	}

	@RequiresPermissions("zjvideo:news:edit")
	@RequestMapping(value = "delete/news")
	public String deleteNews(News news, RedirectAttributes redirectAttributes) {
		newsService.deleteNews(news);
		addMessage(redirectAttributes, "删除资讯成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/news/?repage";
	}	
}