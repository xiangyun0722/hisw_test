<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教室管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/classroom/">教室列表</a></li>
		<shiro:hasPermission name="zjvideo:classroom:edit"><li><a href="${ctx}/zjvideo/classroom/form">教室添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="classroom" action="${ctx}/zjvideo/classroom/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>教室号：</label>
				<form:input path="classnumber" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>教室名称：</label>
				<form:input path="rname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
<%-- 			<li><label>所在位置：</label>
				<form:input path="position" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>教室号</th>
				<th>校区</th>
				<th>教室名称</th>
				<!-- <th>所在位置</th> -->
				<th>容量</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="zjvideo:classroom:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="classroom">
			<tr>
				<td><a href="${ctx}/zjvideo/classroom/form?id=${classroom.id}">
					${classroom.classnumber}
				</a></td>
				<td>
					${classroom.spart}
				</td>
				<td>
					${classroom.rname}
				</td>
	<%-- 			<td>
					${classroom.position}
				</td> --%>
				<td>
					${classroom.room}
				</td>
				<td>
					<fmt:formatDate value="${classroom.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${classroom.remarks}
				</td>
				<shiro:hasPermission name="zjvideo:classroom:edit"><td>
    				<a href="${ctx}/zjvideo/classroom/form?id=${classroom.id}">修改</a>
					<a href="${ctx}/zjvideo/classroom/delete?id=${classroom.id}" onclick="return confirmx('确认要删除该教室吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>