<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>频道管理管理</title>
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
		<li><a href="${ctx}/zjvideo/liveing/">频道管理列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/liveing/form?id=${liveing.id}">频道管理<shiro:hasPermission name="zjvideo:liveing:edit">${not empty liveing.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:liveing:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="liveing" action="${ctx}/zjvideo/liveing/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">频道名称：</label>
			<div class="controls">
				<form:input path="livename" htmlEscape="false" maxlength="150" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">频道号：</label>
			<div class="controls">
				<form:input path="livenum" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">播放地址：</label>
			<div class="controls">
				<form:input path="playurl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">频道logo：</label>
			<div class="controls">
				<button class="file" type="button" id="picurlID" fileType="pic" value="${liveing.picurl}" ><span class="glyphicon glyphicon-plus"><span>添加</button>
				<span class="help-inline"><font color="red">*</font></span>						
			</div>					
		</div>
		<div class="control-group">
			<label class="control-label">在线：</label>
			<div class="controls">
				<form:select path="isonline" class="input-xlarge ">
					<form:options items="${fns:getDictList('liveing_isonline_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>					
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">viewtimes：</label>
			<div class="controls">
				<form:input path="viewtimes" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">增加时间：</label>
			<div class="controls">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${liveing.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间：</label>
			<div class="controls">
				<input name="edittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${liveing.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
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
			<shiro:hasPermission name="zjvideo:liveing:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>		
</body>
</html>