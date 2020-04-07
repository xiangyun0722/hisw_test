package com.thinkgem.jeesite.modules.upload.web;

import com.hisw.core.utils.FileUtil;
import com.thinkgem.jeesite.common.servlet.UploaderServlet;
import com.thinkgem.jeesite.common.web.BaseController;
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Version
 * @Author zcf
 * @Created 2020年02月20  14:11:59
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020年02月20  Zcf  create file
 */
@Controller
@RequestMapping(value = "uploadOnLine")
public class UploadOnLineController extends BaseController {

    Logger logger = Logger.getLogger(UploaderServlet.class);

    @Autowired
    ProjectService projectService;

    @Autowired
    VideosService videosService;

    @Autowired
    UploadfileService uploadfileService;

    @Autowired
    TemplatesService templatesService;


    @RequestMapping(value="/upload",method = {RequestMethod.OPTIONS,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> upload1(HttpServletRequest request, HttpServletResponse response,Long filesize,
                                      String projectId,String name) throws IOException{
        logger.info("=======================================进入上传===============================");

        //设置支持跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","POST, OPTIONS");
        Map<String,Object> ret = new HashMap<String, Object>();

        /*if(filesize == null){
            ret.put("status",false);
            ret.put("msg","filesize不能为空");
            return ret;
        }*/
        if(StringUtils.isBlank(projectId)){
            ret.put("status",false);
            ret.put("msg","projectId不能为空");
            return ret;
        }
        if(StringUtils.isBlank(name)){
            ret.put("status",false);
            ret.put("msg","name不能为空");
            return ret;
        }else{
            int suffix_index = name.lastIndexOf(".");
            if(suffix_index == -1){
                ret.put("status",false);
                ret.put("msg","name缺少后缀");
                return ret;
            }
            String suffix = name.substring(suffix_index);
            System.out.println(suffix);
            //'video/mp4', 'video/ogg', 'video/flv','video/avi','video/wmv','video/rmvb'
            List<String> allSuffix = new LinkedList<>();
            allSuffix.add(".mp4");
            allSuffix.add(".ogg");
            allSuffix.add(".flv");
            allSuffix.add(".avi");
            allSuffix.add(".wmv");
            allSuffix.add(".rmvb");
            if(!allSuffix.contains(suffix)){
                ret.put("status",false);
                ret.put("msg","name不能为空");
                return ret;
            }
        }

        if("OPTIONS".equals(request.getMethod())){
            //检查
            ret.put("status",true);
            return ret;
        }
        //2020-03-03处理空格（因为空格会让转码程序失败）
        name = name.replaceAll(" ","");

        //
        String repositoryPath = FileUtils.getTempDirectoryPath();
        FileUtil.createFolder(repositoryPath);
        //视频上传之后 直接放入到仓库中去。并且保存视频信息到视频表中。
        Project project = projectService.get(projectId);
        String uploadPath = project.getRepositorypath();
        FileUtil.createFolder(uploadPath);
        logger.info("repositoryPath:"+repositoryPath+" uploadPath:"+uploadPath+" filesize:"+filesize+" projectId:"+projectId);


        //上传文件，异地上传文件是以文件流的形式上传的
        try{
            upLoadInfo(request, uploadPath);

            File outFile  = new File(uploadPath,name);
            //上传成功保存视频数据到数据库
            Videos videos = new Videos();
            videos.setAddtime(new Date());
            videos.setName(name);
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
            videos.setProjectid(projectId);
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
            //videosService.insertVideoPushcdnTask(videos);//插入推cdn任务
            /*更新未生成转码状态为生成该转码任务*/
            videos.setgeneratetaskflag(ConstantUtils.GENERATE_TASK_FLAG_YES);
            videosService.save(videos);

            ret.put("vid",(videos != null && videos.getId() != null) ? videos.getId() : "");
            ret.put("newName",name);
            ret.put("filePath",uploadPath.endsWith("/") ? uploadPath + name : uploadPath + "/" + name);
            ret.put("status",true);
            return ret;

        }catch(Exception e){
            logger.error("========排课系统文件上传失败：" + e.toString());

            ret.put("status",false);
            ret.put("msg","文件上传失败");
            return ret;
        }
    }


    public static List<String> upLoadInfo(HttpServletRequest request,String path)throws Exception{
        //存放结果
        List<String> list = new ArrayList<String> ();
        //1、将当前上下文初始化给CommonMutipartResolver
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //2、检查Form中encType是否为multipart/form-data
        if(multipartResolver.isMultipart(request)){
            //将request转为MultipartHttpServletRequest
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取迭代器遍历multipartRequest里的文件名
            Iterator<String> iter = multipartRequest.getFileNames();
            while(iter.hasNext())
            {
                //获取文件并向下遍历
                MultipartFile file = multipartRequest.getFile(iter.next().toString());
                if(file != null)
                {
                    //获取文件类型，即后缀名
                    //2020-03-03处理空格，因为空格会导致转码失败
                    String str = file.getOriginalFilename();
                    String suffix = str.substring(str.lastIndexOf("."));
                    String name = str.substring(0,str.lastIndexOf("."))
                            .replaceAll(" ","");

                    //拼接文件绝对路径
                    String filePath = path + name + suffix;
                    try{//将文件写入到指定的路径当中
                        file.transferTo(new File(filePath));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    //将上传的文件路径放在list集合中，在控制层可以拿到已经上传的文件路径
                    list.add(filePath);
                }
            }
        }
        return list;

    }
}
