<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待处理视频列表</title>
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
		function startfun(url){
			var submit = function (v, h, f) {
			    if (v == 'ok'){
			    	location.href = url;	
			    	//jBox.tip(v, 'info');
			    }else if (v == 'cancel')
			        //jBox.tip(v, 'info');
			    return true; //close
			};
			$.jBox.confirm("确定对选中视频进行转码操作？<br/>将会根据您在“全局设置”里的设置进行转码", "提示", submit);
		}
		
		function startTranscode(id){
			$("#videoId").val(id);
				$.jBox($("#retranscode").html(), {title:"转码模板",width: 700, height: 400, buttons:{"关闭":true},
				bottomText:"请您选择模板进行转码"});
		}		
	</script>
</head>
<body>
<div class="container-fluid" id="main-container">
	<ul class="nav nav-tabs">
		<shiro:hasPermission name="zjvideo:videos:edit"><li class="active"><a href="${ctx}/zjvideo/videos/quequ/list">待处理视频列表</a></li></shiro:hasPermission>
		<%-- <li><a href="${ctx}/zjvideo/videos/form?id=${videos.id}">视频<shiro:hasPermission name="zjvideo:videos:edit">${not empty videos.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:videos:edit">查看</shiro:lacksPermission></a></li> --%>
	</ul>
	<%-- <sys:message content="${message}"/> --%>
	
	<form:form id="searchForm" modelAttribute="videos" action="${ctx}/zjvideo/videos/quequ/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<%-- 	<ul class="ul-form">
			<li><label>视频名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>所属课程：</label>
				<form:input path="programVolume.program.name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>			
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
    <!-- <td style="vertical-align: bottom;"> -->
       		<!-- <li class="btns"><input id="searchZd" type="button" onclick="zdButFun(this)" value="启动自动查询(10s)"/></li> -->
       <!-- </td> -->			
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	
	<div id="retranscode" class="hide">
		<form:form id="inputForm" modelAttribute="videos" action="${ctx}/zjvideo/videos/transcode/start" method="post" class="form-horizontal">
			<input id="videoId" name="id" type="hidden" value=""/>
			<sys:message content="${message}"/>		
			<div class="control-group">
				<label class="control-label">项目转码模板:</label>
				<div class="controls">
					<form:checkboxes element="br" path="templateId" items="${templatesList}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="form-actions">
				<shiro:hasPermission name="zjvideo:videos:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></shiro:hasPermission>
			</div>
		</form:form>
	</div>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>视频名称</th>
				<th>所属项目</th>
				<th>视频源地址</th>
				<!-- <th>视频播放地址</th>
				<th>视频封面</th>
				<th>所属章节名称</th>
				<th>所属课程</th> -->
				<th>管理人</th>
				<th>增加时间</th>
				<!-- <th>更新时间</th> -->
				<!-- <th>转码任务生成状态</th> -->
				<!-- <th>转码描述</th> -->
				<shiro:hasPermission name="zjvideo:videos:edit">
				<!-- <th>转码</th> -->
				<th>操作</th>
				</shiro:hasPermission>
				<%-- <shiro:hasPermission name="zjvideo:videos:edit"><th>二维码下载</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="videos">
			<tr>
				<td>
					${videos.name}
				</td>
				<td>
					${videos.project.name}
				</td>
				<td>
					${videos.source}
				</td>
				<%-- <td>
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
				</td>	 --%>			
		<%-- 		<td>
					<fmt:formatDate value="${videos.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>${videos.createBy.name}</td>
		 		<td>
					<fmt:formatDate value="${videos.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>			
			<%-- 	<td>
					<span class="label label-success arrowed">
					${fns:getDictLabel(videos.generatetaskflag, 'video_generatetask_flag', '')}					
				</td> --%>
	<%-- 			<td>
					${videos.convertmsg}
				</td> --%>
				<shiro:hasPermission name="zjvideo:videos:edit">
				<td>
				<!-- 这个是老版本的程序 --> 
<%-- 					<a href="${ctx}/zjvideo/videos/transcode/form?id=${videos.id}">转码</a>  --%>
						<%-- <a onclick="startfun('${ctx}/zjvideo/videos/transcode/start?id=${videos.id}')" href="javascript:void();">转码</a> --%>
						<%-- <a onclick="startfun('${ctx}/zjvideo/videos/transcode/start?id=${videos.id}')" href="javascript:void();">转码</a> --%>
    					<%-- <a href="javascript:startTranscode('${videos.id}')">转码</a> --%>
    					<%-- <a href="javascript:startTranscode('${videos.id}')">转码</a> --%>
    					<a href="javascript:startTranscode('${videos.id}')">转码</a>
    					<%-- <input id="transcodebtn" class="btn" name="transcod" value="${videos.id}">转码</div> --%>
    					<%-- <a href="${ctx}/zjvideo/videos/form?id=${videos.id}">修改</a> --%>
						<a href="${ctx}/zjvideo/videos/delete/video?id=${videos.id}" onclick="return confirmx('确认要删除该视频吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>	
</body>
</html>