/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.zjvideo.dao.VideotranscodingtaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.entity.ScheduleProgram;
import com.thinkgem.jeesite.modules.zjvideo.dao.ScheduleProgramDao;

/**
 * 排片表Service
 * @author lyy
 * @version 2020-02-06
 */
@Service
@Transactional(readOnly = false)
public class ScheduleProgramService extends CrudService<ScheduleProgramDao, ScheduleProgram> {

	@Autowired
	private ScheduleProgramDao scheduleProgramDao;

	@Autowired
	private VideotranscodingtaskDao videotranscodingtaskDao;

	public ScheduleProgram get(String id) {
		return super.get(id);
	}
	
	public List<ScheduleProgram> findList(ScheduleProgram scheduleProgram) {
		return super.findList(scheduleProgram);
	}
	
	public Page<ScheduleProgram> findPage(Page<ScheduleProgram> page, ScheduleProgram scheduleProgram) {
		return super.findPage(page, scheduleProgram);
	}
	
	public void save(ScheduleProgram scheduleProgram) {
		dao.insert(scheduleProgram);
	}
	
	public void delete(ScheduleProgram scheduleProgram) {
		super.delete(scheduleProgram);
	}

	public List<ScheduleProgram> findShowList(ScheduleProgram scheduleProgram) {
		//图片地址
		String imageUrl = Global.getConfig("web.images.url");
		//视频播放
		String videoUrl = Global.getConfig("web.video.url");
		List<ScheduleProgram> list = scheduleProgramDao.findShowList(scheduleProgram);
		for (ScheduleProgram program:
			 list) {
			if(program.getPicurl() != null){
				program.setCoverHttpUrl(imageUrl + program.getPicurl());
			}
			if(program.getPath() != null){
				program.setPlayHttpUrl(videoUrl + program.getPath());
			}
		}

		return list;
	}

	public void update(ScheduleProgram scheduleProgram) {
		dao.update(scheduleProgram);
	}

	public ScheduleProgram getDetail(String id) {
		ScheduleProgram scheduleProgram = dao.getDetail(id);

		return scheduleProgram;
	}

	/**
	 * 获取正在直播及下场直播信息
	 * @return
	 */
	public Map<String,Object> getLivingProgram() {
		Map<String,Object> map = new HashMap<String, Object>();
		ScheduleProgram scheduleProgram = scheduleProgramDao.getProgramByStatus(3);

		//图片地址
		String imageUrl = Global.getConfig("web.images.url");
		//视频播放
		String videoUrl = Global.getConfig("web.video.url");
		if(scheduleProgram != null && scheduleProgram.getPicurl() != null){
			scheduleProgram.setCoverHttpUrl(imageUrl + scheduleProgram.getPicurl());
		}
		if(scheduleProgram != null && scheduleProgram.getPath() != null){
			scheduleProgram.setPlayHttpUrl(videoUrl + scheduleProgram.getPath());
		}

		try {
			Time time = new Time(System.currentTimeMillis());
			if(scheduleProgram != null && scheduleProgram.getStartTime() != null){
				time = Time.valueOf(scheduleProgram.getStartTime());
			}

			Date date = new Date();
			if(scheduleProgram != null && scheduleProgram.getDate() != null){
				date = scheduleProgram.getDate();
			}

			ScheduleProgram nextProgram = scheduleProgramDao.selectNextProgram(time,date);
			map.put("nextProgram",nextProgram);

		}catch (Exception e){
			logger.error("获取下场直播信息错误",e);
		}
		map.put("livingProgram",scheduleProgram);
		return map;
	}
}