<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直播频道管理</title>
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
		<li><a href="${ctx}/meeting/meetingLiveChannel/">直播频道列表</a></li>
		<li class="active"><a href="${ctx}/meeting/meetingLiveChannel/form?id=${meetingLiveChannel.id}">直播频道<shiro:hasPermission name="meeting:meetingLiveChannel:edit">${not empty meetingLiveChannel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="meeting:meetingLiveChannel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="meetingLiveChannel" action="${ctx}/meeting/meetingLiveChannel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">频道logo：</label>
			<div class="controls">
				<button class="file"  type="button" id="logoID" fileType="pic" value="${meetingLiveChannel.logo}" >
					<span class="glyphicon glyphicon-plus">添加<span>
				</button>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">rtmp高清：</label>
			<div class="controls">
				<form:input path="rtmpHd" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">rtmp标清：</label>
			<div class="controls">
				<form:input path="rtmpSd" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">rtmp普清：</label>
			<div class="controls">
				<form:input path="rtmpNd" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">rtmp声音：</label>
			<div class="controls">
				<form:input path="rtmpVn" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">hls高清：</label>
			<div class="controls">
				<form:input path="hlsHd" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">hls标清：</label>
			<div class="controls">
				<form:input path="hlsSd" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">hls普清：</label>
			<div class="controls">
				<form:input path="hlsNd" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">hls声音：</label>
			<div class="controls">
				<form:input path="hlsVn" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
			 	<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('meeting_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</div>			
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="meeting:meetingLiveChannel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>
</body>
</html>