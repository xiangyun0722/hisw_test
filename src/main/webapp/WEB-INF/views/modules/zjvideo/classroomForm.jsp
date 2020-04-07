<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教室管理</title>
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
		<li><a href="${ctx}/zjvideo/classroom/">教室列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/classroom/form?id=${classroom.id}">教室<shiro:hasPermission name="zjvideo:classroom:edit">${not empty classroom.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:classroom:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="classroom" action="${ctx}/zjvideo/classroom/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">教室号：</label>
			<div class="controls">
				<form:input path="classnumber" htmlEscape="false" maxlength="255" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">校区：</label>
			<div class="controls">
				<form:input path="spart" htmlEscape="false" maxlength="255" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教室名称：</label>
			<div class="controls">
				<form:input path="rname" htmlEscape="false" maxlength="255" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">所在位置：</label>
			<div class="controls">
				<form:input path="position" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">教室类型：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">教室人数：</label>
			<div class="controls">
				<form:input path="room" htmlEscape="false" maxlength="11" class="required"/>
				<span class="help-inline"><font color="red">*(例如:500)</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:classroom:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>