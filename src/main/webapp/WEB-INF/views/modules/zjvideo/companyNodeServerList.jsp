<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单位节点管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/companyNodeServer/">单位节点列表</a></li>
		<shiro:hasPermission name="zjvideo:companyNodeServer:edit"><li><a href="${ctx}/zjvideo/companyNodeServer/form">单位节点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="companyNodeServer" action="${ctx}/zjvideo/companyNodeServer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>策略类型：</label>
				<form:input path="strategyType" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>优选特征：</label>
				<form:input path="optimizationNode" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>服务器名称</th>
				<th>单位名称</th>
				<th>策略类型</th>
				<shiro:hasPermission name="zjvideo:companyNodeServer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="companyNodeServer">
			<tr>
				<td><a href="${ctx}/zjvideo/companyNodeServer/form?id=${companyNodeServer.id}">
					${companyNodeServer.nodeServer.name}
				</a></td>
				<td>
					${companyNodeServer.office.name}
				</td>
				<td>
					${companyNodeServer.strategyType}
				</td>
				<shiro:hasPermission name="zjvideo:companyNodeServer:edit"><td>
    				<a href="${ctx}/zjvideo/companyNodeServer/form?id=${companyNodeServer.id}">修改</a>
					<a href="${ctx}/zjvideo/companyNodeServer/delete?id=${companyNodeServer.id}" onclick="return confirmx('确认要删除该单位节点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>