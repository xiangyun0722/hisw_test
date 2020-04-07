<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.*"%>
<html>
<head>
	<title>视频管理管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/plupload-easyui/bootstrap/easyui.css" type="text/css"></link>
	<script type="text/javascript" src="${ctxStatic}/plupload-easyui/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	/**
	 * 创建上传窗口 公共方法
	 * @param chunk 是否分割大文件
	 * @param callBack 上传成功之后的回调
	 */
	function Uploader(chunk,callBack){
		var addWin = $('<div style="overflow: hidden;"/>');
		var upladoer = $('<iframe/>');
		upladoer.attr({'src':'${ctxStatic}/plupload-easyui/uploader.jsp?chunk='+chunk+"&projectId="+$("#projectId").val(),width:'100%',height:'100%',frameborder:'0',scrolling:'no'});
		addWin.window({
			title:"上传视频文件（支持断点续传），支持avi,mpg,wmv,3gp,mov,mp4,f4v,asf,asx,flv",
			height:350,
			width:550,
			minimizable:false,
			modal:true,
			collapsible:false,
			maximizable:false,
			resizable:false,
			content:upladoer,
			onClose:function(){
				var fw = GetFrameWindow(upladoer[0]);
				var files = fw.files;
				$(this).window('destroy');
				callBack.call(this,files);
			},
			onOpen:function(){
				var target = $(this);
				setTimeout(function(){
					var fw = GetFrameWindow(upladoer[0]);
					fw.target = target;
				},100);
			}
		});
	}
	
	/**
	 * 根据iframe对象获取iframe的window对象
	 * @param frame
	 * @returns {Boolean}
	 */
	function GetFrameWindow(frame){
		return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
	}
	
	function makerUpload(chunk){
		//请先选择所属项目。
		 if($("#projectId").val()==null || $("#projectId").val() == ""){
			alert("请选择所属项目，然后上传视频！");
			return;
		 }
		 Uploader(chunk,function(files){
			 if(files && files.length>0){
				 $("#res").html("成功上传：<br/>"+files.join("<br/>"));
			 }
		 });
	}
	 
	$(document).ready(function() {
		//$("#name").focus();
		$("#inputForm").validate({
			submitHandler: function(form){
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
	<%   
       java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
       java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
       String str_date = formatter.format(currentTime); //将日期时间格式化 
	%>
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/zjvideo/videos/">视频管理列表</a></li> --%>
		<li class="active"><a href="${ctx}/zjvideo/videos/form?id=${videos.id}">视频<shiro:hasPermission name="zjvideo:videos:edit">${not empty videos.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:videos:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="videos" action="${ctx}/zjvideo/videos/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">所属项目:</label>
			<div class="controls">
 				<select name=projectid id="projectId" style="width: 118px">  
	                 <c:forEach items="${projectList}" var="projectItem">  
		                 <c:if test="${projectItem.name == fns:getConfig('gzckProjectName')}" > 
		                     <option selected="selected" value="${projectItem.id}" >  
		                         ${projectItem.name}  
		                     </option> 
		                 </c:if>  
						 <c:if test="${projectItem.name != fns:getConfig('gzckProjectName')}">
	                		 <option value="${projectItem.id}">
	                		 	${projectItem.name}
	                		  </option>
	            		 </c:if>	                    
	                 </c:forEach>  
             	</select>  					
			</div>
	   </div>
	   <div class="control-group">
 			<label class="control-label">视频文件：</label>
			<div class="controls">
				<%-- <form:hidden id="flash" path="source" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="flash" type="flash" uploadPath="/video" selectMultiple="false"/> --%>
<%-- 				<button class="file" type="button" id="sourceID" value="${videos.source}" ><span class="glyphicon glyphicon-plus"><span>添加</button> --%>
				<a class="easyui-linkbutton" href="javascript:makerUpload(true)">文件上传</a><hr/>
				<div id="res"></div>
			</div>
	    </div>
<%-- 		<div class="control-group">
			<label class="control-label">所属单位:</label>
			<div class="controls">
                <sys:treeselect id="project" name="companyid" value="${project.project.id}" labelName="project.name" labelValue="${project.project.name} "
					title="单位" url="/sys/project/treeData" cssClass="input-xsmall required"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>			    	
		<%-- <div class="control-group">
			<label class="control-label">视频模板:</label>
			<div class="controls">
				<form:checkboxes path="templateId" items="${templates}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	  --%>  
		<%-- <div class="control-group">
			<label class="control-label">视频名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频源地址：</label>
			<div class="controls">
				<form:input path="source" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
				<form:input path="clazzid" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签：</label>
			<div class="controls">
				<form:input path="taglist" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">封面图片：</label>
			<div class="controls">
				<form:hidden id="nameImage" path="picurl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>			
		</div>
		<div class="control-group">
			<label class="control-label">时长：</label>
			<div class="controls">
				<form:input path="length" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频类型：</label>
			<div class="controls">
				<form:input path="videotype" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频相对路径：</label>
			<div class="controls">
				<form:input path="path" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频编码格式：</label>
			<div class="controls">
				<form:input path="videocodec" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频大小：</label>
			<div class="controls">
				<form:input path="fileszie" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">码率：</label>
			<div class="controls">
				<form:input path="avgrate" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">FLV,F4V,MP4,3GP,TS,AAC,MP3,VP6,VP8：</label>
			<div class="controls">
				<form:input path="format" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件码流：</label>
			<div class="controls">
				<form:input path="videorate" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">帧率：</label>
			<div class="controls">
				<form:input path="fps" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宽度：</label>
			<div class="controls">
				<form:input path="width" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高度：</label>
			<div class="controls">
				<form:input path="height" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">声音采样率：</label>
			<div class="controls">
				<form:input path="audiocodec" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">声音码率：</label>
			<div class="controls">
				<form:input path="audiorate" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采样率：</label>
			<div class="controls">
				<form:input path="samplingrate" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">质量格式：</label>
			<div class="controls">
				<form:input path="quality" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">HTTP,RTMP,RTSP,HTTP_STREAM,MMS：</label>
			<div class="controls">
				<form:input path="protocol" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IPAD,ANDROID3,ANDROID2,ANDROID,ANDROID1,HTML5,FLASH：</label>
			<div class="controls">
				<form:input path="terminaltype" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频分辨率：</label>
			<div class="controls">
				<form:input path="resolution" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">媒体纵横比：</label>
			<div class="controls">
				<form:input path="aspectratioid" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">状态 ：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('program_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核失败原因：</label>
			<div class="controls">
				<form:input path="auditinfo" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">增加时间：</label>
			<div class="controls">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<%=str_date%>" pattern="yyyy-MM-dd HH:mm:ss"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">增加者：</label>
			<div class="controls">
				<form:input path="adder" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">最后修改时间：</label>
			<div class="controls">
				<input name="edittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${videos.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改者：</label>
			<div class="controls">
				<form:input path="editer" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">转码状态：</label>
			<div class="controls">
				<form:input path="convertstatus" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转码描述：</label>
			<div class="controls">
				<form:input path="convertmsg" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">转码所用时间（秒）：</label>
			<div class="controls">
				<form:input path="convertusetime" htmlEscape="false" maxlength="30" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转码开始时间：</label>
			<div class="controls">
				<input name="convertstarttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${videos.convertstarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">锁定标志：</label>
			<div class="controls">
				<form:input path="slock" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">mp4视频路径：</label>
			<div class="controls">
				<form:input path="mp4path" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:videos:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		 --%>
	</form:form>
<%-- <%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>	 --%>
</body>
</html>