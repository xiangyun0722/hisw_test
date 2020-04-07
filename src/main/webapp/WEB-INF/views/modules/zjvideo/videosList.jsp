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
		var time=0;
		var startFlag=false;
		var isRunFlag=false;
		function jishi(){ //程序会1s钟 调用一次
			isRunFlag=true;
			if(startFlag){
				++time;
				time=time%10;
				$("#searchZd").val("停止自动查询("+(10-time)+"s)");
				if(time==0){//能被60整除，触发刷新
					$("#searchForm").submit();
		        	return false;
				}
			}
		}

		function zdButFun(but){
			if(!isRunFlag){
				setInterval("jishi()",1000);
			}
			if(!startFlag){
				time=0;
				startFlag=true;//开启计时
				$(but).val("停止自动查询(10s)");
			}else{
				startFlag=false;//停止计时
				$(but).val("启动自动查询(10s)");
			}
		} 		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/zjvideo/videos/">转码任务列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:videos:edit"><li><a href="${ctx}/zjvideo/videos/form">添加视频</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="videos" action="${ctx}/zjvideo/videos/transcodetask/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>视频名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>所属课程：</label>
				<form:input path="programVolume.program.name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    <!-- <td style="vertical-align: bottom;"> -->
       		<li class="btns"><input id="searchZd" type="button" onclick="zdButFun(this)" value="启动自动查询(10s)"/></li>
       <!-- </td> -->			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>视频名称</th>
				<th>视频源地址</th>
				<th>视频播放地址</th>
				<th>视频封面</th>
				<th>所属章节名称</th>
				<th>所属课程</th>
				<th>增加时间</th>
				<th>转码任务生成状态</th>
				<shiro:hasPermission name="zjvideo:videos:edit"><th>操作</th></shiro:hasPermission>
				<shiro:hasPermission name="zjvideo:videos:edit"><th>二维码下载</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="videos">
			<tr>
				<td>
					${videos.name}
				</td>
				<td>
					${videos.source}
				</td>
				<td>
					${videos.path}
				</td>				
				<td>
					<img  height=60 width=80  src="${videos.picurl}" >
				</td>				
				<td>
					${videos.programVolume.name}
				</td>
				<td>
					${videos.programVolume.program.name}
				</td>				
				<td>
					<fmt:formatDate value="${videos.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<span class="label label-success arrowed"></span>
					${fns:getDictLabel(videos.generatetaskflag, 'video_generatetask_flag', '')}					
				</td>
				<shiro:hasPermission name="zjvideo:videos:edit"><td>
    				<%-- <a href="${ctx}/zjvideo/videos/form?id=${videos.id}">修改</a> --%>
					<a href="${ctx}/zjvideo/videos/delete/video?id=${videos.id}" onclick="return confirmx('确认要删除该视频吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				<shiro:hasPermission name="zjvideo:videos:edit"><td>
					<a href="${ctx}/zjvideo/videos/getQrcode?id=${videos.id}">下载</a>
				</td></shiro:hasPermission>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>