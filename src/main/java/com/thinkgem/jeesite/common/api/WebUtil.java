/**
 * <p> * Title: 叶明开发的基础框架-基础信息库* </p>
 * <p> * Description: java工作者工作室* </p>
 * <p> * Copyright: Copyright (c) 2011-2013 * </p>
 * <p> * Company: java工作者工作室* </p>
 * @author 叶明（开发）
 * @version 0.1
 */
package com.thinkgem.jeesite.common.api;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 程序创建时间：2012-6-11 - 下午2:37:59
 * </p>
 * <p>
 * 程序开发者：叶明 (guming123416@163.com)
 * </p>
 * <p>
 * 程序修改者：
 * </p>
 * <p>
 * 程序修改时间：2012-6-11 - 下午2:37:59
 * </p>
 * <p>
 * 程序作用：
 * </p>
 * <p>
 * 1、
 * </p>
 * <p>
 * 2、
 * </p>
 * <p>
 * 程序修改：
 * </p>
 * <p>
 * 1、
 * </p>
 * <p>
 * 2、
 * </p>
 * <p>
 * 
 * @version 0.1
 *          </p>
 */
public class WebUtil {

	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	// public static SimpleDateFormat Date_format = new
	// SimpleDateFormat("yyyy-MM-dd");
	// public static SimpleDateFormat Date_time_format = new
	// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	// public static SimpleDateFormat time_format = new
	// SimpleDateFormat("hh:mm:ss");

	// private final static Logger LOGGER =
	// LoggerFactory.getLogger(WebUtil.class);
	/**
	 * 检查传递过来的字符串是不是空或者null
	 * 
	 * @param str
	 *            需要检查的字符串
	 * @return 如果是空，则返回true，否则返回false
	 */

	static {
		ConvertUtils.register(new UtilDateConverter(), Date.class);
	}

	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isBlank(StringBuilder str) {
		return StringUtils.isBlank(str);
	}
	
	
	@SuppressWarnings("serial")
	private final static List<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>(){{
		add(Long.class);
		add(Integer.class);
		add(String.class);
		add(Float.class);
		add(Short.class);
		add(Byte.class);
		add(Double.class);
		add(java.util.Date.class);
		add(java.sql.Date.class);
		add(java.sql.Timestamp.class);
	}};
	
	public final static boolean isPrimitive(Class<?> cls) {
		return cls.isPrimitive() || PrimitiveClasses.contains(cls);
	}

	/**
	 * 去除Null
	 * 
	 * @param str
	 * @return
	 * @author :叶明
	 * @dateTime :@2009-9-12 -- 上午06:04:04
	 */
	public static String replaceNull(String str) {
		return StringUtils.trimToEmpty(str);
	}

