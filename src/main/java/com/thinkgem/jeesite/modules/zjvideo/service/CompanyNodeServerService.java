/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.dao.CompanyNodeServerDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.CompanyNodeServer;

/**
 * 单位节点Service
 * @author j.feng
 * @version 2015-07-16
 */
@Service
@Transactional(readOnly = true)
public class CompanyNodeServerService extends CrudService<CompanyNodeServerDao, CompanyNodeServer> {

	@Autowired
	private CompanyNodeServerDao companyNodeServerDao;
	
	public CompanyNodeServer get(String id) {
		return super.get(id);
	}
	
	public List<CompanyNodeServer> findList(CompanyNodeServer companyNodeServer) {
		return super.findList(companyNodeServer);
	}
	
	public Page<CompanyNodeServer> findPage(Page<CompanyNodeServer> page, CompanyNodeServer companyNodeServer) {
		return super.findPage(page, companyNodeServer);
	}
	
	@Transactional(readOnly = false)
	public void save(CompanyNodeServer companyNodeServer) {
		super.save(companyNodeServer);
	}
	
	@Transactional(readOnly = false)
	public void delete(CompanyNodeServer companyNodeServer) {
		super.delete(companyNodeServer);
	}
	
	/**
	 * 按照公司ID查询源站服务器
	 * @param companyNodeServer
	 * @return
	 */
	public CompanyNodeServer stationnodeServerQueryByCompanyId(CompanyNodeServer companyNodeServer){
		List<CompanyNodeServer> companyNodeServers = companyNodeServerDao.stationnodeServerQueryByCompanyId(companyNodeServer);
		if(companyNodeServers != null &&companyNodeServers.size() > 0){
			return companyNodeServers.get(0);
		}
		return null;
	}
	
	/**
	 * 按照公司ID随机查询一台节点服务器
	 * @param companyNodeServer
	 * @return
	 */
	public CompanyNodeServer nodeServerQueryByCompanyId(CompanyNodeServer companyNodeServer){
		List<CompanyNodeServer> companyNodeServers = companyNodeServerDao.nodeServerQueryByCompanyId(companyNodeServer);
		if(companyNodeServers != null && companyNodeServers.size() > 0){
			return companyNodeServers.get(0);
		}
		return null;
	}
}