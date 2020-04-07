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
import com.thinkgem.jeesite.modules.zjvideo.entity.Record;
import com.thinkgem.jeesite.modules.zjvideo.service.RecordService;

/**
 * 录制Controller
 * @author j.feng
 * @version 2015-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/record")
public class RecordController extends BaseController {

	@Autowired
	private RecordService recordService;
	
	@ModelAttribute
	public Record get(@RequestParam(required=false) String id) {
		Record entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recordService.get(id);
		}
		if (entity == null){
			entity = new Record();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:record:view")
	@RequestMapping(value = {"list", ""})
	public String list(Record record, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Record> page = recordService.findPage(new Page<Record>(request, response), record); 
		model.addAttribute("page", page);
		return "modules/zjvideo/recordList";
	}

	@RequiresPermissions("zjvideo:record:view")
	@RequestMapping(value = "form")
	public String form(Record record, Model model) {
		model.addAttribute("record", record);
		return "modules/zjvideo/recordForm";
	}

	@RequiresPermissions("zjvideo:record:edit")
	@RequestMapping(value = "save")
	public String save(Record record, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, record)){
			return form(record, model);
		}
		recordService.save(record);
		addMessage(redirectAttributes, "保存录制成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/record/?repage";
	}
	
	@RequiresPermissions("zjvideo:record:edit")
	@RequestMapping(value = "delete")
	public String delete(Record record, RedirectAttributes redirectAttributes) {
		recordService.delete(record);
		addMessage(redirectAttributes, "删除录制成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/record/?repage";
	}

}