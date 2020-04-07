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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.vod.VideoTool;
import com.thinkgem.jeesite.modules.live.entity.ChannelFiles;
import com.thinkgem.jeesite.modules.live.entity.LiveLiveChannel;
import com.thinkgem.jeesite.modules.live.service.ChannelFilesService;
import com.thinkgem.jeesite.modules.live.utils.ExeCmdUtil;

/**
 * 直播转码线程。这里执行一个 shell 命令。循环执行一个文件流
 * @author tanwenkai（谭文开）   E-mail:tanwenkai@qq.com QQ:497460409
 * @version 创建时间：2016年9月20日 下午6:39:12
 */
public class LiveTranscodingThread extends Thread {
	
	static Logger logger = Logger.getLogger(LiveTranscodingThread.class);
	
	// /usr/local/hido/hido -loglevel quiet -rtbufsize 2000000k -i rtmp://localhost/swaps/ch2bak  -s 640*360 -b:v 800k -vcodec libx264 -acodec libvo_aacenc -hls_time 8 -hls_list_size 10 -hls_wrap 10 -f hls /home/streams/ch2bak/hd/live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y /home/streams/vod/ch2/20160920.flv
	
	public static String RTMP_PORT= Global.getConfig("rtmp.port");
	
	public static String VOD_ROOT = Global.getConfig("vod.root");//例如：/home/streams/vod/chinanet
	
	public static String HLS_ROOT = Global.getConfig("hls.root");//例如：/streams/hls 默认为：/dev/shm/hls
	
	public static int MAX_FAILL_COUNT = 10;

	//vod prefix 视频下载前缀
	public static String DOWNLOAD_VOD_PREFIX = Global.getConfig("download.vod.prefix");//例如：http://ww
	
	private ChannelFilesService  channelFilesService = SpringContextHolder.getBean(ChannelFilesService.class);
	
	private String cid;//执行的会议命令
	
	public LiveTranscodingThread(String cid){
		this.cid =cid;
		setMaxFailCount();//每次转码设置最大的失败次数。
		start();
	}
	
	/**
	 * 设置最大的失败次数。
	 */
	public static void setMaxFailCount(){
		if(StringUtils.isBlank(Global.getConfig("max.fail.count"))){//如果不为空。则设置。
			MAX_FAILL_COUNT = Integer.parseInt(Global.getConfig("max.fail.count"));
		}
	}

