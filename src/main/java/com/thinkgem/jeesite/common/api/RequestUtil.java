package com.thinkgem.jeesite.common.api;
/**
 * <p> * Title: 叶明开发的基础框架-基础信息库* </p>
 * <p> * Description: java工作者工作室* </p>
 * <p> * Copyright: Copyright (c) 2011-2013 * </p>
 * <p> * Company: java工作者工作室* </p>
 * @author 叶明（开发）
 * @version 0.1
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>程序创建时间：2012-6-11 - 下午2:43:33</p>
 * <p>程序开发者：叶明 (guming123416@163.com)</p>
 * <p>程序修改者： </p>
 * <p>程序修改时间：2012-6-11 - 下午2:43:33</p>
 * <p>程序作用：</p>
 * <p>1、</p>
 * <p>2、</p>
 * <p>程序修改：</p>
 * <p>1、</p>
 * <p>2、</p>
 * <p>@version 0.1</p>
 */
public class RequestUtil {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);
	
	/**
	 * 获取提交中的String类型的字符串
	 * @param sKey 表示指定的字符串
	 * @param request 表示有web页面传递过来的HttpServletRequest请求
	 * @return 如果匹配成功，则返回该数值，否则返回null
	 */
	public static String getString(String sKey,HttpServletRequest request) {
		String sReturn ="";

        if (request.getParameterValues(sKey) != null) {
            sReturn = "";
            String[] sArray = request.getParameterValues(sKey);
            for (int i = 0; i < sArray.length - 1; i++) {
                sReturn +=new String(sArray[i]) + ",";
            }
           sReturn += new String(sArray[sArray.length - 1]);

        }
		return sReturn;
	}
	
	/**
	 * 获取int类型的参数。增加默认
	 * @param cKey
	 * @param defVal
	 * @param request
	 * @return
	 */
	public static int getInt(String cKey,int defVal,HttpServletRequest request) {
		String cReturn = getStringByOther(cKey, request);
		return NumberUtils.toInt(cReturn,defVal);
	}
	
	/**
	 * 获取int类型参数
	 * @param cKey 表示指定的字符串
	 * @param request 表示有web页面传递过来的HttpServletRequest请求
	 * @return 如果匹配成功，则返回该数值，否则，返回0；
	 */
	public static int getInt(String cKey,HttpServletRequest request) {
		return getInt(cKey, 0, request);
	}
	
	public static long getLong(String cKey,long defVal,HttpServletRequest request) {
		String cReturn = getStringByOther(cKey, request);
		return NumberUtils.toLong(cReturn,defVal);
	}
	

	/**
	 * 获取long类型的变量发生错误
	 * @param cKey 表示指定的字符串
	 * @param request 表示有web页面传递过来的HttpServletRequest请求
	 * @return 如果匹配成功，则返回该数值，否则，返回0l；
	 */
	public static long getLong(String cKey,HttpServletRequest request) {
		return getLong(cKey, 0l, request);
	}
	
	public static double getDouble(String cKey,double defVal,HttpServletRequest request) {
		String cReturn = getStringByOther(cKey, request);
		return NumberUtils.toDouble(cReturn,defVal);
	}
	
	/**
	 * 获取double类型参数
	 * @param cKey  表示指定的字符串
	 * @param request 表示有web页面传递过来的HttpServletRequest请求
	 * @return 如果匹配成功，则返回该数值，否则，返回0.0；
	 */
	public static double getDouble(String cKey,HttpServletRequest request) {
		return getDouble(cKey, 0d, request);
	}
	
	public static float getFloat(String cKey,float defval,HttpServletRequest request) {
		String cReturn = getStringByOther(cKey, request);
		return NumberUtils.toFloat(cReturn,defval);
	}
	
	/**
	 * 获取float类型参数
	 * @param cKey 表示指定的字符串
	 * @param request 表示有web页面传递过来的HttpServletRequest请求
	 * @return 如果匹配成功，则返回该数值，否则，返回0.0f；
	 */
	public static float getFloat(String cKey,HttpServletRequest request) {
		return getFloat(cKey, 0f, request);
	}
	
	/**
	 * 通用的方法，可以获取int、float、long以及doblue
	 * @param cKey 表示指定的字符串
	 * @param request 表示有web页面传递过来的HttpServletRequest请求
	 * @return 如果匹配成功，则返回该数值，否则返回null
	 */
	private static String getStringByOther(String cKey,HttpServletRequest request) {
		String cReturn =null;
		if(request.getParameter(cKey)!=null) {
			String sV = request.getParameter(cKey);
			if(sV !=null) {
				sV = sV.trim();
				for(int i=0;i<sV.length();i++) {
					int myASCII = sV.charAt(i);
					if (!(myASCII >= 45 && myASCII <= 57)){
						return null;
					}else{
						if(myASCII==47){
							return null;
						}
					}
				}
				cReturn =sV;
			}
		}
		return cReturn;
	}

	public static Date getDate(String cKey,HttpServletRequest request) {
		Date date = null;
		String cReturn = getStringByOther(cKey, request);
		if(cReturn!=null) {
			try {
				date =DateUtils.parseDate(cReturn,"yyyy-MM-dd");
			} catch (Exception ex) {
				logger.error("get date type error："+ex.getMessage());
				logger.debug(null,ex);
			}
		}
		return date;
	}
	
	public static Date getDatetime(String cKey,HttpServletRequest request) {
		Date date = null;
		String cReturn = getStringByOther(cKey, request);
		if(cReturn!=null) {
			try {
				date =DateUtils.parseDate(cReturn,"yyyy-MM-dd HH:mm:ss");
			} catch (Exception ex) {
				logger.error("get date type error："+ex.getMessage());
				logger.debug(null,ex);
			}
		}
		return date;
	}
	/**
	 * 获取IP地址
	 * @param req
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest req) {
		String ip = req.getHeader("X-Forwarded-For");
		if(StringUtils.isNotBlank(ip)){
			String[] ips = StringUtils.split(ip,',');
			if(ips!=null){
				for(String tmpip : ips){
					if(StringUtils.isBlank(tmpip))
						continue;
					tmpip = tmpip.trim();
					if(isIPAddr(tmpip) && !tmpip.startsWith("10.") && !tmpip.startsWith("192.168.") && !"127.0.0.1".equals(tmpip)){
						return tmpip.trim();
					}
				}
			}
		}
		ip = req.getHeader("x-real-ip");
		if(isIPAddr(ip))
			return ip;
		ip = req.getRemoteAddr();
		if(ip.indexOf('.')==-1)
			ip = "127.0.0.1";
		return ip;
	}

	/**
	 * 获取HTTP端口
	 * @param req
	 * @return
	 * @throws MalformedURLException
	 */
	public static int getHttpPort(HttpServletRequest req) {
		try {
			return new URL(req.getRequestURL().toString()).getPort();
		} catch (MalformedURLException excp) {
			return 80;
		}
	}

	/**
	 * @param addr
	 * @return
	 */
	private static boolean isIPAddr(String addr) {
		if(StringUtils.isEmpty(addr))
			return false;
		String[] ips = StringUtils.split(addr, '.');
		if(ips.length != 4)
			return false;
		try{
			int ipa = Integer.parseInt(ips[0]);
			int ipb = Integer.parseInt(ips[1]);
			int ipc = Integer.parseInt(ips[2]);
			int ipd = Integer.parseInt(ips[3]);
			return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0
					&& ipc <= 255 && ipd >= 0 && ipd <= 255;
		}catch(Exception e){}
		return false;
	}
	
	/**
	 * 获取用户访问URL中的根域名
	 * 例如: www.dlog.cn -> dlog.cn
	 * @param host
	 * @return
	 */
	public static String getDomainOfServerName(String host){
		if(isIPAddr(host))
			return null;
		String[] names = StringUtils.split(host, '.');
		int len = names.length;
		if(len==1) return null;
		if(len==3){
			return makeup(names[len-2],names[len-1]);
		}
		if(len>3){
			String dp = names[len-2];
			if(dp.equalsIgnoreCase("com")||dp.equalsIgnoreCase("gov")||dp.equalsIgnoreCase("net")||dp.equalsIgnoreCase("edu")||dp.equalsIgnoreCase("org"))
				return makeup(names[len-3],names[len-2],names[len-1]);
			else
				return makeup(names[len-2],names[len-1]);
		}
		return host;
	}

	/**
	 * 判断是否为搜索引擎
	 * @param req
	 * @return
	 */
	public static boolean isRobot(HttpServletRequest req){
		String ua = req.getHeader("user-agent");
		if(StringUtils.isBlank(ua)) return false;
		return (ua != null
				&& (ua.indexOf("Baiduspider") != -1 || ua.indexOf("Googlebot") != -1
						|| ua.indexOf("sogou") != -1
						|| ua.indexOf("sina") != -1
						|| ua.indexOf("iaskspider") != -1
						|| ua.indexOf("ia_archiver") != -1
						|| ua.indexOf("Sosospider") != -1
						|| ua.indexOf("YoudaoBot") != -1
						|| ua.indexOf("yahoo") != -1 
						|| ua.indexOf("yodao") != -1
						|| ua.indexOf("MSNBot") != -1
						|| ua.indexOf("spider") != -1
						|| ua.indexOf("Twiceler") != -1
						|| ua.indexOf("Sosoimagespider") != -1
						|| ua.indexOf("naver.com/robots") != -1
						|| ua.indexOf("Nutch") != -1
						|| ua.indexOf("spider") != -1));	
	}

	/**
	 * @return
	 */
	private static String makeup(String...ps){
		StringBuilder s = new StringBuilder();
		for(int idx = 0; idx < ps.length; idx++){
			if(idx > 0)
				s.append('.');
			s.append(ps[idx]);
		}
		return s.toString();
	}


    /**
     * 从请求中直接获取对象
     * @param beanClass
     * @param request
     * @param <T>
     * @return
     */
    public static  <T> T  form(Class<T> beanClass,HttpServletRequest request) {
       return WebUtil.populateObject(beanClass, request);
    }
	
}
