<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>flexPaper 手机显示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/flexpaper/css/flexpaper.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/flexpaper/jquery.extensions.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/flexpaper/flexpaper.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/flexpaper/flexpaper_handlers.js"></script>
  </head>
  <script type="text/javascript">
  	var path = "<%=request.getParameter("path")%>";
  	path = "${pageContext.request.contextPath}/../"+path;
  	var totalPage="<%=request.getParameter("totalPage")%>";
  </script>
  <body>
<!--    <div id="documentViewer" class="flexpaper_viewer" style="position:absolute;left:10px;top:10px;width:650px;height:500px"></div> -->
   <div id="documentViewer" class="flexpaper_viewer" style="position:absolute;left:10px;top:10px;width:650px;height:500px"></div>
   <div id="showid"></div>
<script type="text/javascript">
            var iMGFiles = document.getElementById("IMGFiles").value+"_{page}.png";
            var jSONFile = document.getElementById("JSONFile").value;
            var startDocument = "Paper";
 jQuery.get((!window.isTouchScreen)?'UI_flexpaper_desktop.html':'UI_flexpaper_mobile_ios.html',
                    function(toolbarData) {
                        $('#documentViewer').FlexPaperViewer(
                                { config : {
									key : '@ad2c07bb266ee47ecc6$1bbc98323ee22db7a88',
                                    IMGFiles : iMGFiles,
                                    JSONFile : jSONFile,

                                    Scale : 1.2,
                                    ZoomTransition : 'easeOut',
                                    ZoomTime : 0.5,
                                    ZoomInterval : 0.2,
                                    FitPageOnLoad : false,
                                    FitWidthOnLoad : true,
                                    FullScreenAsMaxWindow : false,
                                    ProgressiveLoading : false,
                                    MinZoomSize : 0.2,
                                    MaxZoomSize : 5,
                                    SearchMatchAll : false,

                                    InitViewMode : 'Portrait',
                                    RenderingOrder : 'html,flash',
                                    StartAtPage : '',

                                    ViewModeToolsVisible : true,
                                    ZoomToolsVisible : false,
                                    NavToolsVisible : false,
                                    CursorToolsVisible : false,
                                    SearchToolsVisible : false,
                                    localeChain: 'en_US'
                                }}
                        );
                    });

	</script>
</div>
  </body>
</html>
