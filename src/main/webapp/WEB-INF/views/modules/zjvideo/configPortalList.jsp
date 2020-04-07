<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配置管理管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/config/">配置管理列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:config:edit"><li><a href="${ctx}/zjvideo/config/form">配置管理添加</a></li></shiro:hasPermission> --%>
	</ul>
<%-- 	<form:form id="searchForm" modelAttribute="config" action="${ctx}/zjvideo/config/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>配置说明：</label>
				<form:input path="configExplain" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>配置值：</label>
				<form:input path="configValue" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>配置说明</th>
				<th>配置值</th>
				<shiro:hasPermission name="zjvideo:config:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="config">
			<tr>
				<td>
					${config.configexplain}
				</td>
				<td>
					${config.configValue}
				</td>
				<shiro:hasPermission name="zjvideo:config:edit"><td>
    				<a href="${ctx}/zjvideo/config/portal/form?id=${config.id}">修改</a>
					<%-- <a href="${ctx}/zjvideo/config/delete?id=${config.id}" onclick="return confirmx('确认要删除该配置管理吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>