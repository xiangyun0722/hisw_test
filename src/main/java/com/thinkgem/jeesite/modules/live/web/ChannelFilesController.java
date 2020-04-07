/**
 * 
 */
package com.thinkgem.jeesite.modules.live.web;

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
import com.thinkgem.jeesite.modules.live.entity.ChannelFiles;
import com.thinkgem.jeesite.modules.live.service.ChannelFilesService;

/**
 * 直播频道录制文件Controller
 * @author tanwenkai@qq.com
 * @version 2016-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/live/channelFiles")
public class ChannelFilesController extends BaseController {

	@Autowired
	private ChannelFilesService channelFilesService;
	
	@ModelAttribute
	public ChannelFiles get(@RequestParam(required=false) String id) {
		ChannelFiles entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = channelFilesService.get(id);
		}
		if (entity == null){
			entity = new ChannelFiles();
		}
		return entity;
	}
	
	@RequiresPermissions("live:channelFiles:view")
	@RequestMapping(value = {"list", ""})
	public String list(ChannelFiles channelFiles, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ChannelFiles> page = channelFilesService.findPage(new Page<ChannelFiles>(request, response), channelFiles); 
		model.addAttribute("page", page);
		return "modules/live/channelFilesList";
	}

	@RequiresPermissions("live:channelFiles:view")
	@RequestMapping(value = "form")
	public String form(ChannelFiles channelFiles, Model model) {
		model.addAttribute("channelFiles", channelFiles);
		return "modules/live/channelFilesForm";
	}
	
	@RequiresPermissions("live:channelFiles:view")
	@RequestMapping(value = "view")
	public String view(ChannelFiles channelFiles, Model model) {
		model.addAttribute("channelFiles", channelFiles);
		return "modules/live/channelFilesView";
	}
	
	@RequiresPermissions("live:channelFiles:edit")
	@RequestMapping(value = "save")
	public String save(ChannelFiles channelFiles, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, channelFiles)){
			return form(channelFiles, model);
		}
		channelFilesService.save(channelFiles);
		addMessage(redirectAttributes, "保存直播频道录制文件成功");
		return "redirect:"+Global.getAdminPath()+"/live/channelFiles/?repage";
	}
	
	@RequiresPermissions("live:channelFiles:edit")
	@RequestMapping(value = "delete")
	public String delete(ChannelFiles channelFiles, RedirectAttributes redirectAttributes) {
		channelFilesService.delete(channelFiles);
		addMessage(redirectAttributes, "删除直播频道录制文件成功");
		return "redirect:"+Global.getAdminPath()+"/live/channelFiles/?repage";
	}

}