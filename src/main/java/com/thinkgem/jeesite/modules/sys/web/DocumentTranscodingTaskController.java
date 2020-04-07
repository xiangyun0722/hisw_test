/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hisw.solr.pojo.SolrBean;
import com.hisw.solr.service.SolrjQuery;
import com.hisw.solr.service.SolrjService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.DocumentTranscodingTask;
import com.thinkgem.jeesite.modules.sys.service.DocumentTranscodingTaskService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.zjvideo.util.DownloadFileNameEncoder;

/**
 * 系统文档转码功能Controller
 * @author twk
 * @version 2015-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/documentTranscodingTask")
public class DocumentTranscodingTaskController extends BaseController {
	
	static Logger logger = org.apache.log4j.Logger.getLogger(DocumentTranscodingTaskController.class);

	@Autowired
	private DocumentTranscodingTaskService documentTranscodingTaskService;
	
	@RequiresPermissions("sys:documentTranscodingTask:view")
	@RequestMapping(value = "getJson")
	public DocumentTranscodingTask getJson(Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		DocumentTranscodingTask documentTranscodingTask =	documentTranscodingTaskService.get(request.getParameter("id"));
		renderString(response,documentTranscodingTask );
		return null;
	}
	
	@ModelAttribute
	public DocumentTranscodingTask get(@RequestParam(required=false) String id) {
		DocumentTranscodingTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = documentTranscodingTaskService.get(id);
		}
		if (entity == null){
			entity = new DocumentTranscodingTask();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:documentTranscodingTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(DocumentTranscodingTask documentTranscodingTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DocumentTranscodingTask> page = documentTranscodingTaskService.findPage(new Page<DocumentTranscodingTask>(request, response), documentTranscodingTask); 
		model.addAttribute("page", page);
		return "modules/sys/documentTranscodingTaskList";
	}
	
	/***
	 * 刷新缓存信息。
	 * @param documentTranscodingTask
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:documentTranscodingTask:view")
	@ResponseBody
	@RequestMapping(value = {"refreshList"})
	public List<DocumentTranscodingTask> refreshList(HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询指定的几个
		String ids = request.getParameter("ids");
		ids = StringUtils.removeStart(ids, ",");
		List<DocumentTranscodingTask> lists = new ArrayList<DocumentTranscodingTask>();
		if(org.apache.commons.lang.StringUtils.isNotBlank(ids)){
			lists = documentTranscodingTaskService.findListByIds(ids);	
		}
		return lists;
	}
	
	/***
	 * 查询文档信息。
	 * @param documentTranscodingTask
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:documentTranscodingTask:view")
	@RequestMapping(value = "search")
	public String search(DocumentTranscodingTask documentTranscodingTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("mess", "1");
		Page<SolrBean> page = new Page<SolrBean>(request, response);
		// page.setPageNo(1);
		String keywords = request.getParameter("keywords");
		page.setPageSize(10);
		//设置 solrUrl 信息。SOLR_SERVER_URL
		SolrjQuery.solrUrl = DictUtils.getDictValue("SOLR_SERVER_URL", "sys_config","http://l15.hisw.cn/solr-4.9.1");
		List<SolrBean> dos=new ArrayList<SolrBean>();
		if(org.apache.commons.lang.StringUtils.isNotBlank(keywords)){
			try {
//				SolrBean searchBean = new SolrBean();
//				searchBean.setTitle(keywords);
//				searchBean.setKeywords(keywords);
//				searchBean.setContent(keywords);
				SolrBean sortBean = new SolrBean();
				sortBean.setId("desc");
				SolrjService solrjService =new  SolrjService();
				dos = solrjService.querySolrBeanResult(keywords, sortBean, (page.getPageNo() - 1) * page.getPageSize()*1l, page.getPageSize()*1l);
				//dos = SolrJUtils.getInstance().queryDocument(documentV.getKeyword(), page);
			} catch (Exception e) {
				addMessage(model, "搜索引擎异常!");
				model.addAttribute("mess", "0");
				logger.error(e.toString(), e);
			}
		}else{
			addMessage(model, "必须输入关键字");
			model.addAttribute("mess", "0");
		}
		page.setList(dos);
		model.addAttribute("page", page);
		return "modules/sys/documentTranscodingTaskListSearch";
	}

	@RequiresPermissions("sys:documentTranscodingTask:view")
	@RequestMapping(value = "form")
	public String form(DocumentTranscodingTask documentTranscodingTask, Model model) {
		model.addAttribute("documentTranscodingTask", documentTranscodingTask);
		return "modules/sys/documentTranscodingTaskForm";
	}
	
	/***
	 * 下载文档。
	 * @param documentTranscodingTask
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("sys:documentTranscodingTask:edit")
	@RequestMapping(value = "download")
	public String download(DocumentTranscodingTask documentTranscodingTask, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		try {
	    	/**下载**/
	    	File file = new File(documentTranscodingTask.getFilepath());
	        // 以流的形式下载文件。
	        InputStream fis = new BufferedInputStream(new FileInputStream(documentTranscodingTask.getFilepath()));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        response.reset();
	        // 设置response的Header
	        String originalName = documentTranscodingTask.getOriginalName();
	        response.addHeader("Content-Length",""+file.length());
	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType("application/octet-stream");
	        String userAgent = request.getHeader("User-Agent");
			logger.info("userAgent:"+userAgent);
			DownloadFileNameEncoder encoder = new DownloadFileNameEncoder();
			originalName = encoder.encode(originalName, userAgent);
			response.setHeader("Content-Disposition", "attachment; filename="+originalName);
	      //response.setHeader("Content-Disposition", "attachment; filename="+encodingFileName(originalName));
	      //response.addHeader("Content-Disposition", "attachment; filename="+ fileName);
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
	    	} catch (Exception e) {
	    		logger.error(e,e);
	    		renderString(response, "文件不存在");
	    	}
		return null;
	}

	@RequiresPermissions("sys:documentTranscodingTask:edit")
	@RequestMapping(value = "save")
	public String save(DocumentTranscodingTask documentTranscodingTask, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, documentTranscodingTask)){
			return form(documentTranscodingTask, model);
		}
		String fileJson = request.getParameter("fileJson");
		if(StringUtils.isNotBlank(fileJson)){
			UploadFileInfo  uploadFileInfo = UploadFileInfo.StrToBean(fileJson);
			documentTranscodingTask.setFilepath(uploadFileInfo.getFilePath());
			documentTranscodingTask.setLength(uploadFileInfo.getLength());
			documentTranscodingTask.setOriginalName(uploadFileInfo.getOldFileName());
		}
		documentTranscodingTask.setStatus(0);
		documentTranscodingTaskService.save(documentTranscodingTask);
