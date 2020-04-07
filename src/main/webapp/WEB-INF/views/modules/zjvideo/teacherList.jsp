<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/teacher/">教师信息列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:teacher:edit"><li><a href="${ctx}/zjvideo/teacher/form">教师信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="teacher" action="${ctx}/zjvideo/teacher/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>教师工号：</label>
				<form:input path="teacherno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>教师姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="recomment" class="input-medium">
					<form:options items="${fns:getDictList('zjvideo_teacher_recomment')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>教师状态：</label>
				<form:select path="status" class="input-medium">
					<form:options items="${fns:getDictList('zjvideo_teacher_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>ad用户：</label>
				<form:select path="adflag" class="input-medium">
					<form:options items="${fns:getDictList('stu_ad_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>教师工号</th>
				<th>教师姓名</th>
				<th>电话</th>
				<th>手机</th>
				<th>微信</th>
				<th>隶属学院</th>
				<th>隶属系</th>
				<th>教师头像</th>
				<th>课程数量</th>
				<th>类型</th>
				<th>AD用户</th>
				<th>教师简介</th>
				<th>教师状态</th>
				<shiro:hasPermission name="zjvideo:teacher:edit"><th width="95">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="teacher">
			<tr>
				<td>
					${teacher.teacherno}
				</td>
				<td>
					${teacher.realname}
				</td>
				<td>
					${teacher.telphone}
				</td>
				<td>
					${teacher.mobile}
				</td>
				<td>
					${teacher.webchat}
				</td>
				<td>
					${teacher.college}
				</td>
				<td>
					${teacher.fasten}
				</td>
				<td>
					<img  height=60 width=80  src="${teacher.picurl}" >
				</td>
				<td>
					${teacher.coursenum}
				</td>
				<td>
					${fns:getDictLabel(teacher.recomment, 'zjvideo_teacher_recomment', '')}
				</td>
				<td>
					${fns:getDictLabel(teacher.adflag, 'stu_ad_flag', '')}
				</td>
				<td>
					${teacher.detail}
				</td>
				<td>
					${fns:getDictLabel(teacher.status, 'zjvideo_teacher_status', '')}
				</td>
				<shiro:hasPermission name="zjvideo:teacher:edit"><td>
    				<%-- <a href="${ctx}/zjvideo/teacher/form?id=${teacher.id}">修改</a> --%>
  					<c:if test="${!teacher.suspended}">
						<a href="${ctx}/zjvideo/teacher/update/active?id=${teacher.id}" onclick="return confirmx('确认要激活吗？', this.href)">激活群组</a>
					</c:if>
					<c:if test="${teacher.suspended}">
						<a href="${ctx}/zjvideo/teacher/update/suspend?id=${teacher.id}" onclick="return confirmx('确认挂起吗？', this.href)">挂起群组</a>
					</c:if>    				
					<a href="${ctx}/zjvideo/teacher/delete?id=${teacher.id}" onclick="return confirmx('确认要删除该教师信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>