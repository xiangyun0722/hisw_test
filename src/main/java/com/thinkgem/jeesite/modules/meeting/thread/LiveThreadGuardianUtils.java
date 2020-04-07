package com.thinkgem.jeesite.modules.meeting.thread;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/***
 * 直播线程守护工具类。
 * @author tanwenkai（谭文开）   E-mail:tanwenkai@qq.com QQ:497460409
 * @version 创建时间：2016年9月20日 下午6:32:23
 */
public class LiveThreadGuardianUtils {
	
	static Logger logger = Logger.getLogger(LiveThreadGuardianUtils.class);
	
	public static Map<String,Thread> liveThreadPool = new HashMap<String,Thread>(); 
	
//	public static Map<String, String>  liveChannel =new HashMap<String, String>();
	
	
	public static void startLive(String id){//频道id
		if(liveThreadPool.get(id)!=null){
			logger.info("liveChannel 中存在频道"+id+"的转码进程");			
		}else{
			logger.info("启动频道。。"+id);
			//Thread cThread = liveThreadPool.get(id);
			Thread live= new LiveTranscodingThread(id);
//			liveThreadPool.put(id, live);
			liveThreadPool.put(id, live);	
		}
	}
	
	public static void stopLive(String id){//频道id
		 
	}
	 
}
