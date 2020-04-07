/**
 * 
 */
package com.thinkgem.jeesite.modules.live.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import com.hisw.core.utils.HttpClientUtil4;
import com.thinkgem.jeesite.common.utils.IdGen;

/**
 * 直播频道录制文件ApiController
 * @author tanwenkai@qq.com
 * @version 2016-12-12
 */
public class ChannelFilesAppApiControllerTest{

	private static Logger logger =Logger.getLogger(ChannelFilesAppApiControllerTest.class);
	
	private static String url = "http://127.0.0.1/xjbweb/api/channelFiles/";
	
	static RestTemplate restTemplate = null;
	
	static{
	}
	
	@Before 
	public void setUp() throws Exception {
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void get() throws ClientProtocolException, IOException {
		Map<String ,String> urlVariables = new HashMap<String ,String>(); 
		urlVariables.put("id", "1");
		String tempUrl =url+"get";
		System.out.println(tempUrl);
		String result= HttpClientUtil4.sendPost(tempUrl,urlVariables);
		System.out.println(result);
	}
	
	@Test
	public void list() throws ClientProtocolException, IOException {
		Map<String ,String> urlVariables = new HashMap<String ,String>(); 
		String tempUrl =url+"list";
		System.out.println(tempUrl);
		String result= HttpClientUtil4.sendPost(tempUrl,urlVariables);
		System.out.println(result);
	}
	
	@Test
	public void save() throws ClientProtocolException, IOException {
		Map<String ,String> urlVariables = new HashMap<String ,String>(); 
		urlVariables.put("id", IdGen.uuid());
		String tempUrl =url+"save";
		System.out.println(tempUrl);
		String result= HttpClientUtil4.sendPost(tempUrl,urlVariables);
		System.out.println(result);
	}
	
	@Test
	public void delete() throws ClientProtocolException, IOException {
		Map<String ,String> urlVariables = new HashMap<String ,String>(); 
		urlVariables.put("id", "1");
		String tempUrl =url+"delete";
		System.out.println(tempUrl);
		String result= HttpClientUtil4.sendPost(tempUrl,urlVariables);
		System.out.println(result);
	}
	
}