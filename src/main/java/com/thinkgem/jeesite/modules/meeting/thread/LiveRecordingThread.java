package com.thinkgem.jeesite.modules.meeting.thread;

import java.io.File;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;
import com.hisw.core.utils.DateFormatter;
import com.hisw.core.utils.FileUtil;
import com.hisw.core.utils.UrlUtil;
import com.hisw.core.utils.process.ExecuteResult;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.vod.VideoTool;
import com.thinkgem.jeesite.modules.live.entity.ChannelFiles;
import com.thinkgem.jeesite.modules.live.entity.LiveLiveChannel;
import com.thinkgem.jeesite.modules.live.service.ChannelFilesService;
import com.thinkgem.jeesite.modules.live.utils.ExeCmdUtil;

/***
 * 录制直播线程
 * @author tanwenkai（谭文开）   E-mail:tanwenkai@qq.com QQ:497460409
 * @version 创建时间：2017年6月7日 上午11:21:56
 */
public class LiveRecordingThread extends Thread {
	
	static Logger logger = Logger.getLogger(LiveRecordingThread.class);
	
	public static String VOD_ROOT = Global.getConfig("vod.root");//例如：/home/streams/vod/chinanet
	
//	public static int MAX_FAILL_COUNT = 1;

	//vod prefix 视频下载前缀
	public static String DOWNLOAD_VOD_PREFIX = Global.getConfig("download.vod.prefix");//例如：http://ww
	
	private ChannelFilesService  channelFilesService = SpringContextHolder.getBean(ChannelFilesService.class);
	
	private String id;//直播的id
	
	private String recordingRtmpUrl;//录制的rtmpUrl
	
	public LiveRecordingThread(String id,String recordingRtmpUrl){
		this.id = id;
		this.recordingRtmpUrl = recordingRtmpUrl;
		start();
	}
	

	@Override
	public void run() {
		super.run();
		int failCount = 0;
		try {
//			while (failCount< MAX_FAILL_COUNT ) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			String vodFoder = VOD_ROOT+"/"+id;
			FileUtil.createFolders(vodFoder);
			String startTime = DateFormatter.getNotyyyyMMddHHmmss();
			Date startDateTime = new Date();
			String fileName = startTime +".flv";
			String flvPath = vodFoder+"/"+fileName;
			String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i "+recordingRtmpUrl+"  -qscale 0 -vcodec copy -acodec copy -ar 44100 -f flv -y "+ flvPath;
			logger.info("启动录制，频道："+id+",failCount:"+failCount+", start ");
			ExecuteResult  result =ExeCmdUtil.exeCmdResult(cmd);
			logger.info(result);
			stopWatch.stop();
			double countSec = stopWatch.getTotalTimeSeconds();
			logger.info("录制结束，频道："+id+",failCount:"+failCount+" 用时："+countSec+" s"+", end ");
			if( countSec<=60 ){//小于60s 。则说明流断了
				++failCount;//算一次没有流的异常 
				//删除上一次的录制的文件和清空直播的m3u8文件。
				FileUtil.delFile(flvPath);
			}else{
				failCount = 0;//初始化为0重新计数
				//文件信息入库。文件大小，文件开始时间，文件结束时间，文件预览地址，文件下载地址。
				//startTime,endtime,size,downLoadUrl,
				ChannelFiles channelFiles = new ChannelFiles();
				channelFiles.setName(fileName);
				channelFiles.setFilePath(flvPath);
				File flvFile = new File(flvPath);
				channelFiles.setFileSize(flvFile.length());
				channelFiles.setStartDatetime(startDateTime);//开始录制时间
				channelFiles.setEndDatetime(new Date());	 //结束时间
				channelFiles.setPlayTime(VideoTool.getPlayTime(flvPath));
				String downloadUrl = UrlUtil.addUrl(DOWNLOAD_VOD_PREFIX, id+"/"+fileName);//拼装下载地址
				channelFiles.setDownloadUrl(downloadUrl);
				channelFiles.setLiveChannel(new LiveLiveChannel(id));
				channelFilesService.save(channelFiles);
			}
			YamdiThread.flvFilesQueue.offer(flvPath);
//			}
		} catch (Exception e) {
			logger.error(e,e);
		}finally{
			logger.info("录制直播：移除当前线程中的进程。。id:"+id);
			LiveRecordingUtils.liveRecordingThreadPool.remove(id);
		}
	}
	
	
}
