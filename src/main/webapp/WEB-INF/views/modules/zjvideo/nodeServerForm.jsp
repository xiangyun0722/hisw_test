<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点服务器管理</title>
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
		<li><a href="${ctx}/zjvideo/nodeServer/">节点服务器列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/nodeServer/form?id=${nodeServer.id}">节点服务器<shiro:hasPermission name="zjvideo:nodeServer:edit">${not empty nodeServer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:nodeServer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="nodeServer" action="${ctx}/zjvideo/nodeServer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">服务器名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">源站播放地址：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*(例如：http://www.hisw.cn:8081/vod ,此参数会用作拼接视频的播放地址 )</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">cdn播放地址：</label>
			<div class="controls">
				<form:input path="cdnurl" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IP地址：</label>
			<div class="controls">
				<form:input path="ipaddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负载上限：</label>
			<div class="controls">
				<form:input path="maxload" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网络负载：</label>
			<div class="controls">
				<form:input path="loadnum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在线状态：</label>
			<div class="controls">
				<form:select path="onlineFlag" class="input-xlarge ">
					<form:options items="${fns:getDictList('server_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机房位置：</label>
			<div class="controls">
				<form:input path="location" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优选参数：</label>
			<div class="controls">
				<form:input path="optimalConfig" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">网络运营商：</label>
			<div class="controls">
				<form:select path="carrier" class="input-medium">
					<form:options items="${fns:getDictList('carrier_network')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:nodeServer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>