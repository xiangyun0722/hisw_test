<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.*"%>
<html>
<head>
	<title>分配角色</title>
	<meta name="decorator" content="blank"/>
	<script type="text/javascript">
		$(document).ready(function() {
		    var $subBox = $("input[name='ids']");
            $subBox.click(function(){
				var str = '';
				for(var i=0;i < document.getElementsByName('ids').length;i++)
				{
			  		  if(document.getElementsByName('ids')[i].checked){
			  			if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
			  			$("#videoId").val(str);
					  }
				}	
            });	
            $subBox.each(function(){
	    		if ($(this).val()=="${value}"){
	    			$(this).attr("checked",true);
	    		}
	    	});

		    $subBox.dblclick(function(){
    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
	    	});
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
	<form:form id="searchForm" modelAttribute="videos" action="${ctx}/zjvideo/videos/unUsedList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" name="value" id="videoId" value="${value}" />
		<ul class="ul-form">
			<li><label>视频名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">视频编号</th>
				<th>视频名称</th>
				<th>视频源地址</th>
				<!-- <th>视频播放地址</th> -->
				<!-- <th>视频封面</th> -->
				<th>增加时间</th>
				<!-- <th>转码描述</th> -->
				<!-- <th>二维码下载</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="videos">
			<tr>
				<td style="text-align:center;">
					<input type="radio" name="ids" id="btnId" value="${videos.id}" " />
				</td>			
				<td>
					${videos.name}
				</td>
				<td>
					${videos.source}
				</td>
		<%-- 		<td>
					${videos.path}
				</td> --%>					
		<%-- 		<td>
					<img  height=60 width=80  src="${videos.picurl}" >
				</td>	 --%>			
				<td>
					<fmt:formatDate value="${videos.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
	<%-- 			<td>
					${fns:getDictLabel(videos.convertstatus, 'video_convert_status', '')}					
				</td> --%>
<%-- 				<td>
					${videos.convertmsg}
				</td> --%>
			<%-- 	<shiro:hasPermission name="zjvideo:videos:edit"><td>
    				<a href="${ctx}/zjvideo/videos/getQrcode?id=${videos.id}">下载</a>
				</td></shiro:hasPermission>	 --%>		
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>