<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
/***
下載方法
***/
function down(str){
	window.open("${ctx}/business/documentInfo!downloadFile.action?changeFileName="+str);
}
/**
查看文档
**/
function viewDocument(filePath){
	$.post("${ctx}/business/documentSearch!isFileExists.action?filePath="+filePath,function(data){
		if (data=="TRUE") {
			art.dialog({
   				 time: 1,
   				 zIndex:50000
			});
			art.dialog.open('${ctx}/flexpaper.jsp?path='+filePath, {
				title: '文档',
				drag:false,
				padding:0,
				width:'680px',
				lock:true
			});
		}else if(data=="FALSE1"){
			art.dialog({
   				 time: 1,
   				 zIndex:50000,
   				 content: '加密文件,不可查看,请去下载!'
			});
			//art.dialog.tips('文件不存在!');
		}else if(data=="FALSE2"){
			art.dialog({
   				 time: 1,
   				 zIndex:50000,
   				 content: '请稍等!'
			});
			//art.dialog.tips('文件不存在!');
		}
	});
}

/***
查看历史信息
***/
function showHistory(projectId,annexType){
	$.post("${ctx}/business/projectTaskAnnex!showHistory.action?projectId="+projectId+"&annexType="+annexType,function(data){
		art.dialog({
//			title: '文档',
			id:'fileList',
			content :data,
			drag:false,
			zIndex:40000,
			padding:0,
			lock:true,
			cancel:""
		});
	});
}