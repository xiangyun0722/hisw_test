<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/program/">课程管理列表</a></li>
		<shiro:hasPermission name="zjvideo:program:edit"><li><a href="${ctx}/zjvideo/program/form">课程管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="program" action="${ctx}/zjvideo/program/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>英文名称：</label>
				<form:input path="ename" htmlEscape="false" maxlength="150" class="input-medium"/>
			</li>
			<li><label>视频状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请您选择"/>
					<form:options items="${fns:getDictList('program_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>课程名称</th>
				<th>英文名称</th>
				<th>可观看权限组</th>
				<th>主讲人</th>
				<th>时长</th>
				<th>课程海报</th>
				<th>视频状态</th>
				<!-- <th>视频看点</th> -->
				<th>推荐</th>
				<shiro:hasPermission name="zjvideo:program:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="program">
			<tr>
				<td>
					${program.name}
				</td>
				<td>
					${program.ename}
				</td>
				<td>
					${program.ownGroupName}
				</td>
				<td>
					${program.actor}
				</td>
				<td>
					${program.hour}
				</td>
				<td>
					<img  height=60 width=80  src="${program.picurl}" >			
				</td>
				<td>
					${fns:getDictLabel(program.status, 'program_status', '')}					
				</td>
		<%-- 		<td>
					${program.viewpoint}
				</td> --%>
				<td>
					${fns:getDictLabel(program.isrecommend, 'program_recommend_type', '')}					
				</td>				
				<shiro:hasPermission name="zjvideo:program:edit"><td>
    				<a href="${ctx}/zjvideo/programVolume/program/List?id=${program.id}">章节管理</a>
    				<a href="${ctx}/zjvideo/program/form/update?id=${program.id}">修改</a>
					<a href="${ctx}/zjvideo/program/delete?id=${program.id}" onclick="return confirmx('确认要删除该课程吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>