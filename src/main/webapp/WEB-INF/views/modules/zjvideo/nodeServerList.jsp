<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点服务器管理</title>
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
		<li class="active"><a href="${ctx}/zjvideo/nodeServer/">节点服务器列表</a></li>
		<shiro:hasPermission name="zjvideo:nodeServer:edit"><li><a href="${ctx}/zjvideo/nodeServer/form">节点服务器添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="nodeServer" action="${ctx}/zjvideo/nodeServer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>服务器名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>在线状态：</label>
				<form:select path="onlineFlag" class="input-medium">
					<form:options items="${fns:getDictList('server_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>IP地址：</label>
				<form:input path="ipaddress" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>服务器名称</th>
				<th>源站播放地址</th>
				<th>cdn播放地址</th>
				<th>IP地址</th>
				<th>负载上限</th>
				<th>在线状态</th>
				<th>网络负载</th>
				<th>网络运营商</th>
				<th>机房位置</th>
				<th>优选参数</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="zjvideo:nodeServer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="nodeServer">
			<tr>
				<td><a href="${ctx}/zjvideo/nodeServer/form?id=${nodeServer.id}">
					${nodeServer.name}
				</a></td>
				<td>
					${nodeServer.url}
				</td>
				<td>
					${nodeServer.cdnurl}
				</td>
				<td>
					${nodeServer.ipaddress}
				</td>
				<td>
					${nodeServer.maxload}
				</td>
				<td>
					${fns:getDictLabel(nodeServer.onlineFlag, 'server_status', '')}
				</td>
				<td>
					${nodeServer.loadnum}
				</td>
			    <td>
			     	${fns:getDictLabel(nodeServer.carrier, 'carrier_network', '')}
			    </td>	
				<td>
					${nodeServer.location}
				</td>	
				<td>
					${nodeServer.optimalConfig}
				</td>							    						
				<td>
					<fmt:formatDate value="${nodeServer.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${nodeServer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="zjvideo:nodeServer:edit"><td>
    				<a href="${ctx}/zjvideo/nodeServer/form?id=${nodeServer.id}">修改</a>
					<a href="${ctx}/zjvideo/nodeServer/delete?id=${nodeServer.id}" onclick="return confirmx('确认要删除该节点服务器吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>