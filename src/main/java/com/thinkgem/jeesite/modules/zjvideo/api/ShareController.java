package com.thinkgem.jeesite.modules.zjvideo.api;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RequestMapping(value = "api/share")
@Controller
public class ShareController {


    @RequestMapping("/play")
    public String sharePlayPage(HttpServletRequest request, Model model){
        String videoUrl = request.getParameter("videoUrl");
        model.addAttribute("videoUrl", videoUrl);
        return "modules/zjvideo/sharePlayVideo";
    }


}
