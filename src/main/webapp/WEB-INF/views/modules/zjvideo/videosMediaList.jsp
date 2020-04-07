<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>已处理视频列表</title>
	<meta name="decorator" content="default"/>
	<!--查看图片插件 -->
	<%--<link rel="stylesheet" media="screen" type="text/css" href="${ctxStatic}/zoomimage/css/zoomimage.css" />
    <link rel="stylesheet" media="screen" type="text/css" href="${ctxStatic}/zoomimage/css/custom.css" />
	<script type="text/javascript" src="${ctxStatic}/zoomimage/js/jquery.js"></script>   
    <script type="text/javascript" src="${ctxStatic}/zoomimage/js/eye.js"></script>
    <script type="text/javascript" src="${ctxStatic}/zoomimage/js/utils.js"></script>
    <script type="text/javascript" src="${ctxStatic}/zoomimage/js/zoomimage.js"></script>
    <script type="text/javascript" src="${ctxStatic}/zoomimage/js/layout.js"></script>
	<script src="${ctxStatic}/zoomimage/js/ace-elements.min.js"></script>
	<script src="${ctxStatic}/zoomimage/js/ace.min.js"></script>    
	<script type="text/javascript" src="${ctxStatic}/zeroClipboard/ZeroClipboard.js"></script>--%>
	<!--video.js插件-->
	<link href="${ctxStatic}/vide7.5.5/css/video-js.min.css" rel="stylesheet">
	<script type="text/javascript" src="${ctxStatic}/vide7.5.5/js/video.min.js"></script>
	
	<!--查看图片插件 -->	
	<script type="text/javascript">
		$(document).ready(function(){
		});
		function getRandomCode(length) {
			if (length > 0) {
				var data = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"];
				var nums = "";
				for (var i = 0; i < length; i++) {
					var r = parseInt(Math.random() * 61);
					nums += data[r];
				}
				return nums;
			} else {
				return false;
			}
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function startTranscode(id){
			$("#videoId").val(id);
			$.jBox($("#retranscode").html(), {title:"转码模板",width: 700, height: 400, buttons:{"关闭":true},
				bottomText:"请您选择模板进行转码"});
		}

		function share(path){
			window.open(path,"_blank");
		}
		function playVideo(path){
			var number = getRandomCode(5);
			var player;
			var mp4Index = path.indexOf("mp4");
			var m3u8Index = path.indexOf("m3u8");
			var type;
			if(mp4Index > 0){
				type = "video/mp4";
			}
			if(m3u8Index > 0){
				type = "application/x-mpegURL";
			}
			$.jBox("<video id='example_video_"+number+"' class='video-js vjs-big-play-centered vjs-fluid' controls>" +
					"\t\t</video>", {title:"视频播放",width: 850, height: 560, buttons:{"关闭":true},
				submit:function(v, h, f){
					if (v==true){
						player.dispose();
					}
				}});
			player = videojs('example_video_'+number+'',{
				controls: true,
				autoplay: true,
				sources: [
					{
						src:path,
						type: type
					},
				],
				preload: 'auto'
			});
		}
	</script>
</head>
<body>
<div class="container-fluid" id="main-container">
<!-- 	<textarea id="text">点击下面的按钮复制我吧！</textarea><br/> -->
<!-- 	<button id="btn">点击复制</button> -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/zjvideo/videos/media/list">已完成视频列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:videos:edit"><li><a href="${ctx}/zjvideo/videos/form">视频管理添加</a></li></shiro:hasPermission> --%>
	</ul>
<%-- 	<sys:message content="${message}"/>
	<div id="retranscode" class="hide">
		<form:form id="inputForm" modelAttribute="videos" action="${ctx}/zjvideo/videos/transcode/start" method="post" class="form-horizontal">
			<input id="videoId" name="id" type="hidden" value=""/>
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
	</div> --%>		
	<form:form id="searchForm" modelAttribute="videos" action="${ctx}/zjvideo/videos/media/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>视频名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th style="width: 20%">名称</th>
				<!-- <th>播放地址</th> -->
				<!-- <th>项目</th>
				<th>源文件</th> -->
				<th style="width: 8%">封面</th>
				<th style="width: 15%">完成时间</th>
				<th style="width: 10%">视频时长</th>
				<%--<th style="width: 15%">单位名称</th>--%>
				<th style="width: 12%">项目名称</th>
				<%--<th style="width: 20%">播放列表</th>--%>
				<shiro:hasPermission name="zjvideo:videos:edit"><th style="width: 10%">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="videos">
			<tr>
				<td>
					${videos.name}
				</td>
		<%-- 		<td>
					<input type="text" value="${videos.path}" id="copy_txt${videos.id}" readonly="readonly"/>
					<a href="javascirpt:void();" id="copy_btn${videos.id}" data='${videos.id}' class="copyBtn">复制</a>
				</td> --%>
				<%-- <td>
					${videos.project.name}
				</td>
				<td>
					${videos.source}
				</td> --%>
				<td>
					<%-- <img  height=60 width=80  src="${videos.picurl}" > --%>
					<a href="javascript:playVideo('${videos.path}')" class="bwGal"><img src="${videos.picurl}" height="60" width="80"></img></a>
				</td>				
				<td>
					<fmt:formatDate value="${videos.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:secToTime(videos.length)}
				</td>
				<%--<td>
						${videos.companyName}
				</td>--%>
				<td>
						${videos.project.name}
				</td>
				<%--<shiro:hasPermission name="zjvideo:videos:edit"><td>
    				<a href="${ctx}/zjvideo/videos/media/videos?id=${videos.id}">播放列表</a>
				</td></shiro:hasPermission>	--%>
			    <shiro:hasPermission name="zjvideo:videos:edit"><td style="width: 100px;" class="center">
					<a href="${ctx}/zjvideo/videos/media/videos?id=${videos.id}">播放列表</a>
					<%--<a href="javascript:playVideo('${videos.path}')">预览</a>--%>
					<a href="javascript:share('http://eduliveweb.3xmt.com/share/videoplayer.html?videoUrl=${videos.path}')">分享</a>
					<a href="${ctx}/zjvideo/videos/delete/video?id=${videos.id}" onclick="return confirmx('确认要删除该视频吗？', this.href)">删除</a>
				<%-- 	<a href="${ctx}/zjvideo/videos/getQrcode?id=${videos.id}">下载</a>
					<a href="${ctx}/zjvideo/videos/retranscode?id=${videos.id}">重转</a> --%>
					<%-- <a href="javascript:startTranscode('${videos.id}')">重转</a> --%>
				</td></shiro:hasPermission>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<style type="text/css">
		li {list-style-type:none;}
	</style>
	<ul class="navigationTabs">
           <li><a></a></li>
           <li></li>
       </ul>
    <script type="text/javascript">
    $(".copyBtn").each(function(i){
        var id = $(this).attr('data');
        var clip=null;
        clip = new ZeroClipboard.Client();
        ZeroClipboard.setMoviePath('${ctxStatic}/zeroClipboard/ZeroClipboard.swf');  //和html不在同一目录需设置setMoviePath
        ZeroClipboard.setMoviePath('${ctxStatic}/zeroClipboard/ZeroClipboard10.swf');
        clip.setHandCursor(true);
        clip.setText( $("#copy_txt"+id).val() );
        clip.addEventListener('complete', function (client, text) {
        	$.jBox.tip('已复制到剪切板！');
        });
        clip.glue( 'copy_btn'+id);
 	});
    </script>
</body>
</html>