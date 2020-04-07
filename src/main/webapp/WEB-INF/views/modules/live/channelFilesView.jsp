<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直播频道录制文件管理</title>
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
		<li><a href="${ctx}/live/channelFiles/">直播频道录制文件列表</a></li>
		<li class="active"><a href="${ctx}/live/channelFiles/view?id=${channelFiles.id}">直播频道录制文件查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="channelFiles" action="${ctx}/live/channelFiles/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">文件名：</label>
			<div class="controls">
				 ${channelFiles.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">直播频道id：</label>
			<div class="controls">
				 ${channelFiles.liveChannel.id}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件大小：</label>
			<div class="controls">
				 ${channelFiles.fileSize}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件地址：</label>
			<div class="controls">
				 ${channelFiles.filePath}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下载地址：</label>
			<div class="controls">
				 ${channelFiles.downloadUrl}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始录制时间：</label>
			<div class="controls">
				<input name="startDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${channelFiles.startDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束录制时间：</label>
			<div class="controls">
				<input name="endDatetime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${channelFiles.endDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总时长：</label>
			<div class="controls">
				 ${channelFiles.playTime}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				${channelFiles.remarks}
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>