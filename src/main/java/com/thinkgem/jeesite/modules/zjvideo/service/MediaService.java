/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hisw.core.utils.UrlUtil;
import com.thinkgem.jeesite.common.utils.PrintURLFilter;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.zjvideo.entity.CompanyNodeServer;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.zjvideo.dao.MediaDao;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;

/**
 * 媒资信息Service
 * @author j.feng
 * @version 2015-06-03
 */
@Service
@Transactional(readOnly = true)
public class MediaService extends CrudService<MediaDao, Media> {

	@Autowired
	private MediaDao mediaDao;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private CompanyNodeServerService companyNodeServerService;
	
	public Media get(String id) {
		return super.get(id);
	}
	
	public List<Media> findList(Media media) {
		return super.findList(media);
	}
	
	public Page<Media> findPage(Page<Media> page, Media media) {
		media.getSqlMap().put("dsf", dataScopeFilter(media.getCurrentUser(), "o", "u"));	
		return super.findPage(page, media);
	}
	
	@Transactional(readOnly = false)
	public void save(Media media) {
		super.save(media);
	}
	
	@Transactional(readOnly = false)
	public void delete(Media media) {
		super.delete(media);
	}
	
	/**
	 * 数据库删除媒资视频信息
	 * @param media
	 */
	@Transactional(readOnly = false)
	public void deleteMedia(Media media){
		mediaDao.deleteMedia(media);
	}
	
	/**
	 * 按照文件名查询可播放视频
	 * @param program
	 * @return
	 */
	public Media mediaQueryByName(Media media){
		List<Media> mediaes = mediaDao.mediaQueryByName(media);
		if(mediaes != null && mediaes.size() > 0){
			return mediaes.get(0);
		}
		return null;
	}

