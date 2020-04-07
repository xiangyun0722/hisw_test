<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>码流列表</title>
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

	<script type="text/javascript">
		$(document).ready(function() {
			$("#videoNameId").val("");
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
		function playSourceVideo(path){
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
			$.jBox('<video id="example_video_'+number+'" class="video-js vjs-big-play-centered vjs-fluid" controls>\n' +
					'\t\t</video>', {title:"视频播放",width: 850, height: 560, buttons:{"关闭":true},
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

		function playCdnVideo(path){
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
			$.jBox('<video id="example_video_'+number+'" class="video-js vjs-big-play-centered vjs-fluid" controls>\n' +
					'\t\t</video>', {title:"视频播放",width: 850, height: 560, buttons:{"关闭":true},
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
	<style>
	   .frontBold{color:#000; font-size:15px; font-weight:bold;}
	</style>
</head>
<body>
<div class="container-fluid" id="main-container">
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/zjvideo/videos/media/list">已处理视频列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/videos/media/videos/list">码流视频列表</a></li>
		<%-- <shiro:hasPermission name="zjvideo:videos:edit"><li><a href="${ctx}/zjvideo/videos/form">视频管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<%-- <c:if test="${total=='1'}">
		<div id="retranscode"> 
			<table width="400px" border="0px" align="left"> 
			　　<tr>
					<td align="left">
						<img src="${datail.picurl}" height="60" width="80"></img>
						<a href="${datail.picurl}" class="bwGal"><img src="${datail.picurl}" height="150" width="150"></img></a>						
					</td> 
					<td align="left" width="500px">
						<div>
							名称 <span class="frontBold">${datail.name}</span>
							<input id="addCell" name="name"  disabled="disabled" value="${datail.name}"/>
						</div>
						<div>
							所属项目 <span class="frontBold">${datail.project.name}</span>
							<input id="addRows"　name="project" disabled="disabled" value="${datail.project.name}"/>
						</div>
					</td>
				</tr> 
			</table> 
		</div> 
	</c:if> --%>
	<%-- <form:form id="searchForm" modelAttribute="videos" action="${ctx}/zjvideo/videos/media/videos/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>视频名称：</label>
				<form:input path="name" id="videoNameId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>视频格式:</label>
				<form:select path="format" class="input-medium">
					<form:options items="${fns:getDictList('template_format')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>视频状态:</label>
				<form:select path="type" class="input-medium">
					<form:options items="${fns:getDictList('template_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>				
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
    		<!-- <td style="vertical-align: bottom;"> -->
       		<li class="btns"><input id="searchZd" type="button" onclick="zdButFun(this)" value="启动自动查询(10s)"/></li>
       		<!-- </td> -->			
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<div>
	</div>
	<div>
	</div>
	<div id="retranscode" class="hide">

	</div>
<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<!-- <th>码流播放地址</th> -->
				<th>视频名称</th>
				<!-- <th>所属项目</th> -->
				<th>播放地址</th>
				<th>格式</th>
				<th>模板名称</th>
			<!-- 	<th>类型</th> -->
				<%--<th>视频封面</th>--%>
				<th>创建时间</th>
				<!-- <th>缓存CDN状态</th> -->
				<!-- <th>增加时间</th> -->
				<shiro:hasPermission name="zjvideo:videos:edit"><th>操作</th></shiro:hasPermission>
				<%-- <shiro:hasPermission name="zjvideo:videos:edit"><th>二维码下载</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="videos">
			<tr>
				<td>
					${videos.id}
				</td>	
		<%-- 		<td>
					<input type="text" value="${videos.path}" id="copy_txt${videos.id}" readonly="readonly"/>
					<a href="javascirpt:void();" id="copy_btn${videos.id}" data='${videos.id}' class="copyBtn">复制</a>
				</td> --%>		
				<td>
					${videos.name}
				</td>
		<%-- 		<td>
					${videos.project.name}
				</td>  --%>
				<td>
					${videos.cdnPath}
			</td>
				<td>
					${fns:getDictLabel(videos.format, 'template_format', '')}
				</td>
		<%-- 		<td>
					${fns:getDictLabel(videos.type, 'template_status', '')}
				</td>	 --%>
				<%--<td>
					<a href="${videos.picurl}" class="bwGal"><img src="${videos.picurl}" height="60" width="80"></img></a>
				</td>--%>
				<td>
					${videos.templateName}
				</td>
				<td>
					<fmt:formatDate value="${videos.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>	
			<%-- 	<td>
					${fns:getDictLabel(videos.cacheflag, 'cdn_cache_flag', '')}
				</td> --%>											
				<%--<td>
					<fmt:formatDate value="${videos.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<shiro:hasPermission name="zjvideo:videos:edit"><td>
					<a href="javascript:playSourceVideo('${videos.path}')">源站播放</a>
					<a href="javascript:playCdnVideo('${videos.cdnPath}')">cdn播放</a>
    				<%-- <a href="${ctx}/zjvideo/videos/form?id=${videos.id}">修改</a> --%>
	  <%-- 				<c:if test="${videos.iscache=='0'}">
						<a href="${ctx}/zjvideo/videos/update/active?id=${videos.id}" onclick="return confirmx('确认要开启吗？', this.href)">开启缓存</a>
					</c:if>
					<c:if test="${videos.iscache=='1'}">
						<a href="${ctx}/zjvideo/videos/update/suspend?id=${videos.id}" onclick="return confirmx('确认关闭吗？', this.href)">关闭缓存</a>
					</c:if>   --%>  				
					<a style="width: 30%" href="${ctx}/zjvideo/videos/delete/video/media?id=${videos.id}" onclick="return confirmx('确认要删除该视频吗？', this.href)">删除</a>
					<%-- <a href="${ctx}/zjvideo/videos/getQrcode?id=${videos.id}">下载</a> --%>
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