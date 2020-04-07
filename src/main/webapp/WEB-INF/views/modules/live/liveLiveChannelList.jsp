<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直播频道管理</title>
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
		<li class="active"><a href="${ctx}/live/liveLiveChannel/">直播频道列表</a></li>
<%-- 	<shiro:hasPermission name="live:liveLiveChannel:edit"><li><a href="${ctx}/live/liveLiveChannel/form">直播频道添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="liveLiveChannel" action="${ctx}/live/liveLiveChannel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>外部系统关联id：</label>
				<form:input path="relId" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>频道秘钥：</label>
				<form:input path="channelSecretKey" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>推送rtmp流地址：</label>
				<form:input path="pushRtmpUrl" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>rtmp播放地址：</label>
				<form:input path="rtmpUrl" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>hls播放地址：</label>
				<form:input path="hlsUrl" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>外部系统关联id</th>
				<th>频道秘钥</th>
				<th>推送rtmp流地址</th>
				<th>rtmp播放地址</th>
				<th>hls播放地址</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="live:liveLiveChannel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveLiveChannel">
			<tr>
				<td><a href="${ctx}/live/liveLiveChannel/form?id=${liveLiveChannel.id}">
					${liveLiveChannel.name}
				</a></td>
				<td>
					${liveLiveChannel.relId}
				</td>
				<td>
					${liveLiveChannel.channelSecretKey}
				</td>
				<td>
					${liveLiveChannel.pushRtmpUrl}
				</td>
				<td>
					${liveLiveChannel.rtmpUrl}
				</td>
				<td>
					${liveLiveChannel.hlsUrl}
				</td>
				<td>
					<fmt:formatDate value="${liveLiveChannel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${liveLiveChannel.remarks}
				</td>
				<shiro:hasPermission name="live:liveLiveChannel:edit">
				<td>
    				<a href="${ctx}/live/liveLiveChannel/form?id=${liveLiveChannel.id}">修改</a>
					<a href="${ctx}/live/liveLiveChannel/delete?id=${liveLiveChannel.id}" onclick="return confirmx('确认要删除该直播频道吗？', this.href)">删除</a>
					<shiro:hasPermission name="live:liveLiveChannel:view">
					<a href="${ctx}/live/liveLiveChannel/view?id=${liveLiveChannel.id}">查看</a>
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