package com.thinkgem.jeesite.modules.zjvideo.api;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.thinkgem.jeesite.common.api.ExceptionUtil;
import com.thinkgem.jeesite.common.api.RestResponse;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.zjvideo.entity.Templates;
import com.thinkgem.jeesite.modules.zjvideo.service.TemplatesService;

@Controller
@RequestMapping(value = "api/template")
@CrossOrigin
public class TemplatesApiController extends BaseController{
	
	@Autowired
	private TemplatesService templatesService;
	
	@RequestMapping(value ="/get")
    @ResponseBody
	public RestResponse get(HttpServletRequest request, HttpServletResponse response){
		RestResponse restResponse = new RestResponse();
        try {
			Integer id =Integer.parseInt(request.getParameter("projectid"));
			Assert.notNull(id, "id错误");
			List<Templates> list=templatesService.getListByProjectid(id);
			restResponse.setData(list);
		} catch (Exception ex) {
	    	restResponse= ExceptionUtil.dealException(ex);
	    }
	    return restResponse;
	}

}
