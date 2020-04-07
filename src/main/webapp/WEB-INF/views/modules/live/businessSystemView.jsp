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
		<li class="active"><a href="${ctx}/live/businessSystem/view?id=${businessSystem.id}">直播业务系统查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="businessSystem" action="${ctx}/live/businessSystem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">接入系统名称：</label>
			<div class="controls">
				 ${businessSystem.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图标：</label>
			<div class="controls">
				<button class="file" type="button" id="logoID" fileType="pic" value="${businessSystem.logo}"><span class="glyphicon glyphicon-plus"><span>添加</button>（只能上传图片）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公共key：</label>
			<div class="controls">
				 ${businessSystem.publicKey}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				${businessSystem.remarks}
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>