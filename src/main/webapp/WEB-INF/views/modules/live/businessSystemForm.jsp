<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直播业务系统管理</title>
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
		<li><a href="${ctx}/live/businessSystem/">直播业务系统列表</a></li>
		<li class="active"><a href="${ctx}/live/businessSystem/form?id=${businessSystem.id}">直播业务系统<shiro:hasPermission name="live:businessSystem:edit">${not empty businessSystem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="live:businessSystem:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="businessSystem" action="${ctx}/live/businessSystem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">系统名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图标：</label>
			<div class="controls">
				<button class="file" type="button" id="logoID" fileType="pic" value="${businessSystem.logo}"><span class="glyphicon glyphicon-plus"><span>添加</button>（只能上传图片）
			</div>
		</div>
		<c:if test="${businessSystem.id!=null}">
			<div class="control-group">
				<label class="control-label">公共key：</label>
				<div class="controls">
					<form:input path="publicKey" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="live:businessSystem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>
</body>
</html>