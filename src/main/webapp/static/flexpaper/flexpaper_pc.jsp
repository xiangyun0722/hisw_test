<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文档显示</title>
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
jQuery.get((!window.isTouchScreen)?'${ctxStatic}/flexpaper/UI_flexpaper_desktop.html':'${ctxStatic}/flexpaper/UI_flexpaper_mobile.html',function(toolbarData) {
	//$("#showid").html(toolbarData);
 $('#documentViewer').FlexPaperViewer(
                       { config : {
  		   key : '@ad2c07bb266ee47ecc6$1bbc98323ee22db7a88',
           SWFFile : escape([path]),
         //IMGFiles : 'docs/Paper.pdf_{page}.png',
           //JSONFile : 'docs/Paper.js',
           //PDFFile : 'pdf/Paper.pdf',
           					Toolbar:toolbarData,
                           Scale : 0.6,
                           ZoomTransition : 'easeOut',
                           ZoomTime : 0.5,
                           ZoomInterval : 0.2,
                           FitPageOnLoad :  false,
                           FitWidthOnLoad :true,
                           FullScreenAsMaxWindow : false,
                           ProgressiveLoading : false,
                           MinZoomSize : 0.2,
                           MaxZoomSize : 5,
                           SearchMatchAll : false,
                           InitViewMode : 'Portrait',
                           RenderingOrder : 'flash,html',
                           StartAtPage : '',
						   PrintPaperAsBitmap:false,
                           ViewModeToolsVisible : true,
                           ZoomToolsVisible : true,
                           NavToolsVisible : true,
                           CursorToolsVisible : false,
                           SearchToolsVisible : false,
                           WMode : 'window',
                           localeChain: 'zh_CN'
                       }}
                   );
                    });
</script>
</div>
  </body>
</html>
