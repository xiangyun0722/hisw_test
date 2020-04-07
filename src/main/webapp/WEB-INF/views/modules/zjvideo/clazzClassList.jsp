<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程分类管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/clazz/class/list">课程分类管理</a></li>
		<li><a href="${ctx}/zjvideo/clazz/class/form">课程分类添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="clazz" action="${ctx}/zjvideo/clazz/class/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分类名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分类名称</th>
				<th>增加时间</th>
				<!-- <th>最后修改 时间</th> -->
				<shiro:hasPermission name="zjvideo:clazz:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="clazz">
			<tr>
				<td>
					${clazz.name}
				</td>
				<td>
					<fmt:formatDate value="${clazz.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
		<%-- 		<td>
					<fmt:formatDate value="${clazz.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<shiro:hasPermission name="zjvideo:clazz:edit"><td>
    				<a href="${ctx}/zjvideo/clazz/class/form?id=${clazz.id}">修改</a>
					<a href="${ctx}/zjvideo/clazz/class/delete?id=${clazz.id}" onclick="return confirmx('确认要删除该二级分类吗？', this.href)">删除</a>
					<a href="${ctx}/zjvideo/clazz/class/level/list?id=${clazz.id}">二级课程分类</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>