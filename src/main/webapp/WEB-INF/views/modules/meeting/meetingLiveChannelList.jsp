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
		<li class="active"><a href="${ctx}/meeting/meetingLiveChannel/">直播频道列表</a></li>
		<shiro:hasPermission name="meeting:meetingLiveChannel:edit"><li><a href="${ctx}/meeting/meetingLiveChannel/form">直播频道添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="meetingLiveChannel" action="${ctx}/meeting/meetingLiveChannel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%-- <li><label>频道logo：</label>
				<form:input path="logo" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>rtmp高清：</label>
				<form:input path="rtmpHd" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>rtmp标清：</label>
				<form:input path="rtmpSd" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>rtmp普清：</label>
				<form:input path="rtmpNd" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>rtmp声音：</label>
				<form:input path="rtmpVn" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>hls高清：</label>
				<form:input path="hlsHd" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>hls标清：</label>
				<form:input path="hlsSd" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>hls普清：</label>
				<form:input path="hlsNd" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>hls声音：</label>
				<form:input path="hlsVn" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li> --%>
			<li><label>状态：</label>
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
				<th>备注信息</th>
				<th>频道LOGO</th>
				<th>rtmp高清</th>
				<th>rtmp标清</th>
				<th>rtmp普清</th>
				<th>rtmp声音</th>
				<th>hls高清</th>
				<th>hls标清</th>
				<th>hls普清</th>
				<th>hls声音</th>
				<th>状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="meeting:meetingLiveChannel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="meetingLiveChannel">
			<tr>
				<td><a href="${ctx}/meeting/meetingLiveChannel/form?id=${meetingLiveChannel.id}">
					${meetingLiveChannel.name}
				</a></td>
				<td>
					${meetingLiveChannel.remarks}
				</td>
				<td>
					<img  height=60 width=80 style="max-height: 60px;max-width: 80px" src="${meetingLiveChannel.logo}" >
				</td>
				<td>
					${meetingLiveChannel.rtmpHd}
				</td>
				<td>
					${meetingLiveChannel.rtmpSd}
				</td>
				<td>
					${meetingLiveChannel.rtmpNd}
				</td>
				<td>
					${meetingLiveChannel.rtmpVn}
				</td>
				<td>
					${meetingLiveChannel.hlsHd}
				</td>
				<td>
					${meetingLiveChannel.hlsSd}
				</td>
				<td>
					${meetingLiveChannel.hlsNd}
				</td>
				<td>
					${meetingLiveChannel.hlsVn}
				</td>
				<td>
					 ${fns:getDictLabel(meetingLiveChannel.status, 'meeting_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${meetingLiveChannel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="meeting:meetingLiveChannel:edit"><td>
    				<a href="${ctx}/meeting/meetingLiveChannel/form?id=${meetingLiveChannel.id}">修改</a>
					<a href="${ctx}/meeting/meetingLiveChannel/delete?id=${meetingLiveChannel.id}" onclick="return confirmx('确认要删除该直播频道吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>