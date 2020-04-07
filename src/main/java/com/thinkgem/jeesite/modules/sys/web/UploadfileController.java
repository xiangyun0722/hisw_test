/**
 * 
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Uploadfile;
import com.thinkgem.jeesite.modules.sys.service.UploadfileService;

/**
 * 系统上传文件Controller,支持文件断点续传功能。
 * @author twk
 * @version 2015-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/uploadfile")
public class UploadfileController extends BaseController {
	
	@Autowired
	private UploadfileService uploadfileService;
	
	/**
	 * 检查文件是否存在。
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "ckeck")
//	public String checkFile(HttpServletRequest request, HttpServletResponse response, Model model){
//		String repositoryPath = FileUtils.getTempDirectoryPath();
//		FileUtil.createFolder(repositoryPath);
//		String fileName= request.getParameter("filename");
//		String chunk_size = request.getParameter("chunk_size");
//		String filesize = request.getParameter("filesize");
//		System.out.println("fileName:"+fileName +" chunk_size:"+chunk_size +" filesize:"+filesize);
//		Uploadfile temp = new Uploadfile();
//		temp.setFilename(fileName);
//		temp.setFilesize(Long.parseLong(filesize));
//		Uploadfile uploadfile = uploadfileService.get(temp);
//		if(uploadfile!=null){//说明上传过
//			Integer schunk = uploadfile.getSchunk();
//			deleteFile(repositoryPath+schunk+"_"+fileName);
//			long off = schunk  * Long.parseLong(chunk_size); 
//			System.out.println("off:"+off);
//			renderString(response, "{\"off\":"+off+"}", "application/json");
//		}else{
//			renderString(response, "{\"off\":0}", "application/json");
//		}
//		return null;
//	}
	
	/**
	@RequestMapping(value = "uploader")
	public String uploader(HttpServletRequest request, HttpServletResponse response, Model model){
		String msg ="";
		String repositoryPath = FileUtils.getTempDirectoryPath();
		FileUtil.createFolder(repositoryPath);
		String uploadPath =DictUtils.getDictValue("VIRTUAL_DIRECTORIES", "sys_config", "");
		logger.info("repositoryPath:"+repositoryPath+" uploadPath:"+uploadPath);
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
				upload.setSizeMax(5 * 1024 * 1024 * 1024);// 设置附近大小
				List<FileItem> items = upload.parseRequest(request);
				// 生成新文件名
				String newFileName = null;
				for (FileItem item : items) {
					if (!item.isFormField()) {// 如果是文件类型
						name = newFileName;// 获得文件名
						if (name != null) {
							String nFname = newFileName;
							if (schunk != null) {
								nFname = schunk + "_" + name;
							}
							File savedFile = new File(uploadPath, nFname);
							item.write(savedFile);
						}
					} else {
						// 判断是否带分割信息
						if (item.getFieldName().equals("chunk")) {
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
				if(org.apache.commons.lang.StringUtils.isBlank(newFileName)){
					logger.error("newFileName is null ,"+schunk);
				}else{
					logger.info(" schunk:"+schunk+" schunks:"+schunks+" newFileName:"+newFileName);
				}
				if (schunk != null && schunk == 0) {
					Uploadfile temp = new Uploadfile();
					temp.setFilename(newFileName);
					//temp.setFilesize(filesize);
					Uploadfile uploadfileTemp = uploadfileService.get(temp);
					if (uploadfileTemp == null) {
						Uploadfile uploadfile = new Uploadfile();
						uploadfile.setFilename(newFileName);
						uploadfile.setSchunk(schunk);
						uploadfile.setSchunks(schunks);
						uploadfileService.save(uploadfile);
					} else {
						System.out.println("已经上传完成");
					}
				} else {
					//查询文件并且修改schunk 
					Uploadfile temp = new Uploadfile();
					temp.setFilename(newFileName);
					//temp.setFilesize(filesize);
					Uploadfile uploadfileTe = uploadfileService.get(temp);
					if(uploadfileTe != null ){
						uploadfileTe.setSchunk(schunk);
						uploadfileService.save(uploadfileTe);
					}else{
						logger.info("newFileName:"+newFileName +" not find ");
					}
				}
				if (schunk != null && schunk + 1 == schunks) {
					outputStream = new BufferedOutputStream(new FileOutputStream(new File(uploadPath,newFileName)));
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
				}
				msg = "{\"status\":true,\"newName\":\"" + newFileName+ "\"}" ;
			} catch (FileUploadException e) {
				e.printStackTrace();
				msg ="{\"status\":false}";
			} catch (Exception e) {
				e.printStackTrace();
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
		return null;
	}**/
	
	public boolean deleteFile(String sPath) {
		logger.error("sPath:"+sPath);
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	
	
	@ModelAttribute
	public Uploadfile get(@RequestParam(required=false) String id) {
		Uploadfile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = uploadfileService.get(id);
		}
		if (entity == null){
			entity = new Uploadfile();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:uploadfile:view")
	@RequestMapping(value = {"list", ""})
	public String list(Uploadfile uploadfile, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Uploadfile> page = uploadfileService.findPage(new Page<Uploadfile>(request, response), uploadfile); 
		model.addAttribute("page", page);
		return "modules/sys/uploadfileList";
	}

	@RequiresPermissions("sys:uploadfile:view")
	@RequestMapping(value = "form")
	public String form(Uploadfile uploadfile, Model model) {
		model.addAttribute("uploadfile", uploadfile);
		return "modules/sys/uploadfileForm";
	}

	@RequiresPermissions("sys:uploadfile:edit")
	@RequestMapping(value = "save")
	public String save(Uploadfile uploadfile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, uploadfile)){
			return form(uploadfile, model);
		}
		uploadfileService.save(uploadfile);
		addMessage(redirectAttributes, "保存上传文件成功");
		return "redirect:"+Global.getAdminPath()+"/sys/uploadfile/?repage";
	}
	
	@RequiresPermissions("sys:uploadfile:edit")
	@RequestMapping(value = "delete")
	public String delete(Uploadfile uploadfile, RedirectAttributes redirectAttributes) {
		uploadfileService.delete(uploadfile);
		addMessage(redirectAttributes, "删除上传文件成功");
		return "redirect:"+Global.getAdminPath()+"/sys/uploadfile/?repage";
	}

}