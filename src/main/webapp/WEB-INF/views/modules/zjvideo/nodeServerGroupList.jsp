<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点服务器组管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/nodeServerGroup/">节点服务器组列表</a></li>
		<shiro:hasPermission name="zjvideo:nodeServerGroup:edit"><li><a href="${ctx}/zjvideo/nodeServerGroup/form">节点服务器组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="nodeServerGroup" action="${ctx}/zjvideo/nodeServerGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>组名称：</label>
				<form:input path="groupname" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="zjvideo:nodeServerGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="nodeServerGroup">
			<tr>
				<td><a href="${ctx}/zjvideo/nodeServerGroup/form?id=${nodeServerGroup.id}">
					${nodeServerGroup.groupname}
				</a></td>
				<td>
					<fmt:formatDate value="${nodeServerGroup.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${nodeServerGroup.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="zjvideo:nodeServerGroup:edit"><td>
    				<a href="${ctx}/zjvideo/nodeServerGroup/form?id=${nodeServerGroup.id}">节点服务器列表</a>
					<a href="${ctx}/zjvideo/nodeServerGroup/delete?id=${nodeServerGroup.id}" onclick="return confirmx('确认要删除该节点服务器组吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>