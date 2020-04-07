<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议操作记录管理</title>
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
		<li><a href="${ctx}/meeting/meetingMeetingInfo/">会议操作记录列表</a></li>
		<li class="active"><a href="${ctx}/meeting/meetingMeetingInfo/form?id=${meetingMeetingInfo.id}">会议操作记录<shiro:hasPermission name="meeting:meetingMeetingInfo:edit">${not empty meetingMeetingInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="meeting:meetingMeetingInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="meetingMeetingInfo" action="${ctx}/meeting/meetingMeetingInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">会议名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100"  cssClass="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">简介：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="6"  class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主讲人手机号：</label>
			<div class="controls">
				<form:input path="present" htmlEscape="false" maxlength="64" cssClass="input-xsmall required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会议时长（分钟）：</label>
			<div class="controls">
				<form:input path="length" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">封面：</label>
			<div class="controls">
				<button class="file"  type="button" id="imgID" fileType="pic" value="${meetingMeetingInfo.img}" >
					<span class="glyphicon glyphicon-plus">添加<span>
				</button>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片介绍：</label>
			<div class="controls">
				<form:input path="imgs" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">讲稿:</label>
			<div class="controls">
				<!-- dataType="json" maxNumber="100"  -->
				<button class="file"  type="button" id="pptID" dataType="json" fileType="doc" value="${meetingMeetingInfo.ppt}" >
					<span class="glyphicon glyphicon-plus">添加<span>
				</button>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
	<%-- 	<div class="control-group">
			<label class="control-label">讲稿UUID：</label>
			<div class="controls">
				<form:input path="pptUuid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">录播视频：</label>
			<div class="controls">
				<form:input path="video" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会议类型：</label>
			<div class="controls">
		 	<form:select path="type" class="input-xlarge ">
				<form:options items="${fns:getDictList('meeting_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属频道：</label>
			<div class="controls">
				<form:select path="meetingLiveChannel.id" class="input-xlarge ">
					<form:options items="${meetingLiveChannelList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${meetingMeetingInfo.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${meetingMeetingInfo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参会人数上限：</label>
			<div class="controls">
				<form:input path="maxNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录类型：</label>
			<div class="controls">
			 	<form:select path="loginType" class="input-xlarge ">
					<form:options items="${fns:getDictList('meeting_login_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="meeting:meetingMeetingInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>
</body>
</html>