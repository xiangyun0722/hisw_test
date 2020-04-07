package com.thinkgem.jeesite.modules.zjvideo.job;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.zjvideo.dao.RunmonitorDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * 定时向监控表更新数据 表示程序正常
 */
@Controller
public class MonitorJob {

    private Logger log = Logger.getLogger(PushCdnCacheJob.class);
    //监控码
    private String code = Global.getConfig("monitor.code");

    @Autowired
    private RunmonitorDao runmonitorDao;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void run(){
        log.info("上报监控");
        runmonitorDao.updateTimeByCode(code);
    }


}
