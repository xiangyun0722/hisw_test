package com.thinkgem.jeesite.common.utils.vod;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
  
/**  
 * Java获取播放文件的时长 
 *   
 * @author 高焕杰
 */  
public class VideoTool{   
  
    /** 
     * Java获取播放文件的时长，支持的播放文件的格式有：mp3、mp4、f4v、3gp、flv、rmvb、mkv，其它格式没有测试。
     * 
     * @author 高焕杰
     */  
    public static String getPlayTime(String  playFilePath) {
        String playTime = null;
        File playFile = new File(playFilePath); 
        if (playFile.exists()) {
            try {
                Encoder encoder = new Encoder();
                MultimediaInfo multimediaInfo = encoder.getInfo( playFile);
                playTime = TimeFormatTool.convertTimeFormat((multimediaInfo.getDuration())/1000);
            } catch (InputFormatException e) {
                e.printStackTrace();
            } catch (EncoderException e) {
                e.printStackTrace();
            }
        }
        return  playTime;
    }
}