<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师群组管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/teacherGroup/">教师群组列表</a></li>
		<shiro:hasPermission name="zjvideo:teacherGroup:edit"><li><a href="${ctx}/zjvideo/teacherGroup/form">教师群组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="teacherGroup" action="${ctx}/zjvideo/teacherGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>教师ID：</label>
				<form:input path="teacherid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>群组ID：</label>
				<form:input path="groupid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>教师ID</th>
				<th>群组ID</th>
				<shiro:hasPermission name="zjvideo:teacherGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="teacherGroup">
			<tr>
				<td><a href="${ctx}/zjvideo/teacherGroup/form?id=${teacherGroup.id}">
					${teacherGroup.teacherid}
				</a></td>
				<td>
					${teacherGroup.groupid}
				</td>
				<shiro:hasPermission name="zjvideo:teacherGroup:edit"><td>
    				<a href="${ctx}/zjvideo/teacherGroup/form?id=${teacherGroup.id}">修改</a>
					<a href="${ctx}/zjvideo/teacherGroup/delete?id=${teacherGroup.id}" onclick="return confirmx('确认要删除该教师群组吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>