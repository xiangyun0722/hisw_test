<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.*"%>
<html>
<head>
	<title>一级分类管理</title>
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
		<%   
	       java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	       java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
	       String str_date = formatter.format(currentTime); //将日期时间格式化 
		%> 			
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/zjvideo/classType/">一级分类列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/classType/form?id=${classType.id}">一级分类<shiro:hasPermission name="zjvideo:classType:edit">${not empty classType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:classType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="classType" action="${ctx}/zjvideo/classType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类别名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">子分类数量：</label>
			<div class="controls">
				<form:input path="counts" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">增加时间：</label>
			<div class="controls">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<%=str_date%>" pattern="yyyy-MM-dd HH:mm:ss" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">最后修改 时间：</label>
			<div class="controls">
				<input name="edittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${classType.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">状态标志位0:正常，1：锁定：</label>
			<div class="controls">
				<form:input path="slock" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:classType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>