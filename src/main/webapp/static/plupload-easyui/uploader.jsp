<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>视频文件上传</title>
    <link rel="stylesheet" href="${ctxStatic}/plupload-easyui/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css"></link>
    <%--百度静态资源公共库 <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script> --%>
	<script src="http://apps.bdimg.com/libs/jquery/1.8.3/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic}/plupload-easyui/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/plupload-easyui/js/plupload.dev.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plupload-easyui/js/moxie.js"></script>
    <script type="text/javascript" src="${ctxStatic}/plupload-easyui/js/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="${ctxStatic}/plupload-easyui/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
  <body style="padding: 0;margin: 0;">
  <input type="hidden" id="ckeckUploaderUrl" value="${pageContext.request.contextPath}/servlet/ckeckUploader">
<div id="uploader">&nbsp;</div>
<script type="text/javascript">
var files = [];
var errors = [];
var type = 'file';
var chunk = eval('${param.chunk}');
var projectId = eval('${param.projectId}');
var max_file_size = '2000mb';
//avi  mpg  wmv 3gp mov mp4 asf asx flv
var vod="avi,mpg,wmv,3gp,mov,mp4,f4v,asf,asx,flv";
var doc="zip,doc,docx,xls,xlsx,ppt,pptx";
var filters = {title : "Video files", extensions :vod};
//  {title : "Image files", extensions : "jpg,gif,png"},     
$("#uploader").pluploadQueue($.extend({
	runtimes : 'html5,flash,html4',
	url : '${pageContext.request.contextPath}/servlet/uploader',
	max_file_size : max_file_size,
	file_data_name:'file',
	filters : [filters],
	// Flash settings
	flash_swf_url : '${ctxStatic}/plupload-easyui/js/Moxie.swf',
	// Silverlight settings
	silverlight_xap_url : '${ctxStatic}/plupload-easyui/js/Moxie.xap',
	init:{
		FileUploaded:function(uploader,file,response){
			if(response.response){
				var rs = $.parseJSON(response.response);
				if(rs.status){
					files.push(file.name);
				}else{
					errors.push(file.name);
				}
			}
		},
		UploadComplete:function(uploader,fs){
			var e= errors.length ? ",失败"+errors.length+"个("+errors.join("、")+")。" : "。";
		     if(errors.length>0){
		         alert("上传失败！"+e);
             }else {
                 alert("上传完成！共" + fs.length + "个。成功" + files.length );
             }
			target.window("close");
		}
	}
},(chunk ? {chunk_size:'10mb'} : {})));
</script>
  </body>
</html>
