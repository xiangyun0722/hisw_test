package com.thinkgem.jeesite.common.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.hisw.core.utils.FileUtil;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.entity.Uploadfile;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.UploadfileService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.zjvideo.entity.Project;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;
import com.thinkgem.jeesite.modules.zjvideo.entity.Videos;
import com.thinkgem.jeesite.modules.zjvideo.service.ProjectService;
import com.thinkgem.jeesite.modules.zjvideo.service.TemplatesService;
import com.thinkgem.jeesite.modules.zjvideo.service.VideosService;
import com.thinkgem.jeesite.modules.zjvideo.util.ConstantUtils;

public class UploaderServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger(UploaderServlet.class);
	
	private static final long serialVersionUID = 1L;
	
	private static UploadfileService uploadfileService = SpringContextHolder.getBean(UploadfileService.class);
	
	private static ProjectService projectService = SpringContextHolder.getBean(ProjectService.class);
	
	private static VideosService videosService = SpringContextHolder.getBean(VideosService.class);
	
	private static TemplatesService templatesService = SpringContextHolder.getBean(TemplatesService.class);
	
	String repositoryPath;

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("=======================================进入上传===============================");
		response.setCharacterEncoding( "UTF-8" );
		//设置支持跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		String msg ="";
		String vid = "";
		//tomacat 路径
		String repositoryPath = FileUtils.getTempDirectoryPath();


		FileUtil.createFolder(repositoryPath);
		//获取文件大小
		String filesizeStr = request.getParameter("filesize");
		//视频上传之后 直接放入到仓库中去。并且保存视频信息到视频表中。
		//项目id  上传属于哪个模块的视频
		String projectIdStr = request.getParameter("projectId");
		Project project = projectService.get(projectIdStr);

		// 资源库目录  /home/edulive/repo/repository/company_48/project_22/
		String uploadPath = project.getRepositorypath();
		//创建文件  /home/edulive/repo/repository/company_48/project_22/
		FileUtil.createFolder(uploadPath);
		logger.info("repositoryPath:"+repositoryPath+" uploadPath:"+uploadPath+" filesize:"+filesizeStr+" projectId:"+projectIdStr);
		Integer schunk = null;// 分割块数
		Integer schunks = null;// 总分割数
		String name = null;// 文件名
		BufferedOutputStream outputStream = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024);
				factory.setRepository(new File(repositoryPath));// 设置临时目录
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(5 * 1024 * 1024 * 1024);// 设置附近大小  总文件大小
				List<FileItem> items = upload.parseRequest(request); //解析请求正文内容
				// 生成新文件名
				String newFileName = null;
				//文件大小
				long filesize = Long.parseLong(filesizeStr);
				for (FileItem item : items) {
					if (!item.isFormField()) {// 如果是文件类型
						if(item.getSize() == 10485760 || schunk==(schunks-1)){
						name = newFileName;// 获得文件名
						if (name != null) {
							String nFname = newFileName;
							if (schunk != null) {
								nFname = schunk + "_" + name;
								logger.info("==========================nfname:" + nFname);
							}
							FileUtil.createFolders(new File(uploadPath));
							File savedFile = new File(uploadPath, nFname);
							item.write(savedFile);

						  }
						} else{

							item.delete();
                            response.sendError(500,"文件因为网络问题而丢包");
							logger.info("==============================文件因为网络问题而丢包");
							msg ="{\"status\":false}";
							renderString(response,msg, "application/json");
							return;
						}
					} else {
						// 判断是否带分割信息
						if (item.getFieldName().equals("chunk")) {
							logger.info("=======================================item.getString():"+item.getString());
							schunk = Integer.parseInt(item.getString());
						}
						if (item.getFieldName().equals("chunks")) {
							schunks = Integer.parseInt(item.getString());
						}
						if (item.getFieldName().equals("name")) {
							newFileName = new String(item.getString().getBytes("8859_1"),"utf-8");
						}
					}
				}
				newFileName = org.apache.commons.lang.StringUtils.replace(newFileName, " ","");
				logger.info(" schunk:"+schunk+" schunks:"+schunks+" newFileName:"+newFileName);
				File outFileTemp  = new File(uploadPath,newFileName);
				if (schunk != null && schunk == 0) {
					Uploadfile temp = new Uploadfile();
					temp.setFilename(newFileName);
					temp.setFilesize(filesize);
					Uploadfile uploadfileTemp = uploadfileService.get(temp);
					if (uploadfileTemp == null) {
						Uploadfile uploadfile = new Uploadfile();
						uploadfile.setFilename(newFileName);
						uploadfile.setFilepath(outFileTemp.getAbsolutePath());
						uploadfile.setSchunk(schunk);
						uploadfile.setSchunks(schunks);
						uploadfile.setFilesize(filesize);
						uploadfile.setProject(project);
						uploadfileService.save(uploadfile); 
					} else {
						System.out.println("已经上传完成");
					}
				} else {
					//查询文件并且修改schunk 
					Uploadfile temp = new Uploadfile();
					temp.setFilename(newFileName);
					temp.setFilesize(filesize);
					temp.setProject(project);
					Uploadfile uploadfileTe = uploadfileService.get(temp);
					if(uploadfileTe != null ){
						uploadfileTe.setSchunk(schunk);
						uploadfileService.save(uploadfileTe);
					}else{
						logger.info("newFileName:"+newFileName +" not find ");
					}
				}
				if (schunk != null && schunk + 1 == schunks) {
					File outFile  = new File(uploadPath,newFileName);
					outputStream = new BufferedOutputStream(new FileOutputStream(outFile));
					// 遍历文件合并
					for (int i = 0; i < schunks; i++) {
						File tempFile = new File(uploadPath, i + "_" + name);
						logger.info("合并文件："+tempFile.getAbsolutePath());
						byte[] bytes = FileUtils.readFileToByteArray(tempFile);
						outputStream.write(bytes);
						outputStream.flush();
						tempFile.delete();
					}
					outputStream.flush();
					//视频上传之后 直接放入到仓库中去。并且保存视频信息到视频表中。
					Videos videos = new Videos();
					videos.setAddtime(new Date());
					videos.setName(newFileName);
					videos.setgeneratetaskflag(ConstantUtils.GENERATE_TASK_FLAG_NO);//上传视频初始化状态0代表未生成转码任务
					//上传视频目录
					videos.setCompanyid(project.getCompanyid());
					//平台用户Id保存
					User user = project.getCreateBy();//设置上传
					videos.setCreateBy(user);//项目负责人
					//videos.setCreateBy(videos.getCurrentUser());//改为谁上传就归谁
					videos.setUploadBy(UserUtils.getUser());
					//拷贝上传的单个文件到转码资源库中
					//获取视频绝对路径
					videos.setProject(project);
					videos.setProjectid(projectIdStr);
					videos.setSource(outFile.getAbsolutePath());
					videosService.save(videos);
					//****广州参考视频平台*****//
					//根据广州参考系统要求，视频上传成功默认从工程下面选择一个模板，进行转码

                  List<Templates>  templatesListTemp = new ArrayList<Templates>();
					//原有逻辑，获取项目下所有转码模板列表
					templatesListTemp = templatesService.getOwnTemplates(videos.getProject());
					for (Templates templates : templatesListTemp) {
						templates.setConvertstatus(Templates.convertstatus_wait);
					}
					videos.setTemplatesList(templatesListTemp);
					videosService.insertVideoTranscodingTask(videos);//插入转码任务
					videosService.insertVideoPushcdnTask(videos);//插入推cdn任务	
					/*更新未生成转码状态为生成该转码任务*/
					videos.setgeneratetaskflag(ConstantUtils.GENERATE_TASK_FLAG_YES);
					videosService.save(videos);
					vid = (videos != null && videos.getId() != null) ? videos.getId() : "";
				}
				String filePath = uploadPath.endsWith("/") ? uploadPath + newFileName : uploadPath + "/" + newFileName;
				msg = "{\"status\":true,\"newName\":\"" + newFileName+ "\",\"filePath\":\""+filePath+"\",\"vid\":\""+vid+"\"}" ;
			} catch (FileUploadException e) {
				logger.error(e,e);
				msg ="{\"status\":false}";
			} catch (Exception e) {
				logger.error(e,e);
				msg = "{\"status\":false}" ;
			} finally {
				try {
					if (outputStream != null)
						outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		renderString(response,msg, "application/json");
		//return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		repositoryPath = FileUtils.getTempDirectoryPath();
		System.out.println("临时目录：" + repositoryPath);
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
