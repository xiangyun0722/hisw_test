/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.ScheduleProgram;
import org.apache.ibatis.annotations.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * 排片表DAO接口
 * @author lyy
 * @version 2020-02-06
 */
@MyBatisDao
public interface ScheduleProgramDao extends CrudDao<ScheduleProgram> {

    List<ScheduleProgram> findShowList(ScheduleProgram scheduleProgram);

    /**
     * 根据状态查询
     * @param status
     * @return
     */
    ScheduleProgram getProgramByStatus(int status);

    ScheduleProgram getDetail(String id);

    ScheduleProgram  selectNextProgram(@Param("time") Time time,@Param("date") Date date);
}