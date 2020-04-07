/**
 * 
 */
package com.thinkgem.jeesite.modules.live.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.live.entity.BusinessSystem;

/**
 * 直播业务系统DAO接口
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
@MyBatisDao
public interface BusinessSystemDao extends CrudDao<BusinessSystem> {

	BusinessSystem getByPublicKey(String publicKey);
	
}