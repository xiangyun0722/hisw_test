<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转码管理</title>
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
		
		/***
		刷新状态
		***/
		function refreshStatus(){
			var ids="";
			$(".needrefresh").each(function(){
				ids=ids+",'"+$(this).attr("id")+"'";
		    });
			//ajax查询需要更新状态的任务信息。
			jQuery.ajaxSetup({ 
			 	cache:false
			});
			$.post("${ctx}/zjvideo/videotemplatere/refreshList",{ids:ids},function(data){
				//遍历全部的返回数据。并且把状态为2和3的class属性移除掉。
				if(data!=null){
					for(i=0;i<data.length;i++){
						setStatusSpan(data[i].id,data[i].status);
					}
				}
			});
		}
		var ref = setInterval(function(){
			refreshStatus();
		},10000);
		/***
		设置状态的显示内容
		***/
		function setStatusSpan(id,status){
			var span ="";
			if(status==-1){
				span = "<span class='label label-lg label-yellow arrowed-in'>待转码</span>";	
			}else if(status==0){
				span = "<span class='label label-info'>转码中</span>";
			}else if(status==1){
				span = "<span class='label label-success'>转码成功</span>";
			}else if(status==2){
				span = "<span class='label label-important'>转码失败</span>";
			}
			//设置id为这个值的
			$("#"+id).html(span);
			if(status==1 || status==2){
				$("#"+id).removeClass("needrefresh");
			}
			
		}		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/zjvideo/videos/transcodetask/list">转码任务列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:videos:edit"><li><a href="${ctx}/zjvideo/videos/form">添加视频</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="videotranscodingtask" action="${ctx}/zjvideo/videos/transcodetask/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>转码格式:</label>
				<form:select path="template.format" class="input-medium">
					<form:options items="${fns:getDictList('template_format')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>视频类型:</label>
				<form:select path="template.type" class="input-medium">
					<form:options items="${fns:getDictList('template_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>	
			 
			<li><label>转码状态:</label>
				<form:select path="convertstatus" class="input-medium">
					<form:options items="${fns:getDictList('video_convert_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    <!-- <td style="vertical-align: bottom;"> -->
       		<!-- <li class="btns"><input id="searchZd" type="button" onclick="zdButFun(this)" value="启动自动查询(10s)"/></li> -->
       <!-- </td> -->			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 20%">视频名称</th>
				<th style="width: 20%">视频源地址</th>
				<th>模板名称</th>
				<th>转码格式</th>
				<th>视频类型</th>
				<th>上传用户</th>
				<th>增加时间</th>
				<th>转码状态</th>
				<shiro:hasPermission name="zjvideo:videos:edit"><th>操作</th></shiro:hasPermission>
				<%-- <shiro:hasPermission name="zjvideo:videos:edit"><th>二维码下载</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="videotranscodingtask">
			<tr>
				<td>
					${videotranscodingtask.video.name}
				</td>			
				<td>
					${videotranscodingtask.video.source}
				</td>
				<td>
					${videotranscodingtask.template.name}
				</td>
				<td>
					${fns:getDictLabel(videotranscodingtask.template.format, 'template_format', '')}
				</td>	
				<td>
					${fns:getDictLabel(videotranscodingtask.template.type, 'template_status', '')}
				</td>
				<td>
						${videotranscodingtask.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${videotranscodingtask.video.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
		<%-- 		<td style="width: 60px;" class="center">
					<c:if test="${videotranscodingtask.convertstatus == '-1' }"><span title="${videotranscodingtask.convertmsg}" class="label label-info arrowed-in-right arrowed">等待转码</span></c:if>
					<c:if test="${videotranscodingtask.convertstatus == '0' }"><span title="${videotranscodingtask.convertmsg}" class="label label-warning">转码中</span></c:if>
					<c:if test="${videotranscodingtask.convertstatus == '1' }"><span title="${videotranscodingtask.convertmsg}" class="label label-important arrowed-in">转码成功</span></c:if>
					<c:if test="${videotranscodingtask.convertstatus == '2' }"><span title="${videotranscodingtask.convertmsg}" class="label label-large label-purple">转码失败</span></c:if>
				</td> --%>
				<td>
					<div id="${videotranscodingtask.id}"
						<c:if test="${videotranscodingtask.convertstatus!=1 && videotranscodingtask.convertstatus!=2}">
							class="needrefresh"
						</c:if>
						>
						<c:if test="${videotranscodingtask.convertstatus==-1}">
							<span class="label">待转码</span>
						</c:if>
						<c:if test="${videotranscodingtask.convertstatus==0}">
							<span class="label label-info">转码中</span>
						</c:if>
						<c:if test="${videotranscodingtask.convertstatus==1}">
							<span class="label label-success">转码成功</span>
						</c:if>
						<c:if test="${videotranscodingtask.convertstatus==2}">
							<span title="${videotranscodingtask.convertmsg}" class="label label-important" >转码失败</span>
						</c:if>
						<%--
						${fns:getDictLabel(documentTranscodingTask.status, 'transcoding_status', '')}
						--%>
					</div>
				</td>					
				<td>
					<a href="${ctx}/zjvideo/videotemplate/task/delete?id=${videotranscodingtask.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
					<c:if test="${videotranscodingtask.convertstatus==2}">
						<a href="${ctx}/zjvideo/videotemplate/task/reset?id=${videotranscodingtask.id}" onclick="return confirmx('确认要重转吗？', this.href)">重转</a>
					</c:if>
				</td>
			<%-- 	<shiro:hasPermission name="zjvideo:videos:edit"><td>
					<a href="${ctx}/zjvideo/videos/getQrcode?id=${videos.id}">下载</a>
				</td></shiro:hasPermission>		 --%>		
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>