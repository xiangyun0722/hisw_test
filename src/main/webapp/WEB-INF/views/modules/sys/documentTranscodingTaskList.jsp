<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档转码任务管理</title>
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
		刷新状态
		***/
		function refreshStatus(){
			var ids="";
			$(".needrefresh").each(function(){
				ids=ids+",'"+$(this).attr("id")+"'";
		    });
			if( ids != '' ){
				//ajax查询需要更新状态的任务信息。
				jQuery.ajaxSetup({ 
				 	cache:false
				});
				$.post("${ctx}/sys/documentTranscodingTask/refreshList",{ids:ids},function(data){
					//遍历全部的返回数据。并且把状态为2和3的class属性移除掉。
					if(data!=null){
						for(i=0;i<data.length;i++){
							setStatusSpan(data[i].id,data[i].status);
						}
					}
				});
			}
		}
		var ref = setInterval(function(){
			refreshStatus();
		},10000);
		/***
		设置状态的显示内容
		***/
		function setStatusSpan(id,status){
			var span ="";
			if(status==0){
				span = "<span class='label label-lg label-yellow arrowed-in'>待转码</span>";	
			}else if(status==1){
				span = "<span class='label label-info'>转码中</span>";
			}else if(status==2){
				span = "<span class='label label-success'>转码成功</span>";
			}else if(status==3){
				span = "<span class='label label-important'>转码失败</span>";
			}
			//设置id为这个值的
			$("#"+id).html(span);
			if(status==2 || status==3){
				$("#"+id).removeClass("needrefresh");
			}
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/documentTranscodingTask">文档转码任务列表</a></li>
		<shiro:hasPermission name="sys:documentTranscodingTask:edit"><li><a href="${ctx}/sys/documentTranscodingTask/form">文档转码任务添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="documentTranscodingTask" action="${ctx}/sys/documentTranscodingTask/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>处理主机IP：</label>
				<form:input path="dealHostIp" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> 
			<li><label>业务id：</label>
				<form:input path="businessId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> 
			--%>
			<li><label>文档名称：</label>
				<form:input path="originalName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>文件地址：</label>
				<form:input path="filepath" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>转码状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请您选择"/>
					<form:options items="${fns:getDictList('transcoding_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>转码任务编号</th>
<!-- 				<th>处理主机IP</th> -->
				<th>文档名称</th>
				<th>大小</th>
<!-- 				<th>文件地址</th> -->
				<th>转码状态</th>
				<th>总页数</th>
<!-- 				<th>swfs文件地址</th> -->
<!-- 				<th>单个swf文件地址</th> -->
<!-- 				<th>图片地址</th> -->
<!-- 				<th>js文件</th> -->
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:documentTranscodingTask:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="documentTranscodingTask">
			<tr>
				<td>
					<a href="${ctx}/sys/documentTranscodingTask/form?id=${documentTranscodingTask.id}">
						${documentTranscodingTask.businessId}
					</a>
				</td>
<!-- 				<td> -->
<%-- 					${documentTranscodingTask.dealHostIp} --%>
<!-- 				</td> -->
				<td>
					${documentTranscodingTask.originalName}
				</td>
				<td>
					<fmt:formatNumber value="${documentTranscodingTask.length/1024/1024}" pattern="0.0#"/>M
				</td>
<!-- 				<td> -->
<%-- 					${documentTranscodingTask.filepath} --%>
<!-- 				</td> -->
				<td>
					<div id="${documentTranscodingTask.id}"
						<c:if test="${documentTranscodingTask.status!=2 && documentTranscodingTask.status!=3}">
							class="needrefresh">
						</c:if>
 						<c:if test="${documentTranscodingTask.status==0}">
							<span class="label">待转码</span>
						</c:if>
						<c:if test="${documentTranscodingTask.status==1}">
							<span class="label label-info">转码中</span>
						</c:if>
						<c:if test="${documentTranscodingTask.status==2}">
							<span class="label label-success">转码成功</span>
						</c:if>
						<c:if test="${documentTranscodingTask.status==3}">
							<span class="label label-important">转码失败</span>
						</c:if>
						<%--
						${fns:getDictLabel(documentTranscodingTask.status, 'transcoding_status', '')}
						--%>
					</div>
				</td>
				<td>
					${documentTranscodingTask.totalPageNumber}
				</td>
<!-- 				<td> -->
<%-- 					${documentTranscodingTask.swfsPath} --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${documentTranscodingTask.swfPath} --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${documentTranscodingTask.imagesPath} --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${documentTranscodingTask.jsPath} --%>
<!-- 				</td> -->
				<td>
					<fmt:formatDate value="${documentTranscodingTask.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${documentTranscodingTask.remarks}
				</td>
				<shiro:hasPermission name="sys:documentTranscodingTask:edit"><td>
					<c:if test="${documentTranscodingTask.status==2}">
						<a  href="javascript:viewDocument('${documentTranscodingTask.swfPath}')">查看</a>
					</c:if>
					<a href="${ctx}/sys/documentTranscodingTask/download?id=${documentTranscodingTask.id}">下载</a>
    				<a href="${ctx}/sys/documentTranscodingTask/form?id=${documentTranscodingTask.id}">修改</a>
					<a href="${ctx}/sys/documentTranscodingTask/delete?id=${documentTranscodingTask.id}" onclick="return confirmx('确认要删除该文档转码任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>