	/**
	 * 源站地址
	 * @param media
	 * @return
	 */
	public String getPath(Media media){
		try {
			Office office = officeService.get(media.getCompanyid());
			String path ="";
			String rootUrl = null;
			if(media!=null){
				CompanyNodeServer companyNodeServer = new CompanyNodeServer();
				companyNodeServer.setCompanyid(office.getId());
				if("1".equalsIgnoreCase(media.getIscache())){//是缓存
					path = media.getCachePath();
					//随机获取一个服务器 节点。
					CompanyNodeServer nodeServer = companyNodeServerService.nodeServerQueryByCompanyId(companyNodeServer);
					if(nodeServer == null){
						return "";
					}
					if(nodeServer.getNodeServer() == null){
						return "";
					}
					rootUrl = nodeServer.getNodeServer().getUrl();
				}else{
					path = media.getPath();
					//这个需要查询源站的视频信息拼接返回。
					//查询这个公司配置的全部节点信息。从中间表获取。
					CompanyNodeServer nodeServer = companyNodeServerService.stationnodeServerQueryByCompanyId(companyNodeServer);
					if(nodeServer == null){
						return "";
					}
					if(nodeServer.getNodeServer() == null){
						return "";
					}
					rootUrl = nodeServer.getNodeServer().getUrl();
				}
			}else{
				logger.error("找不到 媒资信息。。。");
				return null;
			}
			if(StringUtils.isBlank(rootUrl)){
				return "";
			}
			if(!rootUrl.startsWith("http:")){
				rootUrl ="http://"+rootUrl;
			}
			if(rootUrl.contains("{")){   //自定义的处理方法
				String tempPath=path;
				int startIndex=tempPath.indexOf("/")+1;
				int subIndex=tempPath.indexOf("/nocache");
				String company=tempPath.substring(startIndex,subIndex);  //company变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("project");
				subIndex=tempPath.indexOf("/video");
				String project=tempPath.substring(startIndex,subIndex);   //project变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				subIndex=tempPath.indexOf("/hd");
				String video=tempPath.substring(startIndex,subIndex);  //video变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				String file=tempPath.substring(startIndex); //file变量
				if(rootUrl.indexOf("{company}")>-1){
					rootUrl=rootUrl.replace("{company}", company);
				}
				if(rootUrl.indexOf("{project}")>-1){
					rootUrl=rootUrl.replace("{project}", project);
				}
				if(rootUrl.indexOf("{video}")>-1){
					rootUrl=rootUrl.replace("{video}", video);
				}
				if(rootUrl.indexOf("{file}")>-1){
					rootUrl=rootUrl.replace("{file}", file);
				}
				path=rootUrl;
			}else{
				path = UrlUtil.addUrl(rootUrl, path);
			}
			return path;
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * cdn地址
	 * @param media
	 * @return
	 */
	public String getCdnPath(Media media){
		try {
			Office office = officeService.get(media.getCompanyid());
			String path ="";
			String rootUrl = null;
			if(media!=null){
				CompanyNodeServer companyNodeServer = new CompanyNodeServer();
				companyNodeServer.setCompanyid(office.getId());
				if("1".equalsIgnoreCase(media.getIscache())){//是缓存
					path = media.getCachePath();
					//随机获取一个服务器 节点。
					CompanyNodeServer nodeServer = companyNodeServerService.nodeServerQueryByCompanyId(companyNodeServer);
					if(nodeServer == null){
						return "";
					}
					if(nodeServer.getNodeServer() == null){
						return "";
					}
					rootUrl = nodeServer.getNodeServer().getCdnurl();
				}else{
					path = media.getPath();
					//这个需要查询源站的视频信息拼接返回。
					//查询这个公司配置的全部节点信息。从中间表获取。
					CompanyNodeServer nodeServer = companyNodeServerService.stationnodeServerQueryByCompanyId(companyNodeServer);
					if(nodeServer == null){
						return "";
					}
					if(nodeServer.getNodeServer() == null){
						return "";
					}
					rootUrl = nodeServer.getNodeServer().getCdnurl();
				}
			}else{
				logger.error("找不到 媒资信息。。。");
				return null;
			}
			if(StringUtils.isBlank(rootUrl)){
				return "";
			}
			if(!rootUrl.startsWith("http:")){
				rootUrl ="http://"+rootUrl;
			}
			if(rootUrl.contains("{")){   //自定义的处理方法
				String tempPath=path;
				int startIndex=tempPath.indexOf("/")+1;
				int subIndex=tempPath.indexOf("/nocache");
				String company=tempPath.substring(startIndex,subIndex);  //company变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("project");
				subIndex=tempPath.indexOf("/video");
				String project=tempPath.substring(startIndex,subIndex);   //project变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				subIndex=tempPath.indexOf("/hd");
				String video=tempPath.substring(startIndex,subIndex);  //video变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				String file=tempPath.substring(startIndex); //file变量
				rootUrl=rootUrl.replace("{company}", company);
				rootUrl=rootUrl.replace("{project}", project);
				rootUrl=rootUrl.replace("{video}", video);
				rootUrl=rootUrl.replace("{file}", file);
				path=rootUrl;
			}else{
				path = UrlUtil.addUrl(rootUrl, path);
			}
			return path;
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 源站地址
	 * @param companyId
	 * @param path
	 * @return
	 */
	public String getPath(String companyId,String path){
		try {
			String rootUrl = null;
			CompanyNodeServer companyNodeServer = new CompanyNodeServer();
			companyNodeServer.setCompanyid(companyId);
			//这个需要查询源站的视频信息拼接返回。
			//查询这个公司配置的全部节点信息。从中间表获取。
			CompanyNodeServer nodeServer = companyNodeServerService.stationnodeServerQueryByCompanyId(companyNodeServer);
			if(nodeServer == null){
				return "";
			}
			if(nodeServer.getNodeServer() == null){
				return "";
			}
			rootUrl = nodeServer.getNodeServer().getUrl();
			if(StringUtils.isBlank(rootUrl)){
				return "";
			}
			if(!rootUrl.startsWith("http:")){
				rootUrl ="http://"+rootUrl;
			}
			if(rootUrl.contains("{")){   //自定义的处理方法
				String tempPath=path;
				int startIndex=tempPath.indexOf("/")+1;
				int subIndex=tempPath.indexOf("/nocache");
				String company=tempPath.substring(startIndex,subIndex);  //company变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("project");
				subIndex=tempPath.indexOf("/video");
				String project=tempPath.substring(startIndex,subIndex);   //project变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				subIndex=tempPath.indexOf("/hd");
				String video=tempPath.substring(startIndex,subIndex);  //video变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				String file=tempPath.substring(startIndex); //file变量
				rootUrl=rootUrl.replace("{company}", company);
				rootUrl=rootUrl.replace("{project}", project);
				rootUrl=rootUrl.replace("{video}", video);
				rootUrl=rootUrl.replace("{file}", file);
				path=rootUrl;
			}else{
				path = UrlUtil.addUrl(rootUrl, path);
			}
			return path;
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * cdn地址
	 * @param companyId
	 * @param path
	 * @return
	 */
	public String getCdnPath(String companyId,String path){
		try {
			String rootUrl = null;
			CompanyNodeServer companyNodeServer = new CompanyNodeServer();
			companyNodeServer.setCompanyid(companyId);
			//这个需要查询源站的视频信息拼接返回。
			//查询这个公司配置的全部节点信息。从中间表获取。
			CompanyNodeServer nodeServer = companyNodeServerService.stationnodeServerQueryByCompanyId(companyNodeServer);
			if(nodeServer == null){
				return "";
			}
			if(nodeServer.getNodeServer() == null){
				return "";
			}
			rootUrl = nodeServer.getNodeServer().getCdnurl();
			if(StringUtils.isBlank(rootUrl)){
				return "";
			}
			if(!rootUrl.startsWith("http:")){
				rootUrl ="http://"+rootUrl;
			}
			if(rootUrl.contains("{")){   //自定义的处理方法
				String tempPath=path;
				int startIndex=tempPath.indexOf("/")+1;
				int subIndex=tempPath.indexOf("/nocache");
				String company=tempPath.substring(startIndex,subIndex);  //company变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("project");
				subIndex=tempPath.indexOf("/video");
				String project=tempPath.substring(startIndex,subIndex);   //project变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				subIndex=tempPath.indexOf("/hd");
				String video=tempPath.substring(startIndex,subIndex);  //video变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				String file=tempPath.substring(startIndex); //file变量
				rootUrl=rootUrl.replace("{company}", company);
				rootUrl=rootUrl.replace("{project}", project);
				rootUrl=rootUrl.replace("{video}", video);
				rootUrl=rootUrl.replace("{file}", file);
				path=rootUrl;
			}else{
				path = UrlUtil.addUrl(rootUrl, path);
			}
			return path;
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 图片源站地址
	 * @param companyId
	 * @param picurl
	 * @return
	 */
	public String getPicurl(String companyId,String picurl){
		try {
			String path =picurl;
			String rootUrl = null;
			//这个需要查询源站的视频信息拼接返回。
			//查询这个公司配置的全部节点信息。从中间表获取。
			CompanyNodeServer companyNodeServer = new CompanyNodeServer();
			companyNodeServer.setCompanyid(companyId);
			CompanyNodeServer nodeServer = companyNodeServerService.stationnodeServerQueryByCompanyId(companyNodeServer);
			if(nodeServer == null){
				return "";
			}
			if(nodeServer.getNodeServer() == null){
				return "";
			}
			rootUrl = nodeServer.getNodeServer().getUrl();
			if(StringUtils.isBlank(rootUrl)){
				return "";
			}
			if(!rootUrl.startsWith("http:")){
				rootUrl ="http://"+rootUrl;
			}
			if(rootUrl.contains("{")){   //自定义的处理方法
				String tempPath=path;
				int startIndex=tempPath.indexOf("/")+1;
				int subIndex=tempPath.indexOf("/nocache");
				String company=tempPath.substring(startIndex,subIndex);  //company变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("project");
				subIndex=tempPath.indexOf("/video");
				String project=tempPath.substring(startIndex,subIndex);   //project变量
				tempPath=tempPath.substring(subIndex);
				startIndex=tempPath.indexOf("/")+1;
				String video=tempPath.substring(startIndex);  //video变量
				String file=""; //file变量
				rootUrl=rootUrl.replace("{company}", company);
				rootUrl=rootUrl.replace("{project}", project);
				rootUrl=rootUrl.replace("{video}", video);
				rootUrl=rootUrl.replace("{file}", file);
				path=rootUrl.substring(0,rootUrl.length()-1);//去掉最后"/"
			}else{
				path = UrlUtil.addUrl(rootUrl, path);
			}
			return path;
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

}