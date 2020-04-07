/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.hisw.core.utils.UrlUtil;
import com.thinkgem.jeesite.common.api.RestErrorCode;
import com.thinkgem.jeesite.common.api.RestException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.zjvideo.dao.PicDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Pic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


/**
 * 图片Service
 * @author lyy
 * @version 2020-02-06
 */
@Service
@Transactional(readOnly = false)
public class PicService extends CrudService<PicDao, Pic> {

	public Pic get(String id) {
		return super.get(id);
	}
	
	public List<Pic> findList(Pic pic) {
		return super.findList(pic);
	}
	
	public Page<Pic> findPage(Page<Pic> page, Pic pic) {
		return super.findPage(page, pic);
	}
	
	@Transactional(readOnly = false)
	public void save(Pic pic) {
		dao.insert(pic);
	}
	
	@Transactional(readOnly = false)
	public void delete(Pic pic) {
		super.delete(pic);
	}

	public Pic upload(MultipartFile file) throws Exception {
		//判断是否图片
		if(!FileUtils.isImage(file.getOriginalFilename())){
			throw new RestException(RestErrorCode.SYSTEM_ERROR_CODE,"请上传图片");
		}

		Pic pic = new Pic();
		//图片上传路径
		String dir = DictUtils.getDictValue("VIRTUAL_DIRECTORIES", "sys_config", "");
		if(!StringUtils.endsWith(dir, "/")){
			dir = dir + "/";
		}
		//不存在目录则创建
		File dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		//创建随机名
		String name = UUID.randomUUID().toString()+FileUtils.getExt(file.getOriginalFilename());
		//上传
		String source = dir + name;
		file.transferTo(new File(source));
		//保存
		pic.setSource(source);
		pic.setUrl(name);
		dao.insert(pic);
		//访问地址
		String web_url = DictUtils.getDictValue("PICTURE_HTTP_PREFIX_URL", "sys_config", "");
		pic.setHttpUrl(UrlUtil.addUrl(web_url,name));

		return pic;
	}

	public Pic getDetail(String id) {
		Pic pic = get(id);
		//访问地址
		String web_url = DictUtils.getDictValue("PICTURE_HTTP_PREFIX_URL", "sys_config", "");
		if(pic != null){
			pic.setHttpUrl(UrlUtil.addUrl(web_url,pic.getUrl()));
		}
		return pic;
	}
}