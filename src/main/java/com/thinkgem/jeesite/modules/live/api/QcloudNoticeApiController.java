package com.thinkgem.jeesite.modules.live.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hisw.core.utils.MD5;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.live.entity.LiveLiveChannel;
import com.thinkgem.jeesite.modules.live.service.LiveLiveChannelService;
import com.thinkgem.jeesite.modules.live.vo.QcloudNoticeVO;
import com.thinkgem.jeesite.modules.meeting.thread.LiveRecordingUtils;

/***
 * 腾讯云直播通知接口类
 * @author tanwenkai（谭文开）   E-mail:tanwenkai@qq.com QQ:497460409
 * @version 创建时间：2017年6月7日 上午9:04:26
 */
@Controller
@RequestMapping(value = "api/qcloudNotice")
public class QcloudNoticeApiController {
	
	private static Logger logger = Logger.getLogger(QcloudNoticeApiController.class);

	@Autowired
	private LiveLiveChannelService liveLiveChannelService;
	 
	@RequestMapping(value ="/receiveQcloudNotice")
	@ResponseBody
	public Map<String,Object> receiveQcloudNotice(@RequestBody QcloudNoticeVO qcloudNoticeVO,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//校验sign。sign = MD5(key + t) 
			logger.info("接收到的通知对象:"+qcloudNoticeVO);
			String apiKey = Global.getConfig("live.qcloud.api.secret.key");
			String signCheck = MD5.get32MD5(apiKey+qcloudNoticeVO.getT());
			if(StringUtils.equalsIgnoreCase(signCheck, qcloudNoticeVO.getSign())){
				//如果发现断流。则启动录制。
				if(QcloudNoticeVO.EVENT_TYPE_PUSH_STREAM==qcloudNoticeVO.getEvent_type()){
					//使用录制线程池。录制直播视频。
					LiveLiveChannel liveLiveChannelPara = new LiveLiveChannel();
					String streamId = qcloudNoticeVO.getStream_id();
					liveLiveChannelPara.setStreamId(streamId);
					List<LiveLiveChannel> liveLiveChannelList = liveLiveChannelService.findList(liveLiveChannelPara);
					if(liveLiveChannelList!=null && liveLiveChannelList.size()>=1){
						LiveLiveChannel liveLiveChannelDB= liveLiveChannelList.get(0);
						logger.info("liveLiveChannelList.size():"+liveLiveChannelList.size()+","+liveLiveChannelDB.getId()+","+streamId);
						LiveRecordingUtils.startLive(liveLiveChannelDB.getId(), streamId,liveLiveChannelDB.getRtmpUrl());
					}else{
						logger.error("接收到腾讯云直播发回来的通知。但是从数据库中无法查询到 stream_id："+qcloudNoticeVO.getStream_id()+"的频道信息");
					}
				}
			}else{
				logger.error("签名参数错误！腾讯云sign:"+qcloudNoticeVO.getSign()+",本地检查sign:"+signCheck);
			}
	        //{ "code":0 }
		} catch (Exception e) {
			logger.error(e,e);
		}
        map.put("code", 0);//返回已经成功接收到消息。
        return map;
	}
}