//		//读取全部文件信息。并且入库。
//		File doc = new File("E:/萨顿会议全部的ppt");
//		File[] fs= doc.listFiles();
//		int i=0;
//		for (File file : fs) {
//			++i;
//			DocumentTranscodingTask te = new DocumentTranscodingTask();
//			te.setBusinessId(i+"");
//			te.setOriginalName(file.getName());
//			te.setFilepath(file.getAbsolutePath());
//			te.setStatus(0);
//			documentTranscodingTaskService.save(te);
//		}
		//读取目录下面全部文件。然后全部导入到数据库。做压力测试。
		addMessage(redirectAttributes, "保存文档转码任务成功");
		return "redirect:"+Global.getAdminPath()+"/sys/documentTranscodingTask/?repage";
	}
	
	@RequiresPermissions("sys:documentTranscodingTask:edit")
	@RequestMapping(value = "delete")
	public String delete(DocumentTranscodingTask documentTranscodingTask, RedirectAttributes redirectAttributes) {
		documentTranscodingTaskService.delete(documentTranscodingTask);
		addMessage(redirectAttributes, "删除文档转码任务成功");
		return "redirect:"+Global.getAdminPath()+"/sys/documentTranscodingTask/?repage";
	}
	
	/***
	 * 重新转码
	 * @param documentTranscodingTask
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:documentTranscodingTask:edit")
	@RequestMapping(value = "transcoding_again")
	public String transcodingAgain(DocumentTranscodingTask documentTranscodingTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, documentTranscodingTask)){
			return form(documentTranscodingTask, model);
		}
		documentTranscodingTask.setStatus(0);//等待转码。
		documentTranscodingTaskService.save(documentTranscodingTask);
		addMessage(redirectAttributes, "操作成功,等待转码程序转码");
		return "redirect:"+Global.getAdminPath()+"/sys/documentTranscodingTask/?repage";
	}
}