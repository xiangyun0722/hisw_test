<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>视频模板中间表管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/videotemplate/">视频模板中间表列表</a></li>
		<shiro:hasPermission name="zjvideo:videotemplate:edit"><li><a href="${ctx}/zjvideo/videotemplate/form">视频模板中间表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="videotemplate" action="${ctx}/zjvideo/videotemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模板ID</th>
				<th>视频ID</th>
				<shiro:hasPermission name="zjvideo:videotemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="videotemplate">
			<tr>
				<td><a href="${ctx}/zjvideo/videotemplate/form?id=${videotemplate.id}">
					${videotemplate.templateid}
				</a></td>
				<td>
					${videotemplate.videoid}
				</td>
				<shiro:hasPermission name="zjvideo:videotemplate:edit"><td>
    				<a href="${ctx}/zjvideo/videotemplate/form?id=${videotemplate.id}">修改</a>
					<a href="${ctx}/zjvideo/videotemplate/delete?id=${videotemplate.id}" onclick="return confirmx('确认要删除该视频模板中间表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>