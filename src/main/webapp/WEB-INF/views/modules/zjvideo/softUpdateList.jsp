<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>软件更新管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/softUpdate/">软件更新列表</a></li>
		<shiro:hasPermission name="zjvideo:softUpdate:edit"><li><a href="${ctx}/zjvideo/softUpdate/form">软件更新添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="softUpdate" action="${ctx}/zjvideo/softUpdate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件路径：</label>
				<form:input path="fileurl" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>平台信息</th>
				<th>显示版本</th>
				<th>升级模式</th>
				<th>主版本号</th>
				<th>次版本号</th>
				<th>android版本号</th>
				<th>文件路径</th>
				<shiro:hasPermission name="zjvideo:softUpdate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="softUpdate">
			<tr>
				<td>
					${fns:getDictLabel(softUpdate.platform, 'soft_platform_flag', '')}
				</td>
				<td>
					${softUpdate.filename}
				</td>
				<td>
					${fns:getDictLabel(softUpdate.forceflag, 'soft_update_flag', '')}
				</td>
				<td>
					${softUpdate.mainversion}
				</td>
				<td>
					${softUpdate.minorversion}
				</td>
				<td>
					${softUpdate.androidversion}
				</td>
				<td>
					${softUpdate.fileurl}
				</td>
				<shiro:hasPermission name="zjvideo:softUpdate:edit"><td>
    				<a href="${ctx}/zjvideo/softUpdate/form?id=${softUpdate.id}">修改</a>
					<a href="${ctx}/zjvideo/softUpdate/delete?id=${softUpdate.id}" onclick="return confirmx('确认要删除该软件更新吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>