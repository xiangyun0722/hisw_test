<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资讯管理管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/news/">资讯管理列表</a></li>
		<shiro:hasPermission name="zjvideo:news:edit"><li><a href="${ctx}/zjvideo/news/form">资讯管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="news" action="${ctx}/zjvideo/news/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>新闻标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>所属分类：</label>
				<form:input path="clazz.name" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>				
			</li>			
			<li><label>审核状态:</label>
				<form:select path="status" class="input-medium">
					<form:options items="${fns:getDictList('zjvideo_news_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>标题</th>
				<th>所属分类</th>
				<th>机顶盒缩略图</th>
				<th>是否推荐</th>
				<th>编辑者姓名</th>
				<th>审核</th>
				<shiro:hasPermission name="zjvideo:news:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="news">
			<tr>
				<td>
					${news.title}
				</td>
				<td>
					${news.clazz.name}
				</td>				
				<td>
					<img  height=60 width=80  src="${news.picurl}" >
				</td>
				<td>
					${fns:getDictLabel(news.isrecommend, 'stu_ad_flag', '')}
				</td>
				<td>
					${news.editername}
				</td>
				<td>
					${fns:getDictLabel(news.status, 'zjvideo_news_status', '')}
				</td>
				<shiro:hasPermission name="zjvideo:news:edit"><td>
    				<a href="${ctx}/zjvideo/news/form?id=${news.id}">修改</a>
					<a href="${ctx}/zjvideo/news/delete/news?id=${news.id}" onclick="return confirmx('确认要删除该资讯吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>