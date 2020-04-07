/**
 * 
 */
package com.thinkgem.jeesite.modules.live.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.live.entity.BusinessSystem;
import com.thinkgem.jeesite.modules.live.dao.BusinessSystemDao;

/**
 * 直播业务系统Service
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
@Service
@Transactional(readOnly = true)
public class BusinessSystemService extends CrudService<BusinessSystemDao, BusinessSystem> {

	public BusinessSystem get(String id) {
		return super.get(id);
	}
	
	public List<BusinessSystem> findList(BusinessSystem businessSystem) {
		return super.findList(businessSystem);
	}
	
	public Page<BusinessSystem> findPage(Page<BusinessSystem> page, BusinessSystem businessSystem) {
		return super.findPage(page, businessSystem);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessSystem entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			entity.setPublicKey(IdGen.uuid());
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessSystem businessSystem) {
		super.delete(businessSystem);
	}

	public BusinessSystem getByPublicKey(String publicKey) {
		return dao.getByPublicKey(publicKey);
	}
	
}