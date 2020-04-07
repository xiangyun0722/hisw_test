<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直播频道管理</title>
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
		<li><a href="${ctx}/live/liveLiveChannel/">直播频道列表</a></li>
		<li class="active"><a href="${ctx}/live/liveLiveChannel/view?id=${liveLiveChannel.id}">直播频道查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="liveLiveChannel" action="${ctx}/live/liveLiveChannel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				 ${liveLiveChannel.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">外部系统关联id：</label>
			<div class="controls">
				 ${liveLiveChannel.relId}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">频道秘钥：</label>
			<div class="controls">
				 ${liveLiveChannel.channelSecretKey}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推送rtmp流地址：</label>
			<div class="controls">
				 ${liveLiveChannel.pushRtmpUrl}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转码参数：</label>
			<div class="controls">
				 ${liveLiveChannel.transcodingParas}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">频道logo：</label>
			<div class="controls">
				 ${liveLiveChannel.logo}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">rtmp播放地址：</label>
			<div class="controls">
				 ${liveLiveChannel.rtmpUrl}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">hls播放地址：</label>
			<div class="controls">
				 ${liveLiveChannel.hlsUrl}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态(上线、下线)：</label>
			<div class="controls">
				 ${liveLiveChannel.status}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				${liveLiveChannel.remarks}
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>