<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/student/">学生信息列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:student:edit"><li><a href="${ctx}/zjvideo/student/form">学生信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="student" action="${ctx}/zjvideo/student/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学生学号：</label>
				<form:input path="studentno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>学生名称：</label>
				<form:input path="realname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="recommend" class="input-medium">
					<form:options items="${fns:getDictList('stu_recommend_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>学生学号</th>
				<th>学生名称</th>
				<th>性别</th>
				<th>年龄</th>
				<th>学生头像</th>
				<th>专业</th>
				<th>手机</th>
				<th>联系地址</th>
				<th>类型</th>
				<th>增加时间</th>
				<th>AD用户</th>
				<shiro:hasPermission name="zjvideo:student:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="student">
			<tr>
				<td>
					${student.studentno}
				</td>
				<td>
					${student.realname}
				</td>
				<td>
					${fns:getDictLabel(student.sex, 'stu_sex_flag', '')}
				</td>
				<td>
					${student.age}
				</td>
				<td>
					<img  height=60 width=80  src="${student.picurl}" >					
				</td>
				<td>
					${student.major}
				</td>
				<td>
					${student.mobile}
				</td>
				<td>
					${student.address}
				</td>
				<td>
					${fns:getDictLabel(student.recommend, 'stu_recommend_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${student.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(student.adflag, 'stu_ad_flag', '')}
				</td>
				<shiro:hasPermission name="zjvideo:student:edit"><td>
    				<%-- <a href="${ctx}/zjvideo/student/form?id=${student.id}">修改</a> --%>
    				<%-- ${not empty teacher.id?'修改':'添加'} --%>
	  				<c:if test="${!student.suspended}">
						<a href="${ctx}/zjvideo/student/update/active?id=${student.id}" onclick="return confirmx('确认要激活吗？', this.href)">激活群组</a>
					</c:if>
					<c:if test="${student.suspended}">
						<a href="${ctx}/zjvideo/student/update/suspend?id=${student.id}" onclick="return confirmx('确认挂起吗？', this.href)">挂起群组</a>
					</c:if>
					<a href="${ctx}/zjvideo/student/delete?id=${student.id}" onclick="return confirmx('确认要删除该学生信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>