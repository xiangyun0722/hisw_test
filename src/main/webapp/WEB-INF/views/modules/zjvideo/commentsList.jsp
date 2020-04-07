<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论信息管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/comments/">评论信息列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:comments:edit"><li><a href="${ctx}/zjvideo/comments/form">评论信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="comments" action="${ctx}/zjvideo/comments/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>话题名字：</label>
				<form:input path="topic.name" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>评论内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>话题名字</th>
				<th>评论内容</th>
				<th>话题状态</th>
				<th>用户姓名</th>
				<th>用户昵称</th>
				<th>用户照片</th>
				<!-- <th>话题ID</th> -->
				<th>用户类型</th>
				<th>添加时间</th>
				<shiro:hasPermission name="zjvideo:comments:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comments">
			<tr>
				<td>
					${comments.topic.name}
				</td>			
				<td>
					${comments.content}
				</td>
				<td>
					${fns:getDictLabel(comments.state, 'topic_status', '')}	
				</td>
				<td>
					<%-- ${comments.userid} --%>
					${(comments.type == 1) ? (comments.student.realname) : (comments.teacher.realname)}
				</td>
				<td>
					<%-- ${comments.userid} --%>
					${(comments.type == 1) ? (comments.student.nickname) : (comments.teacher.nickname)}
				</td>							
				<td>
					<%-- ${comments.userid} --%>
					<img  height=60 width=80  src="${(comments.type == 1) ? (comments.student.picurl) : (comments.teacher.picurl)}" >
				</td>	
<%-- 				<td>
					${comments.topicid}
				</td> --%>
				<td>
					${fns:getDictLabel(comments.type, 'comments_type', '')}	
				</td>
				<td>
					<fmt:formatDate value="${comments.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					<fmt:formatDate value="${comments.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${comments.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${comments.remarks}
				</td> --%>
				<shiro:hasPermission name="zjvideo:comments:edit"><td>
    				<%-- <a href="${ctx}/zjvideo/comments/form?id=${comments.id}">修改</a> --%>
					<a href="${ctx}/zjvideo/comments/delete/comment?id=${comments.id}" onclick="return confirmx('确认要删除该评论信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>