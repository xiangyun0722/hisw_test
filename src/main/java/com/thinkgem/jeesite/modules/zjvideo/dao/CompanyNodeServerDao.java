/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.CompanyNodeServer;

/**
 * 单位节点DAO接口
 * @author j.feng
 * @version 2015-07-16
 */
@MyBatisDao
public interface CompanyNodeServerDao extends CrudDao<CompanyNodeServer> {
	/**
	 * 按照公司ID查询源站服务器
	 * @param office
	 * @return
	 */
	List<CompanyNodeServer> stationnodeServerQueryByCompanyId(CompanyNodeServer companyNodeServer);
	
	/**
	 * 按照公司ID随机查询一台节点服务器
	 * @param office
	 * @return
	 */
	List<CompanyNodeServer> nodeServerQueryByCompanyId(CompanyNodeServer companyNodeServer);	
}