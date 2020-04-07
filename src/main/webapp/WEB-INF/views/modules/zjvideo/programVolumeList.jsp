<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>章节信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#startVolume").val(""); 
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
		<li><a href="${ctx}/zjvideo/program">课程管理列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/programVolume/">章节信息列表</a></li>
		<li><a href="${ctx}/zjvideo/programVolume/form">章节信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="programVolume" action="${ctx}/zjvideo/programVolume/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>章节序号：</label>
				<form:input path="volume" id="startVolume" htmlEscape="false"  maxlength="11" class="input-medium"/>
			</li>
<%-- 			<li><label>章节名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>章节序号</th>
				<th>章节名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="programVolume">
			<tr>
				<td>
					${programVolume.volume}
				</td>
				<td>
					${programVolume.name}
				</td>
				<td>
    				<a href="${ctx}/zjvideo/programVolume/form?id=${programVolume.id}">修改</a>
					<a href="${ctx}/zjvideo/programVolume/delete?id=${programVolume.id}" onclick="return confirmx('确认要删除该章节信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>