/**
 * 
 */
package com.thinkgem.jeesite.modules.live.api;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hisw.core.utils.FileUtil;
import com.hisw.core.utils.RandomUtil;
import com.hisw.core.utils.UrlUtil;
import com.thinkgem.jeesite.common.annotation.ApiAuth;
import com.thinkgem.jeesite.common.api.ExceptionUtil;
import com.thinkgem.jeesite.common.api.RequestUtil;
import com.thinkgem.jeesite.common.api.RestErrorCode;
import com.thinkgem.jeesite.common.api.RestResponse;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.live.entity.ChannelFiles;
import com.thinkgem.jeesite.modules.live.entity.LiveLiveChannel;
import com.thinkgem.jeesite.modules.live.service.BusinessSystemService;
import com.thinkgem.jeesite.modules.live.service.ChannelFilesService;
import com.thinkgem.jeesite.modules.live.service.LiveLiveChannelService;
import com.thinkgem.jeesite.modules.live.utils.QcloudUtil;
import com.thinkgem.jeesite.modules.meeting.thread.LiveThreadGuardianUtils;

/**
 * 直播频道ApiController
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
@Controller
@RequestMapping(value = "api/liveLiveChannel")
public class LiveLiveChannelApiController extends BaseController {

	@Autowired
	private LiveLiveChannelService liveLiveChannelService;
	
	@Autowired
	private BusinessSystemService businessSystemService;
	
	@Autowired
	private ChannelFilesService channelFilesService;
	
	@ApiAuth
	@RequestMapping(value ="/get")
    @ResponseBody
	public RestResponse get(HttpServletRequest request,HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
        try {
			String id = request.getParameter("id");
			LiveLiveChannel liveLiveChannel = liveLiveChannelService.get(id);
			Assert.notNull(liveLiveChannel, "id错误");
			restResponse.setData(liveLiveChannel);
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	
	@ApiAuth //必须登录
	@RequestMapping(value = {"list"})
	@ResponseBody
	public RestResponse list(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResponse restResponse = new RestResponse();
        try {
        	LiveLiveChannel liveLiveChannel=RequestUtil.form(LiveLiveChannel.class, request);
        	String uid =request.getParameter("uid");					//用户ID	
			Page<LiveLiveChannel> page = liveLiveChannelService.findPage(new Page<LiveLiveChannel>(request, response), liveLiveChannel); 
			page.isWebservicePageFlag = true;
  	      return new RestResponse(page);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
	
	@ApiAuth
	@RequestMapping(value ="/add")
	@ResponseBody
	public RestResponse add(LiveLiveChannel liveLiveChannel,HttpServletRequest request,HttpServletResponse response) {
		RestResponse restResponse = new RestResponse();
        try {
        	Assert.isTrue(StringUtils.isNotBlank(liveLiveChannel.getRelId()),"relId不能为空");
        	String  publicKey   = request.getParameter("publicKey");
        	liveLiveChannel.setBusinessSystem(businessSystemService.getByPublicKey(publicKey));
        	LiveLiveChannel liveLiveChannellDB = liveLiveChannelService.getByRelId(liveLiveChannel);
    		if( liveLiveChannellDB == null ){
    			liveLiveChannelService.save(liveLiveChannel);//先保存起来。获取id信息。
    			//添加一个参数：直播平台。
    			String livePlatform = request.getParameter("livePlatform");//直播平台。0(默认，腾讯直播，1:本地直播。)
    			if(StringUtils.isBlank(livePlatform) || StringUtils.equalsIgnoreCase(livePlatform,LiveLiveChannel.LIVE_PLATFORM_QCLOUD )){
    				//使用key。调用腾讯直播接口，创建直播信息。
    				String pushstreamKey = Global.getConfig("live.qcloud.pushstream.secret.key");//推流防盗链key。 qcloud key。
    				//String apiKey = Global.getConfig("live.qcloud.api.secret.key");//接口key。
        			String mid = liveLiveChannel.getId();
        			String bizid = "8478";//+RandomUtil.generateConfirmCode(4, 0); 
        			String streamId= bizid+"_"+mid;
        			long tx = DateUtils.addDays(new Date(), 2).getTime()/1000;//推流地址 2天之后超时。
        			String pushUrl = "rtmp://"+bizid+".livepush.myqcloud.com/live/"+streamId+"?bizid="+bizid+"&"+QcloudUtil.getSafeUrl(pushstreamKey,streamId,tx);//bizid="+bizid+"&
        			String  rtmpUrl= "rtmp://"+bizid+".liveplay.myqcloud.com/live/"+streamId;
        			String hlsUrl = "http://"+bizid+".liveplay.myqcloud.com/live/"+streamId+".m3u8";
        			String channelSecretKey = IdGen.uuid();
        			liveLiveChannel.setChannelSecretKey(channelSecretKey);//频道key
        			liveLiveChannel.setPushRtmpUrl(pushUrl);
        			liveLiveChannel.setRtmpUrl(rtmpUrl);
        			liveLiveChannel.setHlsUrl(hlsUrl);
        			liveLiveChannel.setStreamId(streamId);
        			liveLiveChannel.setLivePlatform(LiveLiveChannel.LIVE_PLATFORM_QCLOUD);//腾讯云直播
                	liveLiveChannelService.save(liveLiveChannel);
    			}else if(StringUtils.equalsIgnoreCase(livePlatform,LiveLiveChannel.LIVE_PLATFORM_LOCAL )){//本地直播平台
                	String hlsPrefixUrl= Global.getConfig("hls.prefix.url");//hls 的前缀信息
        			String rtmpPushUrlPrefix= Global.getConfig("rtmp.push.prefix.url");//rtmp://180.153.102.20/swaps/ch1
        			String playRtmpUrlPrefix= Global.getConfig("play.rtmp.prefix.url");
        			String hlsLocalPath 	= Global.getConfig("hls.local.path.prefix");
        			String mid = liveLiveChannel.getId();
        			String channelSecretKey = IdGen.uuid();
        			liveLiveChannel.setChannelSecretKey(channelSecretKey);//频道key
        			String newPlayUrl  = UrlUtil.addUrl(hlsPrefixUrl, mid+"/live.m3u8");
        			FileUtil.createFolder(hlsPrefixUrl+"/"+mid);
        			liveLiveChannel.setHlsUrl(newPlayUrl);//播放地址
        			String newHlsLocalPath = UrlUtil.addUrl(hlsLocalPath, mid);
        			FileUtil.createFolder(newHlsLocalPath);
        			liveLiveChannel.setPushRtmpUrl(UrlUtil.addUrl(rtmpPushUrlPrefix, mid)+"?channelSecretKey="+channelSecretKey+"&relId="+liveLiveChannel.getRelId());//把relId 和  channelSecretKey 加到推流地址上去。
        			liveLiveChannel.setRtmpUrl(UrlUtil.addUrl(playRtmpUrlPrefix, mid));
        			liveLiveChannel.setLivePlatform(LiveLiveChannel.LIVE_PLATFORM_LOCAL);//本地平台。
                	liveLiveChannelService.save(liveLiveChannel);
    			}
    		}else{
    			liveLiveChannel = liveLiveChannellDB;
    		}
    		liveLiveChannel.setBusinessSystem(null);
        	return new RestResponse(liveLiveChannel);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
    }
	
	/**@ApiAuth
	@RequestMapping(value = "start")
	@ResponseBody
	public RestResponse start(LiveLiveChannel liveLiveChannel, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		RestResponse restResponse = new RestResponse();
        try {
        	//使用relid去数据库中查询，如果数据库中不存在。则增加到系统中去。
    		Assert.notNull(liveLiveChannel.getRelId(), "relId不允许为空");
    		String  publicKey = request.getParameter("publicKey");
        	liveLiveChannel.setBusinessSystem(businessSystemService.getByPublicKey(publicKey));
        	String  channelSecretKey = request.getParameter("channelSecretKey");
        	liveLiveChannel.setChannelSecretKey(channelSecretKey);
    		LiveLiveChannel liveLiveChannelDB = liveLiveChannelService.getByRelId(liveLiveChannel);
    		if(liveLiveChannelDB == null){
    			logger.error("查不到相关频道！请创建。");
    		}
    		Assert.notNull(liveLiveChannelDB, "查不到相关频道！请创建。");
			String mid = liveLiveChannelDB.getId();
			LiveThreadGuardianUtils.startLive(mid);//启动频道
			restResponse.setData(liveLiveChannelDB);
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	**/
	
	/***
	 * 频道文件列表
	 * @param liveLiveChannel
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@ApiAuth
	@RequestMapping(value = "fileList")
	@ResponseBody
	public RestResponse fileList(LiveLiveChannel liveLiveChannel, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		RestResponse restResponse = new RestResponse();
        try {
        	//使用relid去数据库中查询，如果数据库中不存在。则增加到系统中去。
    		Assert.notNull(liveLiveChannel.getRelId(), "relId不允许为空");
    		String  publicKey = request.getParameter("publicKey");
        	liveLiveChannel.setBusinessSystem(businessSystemService.getByPublicKey(publicKey));
        	String  channelSecretKey = request.getParameter("channelSecretKey");
        	liveLiveChannel.setChannelSecretKey(channelSecretKey);
        	ChannelFiles channelFilesTemp = new ChannelFiles();
        	channelFilesTemp.setLiveChannel(liveLiveChannel);
        	LiveLiveChannel liveLiveChannelDB = liveLiveChannelService.getByChannelSecretKey(liveLiveChannel);
        	if(liveLiveChannelDB == null){
    			logger.error("查不到相关频道！");
    		}
    		Assert.notNull(liveLiveChannelDB, "查不到相关频道！");
        	ChannelFiles channelFiles = new ChannelFiles();
        	channelFiles.setLiveChannel(liveLiveChannelDB);
        	List<ChannelFiles> channelFilesList = channelFilesService.findList(channelFiles);
			restResponse.setData(channelFilesList);
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	
	
	@ApiAuth
	@RequestMapping(value ="/save")
	@ResponseBody
	public RestResponse save(HttpServletRequest request,HttpServletResponse response) {
		RestResponse restResponse = new RestResponse();
        try {
        	LiveLiveChannel liveLiveChannel=RequestUtil.form(LiveLiveChannel.class, request);
        	liveLiveChannelService.save(liveLiveChannel);
        	return new RestResponse(liveLiveChannel);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
    }
	
//	 on_publish http://localhost:8080/publish;  
//     on_play http://localhost:8080/play;  
//     on_record_done http://localhost:8080/record_done;  
	/***
	 * 推流的时候，需要校验推流合法性，并且启动转码。(本地推流的时候使用)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/publish")
	@ResponseBody
	public String publish(HttpServletRequest request,HttpServletResponse response) {
        try {
        	String  relId     = request.getParameter("relId");
        	String  channelSecretKey = request.getParameter("channelSecretKey");
        	LiveLiveChannel liveLiveChannel = new LiveLiveChannel();
        	liveLiveChannel.setRelId(relId);
        	liveLiveChannel.setChannelSecretKey(channelSecretKey);//公钥
        	LiveLiveChannel liveLiveChannelDb = liveLiveChannelService.getByChannelSecretKey(liveLiveChannel);
        	if(liveLiveChannelDb!=null){
        		//如果频道密钥 为8个8，或者0ab1b45ba644414b90b321c94a97b074 （金山测试），则不需要校验权限。直接绕过验证。
        		if(StringUtils.equalsIgnoreCase(channelSecretKey, "88888888") || StringUtils.equalsIgnoreCase(channelSecretKey, "0ab1b45ba644414b90b321c94a97b074") || StringUtils.equalsIgnoreCase(liveLiveChannelDb.getRelId(), relId)){
            		response.setStatus(201);
            		logger.info("校验成功！"+"relId:"+relId+",channelSecretKey:"+channelSecretKey);
            		//启动频道转码。
            		LiveThreadGuardianUtils.startLive(liveLiveChannelDb.getId());
            		return "校验成功！";
            	}else{
            		logger.error("校验错误！"+"relId:"+relId+",channelSecretKey:"+channelSecretKey);
            		response.setStatus(501);
            		return "公共key，密码错误！";
            	}
        	}else{
        		logger.error("channelSecretKey:"+channelSecretKey+"，错误，频道不存在！");
        		response.setStatus(501);
        		return "频道错误，频道不存在！";
        	}
        } catch (Exception ex) {
        	logger.error(ex.getMessage(),ex);
        }
        return null;
    }
	
	/***
	 * 推送结束事件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/publish_done")
	@ResponseBody
	public String publish_done(HttpServletRequest request,HttpServletResponse response) {
        return "publish_done";
    }
	
	/***
	 * 播放的时候调用的方法。
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/play")
	@ResponseBody
	public String play(HttpServletRequest request,HttpServletResponse response) {
        return "play";
    }
	
	/***
	 * 录制结束事件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/record_done")
	@ResponseBody
	public String record_done(HttpServletRequest request,HttpServletResponse response) {
        return "record_done";
    }
	
	
	//@ApiAuth
	/***
	 * 检查频道key
	 * @param request
	 * @param response
	 * @return
	 */
	/**
	@RequestMapping(value ="/checkChannelSecretKey")
	@ResponseBody
	public String checkChannelSecretKey(HttpServletRequest request,HttpServletResponse response) {
        try {
        	String  relId     = request.getParameter("relId");
        	String  channelSecretKey = request.getParameter("channelSecretKey");
        	LiveLiveChannel liveLiveChannel = new LiveLiveChannel();
        	liveLiveChannel.setRelId(relId);
        	liveLiveChannel.setChannelSecretKey(channelSecretKey);//公钥
        	LiveLiveChannel liveLiveChannelDb = liveLiveChannelService.getByChannelSecretKey(liveLiveChannel);
        	if(liveLiveChannelDb!=null){
        		if(StringUtils.equalsIgnoreCase(liveLiveChannelDb.getRelId(), relId)){
            		response.setStatus(201);
            		logger.info("校验成功！"+"relId:"+relId+",channelSecretKey:"+channelSecretKey);
            		return "校验成功！";
            	}else{
            		logger.error("校验错误！"+"relId:"+relId+",channelSecretKey:"+channelSecretKey);
            		response.setStatus(501);
            		return "公共key，密码错误！";
            	}
        	}else{
        		logger.error("channelSecretKey:"+channelSecretKey+"，错误，频道不存在！");
        		response.setStatus(501);
        		return "频道错误，频道不存在！";
        	}
        } catch (Exception ex) {
        	logger.error(ex.getMessage(),ex);
        }
        return null;
    }
    **/
	
	@ApiAuth
	@RequestMapping(value ="/delete")
	@ResponseBody
	public RestResponse delete(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		RestResponse restResponse = new RestResponse();
        try {
        	LiveLiveChannel liveLiveChannelModel=RequestUtil.form(LiveLiveChannel.class, request);
        	liveLiveChannelService.delete(liveLiveChannelModel);
        	return new RestResponse(RestErrorCode.SUCESS_CODE, "删除成功");
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
}