/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hisw.core.utils.UrlUtil;
import com.thinkgem.jeesite.common.utils.PrintURLFilter;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.zjvideo.entity.CompanyNodeServer;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;
import com.thinkgem.jeesite.modules.zjvideo.service.CompanyNodeServerService;
import com.thinkgem.jeesite.modules.zjvideo.service.MediaService;
import com.thinkgem.jeesite.modules.zjvideo.service.VideosService;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;

/**
 * 视频管理Controller
 * @author j.feng
 * @version 2015-05-06
 */
@Controller
public class FudanVideosController extends BaseController {
	
	static Logger logger =Logger.getLogger(ProgramController.class);
	
	@Autowired
	private VideosService videosService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private CompanyNodeServerService companyNodeServerService;
	
	public static int checkContentType(String inputPath) {
        String type = FilenameUtils.getExtension(inputPath).toLowerCase();
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mpg")) {
            return 0;
        } else if (type.equals("wmv")) {
            return 0;
        } else if (type.equals("3gp")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 0;
        } else if (type.equals("asf")) {
            return 0;
        } else if (type.equals("asx")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        }
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
        else if (type.equals("wmv9")) {
            return 1;
        } else if (type.equals("rm")) {
            return 1;
        } else if (type.equals("rmvb")) {
            return 1;
        }
        return 9;
    }
	
	/**
	 * 按照视频编号或者视频名称查询可播放的视频
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "video/{name:.*}")
	public Object videoMedia(@PathVariable("name") String name,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getMedia(1, name, request, response);
	}
	
	  
	

	
	/*****
	 * 获取确切的媒资信息。
	 * @param type 1 代表 视频信息。 2 代表媒资接口。
	 * @param paraValue 参数值。 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public String getMedia(int type,String paraValue,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String url = PrintURLFilter.getFullURL(request);
		logger.info("get url:"+url);
		String jumpnumberStr =request.getParameter("jumpnumber");
		String videotype =request.getParameter("videotype");//1.flv 2.m3u8 3.yamdi 4.mp4
		String platform = request.getParameter("platform");	//1.代表pc 2.代表手机  3.代表平板。
		boolean isFileName = false;
		StringBuffer requesturl = request.getRequestURL();
		if( checkContentType(requesturl.toString()) ==0 ){//说明是视频文件。使用视频文件名查询播放地址。
			isFileName = true;
		}
		String serverName = request.getServerName();//网站域名
		//查询出公司id。
		Office officeTemp = new Office();
		officeTemp.setDomain(serverName);
		//根据公司域名查询公司
		Office office = officeService.officeQueryByDomain(officeTemp);
		String rootUrl = null;
		//获取域名中的所属公司。
		String path ="";
		if(StringUtils.isNotBlank(paraValue)){
			Media media = new Media();
			if(isFileName){
				media.setName(paraValue);
				logger.info("vidoeName:"+paraValue);
			}else{
				if(type==1){//按照视频id查询
					media.setVideosid(paraValue);//是id查询。	
				}else if(type == 2){
					media.setId(paraValue);//是id查询。	
				}
			}
			//视频格式转换
			if(StringUtils.equalsIgnoreCase(ConstantUtils.FLV_MEDIA, videotype)){
				media.setFormat(Media.FLV_FORMAT);
			}else if(StringUtils.equalsIgnoreCase(ConstantUtils.M3U8_MEDIA, videotype)){
				media.setFormat(Media.M3U8_FORMAT);
			}else if(StringUtils.equalsIgnoreCase(ConstantUtils.MP4_MEDIA, videotype)){
				media.setFormat(Media.MP4_FORMAT);
			}else if(StringUtils.equalsIgnoreCase(ConstantUtils.YAMDI_MEDIA, videotype)){
				media.setFormat(Media.YAMDI_FORMAT);
			}
			//如果是手机
			if(StringUtils.equalsIgnoreCase("2", platform) || StringUtils.equalsIgnoreCase("3", platform)){
				media.setFormat(Media.M3U8_FORMAT);
			}
			Media me = mediaService.mediaQueryByName(media);
			if(me!=null){
				CompanyNodeServer companyNodeServer = new CompanyNodeServer();
				companyNodeServer.setCompanyid(office.getId());
				if("1".equalsIgnoreCase(me.getIscache()) && org.apache.commons.lang.StringUtils.isBlank(jumpnumberStr)){//是缓存
					path = me.getCachePath();
					//随机获取一个服务器 节点。
					CompanyNodeServer nodeServer = companyNodeServerService.nodeServerQueryByCompanyId(companyNodeServer);
					rootUrl = nodeServer.getNodeServer().getUrl();						
				}else{
					path = me.getPath();	
					//这个需要查询源站的视频信息拼接返回。
					//查询这个公司配置的全部节点信息。从中间表获取。
					CompanyNodeServer nodeServer = companyNodeServerService.stationnodeServerQueryByCompanyId(companyNodeServer);
					rootUrl = nodeServer.getNodeServer().getUrl();
				}
			}else{
				logger.error("找不到 媒资信息。。。");
				response.sendRedirect("404");
				return null;
			}
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
			Map<String,String> para= new HashMap<String,String>();
			para.put("jumpnumber", "1");
			String returnurl = UrlUtil.addMapPara(url, para);
			para= new HashMap<String,String>();
			para.put("returnurl", returnurl);
			path = UrlUtil.addMapPara(path, para);
			logger.info("return Media Player:" + path);
		}
		response.sendRedirect(path);
		return null;
	}
	
	/**
	 * 按照码流编号查询可播放的视频,直接使用
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 ***/
	@RequestMapping(value = "media/{name:.*}")
	public Object nameMedia(@PathVariable("name") String name,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getMedia(2, name, request, response);
	}
	
	  
	 /* @RequestMapping({"video/{name:.*}"})
	  public Object nameMedia(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) throws Exception
	  {
	    String vidoeName = name;
	    logger.info(" vidoeName=====================" + vidoeName);
	    String fuDanVideo = new String();
	    String WEB_URL = Global.getConfig("web.video.url");
	    if (StringUtils.isNotBlank(vidoeName))
	    {
	      Media media = new Media();
	      media.setName(vidoeName);
	      logger.info("vidoeName:" + vidoeName);
	      Media me = this.mediaService.mediaQueryByName(media);
	      if (me != null) {
	        fuDanVideo = me.getPath();
	      }
	    }
	    fuDanVideo = WEB_URL + fuDanVideo;
	    String start = request.getParameter("start");
	    String end = request.getParameter("end");
	    if(StringUtils.isNoneBlank(start) && StringUtils.isNoneBlank(end)){
	    	fuDanVideo +="?start="+start+"&end="+end;
	    }
	    logger.info("Media Player=====================" + fuDanVideo);
	    response.sendRedirect(fuDanVideo);
	    return null;
	  }
	  **/
	
	public static void main(String[] args) {
		System.out.println(FilenameUtils.getExtension("http://video.hisw.cn/vod/fdedu/cache/precourse/188/hd.mp4?a=b&c=d"));
	}
	
}