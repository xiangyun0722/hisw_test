/**
 * 
 */
package com.thinkgem.jeesite.modules.zjvideo.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.hisw.core.utils.UrlUtil;
import com.hisw.qrcode.TwoDimensionCode;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Media;
import com.thinkgem.jeesite.modules.zjvideo.entity.Project;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videos;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videotranscodingtask;
import com.thinkgem.jeesite.modules.zjvideo.service.MediaService;
import com.thinkgem.jeesite.modules.zjvideo.service.ProjectService;
import com.thinkgem.jeesite.modules.zjvideo.service.TemplatesService;
import com.thinkgem.jeesite.modules.zjvideo.service.VideosService;
import com.thinkgem.jeesite.modules.zjvideo.service.VideotranscodingtaskService;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;
import com.thinkgem.jeesite.modules.zjvideo.util.DownloadFileNameEncoder;

/**
 * 视频管理Controller
 * @author j.feng
 * @version 2015-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/zjvideo/videos")
public class VideosController extends BaseController {
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
	
	private String videoid;	// 定义一个全局的视频ID
	
	@ModelAttribute
	public Videos get(@RequestParam(required=false) String id) {
		Videos entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = videosService.get(id);
		}
		if (entity == null){
			entity = new Videos();
		}
		return entity;
	}
	
	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping(value = {"list", ""})
	public String list(Videos videos, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Videos> page = videosService.findPage(new Page<Videos>(request, response), videos); 
	    
	    String WEB_URL =Global.getConfig("web.images.url");
	    List<Videos> list = page.getList();
	    if(null != list) {
		    for(Videos video : list) {
			    String picurl = video.getPicurl();
			    String path = video.getPath();
			    if(null != picurl && !picurl.startsWith("http")) {
			    	video.setPicurl(UrlUtil.addUrl(WEB_URL, video.getPicurl()));
			    }
			}
	    }
		model.addAttribute("page", page);
		return "modules/zjvideo/videosList";
	}
	
	/**
	 * 转码任务列表信息
	 * @param videotemplate
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping("transcodetask/list")
	public String transcodetaskList(Videotranscodingtask videotranscodingtask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Videotranscodingtask> page = videosService.queryVideotranscodingtask(new Page<Videotranscodingtask>(request, response), videotranscodingtask); 
		model.addAttribute("page", page);
		return "modules/zjvideo/transcodeList";
	}	
	/**
	 * 未被使用的视频列表
	 * 用户定制弹出窗口
	 * @param videos
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping(value = "/unUsedList")
	public String unUsedlist(Videos videos, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Videos> page = videosService.findProgramVolumeList(new Page<Videos>(request, response), videos); 
	    String WEB_URL =Global.getConfig("web.images.url");
	    List<Videos> list = page.getList();
	    if(null != list) {
		    for(Videos video : list) {
			    String picurl = video.getPicurl();
			    String path = video.getPath();
			    if(null != picurl && !picurl.startsWith("http")) {
			    	video.setPicurl(UrlUtil.addUrl(WEB_URL , video.getPicurl()));
			    }
			}
	    }		
		model.addAttribute("page", page);
		model.addAttribute("value", request.getParameter("value"));
		return "modules/zjvideo/selectVideosForm";
	}
	

	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping(value = "form")
	public String form(Videos videos, Model model) {
		/*加载视频转码的信息*/
		/*VideotranscodingMain videotranscodingMain = new VideotranscodingMain();
		Config config = new Config();
		config.initConfig();
		ListeningDirectory  listeningDirectory =new ListeningDirectory();
		//listeningDirectory.doListeningDir();
		logger.info("ftpFileRoot:"+Config.getFtpFileRoot());
		BusinessThread businessThread=new BusinessThread(Config.getConvertVideoThreadSize());	*/
		
		/*查查所有关联的模板信息*/
	/*	List<Integer> templateIds = Lists.newArrayList();	//模板ids
		if(videos.getId() != null && videos.getId().length() >= 1){
			List<Videos> vides= videosService.queryOwnTemplatesList(videos);
				for (Videos v : vides) {
					templateIds.add(Integer.valueOf(v.getTemplates().getId()));
				}
		}
		videos.setTemplateId(templateIds);
		查询所有的带选择模板类型
		List<Templates> templates = templateService.getAllTemplates();
		model.addAttribute("templates", templates);*/
		//***广州参考，项目需要默认选中**/
		User user = UserUtils.getUser();
		List<Project> projectListItem = new ArrayList<Project>();
		if(StringUtils.isNoneBlank(user.getCompany().getId())){
			Project project  = new Project();
			project.setCompanyid(user.getCompany().getId());
			List<Project> projectList = projectService.findList(project);
			if((null != projectList)&&(projectList.size()>0)){
				projectListItem = projectList;
			}
		}
		//管理员登陆，选择所有的项目列表信息
		if(0 == projectListItem.size()){
			projectListItem = projectService.findList(new Project());
		}
		model.addAttribute("videos", videos);
		model.addAttribute("projectList", projectListItem);
		return "modules/zjvideo/videosForm";
	}
	
	/**
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "save")
	public String save(Videos videos, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (!beanValidator(model, videos)){
			return form(videos, model);
		}
		删除原有视频模板信息
			if(videos.getId() != null && videos.getId().length() >= 1){
			videosService.deleteVideosTemplates(videos);
		}
		logger.info("source:" + videos.getSource());
		//保存开始转码状态转码中...
		videos.setAddtime(new Date());
		videos.setName(request.getParameter("source_oldname"));
		videos.setgeneratetaskflag(ConstantUtils.GENERATE_TASK_FLAG_NO);//上传视频初始化状态0代表未生成转码任务
		videosService.save(videos);
		
		//上传视频目录
		String realPath = DictUtils.getDictValue("VIRTUAL_DIRECTORIES", "sys_config", "");
		//转码资源库目录
		if(StringUtils.isNoneBlank(videos.getSource())){
			Project project = projectService.get(videos.getProjectid());
			videos.setCompanyid(project.getCompanyid());
			//平台用户Id保存
			User user = project.getCreateBy();
			videos.setCreateBy(user);
			String repositorypath = project.getRepositorypath();
			//拷贝上传的单个文件到转码资源库中
			String source = videos.getSource();
			String logicFileName = source.substring(source.indexOf("/") + 1);
			FileUtils.copyFileCover(realPath+logicFileName, repositorypath+logicFileName, true);
			//获取视频绝对路径
			videos.setSource(repositorypath+logicFileName);
			videosService.save(videos);
			//删除上传视频单个文件
			FileUtils.deleteFile(realPath+logicFileName);
		}
		视频模板信息添加
		List<Integer> templateids = videos.getTemplateId();
		List<Templates> templatesList = Lists.newArrayList();
		for(Integer tem : templateids){
			Templates template = templateService.get(String.valueOf(tem));
			templatesList.add(template);
		}
		videos.setTemplatesList(templatesList);
		videosService.insertVideosTemplates(videos);
		视频转码来添加后台记录，不把此视频保存在表中
		videosService.deleteVideo(videos);
		addMessage(redirectAttributes, "上传视频成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/quequ/list?repage";
	}
	**/
	
	
	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping(value = "transcode/form")
	public String transcodeForm(Videos videos, Model model) {
		/*加载视频转码的信息*/
		/*VideotranscodingMain videotranscodingMain = new VideotranscodingMain();
		Config config = new Config();
		config.initConfig();
		ListeningDirectory  listeningDirectory =new ListeningDirectory();
		//listeningDirectory.doListeningDir();
		logger.info("ftpFileRoot:"+Config.getFtpFileRoot());
		BusinessThread businessThread=new BusinessThread(Config.getConvertVideoThreadSize());	*/
		
		/*查查所有关联的模板信息*/
	/*	List<Integer> templateIds = Lists.newArrayList();	//模板ids
		if(videos.getId() != null && videos.getId().length() >= 1){
			List<Videos> vides= videosService.queryOwnTemplatesList(videos);
				for (Videos v : vides) {
					templateIds.add(Integer.valueOf(v.getTemplates().getId()));
				}
		}
		videos.setTemplateId(templateIds);*/
		/*查询所有的带选择模板类型*/
		List<Templates> templates = templateService.getAllTemplates();
		model.addAttribute("templates", templates);
		model.addAttribute("videos", videos);
		return "modules/zjvideo/transcodeForm";
	}

	//老版本的保存转码
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "transcode/save")
	public String transcodeSave(Videos videos, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, videos)){
			return form(videos, model);
		}
		/**	删除原有视频模板信息
		if(videos.getId() != null && videos.getId().length() >= 1){
			videosService.deleteVideosTemplates(videos);
		}
		logger.info("source======================*********" + videos.getSource());
		//保存开始转码状态转码中...
		videos.setAddtime(new Date());
		videos.setgeneratetaskflag(ConstantUtils.GENERATE_TASK_FLAG_NO);//上传视频初始化状态0代表未生成转码任务
		videosService.save(videos);
		**/
		/*视频模板信息添加*/
		List<Integer> templateids = videos.getTemplateId();
		List<Templates> templatesList = Lists.newArrayList();
		for(Integer tem : templateids){
			Templates template = templateService.get(String.valueOf(tem));
			template.setConvertstatus(ConstantUtils.CONVERT_STATUS_WAIT);
			templatesList.add(template);
		}
		videos.setTemplatesList(templatesList);
		videosService.insertVideoTranscodingTask(videos);
		/*更新未生成转码状态为生成该转码任务*/
		videos.setgeneratetaskflag(ConstantUtils.GENERATE_TASK_FLAG_YES);
		videosService.save(videos);
		addMessage(redirectAttributes, "该视频转码任务开启");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/quequ/list?repage";
	}
	
	//新版本的转码。使用转码命令。
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "transcode/start")
	public String transcodeStart(Videos videos, Model model, RedirectAttributes redirectAttributes , HttpServletRequest request,HttpServletResponse response) {
		
		logger.info("获取转码开始信息：==========================" + videos.toString());
		String msg ="";
		try {
			if (!beanValidator(model, videos)){
				return form(videos, model);
			}
			//获取视频所属项目下面的全部模板。然后生成转码任务。
			//获取当前视频信息
			if(videos.getProject() == null){
				msg = "所属项目不能为空！";
				logger.error(msg);
			}else{
				//获取用户选择项目下的转码模板列表
				List<Integer> templateIdList = videos.getTemplateId();
				List<Templates>  templatesListTemp = new ArrayList<Templates>();
				for (Integer templateId : templateIdList) {
					Templates templates = templateService.get(String.valueOf(templateId));
					templates.setConvertstatus(Templates.convertstatus_wait);
					templatesListTemp.add(templates);
				}
				//原有逻辑，获取项目下所有转码模板列表
				if(0 == templateIdList.size()){
					templatesListTemp = templateService.getOwnTemplates(videos.getProject());
					for (Templates templates : templatesListTemp) {
						templates.setConvertstatus(Templates.convertstatus_wait);
					}
					msg = "操作成功,默认选择项目全局设置的转码模板";	
				}
				videos.setTemplatesList(templatesListTemp);
				videosService.insertVideoTranscodingTask(videos);
				/*更新未生成转码状态为生成该转码任务*/
				videos.setgeneratetaskflag(ConstantUtils.GENERATE_TASK_FLAG_YES);
				videosService.save(videos);
				msg = "操作成功";	
			}
		} catch (Exception e) {
			logger.error(e,e);
			msg = e.getMessage();
		}
		addMessage(redirectAttributes, msg);
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/quequ/list?repage";
	}
	
	
	
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "retranscode")
	public String freshTanscoding(Videos videos, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, videos)){
			return form(videos, model);
		}
		//事物删除视频转码任务信息、码流信息
		//videosService.batchDeleteTranscodeInfo(videos);
		//更新生成转码状态为未生成该转码任务
		videos.setgeneratetaskflag(ConstantUtils.GENERATE_TASK_FLAG_NO);
		videosService.save(videos);
		String message = "该视频,进入未处理视频码队列中...！";
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/media/list?repage";
	}	
	
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "delete")
	public String delete(Videos videos, RedirectAttributes redirectAttributes) {
		videosService.delete(videos);
		addMessage(redirectAttributes, "删除视频管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/?repage";
	}

	
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "delete/video")
	public String deleteVideo(Videos videos, RedirectAttributes redirectAttributes) {
		videosService.deleteVideo(videos);
		addMessage(redirectAttributes, "删除视频成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/media/list?repage";
	}
	
	/***
	 * 获取二维码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:edit")
	@ResponseBody
	@RequestMapping("/getQrcode")
	public String get(HttpServletRequest request,HttpServletResponse response) {
		ServletOutputStream outstream;
		try {
			String id = request.getParameter("id");
			Videos  videos  = videosService.get(id);
			String pngName =videos.getName();
			String WEB_URL =Global.getConfig("web.images.url");
			String path = videos.getPath();
		    if(null != path && !path.startsWith("http")) {
		    	path = UrlUtil.addUrl(WEB_URL, path);
		    }
			//变成二维码的内容
			String userAgent = request.getHeader("User-Agent");
			logger.info("userAgent:" + userAgent);
			DownloadFileNameEncoder encoder = new DownloadFileNameEncoder();
			pngName = encoder.encode(pngName, userAgent);
			response.setHeader("Content-Disposition","attachment; filename="+pngName+".png");
			TwoDimensionCode handler = new TwoDimensionCode();
			outstream =response.getOutputStream();
			handler.encoderQRCode(path,outstream,"");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e,e);
		}
		return null;
	}	
	
	/**
	 * 用户上传待解码的原始视频文件
	 * @param videos
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "upload")
	public String uploadVideo(Videos videos, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, videos)){
			return form(videos, model);
		}
		videosService.save(videos);
		addMessage(redirectAttributes, "保存视频管理成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/?repage";
	}
		
	/**
	 * 未处理媒资
	 * @param videos
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping(value = "/quequ/list")
	public String quequList(Videos videos, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Videos> page = videosService.queryQueueVideo(new Page<Videos>(request, response), videos); 
	    String WEB_URL =Global.getConfig("web.images.url");
	    List<Videos> list = page.getList();
	    if(null != list) {
		    for(Videos video : list) {
			    String picurl = video.getPicurl();
			    if(null != picurl && !picurl.startsWith("http")) {
			    	video.setPicurl(UrlUtil.addUrl(WEB_URL , video.getPicurl()));
			    }
			}
	    }		
	    //查询所属公司项目转码模板列表
	    List<Templates>  templatesList = templateService.getAllTemplates();
  		model.addAttribute("templatesList", templatesList);	    
		model.addAttribute("page", page);
		return "modules/zjvideo/videosQueueList";
	}	
	
	/**
	 * 正式媒资
	 * @param videos
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping(value = "/media/list")
	public String videoMediaList(Videos videos, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Videos> page = videosService.queryVideoMedia(new Page<Videos>(request, response), videos); 
	    String WEB_URL =Global.getConfig("web.images.url");
	    List<Videos> list = page.getList();
	    if(null != list) {
		    for(Videos video : list) {
		    	//video.setCdnPath(mediaService.getCdnPath(video.getCompanyid(),video.getPath()));
		    	video.setPath(mediaService.getPath(video.getCompanyid(),video.getPath()));
			    String picurl = video.getPicurl();
			    if(null != picurl && !picurl.startsWith("http")) {
			    	//video.setPicurl(UrlUtil.addUrl(WEB_URL , video.getPicurl()));
					video.setPicurl(mediaService.getPicurl(video.getCompanyid(),video.getPicurl()));
			    }
			    /*if(StringUtils.isNotEmpty(video.getCompanyid())){
			    	Office office = officeService.get(video.getCompanyid());
			    	String path = "http://" + office.getDomain() + "/video/" + video.getId();
			    	video.setPath(path);		    
			    }*/
			}
	    }		
		model.addAttribute("page", page);
		return "modules/zjvideo/videosMediaList";
	}	
	
	/**
	 *
	 * 正式媒资下视频列表
	 * @param videos
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping(value = "/media/videos")
	public String mediaVideos(Videos videos, HttpServletRequest request, HttpServletResponse response, Model model) {
		videoid = videos.getId();
		Media medi = new Media();
		medi.setVideosid(videos.getId());
		Page<Media> page = videosService.queryMediaVideos(new Page<Media>(request, response), medi); 
	    String WEB_URL =Global.getConfig("web.images.url");
	    List<Media> list = page.getList();
	    if(null != list) {
		    for(Media media : list) {
			    String picurl = media.getPicurl();
			    if(null != picurl && !picurl.startsWith("http")) {
			    	//media.setPicurl(UrlUtil.addUrl(WEB_URL , media.getPicurl()));
					media.setPicurl(mediaService.getPicurl(media.getCompanyid(),media.getPicurl()));
			    }
			    if(StringUtils.isNotEmpty(media.getCompanyid())){
			    	//Office office = officeService.get(media.getCompanyid());
			    	//String path = "http://" + office.getDomain() + "/media/" + media.getId();
					String path = mediaService.getPath(media);
					String cdnPath = mediaService.getCdnPath(media);
					media.setCdnPath(cdnPath);
			    	media.setPath(path);
			    }
			}
	    }	
	    Media datail = new Media();
	    String total ="0";
	    if((null != list) && (list.size() > 0)){
	    	total ="1";
	    	datail = list.get(0);
	    	if(!StringUtils.isBlank(datail.getProjectid())){
	    		datail.setProject(projectService.get(datail.getProjectid()));
	    	}
	    }
	    model.addAttribute("total", total);
	    model.addAttribute("datail", datail);	    
		model.addAttribute("page", page);
		return "modules/zjvideo/mediaVideoList";
	}	
	
	/**
	 * 查询正式媒资下视频列表信息
	 * @param videos
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:view")
	@RequestMapping(value = "/media/videos/list")
	public String mediaVideosList(Media media, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Media medi = new Media();*/
		media.setVideosid(videoid);
		/*medi.setName(videos.getName());*/
		Page<Media> page = videosService.queryMediaVideos(new Page<Media>(request, response), media); 
	    String WEB_URL =Global.getConfig("web.images.url");
	    List<Media> list = page.getList();
	    if(null != list) {
		    for(Media medi : list) {
			    String picurl = medi.getPicurl();
			    if(null != picurl && !picurl.startsWith("http")) {
			    	medi.setPicurl(UrlUtil.addUrl(WEB_URL , medi.getPicurl()));
			    }
			}
	    }
		model.addAttribute("page", page);
		return "modules/zjvideo/mediaVideoList";
	}	
	
	/**
	 * 删除正式媒资下视频
	 * @param videos
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "delete/video/media")
	public String deleteMedia(Media media, RedirectAttributes redirectAttributes) {
		mediaService.deleteMedia(media);
		addMessage(redirectAttributes, "删除视频成功");
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/media/videos/list?repage";
	}	
	
	/**
	 * 开启，关闭缓存
	 */
	@RequiresPermissions("zjvideo:videos:edit")
	@RequestMapping(value = "/update/{state}")
	public String updateState(@PathVariable("state") String state,  @RequestParam("id") String mediaId, RedirectAttributes redirectAttributes) {
		String message = null;
		if (state.equals("active")) {
			Media media = mediaService.get(mediaId);
			media.setIscache(Media.YES_CACHE);
			media.setCacheflag(Media.CACHE_FLAG_NO);
			mediaService.save(media);
			message = "开启缓存CDN成功。";
		} else if (state.equals("suspend")) {
			Media media = mediaService.get(mediaId);
			media.setIscache(Media.NO_CACHE);
			media.setCacheflag(Media.CACHE_FLAG_YES);
			mediaService.save(media);
			message = "关闭缓存CDN成功。";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:"+Global.getAdminPath()+"/zjvideo/videos/media/videos/list?repage";
	}	
	
	/**
	 * 剧集添加相关课程
	 * @param videos
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("zjvideo:videos:edit")
    @RequestMapping(value = "add/programvolume", method=RequestMethod.POST)
    public String addProgramvolume(Videos videos, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			String videoIds = request.getParameter("uids");
			List<String> videoIdList = Arrays.asList(videoIds.split(","));
			logger.info("videoIds====" + videoIds + "====videoIdList==="+videoIdList);
			List<Videos> VideosList = new ArrayList<Videos>();
			for (String vid : videoIdList) {
				Videos videosItem = videosService.get(vid);
				VideosList.add(videosItem);
			}			
			renderString(response, VideosList);
			return null;
    }	
}