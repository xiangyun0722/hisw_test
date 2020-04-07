<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档查询管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/* top.$.jBox.tip.mess="${mess}"; */
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function view(href){
			top.$.jBox.open('iframe:'+href,'查看文档',$(top.document).width()-420,$(top.document).height()-180,{
				buttons:{"关闭":true},
				loaded:function(h){
					//$(".jbox-content", top.document).css("overflow-y","hidden");
					//$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
				}
			});
			return false;
		}
		/**
		查看文档
		**/
		function viewDocument(filePath){
			top.art.dialog.open('${ctxStatic}/flexpaper/flexpaper_pc.jsp?path='+filePath, {
				title: '查看文档',
				drag:false,
				padding:0,
				width:'670px',
				height:'540px',
				lock:true
			});
		}
		
		/***
		下載方法
		***/
		function download(str){
			window.open("${ctx}/sys/documentTranscodingTask/download?id="+str);
		}
		
		/**
		查看文档
		**/
		function viewDocument(id){
			$.post("${ctx}/sys/documentTranscodingTask/getJson?id="+id,function(data){
				top.art.dialog.open('${ctxStatic}/flexpaper/flexpaper_pc.jsp?path='+data.swfPath, {
					title: '查看文档',
					drag:false,
					padding:0,
					width:'670px',
					height:'540px',
					lock:true
				});
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/documentTranscodingTask/">文档查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="documentTranscodingTask" action="${ctx}/sys/documentTranscodingTask/search" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>关键字：</label>
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-medium"/>
				<input type="hidden" name="content_type" value="1">
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<%-- <sys:message content="${message}"/> --%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文档名称</th>
				<shiro:hasPermission name="sys:documentTranscodingTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="documentTranscodingTask">
			<tr>
				<td>
					${documentTranscodingTask.title}
				</td>
				<shiro:hasPermission name="sys:documentTranscodingTask:edit"><td>
					<a  href="javascript:viewDocument('${documentTranscodingTask.id}')">查看</a>
					<a href="javascript:download('${documentTranscodingTask.id}')">下载</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>