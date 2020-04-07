/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	/**
	 * 按照域名查询公司
	 * @param office
	 * @return
	 */
	List<Office> officeQueryByDomain(Office office);
	
	/**
	 * 按照登录用户查询所属公司
	 * j.feng 2015年8月4日 下午3:15:48
	 * @param user
	 * @return
	 */
	List<Office> findLogoByUser(User user);	
}
