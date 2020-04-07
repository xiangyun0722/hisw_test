<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直播频道录制文件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/live/channelFiles/">直播频道录制文件列表</a></li>
		<shiro:hasPermission name="live:channelFiles:edit"><li><a href="${ctx}/live/channelFiles/form">直播频道录制文件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="channelFiles" action="${ctx}/live/channelFiles/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>直播频道id：</label>
				<form:input path="liveChannel.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>文件地址：</label>
				<form:input path="filePath" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>下载地址：</label>
				<form:input path="downloadUrl" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文件名</th>
				<th>直播频道id</th>
				<th>文件大小</th>
				<th>文件地址</th>
				<th>下载地址</th>
				<th>开始录制时间</th>
				<th>结束录制时间</th>
				<th>总时长</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="live:channelFiles:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="channelFiles">
			<tr>
				<td><a href="${ctx}/live/channelFiles/form?id=${channelFiles.id}">
					${channelFiles.name}
				</a></td>
				<td>
					${channelFiles.liveChannel.id}
				</td>
				<td>
					<fmt:formatNumber value="${channelFiles.fileSize/1024/1024}" pattern="#.00" minFractionDigits='0' />M
				</td>
				<td>
					${channelFiles.filePath}
				</td>
				<td>
					${channelFiles.downloadUrl}
				</td>
				<td>
					<fmt:formatDate value="${channelFiles.startDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${channelFiles.endDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${channelFiles.playTime}
				</td>
				<td>
					<fmt:formatDate value="${channelFiles.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${channelFiles.remarks}
				</td>
				<shiro:hasPermission name="live:channelFiles:edit">
				<td>
    				<a href="${ctx}/live/channelFiles/form?id=${channelFiles.id}">修改</a>
					<a href="${ctx}/live/channelFiles/delete?id=${channelFiles.id}" onclick="return confirmx('确认要删除该直播频道录制文件吗？', this.href)">删除</a>
					<shiro:hasPermission name="live:channelFiles:view">
					<a href="${ctx}/live/channelFiles/view?id=${channelFiles.id}">查看</a>
					</shiro:hasPermission>
				</td>
				</shiro:hasPermission>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>