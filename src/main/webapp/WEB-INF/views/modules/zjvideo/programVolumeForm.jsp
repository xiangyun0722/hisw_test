<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>章节信息管理</title>
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
	</script>
</head>
<body>
	<!-- 用户自定义弹出窗口显示 -->
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/zjvideo/program">课程管理列表</a></li>
		<li><a href="${ctx}/zjvideo/programVolume/">章节信息列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/programVolume/form?id=${programVolume.id}">章节信息<shiro:hasPermission name="zjvideo:programVolume:edit">${not empty programVolume.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:programVolume:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="programVolume" action="${ctx}/zjvideo/programVolume/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">章节序号：</label>
			<div class="controls">
				<form:input path="volume" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">章节名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选择视频编号：</label>
			<div class="controls">
				<!-- <div id="videoId" name="videoId" href="javascript:" class="btn">添加</div> -->
				<sys:videoselect id="videoId" name="videoId" value="${programVolume.videoId}"/>
				<!-- <a id="relationButton" href="javascript:" class="btn">添加相关</a> -->
		<!-- 		<script type="text/javascript">
					$("#videoId").click(function(){
						top.$.jBox.open("iframe:${ctx}/zjvideo/videos/unUsedList?pageSize=8", "添加剧集",$(top.document).width()-220,$(top.document).height()-180,{
							buttons:{"确定":true}, loaded:function(h){
								$(".jbox-content", top.document).css("overflow-y","hidden");
							}
						});
					});
				</script> -->
			</div>
		</div>			
		<div class="control-group">
			<label class="control-label">详情：</label>
			<div class="controls">
				<form:textarea path="detail" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">课件附件：</label>
			<div class="controls">
				<form:hidden id="files" path="url" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="false"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">课件附件:</label>
			<div class="controls">
				<!-- dataType="json" maxNumber="100"  -->
				<button class="file"  type="button" id="urlID" dataType="json" fileType="doc" value="${programVolume.url}" >
					<span class="glyphicon glyphicon-plus">添加<span>
				</button>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>						
<%-- 		<div class="control-group">
			<label class="control-label">文件前缀：</label>
			<div class="controls">
				<form:input path="prefix" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件后缀：</label>
			<div class="controls">
				<form:input path="suffix" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>	 --%>	
		<%-- <div class="control-group">
			<label class="control-label">章节海报：</label>
			<div class="controls">
				<form:input path="picurl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">章节描述：</label>
			<div class="controls">
				<form:input path="detail" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">播放次数：</label>
			<div class="controls">
				<form:input path="palytimes" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">增加时间：</label>
			<div class="controls">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${programVolume.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">增加者：</label>
			<div class="controls">
				<form:input path="adder" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间：</label>
			<div class="controls">
				<input name="edittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${programVolume.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改者：</label>
			<div class="controls">
				<form:input path="editer" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">锁定标志：</label>
			<div class="controls">
				<form:input path="slock" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教师职称：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教师姓名：</label>
			<div class="controls">
				<form:input path="realname" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教师简介：</label>
			<div class="controls">
				<form:input path="profile" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ppt页面个数：</label>
			<div class="controls">
				<form:input path="pagesize" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">练习题目访问地址：</label>
			<div class="controls">
				<form:input path="exercise" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>	
</body>
</html>