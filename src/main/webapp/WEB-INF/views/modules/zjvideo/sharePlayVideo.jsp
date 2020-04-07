<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>视频播放</title>
    <link href="${ctxStatic}/vide7.4.1/css/video-js.min.css" rel="stylesheet">
    <script src="${ctxStatic}/jquery/jquery-2.1.1.min.js" type="text/javascript" charset="utf-8"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        html,body{
            height: 100%;
        }
        body{
            background-image:url( '${ctxStatic}/img/bg.jpg');
            background-repeat: no-repeat;
            background-size: 100% 100%;
            position: relative;
        }
        #videoPlays{
            width:70%;
            height: 80%;
            border: 8px grey solid;
            border-radius: 10px;
            position: absolute;
            top: 10%;
            left: 15%;
        }
    </style>

</head>
<body>
<video id="videoPlays"   class="video-js vjs-big-play-centered" controls>
</video>
<script type="text/javascript" src="${ctxStatic}/vide7.4.1/js/video.min.js"></script>
<script src="${ctxStatic}/common/zh-CN.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
    var m3u8src= '${videoUrl}';
    var mp4Index = m3u8src.indexOf("mp4");
    var m3u8Index = m3u8src.indexOf("m3u8");
    var type;
    if(mp4Index > 0){
        type = "video/mp4";
    }
    if(m3u8Index > 0){
        type = "application/x-mpegURL";
    }
    var videojs= videojs('videoPlays', {
        language: 'zh-CN',
        controls: true,
        autoplay: false,
        //playbackRates:[1,1.5,2,4],
        sources: [{
            src:m3u8src,
            type: type
        },
        ],
        preload: 'auto'
    });
</script>
</body>
</html>
