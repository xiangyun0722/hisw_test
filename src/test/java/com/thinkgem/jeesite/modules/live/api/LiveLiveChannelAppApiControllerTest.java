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
 * 直播频道ApiController
 * @author tanwenkai@qq.com
 * @version 2016-12-06
 */
public class LiveLiveChannelAppApiControllerTest{

	private static Logger logger =Logger.getLogger(LiveLiveChannelAppApiControllerTest.class);
	
	//private static String url = "http://127.0.0.1:8080/hiswvideoweb/api/liveLiveChannel/";
	
	private static String url = "http://video.hisw.cn/hiswvideoapi/api/liveLiveChannel/";
	
	
	
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
	public void add() throws ClientProtocolException, IOException {
		Map<String ,String> urlVariables = new HashMap<String ,String>(); 
		urlVariables.put("publicKey", "37f3c77e12014181a30816392df0ad2c");
		urlVariables.put("relId", "1");
		urlVariables.put("relId", "2");
		//{"code":0,"msg":"","data":{"id":"9d838da8beba438facaa6bd1ec26f0cd","isNewRecord":false,"createDate":"2016-12-15 00:43:03","updateDate":"2016-12-15 00:43:03","relId":"2","channelSecretKey":"14bceb4aa7d545a9a36946749b074da1","pushRtmpUrl":"rtmp://211.144.68.31/swaps/9d838da8beba438facaa6bd1ec26f0cd","rtmpUrl":"rtmp://211.144.68.31/swaps/9d838da8beba438facaa6bd1ec26f0cd","hlsUrl":"http://211.144.68.31/hls/9d838da8beba438facaa6bd1ec26f0cd/live.m3u8"}}
		String tempUrl =url+"add";
		System.out.println(tempUrl);
		String result= HttpClientUtil4.sendPost(tempUrl,urlVariables);
		System.out.println(result);
	}
	
	/**
	 * 文件list信息
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void fileList() throws ClientProtocolException, IOException {
		Map<String ,String> urlVariables = new HashMap<String ,String>(); 
		urlVariables.put("publicKey", "37f3c77e12014181a30816392df0ad2c");
		urlVariables.put("relId", "1");
		urlVariables.put("channelSecretKey", "a296df1127c94ec2a04d2b64ff57fa43");
		String tempUrl =url+"fileList";
		System.out.println(tempUrl);
		String result= HttpClientUtil4.sendPost(tempUrl,urlVariables);
		System.out.println(result);
	}
	
	@Test
	public void start() throws ClientProtocolException, IOException {
		Map<String ,String> urlVariables = new HashMap<String ,String>(); 
		urlVariables.put("publicKey", "37f3c77e12014181a30816392df0ad2c");
		urlVariables.put("relId", "1");
		urlVariables.put("channelSecretKey", "a296df1127c94ec2a04d2b64ff57fa43");
		String tempUrl =url+"start";
		System.out.println(tempUrl);
		String result= HttpClientUtil4.sendPost(tempUrl,urlVariables);
		System.out.println(result);
	}
	
	/***
	 * 检查频道公共key
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void checkChannelSecretKey() throws ClientProtocolException, IOException {
		Map<String ,String> urlVariables = new HashMap<String ,String>(); 
		urlVariables.put("relId", "1");
		urlVariables.put("channelSecretKey", "a296df1127c94ec2a04d2b64ff57fa43");
		String tempUrl =url+"checkChannelSecretKey";
		
		//relId=1&channelSecretKey=a296df1127c94ec2a04d2b64ff57fa43
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