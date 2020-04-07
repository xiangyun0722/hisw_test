<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议操作记录管理</title>
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
		<li class="active"><a href="${ctx}/meeting/meetingMeetingInfo/">会议操作记录列表</a></li>
		<shiro:hasPermission name="meeting:meetingMeetingInfo:edit"><li><a href="${ctx}/meeting/meetingMeetingInfo/form">会议操作记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="meetingMeetingInfo" action="${ctx}/meeting/meetingMeetingInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会议名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>主讲人：</label>
				<form:input path="present" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>会议类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('meeting_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>会议名称</th>
				<th>简介</th>
				<th>主讲人</th>
				<th>会议时长（分钟）</th>
				<th>封面</th>
				<th>图片介绍</th>
<!-- 				<th>讲稿</th> -->
				<th>录播视频</th>
				<th>会议类型</th>
				<th>所属频道</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="meeting:meetingMeetingInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="meetingMeetingInfo">
			<tr>
				<td><a href="${ctx}/meeting/meetingMeetingInfo/form?id=${meetingMeetingInfo.id}">
					${meetingMeetingInfo.name}
				</a></td>
				<td>
					${meetingMeetingInfo.content}
				</td>
				<td>
					${meetingMeetingInfo.present}
				</td>
				<td>
					${meetingMeetingInfo.length}
				</td>
				<td>
					<img  height=60 width=80  src="${meetingMeetingInfo.img}" >
				</td>
				<td>
					${meetingMeetingInfo.imgs}
				</td>
<!-- 				<td> -->
<%-- 					${meetingMeetingInfo.ppt} --%>
<!-- 				</td> -->
				<td>
					${meetingMeetingInfo.video}
				</td>
				<td>
					${fns:getDictLabel(meetingMeetingInfo.type, 'meeting_type', '')}
				</td>
				<td>
					${meetingMeetingInfo.meetingLiveChannel.name}
				</td>
				<td>
					<fmt:formatDate value="${meetingMeetingInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${meetingMeetingInfo.remarks}
				</td>
				<shiro:hasPermission name="meeting:meetingMeetingInfo:edit"><td>
    				<a href="${ctx}/meeting/meetingMeetingInfo/form?id=${meetingMeetingInfo.id}">修改</a>
					<a href="${ctx}/meeting/meetingMeetingInfo/delete?id=${meetingMeetingInfo.id}" onclick="return confirmx('确认要删除该会议操作记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>