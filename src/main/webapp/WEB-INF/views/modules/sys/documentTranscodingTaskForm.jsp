<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档转码任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/documentTranscodingTask/">文档转码任务列表</a></li>
		<li class="active"><a href="${ctx}/sys/documentTranscodingTask/form?id=${documentTranscodingTask.id}">文档转码任务<shiro:hasPermission name="sys:documentTranscodingTask:edit">${not empty documentTranscodingTask.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:documentTranscodingTask:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="documentTranscodingTask" action="${ctx}/sys/documentTranscodingTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">处理主机IP：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="dealHostIp" htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label">转码任务编号：</label>
			<div class="controls">
				<form:input path="businessId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">文档名称：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="originalName" htmlEscape="false" maxlength="255" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label">文档文件：</label>
			<div class="controls">
				<!-- dataType="json" maxNumber="100" -->
				<button class="file"  type="button" id="fileJsonID" dataType="json" fileType="doc" value="${documentTranscodingTask.fileJson}" >
					<span class="glyphicon glyphicon-plus">添加<span>
				</button>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">转码状态：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:select path="status" class="input-xlarge "> --%>
<%-- 					<form:option value="" label=""/> --%>
<%-- 					<form:options items="${fns:getDictList('transcoding_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
<%-- 				</form:select> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">总页数：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="totalPageNumber" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">一个页面一个swf标示(0：一个文件一个swf，1：一个页面一个swf文件）：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="onePageOneSwfFlag" htmlEscape="false" maxlength="1" class="input-xlarge  digits"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">swfs文件地址：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="swfsPath" htmlEscape="false" maxlength="255" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">单个swf文件地址：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="swfPath" htmlEscape="false" maxlength="255" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">图片地址：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="imagesPath" htmlEscape="false" maxlength="255" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">js文件：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="jsPath" htmlEscape="false" maxlength="255" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">子线程总数：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="childThreadTotal" htmlEscape="false" maxlength="2" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">子线程成功数：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="childThreadSuccess" htmlEscape="false" maxlength="2" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">子线程失败数：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="childThreadFaill" htmlEscape="false" maxlength="2" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
<%-- 		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="sys:documentTranscodingTask:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>
</body>
</html>