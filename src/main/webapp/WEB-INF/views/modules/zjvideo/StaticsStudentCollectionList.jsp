<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>播放历史信息管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/collection/statics/studnet/collection">播放历史信息列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:playHistory:edit"><li><a href="${ctx}/zjvideo/playHistory/form">播放历史信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="collections" action="${ctx}/zjvideo/collection/statics/studnet/collection" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>统计类型：</label>
				<form:select path="staticsflag" class="input-medium">
					<form:options items="${fns:getDictList('collection_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</li>		
			<li><label>学生姓名：</label>
				<form:input path="student.realname" htmlEscape="false" maxlength="11" class="input-medium"/>
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
			<li class="clearfix"></li>						
			<li><label>学生学号：</label>
				<form:input path="student.studentno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学生姓名</th>
				<th>学生学号</th>
				<th>次数</th>
				<th>收藏时间</th>
				<%-- <th>备注信息</th>
				<shiro:hasPermission name="zjvideo:playHistory:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="playHistory">
			<tr>
				<td>
					${playHistory.student.realname}
				</td>
				<td>
					${playHistory.student.studentno}
				</td>
				<td>
					${playHistory.count}
				</td>
				<td>
					<fmt:formatDate value="${playHistory.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>					
<%-- 				<shiro:hasPermission name="zjvideo:playHistory:edit"><td>
    				<a href="${ctx}/zjvideo/playHistory/form?id=${playHistory.id}">修改</a>
					<a href="${ctx}/zjvideo/playHistory/delete?id=${playHistory.id}" onclick="return confirmx('确认要删除该播放历史信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>