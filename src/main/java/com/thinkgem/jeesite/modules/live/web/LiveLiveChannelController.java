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
import com.thinkgem.jeesite.modules.live.entity.LiveLiveChannel;
import com.thinkgem.jeesite.modules.live.service.LiveLiveChannelService;

/**
 * 直播频道Controller
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
@Controller
@RequestMapping(value = "${adminPath}/live/liveLiveChannel")
public class LiveLiveChannelController extends BaseController {

	@Autowired
	private LiveLiveChannelService liveLiveChannelService;
	
	@ModelAttribute
	public LiveLiveChannel get(@RequestParam(required=false) String id) {
		LiveLiveChannel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = liveLiveChannelService.get(id);
		}
		if (entity == null){
			entity = new LiveLiveChannel();
		}
		return entity;
	}
	
	@RequiresPermissions("live:liveLiveChannel:view")
	@RequestMapping(value = {"list", ""})
	public String list(LiveLiveChannel liveLiveChannel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LiveLiveChannel> page = liveLiveChannelService.findPage(new Page<LiveLiveChannel>(request, response), liveLiveChannel); 
		model.addAttribute("page", page);
		return "modules/live/liveLiveChannelList";
	}

	@RequiresPermissions("live:liveLiveChannel:view")
	@RequestMapping(value = "form")
	public String form(LiveLiveChannel liveLiveChannel, Model model) {
		model.addAttribute("liveLiveChannel", liveLiveChannel);
		return "modules/live/liveLiveChannelForm";
	}
	
	@RequiresPermissions("live:liveLiveChannel:view")
	@RequestMapping(value = "view")
	public String view(LiveLiveChannel liveLiveChannel, Model model) {
		model.addAttribute("liveLiveChannel", liveLiveChannel);
		return "modules/live/liveLiveChannelView";
	}
	
	@RequiresPermissions("live:liveLiveChannel:edit")
	@RequestMapping(value = "save")
	public String save(LiveLiveChannel liveLiveChannel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, liveLiveChannel)){
			return form(liveLiveChannel, model);
		}
		liveLiveChannelService.save(liveLiveChannel);
		addMessage(redirectAttributes, "保存直播频道成功");
		return "redirect:"+Global.getAdminPath()+"/live/liveLiveChannel/?repage";
	}
	
	@RequiresPermissions("live:liveLiveChannel:edit")
	@RequestMapping(value = "delete")
	public String delete(LiveLiveChannel liveLiveChannel, RedirectAttributes redirectAttributes) {
		liveLiveChannelService.delete(liveLiveChannel);
		addMessage(redirectAttributes, "删除直播频道成功");
		return "redirect:"+Global.getAdminPath()+"/live/liveLiveChannel/?repage";
	}

}