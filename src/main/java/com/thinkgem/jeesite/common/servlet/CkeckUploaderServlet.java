package com.thinkgem.jeesite.common.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.hisw.core.utils.FileUtil;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.entity.Uploadfile;
import com.thinkgem.jeesite.modules.sys.service.UploadfileService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

public class CkeckUploaderServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger(CkeckUploaderServlet.class);
	
	private static final long serialVersionUID = 1L;
	
	private static UploadfileService uploadfileService = SpringContextHolder.getBean(UploadfileService.class);
	
	String repositoryPath;
	String uploadPath;

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding( "UTF-8" );
		//设置支持跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		String repositoryPath = FileUtils.getTempDirectoryPath();
		FileUtil.createFolder(repositoryPath);
		String fileName= request.getParameter("filename");
		String chunk_size = request.getParameter("chunk_size");
		String filesize = request.getParameter("filesize");
		String projectId = request.getParameter("projectId");
		//System.out.println("fileName:"+fileName +" chunk_size:"+chunk_size +" filesize:"+filesize+" projectId:"+projectId);
		logger.info("fileName:"+fileName +"chunk_size(one file size):"+chunk_size +" filesize:"+filesize+" projectId:"+projectId);
		Uploadfile temp = new Uploadfile();
		temp.setFilename(fileName);
		temp.setFilesize(Long.parseLong(filesize));
		Uploadfile uploadfile = uploadfileService.get(temp);
		if(uploadfile!=null){//说明上传过
			Integer schunk = uploadfile.getSchunk();
			deleteFile(repositoryPath+schunk+"_"+fileName);
			long off = schunk  * Long.parseLong(chunk_size); 
			System.out.println("off:"+off);
			renderString(response, "{\"off\":"+off+"}", "application/json");
		}else{
			renderString(response, "{\"off\":0}", "application/json");
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		repositoryPath = FileUtils.getTempDirectoryPath();
		System.out.println("临时目录：" + repositoryPath);
		uploadPath =DictUtils.getDictValue("VIRTUAL_DIRECTORIES", "sys_config", "");
		System.out.println("目录：" + uploadPath);
		File up = new File(uploadPath);
		if (!up.exists()) {
			up.mkdir();
		}
	}
	
	public boolean deleteFile(String sPath) {
		logger.info("sPath:"+sPath);
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}
