<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>话题信息管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/topic/">话题信息列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:topic:edit"><li><a href="${ctx}/zjvideo/topic/form">话题信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="topic" action="${ctx}/zjvideo/topic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>话题名字：</label>
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
				<th>话题名字</th>
				<th>状态</th>
				<th>添加时间</th>
				<%-- <shiro:hasPermission name="zjvideo:topic:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="topic">
			<tr>
				<td>
					${topic.name}
				</td>
				<td>
					${fns:getDictLabel(topic.status, 'topic_status', '')}					
				</td>
				<td>
					<fmt:formatDate value="${topic.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			<%-- 	<shiro:hasPermission name="zjvideo:topic:edit"><td>
    				<a href="${ctx}/zjvideo/topic/form?id=${topic.id}">修改</a>
					<a href="${ctx}/zjvideo/topic/delete?id=${topic.id}" onclick="return confirmx('确认要删除该话题信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>