	/**
	 * 获取MD5代码
	 * 
	 * @param string
	 * @return
	 */
	public static String md5(String string) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] bytes = string.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			byte[] updateBytes = messageDigest.digest();
			int len = updateBytes.length;
			char myChar[] = new char[len * 2];
			int k = 0;
			for (int i = 0; i < len; i++) {
				byte byte0 = updateBytes[i];
				myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
				myChar[k++] = hexDigits[byte0 & 0x0f];
			}
			return new String(myChar).toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取客户端真实IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 将String字符转换成Long类型
	 * 
	 * @param str
	 * @return
	 * @auther:叶明
	 * @datetime: 2010-10-17 -- 上午11:38:05
	 * @version 1.0
	 */
	public static long parseLong(String str) {
		try {
			return NumberUtils.toLong(str);
		} catch (NumberFormatException ex) {
			return 0l;
		}
	}

	/**
	 * 将String类型字符转化成int
	 * 
	 * @param str
	 * @return
	 * @author:叶明
	 * @time: 2012-1-6 -- 下午1:39:36
	 */
	public static int parseInt(String str) {
		try {
			return NumberUtils.toInt(str);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	/**
	 * 获取网站的绝对路径
	 * 
	 * @param request
	 * @return
	 * @auther:叶明
	 * @datetime: 2010-10-17 -- 上午11:42:41
	 * @version 1.0
	 */
	public static String getWebRealPath(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(request.getServerName());
		if (request.getServerPort() != 80) {
			sb.append(":");
			sb.append(request.getServerPort());
		}
		return sb.toString();
	}

	/**
	 * 一个Hash算法
	 * 
	 * @param str
	 * @return
	 * @author:叶明
	 * @time: 2011-12-22 -- 下午12:22:56
	 * @since 1.0.3
	 */
	public static int RSHash(String str) {
		int b = 378551;
		int a = 63689;
		int hash = 0;
		for (int i = 0; i < str.length(); i++) {
			hash = hash * a + str.charAt(i);
			a = a * b;
		}
		return (hash & 0x7FFFFFFF);
	}

	/**
	 * 使用Hash对字符串进行分析
	 * 
	 * @param str
	 * @param num
	 * @return
	 * @author:叶明
	 * @time: 2011-12-22 -- 下午12:23:18
	 * @since 1.0.3
	 */
	public static int getHashSplit(String str, int num) {
		int hashcode = RSHash(str);
		return hashcode % num;
	}
 

	/**
	 * 属性比较类型.
	 */
	public enum MatchType {
		EQ, NE, LIKE, LT, GT, LE, GE, BETWEEN, IN, IS;
	}

	public static String changeToSql(String where) {
		StringBuilder sb = new StringBuilder();
		if (isBlank(where) == false) {
			String[] keyValues = where.split(";");
			for (int i = 0; i < keyValues.length; i++) {
				String[] keyValue = keyValues[i].split(":");
				if (keyValue[0].endsWith("<L>")) {
					sb.append(" and ").append(keyValue[0].substring(0, keyValue[0].length() - 3)).append("=").append(keyValue[1]);
				} else if (keyValue[0].endsWith("<I>")) {
					sb.append(" and ").append(keyValue[0].substring(0, keyValue[0].length() - 3)).append("=").append(keyValue[1]);
				} else if (keyValue[0].endsWith("<S>")) {
					sb.append(" and ").append(keyValue[0].substring(0, keyValue[0].length() - 3)).append("='").append(keyValue[1])
							.append("'");
				} else if (keyValue[0].endsWith("<N>")) {
					sb.append(" and ").append(keyValue[0].substring(0, keyValue[0].length() - 3)).append("!=").append(keyValue[1]);
				} else if (keyValue[0].endsWith("<D>")) {
					sb.append(" and ").append(keyValue[0].substring(0, keyValue[0].length() - 3)).append(">").append(keyValue[1]);
				} else if (keyValue[0].endsWith("<R>")) {
					sb.append(" and ").append(keyValue[0].substring(0, keyValue[0].length() - 3)).append(" in").append(keyValue[1]);
				} else if (keyValue[0].endsWith("<u>")) {
					keyValue[1] = keyValue[1].replace("/", ":");
					sb.append(" and ").append(keyValue[0].substring(0, keyValue[0].length() - 3)).append(">'").append(keyValue[1])
							.append("'");
				} else if (keyValue[0].endsWith("<d>")) {
					keyValue[1] = keyValue[1].replace("/", ":");
					sb.append(" and ").append(keyValue[0].substring(0, keyValue[0].length() - 3)).append("<'").append(keyValue[1])
							.append("'");
				} else if (keyValue[0].endsWith("<T>")) {
					sb.append(" and ").append(keyValue[1]);
				} else if (keyValue[0].endsWith("<O>")) {
					sb.append(" order by ").append(keyValue[1]);
				} else {
					String v = "";
					if (keyValue.length > 1) {
						v = keyValue[1];
					}
					sb.append(" and ").append(keyValue[0]).append(" like '%").append(v).append("%'");
				}
			}
		}
		return sb.toString();
	}

	public static String splitAndFilterString(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "...";
		}
		return str;
	}

	public static void renderText(StringBuilder text, HttpServletResponse response) {
		try {
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().print(text);
		} catch (IOException e) {
		} finally {
			try {
				response.getWriter().close();
			} catch (IOException e) {
			}
		}
	}

	public static void renderXml(StringBuilder text, HttpServletResponse response) {
		try {
			response.setContentType("text/xml; charset=utf-8");
			response.getWriter().print(text);
		} catch (IOException e) {
		} finally {
			try {
				response.getWriter().close();
			} catch (IOException e) {
			}
		}
	}

	public static void renderJson(Object obj, HttpServletResponse response) {
		try {
			// response.setContentType("text/json; charset=utf-8");
			response.getWriter().print(JSON.toJSONString(obj));
		} catch (Exception e) {
		} finally {
			try {
				response.getWriter().close();
			} catch (IOException e) {
			}
		}
	}

	public static String removeHtml(String input) {
		if ((input == null) || (input.trim().equals("")))
			return "";

		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(input);
		return m.replaceAll("").trim();
	}
 

	/*
	 * private static BeanUtilsBean beanUtilInstance = null; public static
	 * BeanUtilsBean getBeanUtilInstance(){ Co if(null == beanUtilInstance){
	 * beanUtilInstance = BeanUtilsBean.getInstance();
	 * beanUtilInstance.getConvertUtils().register(new UtilDateConverter(),
	 * java.util.Date.class); beanUtilInstance.getConvertUtils().register(new
	 * SqlTimestampConverter(), java.sql.Timestamp.class);
	 * beanUtilInstance.getConvertUtils().register(new SqlTimeConverter(),
	 * java.sql.Time.class); beanUtilInstance.getConvertUtils().register(new
	 * SqlDateConverter(), java.sql.Date.class); } return beanUtilInstance; }
	 */

	public static <T> T populateObject(Class<T> clazz, HttpServletRequest request) {
		try {
			T bean = clazz.newInstance();
			BeanUtilsBean.getInstance().populate(bean, request.getParameterMap());
			return bean;
		} catch (Exception e) {
			return null;
		}
	}

	

	/**
	 * 将html中一些特殊字符转换，然后保存到数据库中
	 * 
	 * @param str
	 *            需要转化的字符
	 * @return 转化完成后，返回的字符串
	 */
	public static String toHtml(String str) {
		String sReturn = "";

		if (isBlank(str) == false) {
			// 首先将中文符号替换
//			str = str.replaceAll("‘", "'");
//			str = str.replaceAll("’", "'");
//			str = str.replaceAll("《", "<");
//			str = str.replaceAll("》", ">");
			// 开始替换英文符号
			// str = str.replaceAll("&","&amp;");
			str = str.replaceAll("\r\n", "<br>");
			str = str.replaceAll("\n", "");
			// str = str.replaceAll("<","&lt;");
			// str = str.replaceAll(">","&gt;");
			str = str.replaceAll("\t", "    ");
			 str = str.replaceAll(" ","&nbsp;");
			str = str.replaceAll("'", "&#39;");
			 str = str.replaceAll("\"","&quot;");
			sReturn = str;
		}
		return sReturn;
	}

	/**
	 * 将html中一些特殊字符转换，然后保存到数据库中
	 * 
	 * @param str
	 *            需要转化的字符
	 * @return 转化完成后，返回的字符串
	 */
	public static String unHtml(String str) {
		String sReturn = "";
		if (isBlank(str) == false) {
			str = str.replaceAll("&quot;", "\"");
			str = str.replaceAll("&#92;", "\\");
			// str = str.replaceAll("&gt;",">");
			// str = str.replaceAll(" ", "\t");
			// str = str.replaceAll("&lt;","<");
			// str = str.replaceAll("&amp;","&");
			str = str.replaceAll("&nbsp;", " ");
			str = str.replaceAll("<br>", "\n");
			str = str.replaceAll("<br />", "\n");
			str = str.replaceAll("<br/>", "\n");
			sReturn = str;
		}
		return sReturn;
	}

	public static String clobToString(Clob clob) {
		String str = "";
		try {
			str = clob.getSubString(1l, (int) clob.length());
		} catch (Exception ex) {
			System.out.println("clob转化为string时异常：" + ex);
		}
		return str;
	}

	/**
	 * 根据时间以及类型格式化时间
	 * 
	 * @param val
	 *            需要格式化的时间字符串
	 * @param type
	 *            0：全部（时间+日期） ,1:日期 2：时间 其他为时间
	 * @return
	 */
	public static Date parseDate(String val, int type) {
		switch (type) {
		case 0:
			return parseDateTime(val);
		case 1:
			return parseDates(val);
		case 2:
			return parseTime(val);
		default:
			return parseDateTime(val);
		}
	}

	private static Date parseDateTime(String val) {
		SimpleDateFormat Date_time_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return Date_time_format.parse(val);
		} catch (Exception ex) {
			return null;
		}
	}

	public static Date parseDates(String val) {
		SimpleDateFormat Date_time_format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return Date_time_format.parse(val);
		} catch (Exception ex) {
			return null;
		}
	}

	private static Date parseTime(String val) {
		SimpleDateFormat time_format = new SimpleDateFormat("hh:mm:ss");
		try {
			return time_format.parse(val);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 首先使用全日期格式化，如果发生异常，在使用短日期格式化，否则返回null
	 * 
	 * @param val
	 * @return
	 */
	public static Date parseDate(String val) {
		Date date = parseDateTime(val);
		if (null == date) {
			date = parseDates(val);
		}
		return date;
	}

	public static String delHTMLTag(String htmlStr) {
		if (StringUtils.isBlank(htmlStr)) {
			return "";
		}
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签
		htmlStr = StringUtils.remove(htmlStr, "\t");
		htmlStr = StringUtils.remove(htmlStr, "\n");
		htmlStr = StringUtils.remove(htmlStr, "\r");
		return StringUtils.trimToEmpty(htmlStr); // 返回文本字符串
	}

	public static void main(String[] args) {

	}


    public static int getBetweenDays(Date startdate,Date enddate){
        int betweenDays = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startdate);
        c2.setTime(enddate);
        // 保证第二个时间一定大于第一个时间
        if(c1.after(c2)){
            c1 = c2;
            c2.setTime(startdate);
        }
        int betweenYears = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR);
        for(int i=0;i<betweenYears;i++){
//            int tmp=countDays(c1.get(Calendar.YEAR));
            betweenDays+=countDays(c1.get(Calendar.YEAR));
            c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1));
        }
        return betweenDays;
    }

    public static int countDays(int year) {
        int n=0;
        for (int i = 1; i <= 12; i++) {
            n += countDays(i,year);
        }
        return n;
    }

    public static int countDays(int month, int year){
        int count = -1;
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                count = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                count = 30;
                break;
            case 2:
                if(year % 4 == 0)
                    count = 29;
                else
                    count = 28;
                if((year % 100 ==0) & (year % 400 != 0))
                    count = 28;
        }
        return count;
    }
}
