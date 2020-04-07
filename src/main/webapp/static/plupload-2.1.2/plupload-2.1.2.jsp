<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
 String ctx = request.getContextPath();
%>
<%-- <script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script> --%>
<script type="text/javascript" src="<%=ctx%>/static/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" defer="defer">
String.prototype.endWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substring(this.length-str.length)==str)
	  return true;
	else
	  return false;
	return true;
}
String.prototype.startWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substr(0,str.length)==str)
	  return true;
	else
	  return false;
	return true;
}
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith); 
	} else { 
		return this.replace(reallyDo, replaceWith); 
	} 
} 

$(window).load(function() {
	initAllFile();
});
/**
 * 删除父节点
 */
function removeThisParent(a){
	$(a).parent("div").remove();
}

/***
 * 判断返回是否json格式
 */
isJson = function(obj){
    var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
    return isjson;
}

/**
 * 初始化全部的File文件信息
 */
function initAllFile(){ 
	//setTimeout(function(){
		jQuery(".file").each(function(index) {
				var name=$(this).attr("name");
				var id=$(this).attr("id");
				var defalutVal = $(this).val();//默认值，初始化上传列表
				var oldFileName = $(this).attr("oldName");
				//var saveAnnex=$(this).attr("saveAnnex");
				var maxNumber=$(this).attr("maxNumber");
				var dataType=$(this).attr("dataType");
				var multi_selection=true;
				if(maxNumber==null || maxNumber==undefined){
					maxNumber=1;//默认只能上次一个文件
					multi_selection=false;
				}else if(maxNumber=='*'){
					maxNumber=100;
				}
				var title='支持全部文件格式 ';
				var extensions ='*';
				var max_file_size=$(this).attr("max_file_size");
				if(max_file_size==null || max_file_size==undefined){
					max_file_size="1000mb";
				}
				if(name!=undefined || name!=null){
					alert("File组件的Name属性不要填写！Id属性填写为后台接受的参数名即可。");
					return;
				}
				if(id==undefined || id==null ){
					alert("file组件必须填写id属性！请id的名字为后台参数接受名！");
					return;
				}else{
					var fileType =$(this).attr("fileType");
					if(fileType=='pic'){
						title='请上传图片格式文件 （jpg,png,bmp,gif,jpeg）';
						extensions ='jpg,png,bmp,gif,jpeg';//"jpg","jpeg","gif","png","bmp"
					}else if(fileType=='doc'){
						title='请上传office格式文件,pdf等 ';
						extensions ='doc,docx,xls,xlsx,ppt,pptx,txt,pdf,odt';
					}else if(fileType=='vod'){
						title='支持格式视频格式avi,mp4,wmv,flv,mov;等 ';
						extensions ='avi,mp4,wmv,flv,mov';
					}else if(fileType=='app'){
						title='支持app格式ipa、pxl、deb、apk;等 ';
						extensions ='ipa,pxl,deb,apk';
					}
				}
				var uploadfilesId=id+"uploadfilesId";
				var filelistDivId=id+"filelistDivId";
				if($("#"+filelistDivId).length<=0){//添加listdiv。到当前对象的后面
					$(this).after("<div id='"+filelistDivId+"'></div>");
				}
				if(maxNumber!=1){
					$(this).after("<input type='button' id='"+uploadfilesId+"' value='上传'>");
				}
				var browse_button=id;
				var containerId=null;
				initFile(id,maxNumber,browse_button,containerId,max_file_size,title,extensions,filelistDivId,uploadfilesId,multi_selection,defalutVal,filelistDivId,oldFileName,dataType);
		});
	//},1000);
}

