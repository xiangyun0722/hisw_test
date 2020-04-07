package com.thinkgem.jeesite.modules.sys.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/***
 * 上传文件的信息
 * @author twk
 */
public class UploadFileInfo {
	
	/***
	 * 字符串转换成 对象
	 * @param jsonBeanString
	 * @return
	 */
	public static UploadFileInfo StrToBean(String jsonBeanString){
		UploadFileInfo  uploadFileInfo = JSONObject.parseObject(jsonBeanString, UploadFileInfo.class);
		return uploadFileInfo;
	}
	/***
	 * 字符串转换成 list bean 对象。 字符串必须是以json 类型的字符串拼接在一起。
	 * @param jsonBeanString
	 * @return
	 */
	public static List<UploadFileInfo> StrToBeanList(String jsonBeanString){
		List<UploadFileInfo>  uploadFileInfoList = new ArrayList<UploadFileInfo>();
		jsonBeanString = StringUtils.replace(jsonBeanString, "},{", "};{");
		String[] jsonInfos = StringUtils.split(";");
		for (String json : jsonInfos) {
			uploadFileInfoList.add(StrToBean(json));
		}
		return uploadFileInfoList;
	}
	
	public String fileName;//最新文件名
	public String oldFileName;//原始文件名称
	public String url;//url的地址
	public String filePath;//文件绝对路径
	public Long length;//文件长度
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "UploadFileInfo [fileName=" + fileName + ", oldFileName="
				+ oldFileName + ", url=" + url + ", filePath=" + filePath
				+ ", length=" + length + "]";
	}
}