	@Override
	public void run() {
		super.run();
		int failCount = 0;
		try {
			while (failCount<= MAX_FAILL_COUNT ) {
				StopWatch stopWatch = new StopWatch();
				stopWatch.start();
				//String cmd = "/usr/local/hido/hido -loglevel quiet -rtbufsize 2000000k -i rtmp://localhost/swaps/"+cid+"  -s 640*360 -b:v 800k -vcodec libx264 -acodec libvo_aacenc -hls_time 8 -hls_list_size 10 -hls_wrap 10 -f hls /streams/hls/"+cid+"/live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y /home/streams/vod/chinanet/"+cid+"_"+DateFormatter.getNotyyyyMMddHHmmss()+".flv";
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 640*360 -b:v 800k -c copy -bsf:v h264_mp4toannexb  -hls_time 8 -hls_list_size 5 -hls_wrap 10 -f hls /streams/hls/"+cid+"/live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y /home/streams/vod/chinanet/"+cid+"_"+DateFormatter.getNotyyyyMMddHHmmss()+".flv";
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 640*360 -b:v 800k -c copy -bsf:v h264_mp4toannexb  -hls_time 10 -hls_list_size 5 -hls_wrap 10 -f hls /streams/hls/"+cid+"/live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y /home/streams/vod/chinanet/"+cid+"_"+DateFormatter.getNotyyyyMMddHHmmss()+".flv";
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 640*360 -b:v 800k -c copy -bsf:v h264_mp4toannexb  -vcodec  libx264 -acodec libvo_aacenc -vcodec copy -acodec copy -ar 44100 -f flv -y /home/streams/vod/chinanet/"+cid+"_"+DateFormatter.getNotyyyyMMddHHmmss()+".flv  -b 2200k -r 30 -f mpegts - | /home/m3u8-segmenter-master/m3u8-segmenter -i - -d 8 -n 10 -p /streams/hls/"+cid+"/live -m /streams/hls/"+cid+"/live.m3u8 -u http://101.95.48.119"; //-u ../../
				//String  cmd ="/usr/local/hido/hido -i myapp.mp4  -hls_time 10 -hls_wrap 10 -hls_list_size 10   -g 2  -pix_fmt yuv420p -f hls test/test.m3u8";
				String vodFoder = VOD_ROOT+"/"+cid;
				FileUtil.createFolders(vodFoder);
				String startTime = DateFormatter.getNotyyyyMMddHHmmss();
				Date startDateTime = new Date();
				String fileName = startTime +".flv";
				String flvPath = vodFoder+"/"+fileName;
				String hlsFoder= HLS_ROOT+"/"+cid+"/";
				FileUtil.createFolders(hlsFoder);
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 1280*720 -b:v 1000k -r 25 -bsf:v h264_mp4toannexb  -hls_time 10 -hls_list_size 5 -hls_wrap 10 -f hls  "+hlsFoder+"live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y "+ flvPath;
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 1280*720 -b:v 1000k -r 25  -hls_time 10 -hls_list_size 5 -hls_wrap 10 -f hls  "+hlsFoder+"live.m3u8  -qscale 0  -c:v libx264 -c:a libvo_aacenc -ar 44100 -f flv -y "+ flvPath;
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 1280*720 -b:v 1000k  -bsf:v h264_mp4toannexb  -hls_time 10 -hls_list_size 5 -hls_wrap 10 -f hls  "+hlsFoder+"live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y "+ flvPath;
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 1280*720 -b:v 1000k  -bsf:v h264_mp4toannexb  -hls_time 10 -hls_list_size 5 -hls_wrap 10 -g 1 -f hls  "+hlsFoder+"live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y "+ flvPath;
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 1280*720 -b:v 1000k  -bsf:v h264_mp4toannexb  -hls_time 10 -hls_list_size 5 -hls_wrap 10 -g 1 -f hls  "+hlsFoder+"live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y "+ flvPath;
				//BI的转码
				String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"   -s 1280*720 -b:v 1000k -vcodec libx264 -acodec libvo_aacenc -hls_time 8 -hls_list_size 4 -hls_wrap 10 -f hls "+hlsFoder+"live.m3u8  -qscale 0 -vcodec copy -acodec copy -ar 44100 -f flv -y "+ flvPath;
				//old
				//String cmd = "/usr/local/hido/hido -loglevel error -rtbufsize 2000000k -i rtmp://localhost:"+RTMP_PORT+"/swaps/"+cid+"  -s 1280*720 -b:v 1000k  -bsf:v h264_mp4toannexb  -hls_time 10 -hls_list_size 5 -hls_wrap 10 -g 1 -f hls  "+hlsFoder+"live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y "+ flvPath;
				//cmd = "ps -ef | grep -v grep";
				logger.info("启动命令，频道："+cid+",failCount:"+failCount+", start ");
				//Srring cmd = "/usr/local/hido/hido -loglevel quiet -rtbufsize 2000000k -i rtmp://localhost/swaps/"+cid+"  -s 640*360 -b:v 800k -vcodec libx264 -acodec libvo_aacenc -hls_time 10 -hls_list_size 3 -hls_wrap 3 -f hls /streams/hls/"+cid+"/live.m3u8  -qscale 0  -vcodec copy -acodec copy -ar 44100 -f flv -y /home/streams/vod/chinanet/"+cid+"_"+DateFormatter.getNotyyyyMMddHHmmss()+".flv";
				ExecuteResult  result =ExeCmdUtil.exeCmdResult(cmd);
				logger.info(result);
				stopWatch.stop();
				double countSec = stopWatch.getTotalTimeSeconds();
				logger.info("转码结束，频道："+cid+",failCount:"+failCount+" 用时："+countSec+" s"+", end ");
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
					String downloadUrl = UrlUtil.addUrl(DOWNLOAD_VOD_PREFIX, cid+"/"+fileName);//拼装下载地址
					channelFiles.setDownloadUrl(downloadUrl);
					channelFiles.setLiveChannel(new LiveLiveChannel(cid));
					channelFilesService.save(channelFiles);
				}
				YamdiThread.flvFilesQueue.offer(flvPath);
				FileUtil.delFolder(hlsFoder);//删除直播内容
			}
		} catch (Exception e) {
			logger.error(e,e);
		}finally{
			logger.info("移除当前线程中的进程。。cid:"+cid);
			LiveThreadGuardianUtils.liveThreadPool.remove(cid);
		}
	}
	
	
}
