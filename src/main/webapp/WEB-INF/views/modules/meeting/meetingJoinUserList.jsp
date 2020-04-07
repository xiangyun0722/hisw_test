<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参会用户管理</title>
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
		<li class="active"><a href="${ctx}/meeting/meetingJoinUser/">参会用户列表</a></li>
		<shiro:hasPermission name="meeting:meetingJoinUser:edit"><li><a href="${ctx}/meeting/meetingJoinUser/form">参会用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="meetingJoinUser" action="${ctx}/meeting/meetingJoinUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>IP地址：</label>
				<form:input path="ip" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>参会时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${meetingJoinUser.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>离开时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${meetingJoinUser.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>IP地址</th>
				<th>上次操作时间</th>
				<th>操作时间</th>
				<th>页号信息</th>
				<th>国家</th>
				<th>省市</th>
				<th>区/县</th>
				<th>网络运营商</th>
				<th>参会时间</th>
				<th>离开时间</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="meeting:meetingJoinUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="meetingJoinUser">
			<tr>
				<td><a href="${ctx}/meeting/meetingJoinUser/form?id=${meetingJoinUser.id}">
					${meetingJoinUser.name}
				</a></td>
				<td>
					${meetingJoinUser.ip}
				</td>
				<td>
					<fmt:formatDate value="${meetingJoinUser.upOperationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${meetingJoinUser.operationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${meetingJoinUser.pageIndex}
				</td>
				<td>
					${meetingJoinUser.countries}
				</td>
				<td>
					${meetingJoinUser.provinces}
				</td>
				<td>
					${meetingJoinUser.city}
				</td>
				<td>
					${meetingJoinUser.network}
				</td>
				<td>
					<fmt:formatDate value="${meetingJoinUser.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${meetingJoinUser.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${meetingJoinUser.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${meetingJoinUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${meetingJoinUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${meetingJoinUser.remarks}
				</td>
				<shiro:hasPermission name="meeting:meetingJoinUser:edit"><td>
    				<a href="${ctx}/meeting/meetingJoinUser/form?id=${meetingJoinUser.id}">修改</a>
					<a href="${ctx}/meeting/meetingJoinUser/delete?id=${meetingJoinUser.id}" onclick="return confirmx('确认要删除该参会用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>