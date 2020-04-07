/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisw.core.utils.UrlUtil;
import com.thinkgem.jeesite.common.api.ExceptionUtil;
import com.thinkgem.jeesite.common.api.RequestUtil;
import com.thinkgem.jeesite.common.api.RestResponse;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videos;
import com.thinkgem.jeesite.modules.zjvideo.service.MediaService;
import com.thinkgem.jeesite.modules.zjvideo.service.ProjectService;
import com.thinkgem.jeesite.modules.zjvideo.service.TemplatesService;
import com.thinkgem.jeesite.modules.zjvideo.service.VideosService;
import com.thinkgem.jeesite.modules.zjvideo.service.VideotranscodingtaskService;
import com.thinkgem.jeesite.modules.zjvideo.web.VideosController;

/**
 * 同步视频接口ApiController
 * @author lionzscat
 * @version 2018-01-24
 */
@Controller
@RequestMapping(value = "api/video/interface")
public class VideosApiController extends BaseController {

static Logger logger =Logger.getLogger(VideosController.class);
	
	@Autowired
	private VideosService videosService;
	
	@Autowired
	private TemplatesService templateService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private VideotranscodingtaskService videotemplateService;
	
	@Autowired
	private OfficeService officeService;	
	
	@RequestMapping(value ="/get")
    @ResponseBody
	public RestResponse get(HttpServletRequest request,HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
        try {
        	//视频编号Id
        	String videoId = request.getParameter("videoId");
        	Assert.notNull(videoId, "videoId不允许为空");
    		Media medi = new Media();
    		medi.setVideosid(videoId);
    		Page<Media> page = videosService.apiQueryMediaVideos(new Page<Media>(request, response), medi); 
    		page.isWebservicePageFlag = true;
    		String WEB_URL =Global.getConfig("web.images.url");
    	    List<Media> list = page.getList();
    	    if(null != list) {
    		    for(Media media : list) {
    			    String picurl = media.getPicurl();
    			    if(null != picurl && !picurl.startsWith("http")) {
    			    	media.setPicurl(UrlUtil.addUrl(WEB_URL , media.getPicurl()));
    			    }
    			    if(StringUtils.isNotEmpty(media.getCompanyid())){
    			    	//Office office = officeService.get(media.getCompanyid());
    			    	//String path = "http://" + office.getDomain() + "/media/" + media.getId();
						String path = mediaService.getPath(media);
    			    	media.setPath(path);
    			    }
    			}
    	    }	
    	    return new RestResponse(page); 
		} catch (Exception ex) {
	    	restResponse=ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}
	
	//@ApiAuth //必须登录
	@RequestMapping(value = {"list"})
	@ResponseBody
	public RestResponse list(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResponse restResponse = new RestResponse();
        try {
        	Videos videos=RequestUtil.form(Videos.class, request);
			Page<Videos> page = videosService.queryVideosList(new Page<Videos>(request, response), videos); 
			page.isWebservicePageFlag = true;
		    String WEB_URL =Global.getConfig("web.images.url");
		    List<Videos> list = page.getList();
		    if(null != list) {
			    for(Videos video : list) {
				    String picurl = video.getPicurl();
				    if(null != picurl && !picurl.startsWith("http")) {
				    	video.setPicurl(UrlUtil.addUrl(WEB_URL , video.getPicurl()));
				    }
				    if(StringUtils.isNotEmpty(video.getCompanyid())){
				    	//Office office = officeService.get(video.getCompanyid());
				    	//String path = "http://" + office.getDomain() + "/video/" + video.getId();
						Media media = new Media();
						media.setVideosid(video.getId());
						Media me = mediaService.mediaQueryByName(media);
						String path = mediaService.getPath(me);
				    	video.setPath(path);		    
				    }
				}
		    }		
			
  	      return new RestResponse(page);
        } catch (Exception ex) {
        	restResponse=ExceptionUtil.dealException(ex);
        }
        return restResponse;
	}
	 
	
}