<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>视频服务器管理管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/server/">视频服务器管理列表</a></li>
		<shiro:hasPermission name="zjvideo:server:edit"><li><a href="${ctx}/zjvideo/server/form">视频服务器管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="server" action="${ctx}/zjvideo/server/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>服务器名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>服务器状态：</label>
				<form:select path="status" class="input-medium">
					<form:options items="${fns:getDictList('server_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>服务器名称</th>
				<th>url前缀地址</th>
				<th>ip地址</th>
				<th>添加时间</th>
				<th>修改时间</th>
				<th>服务器类型</th>
				<th>服务器状态</th>
				<shiro:hasPermission name="zjvideo:server:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="server">
			<tr>
				<td><a href="${ctx}/zjvideo/server/form?id=${server.id}">
					${server.name}
				</a></td>
				<td>
					${server.url}
				</td>
				<td>
					${server.ipaddress}
				</td>
				<td>
					<fmt:formatDate value="${server.addtine}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${server.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(server.servertype, 'server_type_flag', '')}
				</td>
				<td>
					${fns:getDictLabel(server.status, 'server_status', '')}
				</td>
				<shiro:hasPermission name="zjvideo:server:edit"><td>
    				<a href="${ctx}/zjvideo/server/form?id=${server.id}">修改</a>
					<a href="${ctx}/zjvideo/server/delete?id=${server.id}" onclick="return confirmx('确认要删除该视频服务器管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>