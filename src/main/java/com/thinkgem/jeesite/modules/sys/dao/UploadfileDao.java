/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Uploadfile;

/**
 * 系统上传文件DAO接口
 * @author twk
 * @version 2015-07-27
 */
@MyBatisDao
public interface UploadfileDao extends CrudDao<Uploadfile> {
	
}