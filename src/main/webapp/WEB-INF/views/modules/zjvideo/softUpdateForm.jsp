<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>软件更新管理</title>
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
		<li><a href="${ctx}/zjvideo/softUpdate/">软件更新列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/softUpdate/form?id=${softUpdate.id}">软件更新<shiro:hasPermission name="zjvideo:softUpdate:edit">${not empty softUpdate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:softUpdate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="softUpdate" action="${ctx}/zjvideo/softUpdate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">平台信息：</label>
			<div class="controls">
				<form:select path="platform" class="input-xlarge ">
					<form:options items="${fns:getDictList('soft_platform_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">externalurl：</label>
			<div class="controls">
				<form:input path="externalurl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">显示版本：</label>
			<div class="controls">
				<form:input path="filename" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">升级模式：</label>
			<div class="controls">
				<form:select path="forceflag" class="input-xlarge ">
					<form:options items="${fns:getDictList('soft_update_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主版本号：</label>
			<div class="controls">
				<form:input path="mainversion" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">次版本号：</label>
			<div class="controls">
				<form:input path="minorversion" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">android版本号：</label>
			<div class="controls">
				<form:input path="androidversion" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">detail：</label>
			<div class="controls">
				<form:input path="detail" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">文件路径：</label>
			<div class="controls">
				<form:input path="fileurl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">增加时间：</label>
			<div class="controls">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${softUpdate.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">最后修改 时间：</label>
			<div class="controls">
				<input name="edittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${softUpdate.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
	<%-- 	<div class="control-group">
			<label class="control-label">状态标志位0:正常，1：锁定：</label>
			<div class="controls">
				<form:input path="slock" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:softUpdate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>