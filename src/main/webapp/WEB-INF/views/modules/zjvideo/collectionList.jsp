<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收藏信息管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/collection/">收藏信息列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:collection:edit"><li><a href="${ctx}/zjvideo/collection/form">收藏信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="collections" action="${ctx}/zjvideo/collection/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程名称：</label>
				<form:input path="program.name" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>用户类型：</label>
				<form:select path="type" class="input-medium">
					<form:options items="${fns:getDictList('user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>	
			<li><label>用户姓名：</label>
				<form:input path="student.realname" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>编号：</label>
				<form:input path="student.studentno" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>			
			<li>
				<label>收藏时间：</label>
				<input id="beginDate"  name="addtime"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${collections.addtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				&nbsp; --　
				<input id="endDate" name="edittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${collections.edittime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>学生/老师编号</th>
				<th>课程名称</th>
				<th>用户类型</th>
				<th>增加时间</th>
				<%-- <shiro:hasPermission name="zjvideo:collection:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="collection">
			<tr>
				<td>
					${(collection.type == 1) ? (collection.student.realname) : (collection.teacher.realname)}
				</td>			
				<td>
					${(collection.type == 1) ? (collection.student.studentno) : (collection.teacher.teacherno)}
				</td>
				<td>
					${collection.program.name}
				</td>
				<td>
					${fns:getDictLabel(collection.type, 'user_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${collection.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
		<%-- 		<shiro:hasPermission name="zjvideo:collection:edit"><td>
    				<a href="${ctx}/zjvideo/collection/form?id=${collection.id}">修改</a>
					<a href="${ctx}/zjvideo/collection/delete?id=${collection.id}" onclick="return confirmx('确认要删除该收藏信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>