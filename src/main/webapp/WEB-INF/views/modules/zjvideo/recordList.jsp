<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>录制管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/record/">录制列表</a></li>
		<shiro:hasPermission name="zjvideo:record:edit"><li><a href="${ctx}/zjvideo/record/form">录制添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="record" action="${ctx}/zjvideo/record/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>文件名称</th>
				<th>文件源地址</th>
				<!-- <th>classroomid</th> -->
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="zjvideo:record:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="record">
			<tr>
				<td><a href="${ctx}/zjvideo/record/form?id=${record.id}">
					<fmt:formatDate value="${record.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					<fmt:formatDate value="${record.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${record.name}
				</td>
				<td>
					${record.source}
				</td>
	<%-- 			<td>
					${record.classroomid}
				</td> --%>
				<td>
					<fmt:formatDate value="${record.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${record.remarks}
				</td>
				<shiro:hasPermission name="zjvideo:record:edit"><td>
    				<a href="${ctx}/zjvideo/record/form?id=${record.id}">修改</a>
					<a href="${ctx}/zjvideo/record/delete?id=${record.id}" onclick="return confirmx('确认要删除该录制吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>