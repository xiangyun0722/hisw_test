<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>视频模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/zjvideo/template/">视频模板列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/template/form?id=${template.id}">视频模板<shiro:hasPermission name="zjvideo:template:edit">${not empty template.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:template:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="templates" action="${ctx}/zjvideo/template/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模板名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模板命令：</label>
			<div class="controls">
				<form:textarea path="cmd" htmlEscape="false" rows="6" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转换视频格式：</label>
			<div class="controls">
	<%-- 			<form:select path="format" class="input-xlarge ">
					<form:options items="${fns:getDictList('template_format')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				<select name=format id="itemName" style="width: 118px">  
	                 <c:forEach items="${dictFormats}" var="dictFormat">  
	                 	<c:if test="${dictFormat.value == template.format}" > 
		                     <option selected="selected" value="${dictFormat.value}" >  
		                         ${dictFormat.label}  
		                     </option> 
		                 </c:if>  
						 <c:if test="${dictFormat.value != template.format}">
	                		 <option value="${dictFormat.value}">
	                		 	${dictFormat.label}
	                		  </option>
	            		 </c:if>
	                 </c:forEach>  
             	</select>				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频清晰度：</label>
			<div class="controls">
				<select name=type id="itemName" style="width: 118px">  
	                 <c:forEach items="${dictStatus}" var="dictStatu">  
	                 	<c:if test="${dictStatu.value == template.type}" > 
		                     <option selected="selected" value="${dictStatu.value}" >  
		                         ${dictStatu.label}  
		                     </option> 
		                 </c:if>  
						 <c:if test="${dictStatu.value != template.type}">
	                		 <option value="${dictStatu.value}">
	                		 	${dictStatu.label}
	                		  </option>
	            		 </c:if>
	                 </c:forEach>  
             	</select>				
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">水印图片:</label>
			<div class="controls">
				<button class="file" type="button"  id="watermarkID" fileType="pic" value="${template.watermark}" ><span class="glyphicon glyphicon-plus"><span>添加</button>
				<span class="help-inline"><font color="red"></font></span>
			</div>
		</div>		 --%>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:template:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>	
</body>
</html>