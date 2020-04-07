<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>媒资信息管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/media/">媒资信息列表</a></li>
		<shiro:hasPermission name="zjvideo:media:edit"><li><a href="${ctx}/zjvideo/media/form">媒资信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="media" action="${ctx}/zjvideo/media/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>视频ID：</label>
				<form:input path="videosid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>视频类型:nd.普清 sd.标清 fd.流畅 hd.高清 ed.超清：</label>
				<form:input path="type" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>1.flv 2.m3u8 3.yamdi：</label>
				<form:input path="format" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>视频ID</th>
				<th>视频名称</th>
				<th>视频路径</th>
				<th>视频类型:nd.普清 sd.标清 fd.流畅 hd.高清 ed.超清</th>
				<th>1.flv 2.m3u8 3.yamdi</th>
				<shiro:hasPermission name="zjvideo:media:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="media">
			<tr>
				<td><a href="${ctx}/zjvideo/media/form?id=${media.id}">
					${media.videosid}
				</a></td>
				<td>
					${media.name}
				</td>
				<td>
					${media.path}
				</td>
				<td>
					${media.type}
				</td>
				<td>
					${media.format}
				</td>
				<shiro:hasPermission name="zjvideo:media:edit"><td>
    				<a href="${ctx}/zjvideo/media/form?id=${media.id}">修改</a>
					<a href="${ctx}/zjvideo/media/delete?id=${media.id}" onclick="return confirmx('确认要删除该媒资信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>