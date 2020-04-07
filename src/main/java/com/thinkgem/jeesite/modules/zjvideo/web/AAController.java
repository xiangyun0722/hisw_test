package com.thinkgem.jeesite.modules.zjvideo.web;

import com.thinkgem.jeesite.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @Version
 * @Author zcf
 * @Created 2020年02月11  19:10:51
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020年02月11  Zcf  create file
 */
@Controller
@RequestMapping(value = "aa")
public class AAController extends BaseController {

    @RequestMapping(value = "test")
    @ResponseBody
    public String test(HttpServletResponse response){
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        return "{\"status\":true,\"newName\":\"测试一下\",\"filePath\":\"work_install\\nodejs\\node_modules\",\"vid\":\"123\"}" ;
    }


    @RequestMapping(value = "info")
    public String info(){
        ModelAndView m = new ModelAndView();
        m.addObject("i","www.baidu.com");
        return "/modules/zjvideo/aa";
    }

}
