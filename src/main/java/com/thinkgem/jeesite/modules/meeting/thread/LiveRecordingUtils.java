package com.thinkgem.jeesite.modules.meeting.thread;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/***
 * 直播录制工具
 * @author tanwenkai（谭文开）   E-mail:tanwenkai@qq.com QQ:497460409
 * @version 创建时间：2017年6月7日 上午11:20:53
 */
public class LiveRecordingUtils {
	
	static Logger logger = Logger.getLogger(LiveRecordingUtils.class);
	
	public static Map<String,Thread> liveRecordingThreadPool = new HashMap<String,Thread>(); 

	public static void startLive(String id,String streamId,String recordingRtmpUrl){
		if(liveRecordingThreadPool.get(id)!=null){
			logger.info("liveRecordingThreadPool 中存在录制线程："+id+",streamId:"+streamId);			
		}else{
			logger.info("启动直播录制。。id:"+id);
			Thread live= new LiveRecordingThread(id,recordingRtmpUrl);
			liveRecordingThreadPool.put(id, live);	
		}
	}
	
}
