package com.thinkgem.jeesite.modules.meeting.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hisw.core.utils.FileUtil;
import com.hisw.core.utils.UrlUtil;
import com.thinkgem.jeesite.common.api.ExceptionUtil;
import com.thinkgem.jeesite.common.api.RestResponse;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.meeting.entity.MeetingChinanetLiveChannel;
import com.thinkgem.jeesite.modules.meeting.service.MeetingChinanetLiveChannelService;
import com.thinkgem.jeesite.modules.meeting.thread.LiveThreadGuardianUtils;

/**
 * 中国电信企业直播频道Controller
 * @author tanwenkai
 * @version 2016-09-20
 */
@Controller
@RequestMapping(value = "api/meetingChinanetLiveChannel")
public class MeetingChinanetLiveChannelApiController extends BaseController {
	

	@Autowired
	private MeetingChinanetLiveChannelService meetingChinanetLiveChannelService;
	
	/***
	 * 创建一个会议频道
	 * @param meetingChinanetLiveChannel
	 * @param model
	 * @param redirectAttributes
	 * @return 
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public RestResponse add(MeetingChinanetLiveChannel meetingChinanetLiveChannel, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		RestResponse restResponse = new RestResponse();
        try {
        	//使用relid去数据库中查询，如果数据库中不存在。则增加到系统中去。
    		Assert.notNull(meetingChinanetLiveChannel.getRelId(), "relId不允许为空");
    		MeetingChinanetLiveChannel meetingChinanetLiveChannelDB = meetingChinanetLiveChannelService.getByRelId(meetingChinanetLiveChannel);
    		if(meetingChinanetLiveChannelDB==null){
    			//如果不存在。则添加。设置频道的访问路径。
    			meetingChinanetLiveChannelService.save(meetingChinanetLiveChannel);
    			String hlsPrefixUrl= Global.getConfig("hls.prefix.url");//hls 的前缀信息
    			String rtmpPushUrlPrefix= Global.getConfig("rtmp.push.prefix.url");//rtmp://180.153.102.20/swaps/ch1
    			String hlsLocalPath 	= Global.getConfig("hls.local.path.prefix");
    			//ch2bak/hd/live.m3u8 
    			//http://180.153.102.20/hls/ch2bak/live.m3u8
    			String mid = meetingChinanetLiveChannel.getId();
    			String newPlayUrl  = UrlUtil.addUrl(hlsPrefixUrl, mid+"/live.m3u8");
    			FileUtil.createFolder(hlsPrefixUrl+"/"+mid);
    			meetingChinanetLiveChannel.setHlsHd(newPlayUrl);//播放地址
    			String newHlsLocalPath = UrlUtil.addUrl(hlsLocalPath, mid);
    			FileUtil.createFolder(newHlsLocalPath);
//    			String cmd = "/usr/local/hido/hido -loglevel quiet -rtbufsize 2000000k -i rtmp://localhost/swaps/"+mid+""
//    					+ "  -s 640*360 -b:v 800k -vcodec libx264 -acodec libvo_aacenc -hls_time 8 -hls_list_size 10 -hls_wrap 10 -f hls"
//    					+ " "+UrlUtil.addUrl(newHlsLocalPath, "live.m3u8")+"  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y"
//    					+ " "+UrlUtil.addUrl(flvPath, mid+"_"+DateFormatter.getNotyyyyMMddHHmmss()+".flv");
//    			meetingChinanetLiveChannel.setRemarks(cmd);
    			meetingChinanetLiveChannel.setPushRtmpUrl(UrlUtil.addUrl(rtmpPushUrlPrefix, mid));
    			meetingChinanetLiveChannelService.save(meetingChinanetLiveChannel);
    			logger.info(meetingChinanetLiveChannel.toString());
    			//LiveThreadGuardianUtils.startLive(mid);//启动频道
    		}else{
    			meetingChinanetLiveChannel = meetingChinanetLiveChannelDB;
    		}
			restResponse.setData(meetingChinanetLiveChannel);
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	
	/**
	 * 启动一个会议。
	 * @param meetingChinanetLiveChannel
	 * @param model
	 * @param redirectAttributes
	 * @return 
	 */
	@RequestMapping(value = "start")
	@ResponseBody
	public RestResponse start(MeetingChinanetLiveChannel meetingChinanetLiveChannelPara, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		RestResponse restResponse = new RestResponse();
        try {
        	//使用relid去数据库中查询，如果数据库中不存在。则增加到系统中去。
    		Assert.notNull(meetingChinanetLiveChannelPara.getRelId(), "relId不允许为空");
    		MeetingChinanetLiveChannel meetingChinanetLiveChannel = meetingChinanetLiveChannelService.getByRelId(meetingChinanetLiveChannelPara);
    		if(meetingChinanetLiveChannel==null){
    			logger.error("查不到相关频道！请创建。");
    		}
    		Assert.notNull(meetingChinanetLiveChannel, "查不到相关频道！请创建。");
			String mid = meetingChinanetLiveChannel.getId();
			LiveThreadGuardianUtils.startLive(mid);//启动频道
			restResponse.setData(meetingChinanetLiveChannel);
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	 
	@RequestMapping(value = "stop")
	@ResponseBody
	public RestResponse stop(MeetingChinanetLiveChannel meetingChinanetLiveChannelPara, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		RestResponse restResponse = new RestResponse();
        try {
        	//使用relid去数据库中查询，如果数据库中不存在。则增加到系统中去。
    		Assert.notNull(meetingChinanetLiveChannelPara.getRelId(), "relId不允许为空");
    		MeetingChinanetLiveChannel meetingChinanetLiveChannel = meetingChinanetLiveChannelService.getByRelId(meetingChinanetLiveChannelPara);
    		Assert.notNull(meetingChinanetLiveChannel, "查不到相关频道！请创建。");
			LiveThreadGuardianUtils.stopLive(meetingChinanetLiveChannel.getId());//启动频道
			restResponse.setData(meetingChinanetLiveChannel);
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	
	
	@ModelAttribute
	public MeetingChinanetLiveChannel get(@RequestParam(required=false) String id) {
		MeetingChinanetLiveChannel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = meetingChinanetLiveChannelService.get(id);
		}
		if (entity == null){
			entity = new MeetingChinanetLiveChannel();
		}
		return entity;
	}
	
	@RequestMapping(value = "get")
	@ResponseBody
	public RestResponse getInfo(MeetingChinanetLiveChannel meetingChinanetLiveChannelPara, Model model) {
		RestResponse restResponse = new RestResponse();
        try {
        	//使用relid去数据库中查询，如果数据库中不存在。则增加到系统中去。
    		Assert.notNull(meetingChinanetLiveChannelPara.getRelId(), "relId不允许为空");
    		MeetingChinanetLiveChannel meetingChinanetLiveChannel = meetingChinanetLiveChannelService.getByRelId(meetingChinanetLiveChannelPara);
    		Assert.notNull(meetingChinanetLiveChannel, "查不到相关频道,请创建!");
			restResponse.setData(meetingChinanetLiveChannel);
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}

}