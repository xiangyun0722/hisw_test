package com.thinkgem.jeesite.modules.meeting.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;

import com.hisw.core.utils.FileUtil;
import com.hisw.core.utils.process.ExecuteResult;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.live.utils.ExeCmdUtil;

/***
 * flv文件添加关键帧线程
 * @author tanwenkai（谭文开）   E-mail:tanwenkai@qq.com QQ:497460409
 * @version 创建时间：2016年12月8日 下午5:39:27
 */
public class YamdiThread  extends Thread {
	
	private static Logger logger = Logger.getLogger(YamdiThread.class);
	
	private static String yamdiPath = Global.getConfig("yamdi.path");
	
	//public static String yamdiCmd="{yamdiPath}  -i {srcFile} -o  {descFile} ";//转码命令。
	
//	public static BlockingQueue<String> flvFilesQueue = new LinkedBlockingQueue<String>();
	public static ConcurrentLinkedQueue<String> flvFilesQueue = new ConcurrentLinkedQueue<String>();
	
	@Override
	public void run() {
		super.run();
		while(true){ 
			try {
				while (!flvFilesQueue.isEmpty()) {
					String flvFile =flvFilesQueue.poll();
					if(FileUtil.isExists(flvFile)){
						StopWatch stopWatch = new StopWatch();
						stopWatch.start();
						//获取一个flv的 temp文件地址。然后再执行命令。
						String newFileName = flvFile+".yamdi.temp";
						String yamdiCmd    = yamdiPath +" -i "+flvFile+" -o "+newFileName;
						ExecuteResult  executeResult = ExeCmdUtil.exeCmdResult(yamdiCmd);
						logger.info(executeResult);
						stopWatch.stop();
						double countSec = stopWatch.getTotalTimeSeconds();
						logger.info(" 用时："+countSec+" s,命令："+yamdiCmd);
						//再把临时文件copy到原始文件中去。然后再删除临时文件。
						FileUtil.deleteFile(flvFile);
						FileUtil.copyFile(newFileName, flvFile);
						FileUtil.deleteFile(newFileName);
					}else{
						logger.error(flvFile+"文件不存在");
					}
		        }
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e,e);
			}finally{
				try {
					Thread.sleep(10*1000l);
				} catch (InterruptedException e) {
					 logger.error(e,e);
				}
			}
		}
	}
	
}
