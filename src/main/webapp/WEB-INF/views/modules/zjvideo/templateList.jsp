<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>视频模板管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/template/">视频模板列表</a></li>
		<shiro:hasPermission name="zjvideo:template:edit"><li><a href="${ctx}/zjvideo/template/form">视频模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="templates" action="${ctx}/zjvideo/template/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>模板命令：</label>
				<form:input path="cmd" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>视频格式:</label>
				<form:select path="format" class="input-medium">
					<form:options items="${fns:getDictList('template_format')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>视频清晰度:</label>
				<form:select path="type" class="input-medium">
					<form:options items="${fns:getDictList('template_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>模板名称</th>
				<th>模板命令</th>
				<th>视频格式</th>
<!-- 				<th>是否自动转码</th> -->
				<th>视频清晰度</th>
				<shiro:hasPermission name="zjvideo:template:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="template">
			<tr>
				<td><a href="${ctx}/zjvideo/template/form?id=${template.id}">
					${template.name}
				</a></td>
				<td>
					${template.cmd}
				</td>
				<td>
					${fns:getDictLabel(template.format, 'template_format', '')}
				</td>
<!-- 				<td> -->
<%-- 					${fns:getDictLabel(template.auto, 'template_auto_status', '')} --%>
<!-- 				</td>				 -->
				<td>
					${fns:getDictLabel(template.type, 'template_status', '')}
				</td>
				<shiro:hasPermission name="zjvideo:template:edit"><td>
    				<a href="${ctx}/zjvideo/template/form?id=${template.id}">修改</a>
					<a href="${ctx}/zjvideo/template/delete?id=${template.id}" onclick="return confirmx('确认要删除该视频模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>