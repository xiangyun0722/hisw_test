使用说明：
1：在有需要的页面最后面 引入
 <%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>
（注意：这个需要放到 </body>的前面一行）

例如：
	<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>
</body>
</html>

 
2:在有需要上传的地方修改为例如：

<button class="file" type="button" id="imageID" fileType="pic" value="${coach.image}"><span class="glyphicon glyphicon-plus"><span>添加</button>


class 必须为 file
type  必须为button
id为 后台接受的属性+大写ID  例如： "imageID"
value 为默认值
maxNumber 最多的上传数。
dataType： json 如果填添加这个参数，则返回的数据格式默认为 json格式。保存到数据库中，其他的为字符串。
fileType属性可以去掉不填，默认全部，  
pic 为 图片格式文件 （jpg,png,bmp）  。
doc 为 office格式 doc,docx,xls,xlsx,ppt,pptx,txt,pdf,odt
vod 为视频类型格式 avi,mp4,wmv,flv,mov
app 为应用类型 'ipa,pxl,deb,apk'
如果不够可以在plupload-2.1.2.jsp中修改。