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
		<li class="active"><a href="${ctx}/meeting/meetingPptOperationRecords/">会议操作记录列表</a></li>
		<shiro:hasPermission name="meeting:meetingPptOperationRecords:edit"><li><a href="${ctx}/meeting/meetingPptOperationRecords/form">会议操作记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="meetingPptOperationRecords" action="${ctx}/meeting/meetingPptOperationRecords/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>IP地址：</label>
				<form:input path="ip" htmlEscape="false" maxlength="256" class="input-medium"/>
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
				<th>IP地址</th>
				<th>上次操作时间</th>
				<th>操作时间</th>
				<th>页号信息</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="meeting:meetingPptOperationRecords:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="meetingPptOperationRecords">
			<tr>
				<td><a href="${ctx}/meeting/meetingPptOperationRecords/form?id=${meetingPptOperationRecords.id}">
					${meetingPptOperationRecords.name}
				</a></td>
				<td>
					${meetingPptOperationRecords.ip}
				</td>
				<td>
					<fmt:formatDate value="${meetingPptOperationRecords.upOperationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${meetingPptOperationRecords.operationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${meetingPptOperationRecords.pageIndex}
				</td>
				<td>
					<fmt:formatDate value="${meetingPptOperationRecords.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${meetingPptOperationRecords.remarks}
				</td>
				<shiro:hasPermission name="meeting:meetingPptOperationRecords:edit"><td>
    				<a href="${ctx}/meeting/meetingPptOperationRecords/form?id=${meetingPptOperationRecords.id}">修改</a>
					<a href="${ctx}/meeting/meetingPptOperationRecords/delete?id=${meetingPptOperationRecords.id}" onclick="return confirmx('确认要删除该会议操作记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>