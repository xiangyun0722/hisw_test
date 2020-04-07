<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户群组管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/studentGroup/">用户群组列表</a></li>
		<shiro:hasPermission name="zjvideo:studentGroup:edit"><li><a href="${ctx}/zjvideo/studentGroup/form">用户群组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="studentGroup" action="${ctx}/zjvideo/studentGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>群组id：</label>
				<form:input path="groupid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>用户id：</label>
				<form:input path="userid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>群组id</th>
				<th>用户id</th>
				<th>增加时间</th>
				<shiro:hasPermission name="zjvideo:studentGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="studentGroup">
			<tr>
				<td><a href="${ctx}/zjvideo/studentGroup/form?id=${studentGroup.id}">
					${studentGroup.groupid}
				</a></td>
				<td>
					${studentGroup.userid}
				</td>
				<td>
					<fmt:formatDate value="${studentGroup.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="zjvideo:studentGroup:edit"><td>
    				<a href="${ctx}/zjvideo/studentGroup/form?id=${studentGroup.id}">修改</a>
					<a href="${ctx}/zjvideo/studentGroup/delete?id=${studentGroup.id}" onclick="return confirmx('确认要删除该用户群组吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>