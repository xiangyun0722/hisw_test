<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>频道管理管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/liveing/">频道管理列表</a></li>
		<shiro:hasPermission name="zjvideo:liveing:edit"><li><a href="${ctx}/zjvideo/liveing/form">频道管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="liveing" action="${ctx}/zjvideo/liveing/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>频道名称：</label>
				<form:input path="livename" htmlEscape="false" maxlength="150" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>频道名称</th>
				<th>频道号</th>
				<th>播放地址</th>
				<th>频道logo</th>
				<th>在线</th>
				<shiro:hasPermission name="zjvideo:liveing:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="liveing">
			<tr>
				<td><a href="${ctx}/zjvideo/liveing/form?id=${liveing.id}">
					${liveing.livename}
				</a></td>
				<td>
					${liveing.livenum}
				</td>
				<td>
					<embed type="application/x-vlc-plugin" name="VLC" autoplay="no" loop="no" volume="80" width="80" height="100" target="${liveing.playurl}"/>
				</td>
				<td>
					<img  height=60 width=80  src="${liveing.picurl}" >
				</td>
				<td>
					${fns:getDictLabel(liveing.isonline, 'liveing_isonline_flag', '')}
				</td>
				<shiro:hasPermission name="zjvideo:liveing:edit"><td>
    				<a href="${ctx}/zjvideo/liveing/form?id=${liveing.id}">修改</a>
					<a href="${ctx}/zjvideo/liveing/delete?id=${liveing.id}" onclick="return confirmx('确认要删除该频道管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>