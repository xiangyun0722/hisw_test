<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单位节点管理</title>
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
		<li><a href="${ctx}/zjvideo/companyNodeServer/">单位节点列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/companyNodeServer/form?id=${companyNodeServer.id}">单位节点<shiro:hasPermission name="zjvideo:companyNodeServer:edit">${not empty companyNodeServer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:companyNodeServer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="companyNodeServer" action="${ctx}/zjvideo/companyNodeServer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">所属服务器:</label>
			<div class="controls">
                <sys:treeselect id="nodeServerGroup" name="nodeserverid" value="${companyNodeServer.nodeServer.id}" labelName="companyNodeServer.nodeServer.name" labelValue="${companyNodeServer.nodeServer.name} "
					title="服务器" url="/zjvideo/nodeServer/treeData" cssClass="input-xsmall required"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">所属单位:</label>
			<div class="controls">
                <sys:treeselect id="office" name="companyid" value="${companyNodeServer.office.id}" labelName="companyNodeServer.office.name" labelValue="${companyNodeServer.office.name} "
					title="单位" url="/sys/office/treeData" cssClass="input-xsmall required"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">策略类型：</label>
			<div class="controls">
				<form:input path="strategyType" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优选节点相关特征：</label>
			<div class="controls">
				<form:input path="optimizationNode" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:companyNodeServer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>