/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.hisw.core.utils.DateFormatter;
import com.hisw.core.utils.FileUtil;
import com.hisw.core.utils.UrlUtil;
import com.hisw.pic.PicBean;
import com.hisw.pic.PicDealThreadUitl;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 上传Controller
 * @author twk
 * @version 2015-06-11
 */
@Controller
@RequestMapping(value = "sys/upload")
public class UploadController extends BaseController {
	
	static Logger logger=Logger.getLogger(UploadController.class);
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private DictService dictService;
	
	/***
	 * 上传接口 返回 上传的 file 对象
	 * 	 * @param request
	 * @param out
	 * @throws IOException 
	 */
	@RequestMapping("/upload")
	public void upload(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =response.getWriter(); 
		String  format = request.getParameter("format");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();  
		MultipartFile multipartFile =null;
		String fileName = null;
		 for(Map.Entry<String,MultipartFile > set:fileMap.entrySet()){
			 String filekey = set.getKey();//Filedata
			 multipartFile = set.getValue();//文件名
		 }
		 UploadFileInfo  uploadFileInfo = this.storeIOc(multipartRequest, multipartFile);
		 String outInfo =null;
		 if(StringUtils.isNotBlank(format)){
			 outInfo= JSONObject.toJSON(uploadFileInfo).toString();
			 outInfo = org.apache.commons.lang3.StringUtils.replace(outInfo, "\"","'");
		 }else{
			 outInfo = fileName;
		 }
		 logger.info("outInfo:"+outInfo);
		 out.print(outInfo); 
	}
	
	/***
	 * 删除文件接口
	 * @param request
	 * @param out
	 */
	@ResponseBody
	@RequestMapping("/delRecordFile")
	public Map<String, Object>  delRecordFile(HttpServletRequest request) {
		try {
    		String changeFileName = request.getParameter("changeFileName");
    		String fileName = FilenameUtils.getName(changeFileName);
    		String xunimulu = DictUtils.getDictValue("VIRTUAL_DIRECTORIES", "sys_config", "");
    		FileUtil.deleteFiles(xunimulu, fileName);
		} catch (Exception e) {
			logger.error(e,e);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", "true");
		return result;
	}
	//文件返回文件地址
	private UploadFileInfo storeIOc(HttpServletRequest request, MultipartFile file) {
		UploadFileInfo  uploadFileInfo = new UploadFileInfo();
		String realPath = DictUtils.getDictValue("VIRTUAL_DIRECTORIES", "sys_config", "");
		FileUtil.createFolder(realPath);
		String vfPath = DictUtils.getDictValue("VIRTUAL_DIRECTORIES_PATH", "sys_config", "");
		String pictureHttpPrefixUrl = DictUtils.getDictValue("PICTURE_HTTP_PREFIX_URL", "sys_config", "");
		String fileName = "";
		String logImageName = "";
		String oldFileName ="";
		boolean isImag = false;
		if (file.isEmpty()) {
			System.out.println("文件未上传");
			logger.error("文件未上传");
		} else {
			String _fileName = org.apache.commons.lang3.StringUtils.replace(file.getOriginalFilename(), " ", "");;
			oldFileName = _fileName;
			String suffix = _fileName.substring(_fileName.lastIndexOf("."));
			// /**使用UUID生成文件名称**/
			//DateFormatter.getNotyyyyMMddHHmmss() +"_"+ new Random().nextInt(1000)
			logImageName =DateFormatter.shortDirDate(new Date()) +"/"+UUID.randomUUID().toString() + suffix;
			if(!StringUtils.endsWith(realPath, "/")){
				realPath = realPath + "/";
			}
			fileName= realPath+logImageName;
			File restore = new File(fileName);
			if(!restore.getParentFile().exists()){
				FileUtil.createFolders(restore.getParentFile());
			}
			try {
				file.transferTo(restore);
				//获取文件后缀信息
				String extension = FilenameUtils.getExtension(fileName);
				String imgs = ",jpg,png,bmp,gif,jpeg,";
				if(org.apache.commons.lang.StringUtils.indexOf(imgs, extension.toLowerCase())>-1){//如果是图片，则扔到压缩线程中去压缩。
					//转换成缩略图
					logger.info("上传为图片文件，需要压缩图片文件， fileName:"+fileName);
					PicDealThreadUitl.queue.offer(new PicBean(restore, 2));
					isImag = true;
				}
				uploadFileInfo.setLength(file.getSize());
				uploadFileInfo.setFilePath(fileName);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if(!StringUtils.endsWith(vfPath, "/")){
			vfPath = vfPath + "/";
		}
		// 返回默认的图片地址
		//return vfPath + logImageName;
		uploadFileInfo.setFileName(vfPath + logImageName);
		uploadFileInfo.setOldFileName(oldFileName);
		uploadFileInfo.setUrl(UrlUtil.addUrl(pictureHttpPrefixUrl, uploadFileInfo.getFileName()));
		if(isImag){//如果为图片格式。则重新设置值。
			//设置全局的路径
			uploadFileInfo.setFileName(UrlUtil.addUrl(pictureHttpPrefixUrl, uploadFileInfo.getFileName()));	
		}
		return uploadFileInfo;
	}

}
