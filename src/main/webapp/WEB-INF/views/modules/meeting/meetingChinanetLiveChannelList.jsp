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
		<li class="active"><a href="${ctx}/meeting/meetingChinanetLiveChannel/">直播频道列表</a></li>
		<shiro:hasPermission name="meeting:meetingChinanetLiveChannel:edit"><li><a href="${ctx}/meeting/meetingChinanetLiveChannel/form">直播频道添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="meetingChinanetLiveChannel" action="${ctx}/meeting/meetingChinanetLiveChannel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>外部系统关联id：</label>
				<form:input path="relId" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>转码参数：</label>
				<form:input path="transcodingParas" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>状态(上线、下线)：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('meeting_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>转码参数</th>
				<th>rtmp高清</th>
				<th>rtmp标清</th>
				<th>rtmp普清</th>
				<th>rtmp声音</th>
				<th>hls高清</th>
				<th>hls标清</th>
				<th>hls普清</th>
				<th>hls声音</th>
				<th>状态(上线、下线)</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="meeting:meetingChinanetLiveChannel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="meetingChinanetLiveChannel">
			<tr>
				<td><a href="${ctx}/meeting/meetingChinanetLiveChannel/form?id=${meetingChinanetLiveChannel.id}">
					${meetingChinanetLiveChannel.name}
				</a></td>
				<td>
					${meetingChinanetLiveChannel.relId}
				</td>
				<td>
					${meetingChinanetLiveChannel.transcodingParas}
				</td>
				<td>
					${meetingChinanetLiveChannel.rtmpHd}
				</td>
				<td>
					${meetingChinanetLiveChannel.rtmpSd}
				</td>
				<td>
					${meetingChinanetLiveChannel.rtmpNd}
				</td>
				<td>
					${meetingChinanetLiveChannel.rtmpVn}
				</td>
				<td>
					${meetingChinanetLiveChannel.hlsHd}
				</td>
				<td>
					${meetingChinanetLiveChannel.hlsSd}
				</td>
				<td>
					${meetingChinanetLiveChannel.hlsNd}
				</td>
				<td>
					${meetingChinanetLiveChannel.hlsVn}
				</td>
				<td>
					${fns:getDictLabel(meetingChinanetLiveChannel.status, 'meeting_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${meetingChinanetLiveChannel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${meetingChinanetLiveChannel.remarks}
				</td>
				<shiro:hasPermission name="meeting:meetingChinanetLiveChannel:edit"><td>
    				<a href="${ctx}/meeting/meetingChinanetLiveChannel/form?id=${meetingChinanetLiveChannel.id}">修改</a>
					<a href="${ctx}/meeting/meetingChinanetLiveChannel/delete?id=${meetingChinanetLiveChannel.id}" onclick="return confirmx('确认要删除该直播频道吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>