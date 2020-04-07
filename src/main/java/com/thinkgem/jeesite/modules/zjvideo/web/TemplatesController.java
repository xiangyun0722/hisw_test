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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;
import com.thinkgem.jeesite.modules.zjvideo.service.TemplatesService;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;

/**
 * 视频模板Controller
 * @author j.feng
 * @version 2015-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/template")
public class TemplatesController extends BaseController {

	@Autowired
	private TemplatesService templateService;
	
	@ModelAttribute
	public Templates get(@RequestParam(required=false) String id) {
		Templates entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = templateService.get(id);
		}
		if (entity == null){
			entity = new Templates();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:template:view")
	@RequestMapping(value = {"list", ""})
	public String list(Templates template, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Templates> page = templateService.findPage(new Page<Templates>(request, response), template); 
		model.addAttribute("page", page);
		return "modules/zjvideo/templateList";
	}

	@RequiresPermissions("zjvideo:template:view")
	@RequestMapping(value = "form")
	public String form(Templates template, Model model) {
		//视频转码格式列表
		List<Dict> dictFormats = DictUtils.getDictList(ConstantUtils.TEMPLATE_FORMAT);
		//是否选择自动转码模板
		List<Dict> dictAutoStatus = DictUtils.getDictList(ConstantUtils.TEMPLATE_AUTO_STATUS);	
		//视频状态-
		List<Dict> dictStatus= DictUtils.getDictList(ConstantUtils.TEMPLATE_STATUS);			
		model.addAttribute("dictFormats", dictFormats);
		model.addAttribute("dictAutoStatus", dictAutoStatus);
		model.addAttribute("dictStatus", dictStatus);
		model.addAttribute("template", template);
		return "modules/zjvideo/templateForm";
	}

	@RequiresPermissions("zjvideo:template:edit")
	@RequestMapping(value = "save")
	public String save(Templates template, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, template)){
			return form(template, model);
		}
		templateService.save(template);
		addMessage(redirectAttributes, "保存视频模板成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/template/?repage";
	}
	/**
	 * 数据库删除模板信息
	 * @param template
	 * @param redirectAttributes
	 * @return
	 * @author j.feng
	 * @date 2015年5月21日 下午8:29:08
	 */
	@RequiresPermissions("zjvideo:template:edit")
	@RequestMapping(value = "delete")
	public String delete(Templates template, RedirectAttributes redirectAttributes) {
		templateService.deleteTemplates(template);
		addMessage(redirectAttributes, "删除视频模板成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/template/?repage";
	}

}