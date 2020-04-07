<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点组管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/nodeGroup/">节点组列表</a></li>
		<shiro:hasPermission name="zjvideo:nodeGroup:edit"><li><a href="${ctx}/zjvideo/nodeGroup/form">节点组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="nodeGroup" action="${ctx}/zjvideo/nodeGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>节点服务器ID：</label>
				<form:input path="nodeid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>服务器组ID：</label>
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
				<th>节点服务器ID</th>
				<th>服务器组ID</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="zjvideo:nodeGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="nodeGroup">
			<tr>
				<td><a href="${ctx}/zjvideo/nodeGroup/form?id=${nodeGroup.id}">
					${nodeGroup.nodeid}
				</a></td>
				<td>
					${nodeGroup.groupid}
				</td>
				<td>
					<fmt:formatDate value="${nodeGroup.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${nodeGroup.remarks}
				</td>
				<shiro:hasPermission name="zjvideo:nodeGroup:edit"><td>
    				<a href="${ctx}/zjvideo/nodeGroup/form?id=${nodeGroup.id}">修改</a>
					<a href="${ctx}/zjvideo/nodeGroup/delete?id=${nodeGroup.id}" onclick="return confirmx('确认要删除该节点组吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>