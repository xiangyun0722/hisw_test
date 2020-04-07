package com.thinkgem.jeesite.modules.zjvideo.job;

import com.thinkgem.jeesite.common.utils.AliyunCdnUtils;
import com.thinkgem.jeesite.modules.zjvideo.dao.MediaDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;
import com.thinkgem.jeesite.modules.zjvideo.service.MediaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 视频cdn缓存预热定时任务
 * @author liyangyang
 * @date 2019年11月27日14:07:40
 */
//@Controller
public class PushCdnCacheJob {

    private  Logger log = Logger.getLogger(PushCdnCacheJob.class);
    @Autowired
    private MediaService mediaService;
    @Autowired
    private MediaDao mediaDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 每分钟执行
     */
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void run(){
        //查询过去一分钟新增的视频
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,-1);
        Date time = calendar.getTime();
        String format = simpleDateFormat.format(time);
        List<Media> list = mediaDao.selectAfterTime(format);
        if(!list.isEmpty()){
            for (Media media:list) {
                String path = mediaService.getPath(media);
                if(StringUtils.isNotBlank(path)){
                    AliyunCdnUtils.pushObjectCache(path);
                    log.info("视频cdn缓存预热数------->"+list.size());
                }
            }
        }
    }
}
