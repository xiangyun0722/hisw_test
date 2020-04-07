<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组管理管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/group/">组管理列表</a></li>
		<shiro:hasPermission name="zjvideo:group:edit"><li><a href="${ctx}/zjvideo/group/form">组管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="group" action="${ctx}/zjvideo/group/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>组名称：</label>
				<form:input path="groupname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>AD组名：</label>
				<form:input path="adgroupname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>组名称</th>
				<th>AD组名</th>
				<shiro:hasPermission name="zjvideo:group:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="group">
			<tr>
				<td>
					${group.groupname}
				</td>
				<td>
					${group.adgroupname}
				</td>
				<shiro:hasPermission name="zjvideo:group:edit"><td>
    				<a href="${ctx}/zjvideo/group/form?id=${group.id}">修改</a>
					<a href="${ctx}/zjvideo/group/delete?id=${group.id}" onclick="return confirmx('确认要删除该组管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>