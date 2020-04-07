<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直播业务系统管理</title>
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
		<li class="active"><a href="${ctx}/live/businessSystem/">直播业务系统列表</a></li>
		<shiro:hasPermission name="live:businessSystem:edit"><li><a href="${ctx}/live/businessSystem/form">直播业务系统添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="businessSystem" action="${ctx}/live/businessSystem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>系统名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>公共key：</label>
				<form:input path="publicKey" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>系统名称</th>
				<th>图标</th>
				<th>公共key</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="live:businessSystem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="businessSystem">
			<tr>
				<td><a href="${ctx}/live/businessSystem/form?id=${businessSystem.id}">
					${businessSystem.name}
				</a></td>
				<td>
					<c:if test="${businessSystem.logo!=null}">
						<img  height=60 width=80 style="max-height: 60px;max-width: 80px" src="${businessSystem.logo}" >
					</c:if>
				</td>
				<td>
					${businessSystem.publicKey}
				</td>
				<td>
					<fmt:formatDate value="${businessSystem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${businessSystem.remarks}
				</td>
				<shiro:hasPermission name="live:businessSystem:edit">
				<td>
    				<a href="${ctx}/live/businessSystem/form?id=${businessSystem.id}">修改</a>
					<a href="${ctx}/live/businessSystem/delete?id=${businessSystem.id}" onclick="return confirmx('确认要删除该直播业务系统吗？', this.href)">删除</a>
					<shiro:hasPermission name="live:businessSystem:view">
					<a href="${ctx}/live/businessSystem/view?id=${businessSystem.id}">查看</a>
					</shiro:hasPermission>
				</td>
				</shiro:hasPermission>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>