package com.thinkgem.jeesite.test.web;


import com.thinkgem.jeesite.common.servlet.UploaderServlet;
import org.apache.log4j.Logger;

import java.io.File;

public class testUploaderVideoController {
    Logger logger = Logger.getLogger(testUploaderVideoController.class);
    public static void main(String[] args) {
        String filePth = "F:\\home\\edulive\\repo\\repository\\company_48\\project_22\\07-步骤导航.mp4";
        File file = new File(filePth);
        System.out.println(file.exists()+"========================");
    }
}