//初始化全部的上传标签
function initFile(id,maxNumber,browse_button,containerId,max_file_size,title,extensions,filelistDivId,uploadfilesId,multi_selection,defalutVal,filelistDivId,oldFileName,dataType){
	var uploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : browse_button,//'pickfiles', // you can pass in id...
		//container: document.getElementById('container'), // ... or DOM Element itself
		url : '<%=ctx %>/sys/upload/upload?format=json',
		flash_swf_url : '<%=ctx%>/static/plupload-2.1.2/js/Moxie.swf',
		silverlight_xap_url : '<%=ctx%>/static/plupload-2.1.2/js/Moxie.xap',
		multi_selection:multi_selection,
		filters : {
			max_file_size : max_file_size,
			mime_types: [
				{title : title, extensions:extensions}
			]
		},
		init: {
			PostInit: function() {
				document.getElementById(filelistDivId).innerHTML = '';
				//如果有多个文件,多个文件使用;分割
				if(defalutVal!=undefined && defalutVal!=''){
					if(defalutVal.indexOf("},{")>-1){
						defalutVal = defalutVal.replaceAll("},{","};{");
					}else if(defalutVal.indexOf(",")>-1){
						defalutVal = defalutVal.replaceAll(",",";");
					}
					var vals = defalutVal.split(";");
					var oldFileNames = null;
					if(oldFileName!=null && oldFileName!=""){
						oldFileNames = oldFileName.split(";");	
					}
					for(var index=0;index<vals.length;index++){
						var oldName ="";
						var tempValue =vals[index];
						if(dataType=='json'){
							var jsonobj =eval('('+tempValue+')');
							oldName = jsonobj.oldFileName;
						}else{
							if(oldFileNames!=null && oldFileNames.length==vals.length){
								oldName = oldFileNames[index];
							}
						}
						//{"fileName":"http://127.0.0.1/vf/20150818132453.pdf","filePath":"D:/home/zjvideoweb/20150818132453.pdf","oldFileName":"公网托管服务器拓扑.pdf","url":"http://127.0.0.1/vf/20150818132453.pdf"}
						addFileListDiv(id,filelistDivId,id.substring(0,id.length-2),tempValue,index,oldName);
					}
				}
				if(maxNumber!=1){
					document.getElementById(uploadfilesId).onclick = function() {
						uploader.start();
						return false;
					};
				}
			},
			FilesAdded: function(up, files) {
				if($("."+id+"uploadFileList").length<maxNumber){
					plupload.each(files, function(file) {
						document.getElementById(filelistDivId).innerHTML += '<div class="'+id+'uploadFileList" id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b><a onclick=removeThisParent(this)   href=javascript:void(0)  >删除</a> </div>';
					});
					if(maxNumber==1){
						uploader.start();
					}
				}else{
					//删除up的信息
					$.each(up.files, function (i, file) {
				        up.removeFile(files);
				    });
					up.removeFile(files); 
					alert("最多只能上传"+maxNumber+"个");
				}
			},
			UploadProgress: function(up, file) {
				var fileobjdom =document.getElementById(file.id);
				if(fileobjdom !=null ){ 
					document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";	
				}
			},
			Error: function(up, err) {
				alert("出错,"+"\nError #" + err.code + ": " + err.message);
				//document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
			}
		}
	});
	//up 包涵Uploader的全部信息    file 刚刚上传成功的一个文件的基本信息     obj 后台返回的文件返回的基本信息
	uploader.bind("FileUploaded", function(up, file, obj) {
		var jsonresponse =eval('('+obj.response+')');
		var hiddenName=id.substring(0,id.length-2);//去掉后面的那个  ID字符串。
		//返回的是json对象。
		if(dataType=='json'){
			addFileListDiv(id,file.id,hiddenName,obj.response,null,jsonresponse.oldFileName,obj.response);
		}else{
			addFileListDiv(id,file.id,hiddenName,jsonresponse.fileName,null,jsonresponse.oldFileName,obj.response);
		}
		//$("#"+file.id).append("<input type='hidden' name='"+hiddenName+"' value="+jsonresponse.fileName+">");
    });
	uploader.init();
	/**
	 添加隐藏域到文件列表 的div 
	**/
	function addFileListDiv(id,parentId,hiddenName,value,index,oldFileName,jsonresponse){
		if(value!=null && value!=''){
			var info='';
			if(value.endWith("jpg")  || value.endWith("jpeg") || value.endWith("png") || value.endWith("gif")  || value.endWith("bmp")){
				info="<img src='"+value+"' height='80px' width='80px' style='max-height:300px;max-width: 300px' />";
			}
			oldFileName = oldFileName.replaceAll(" ","");
			if(index!=null){
				var disName=value;
				if(oldFileName!=null && oldFileName!=''){
					disName = oldFileName;
				}
				if(info!=''){//说明是图片不需要显示名称。
					disName='';
				}
				$("#"+parentId).append('<div class="'+id+'uploadFileList" id="' + index + '">'+info+' ' + disName + '   <input type=hidden name='+hiddenName+' value='+value+'> <b></b><a onclick=removeThisParent(this)   href=javascript:void(0)  >删除</a> </div>');
			}else{
				var addDoc="<input type='hidden' name='"+hiddenName+"' value="+value+"> <input type='hidden' name='"+hiddenName+"_oldname' value="+oldFileName+">";
				if(jsonresponse!=null && jsonresponse!=''){
					addDoc=addDoc+"<input type='hidden' name='"+hiddenName+"_json' value="+jsonresponse+">";
				}else{
					if(info == null || info==''){
						info = value;
					}
				}
				$("#"+parentId).append(info+addDoc);
			}
		}
	}
}
</script>
