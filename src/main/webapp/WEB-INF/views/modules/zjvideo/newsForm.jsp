<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="java.util.Date"%>
<html>
<head>
	<title>资讯管理管理</title>
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
		<li><a href="${ctx}/zjvideo/news/">资讯管理列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/news/form?id=${news.id}">资讯管理<shiro:hasPermission name="zjvideo:news:edit">${not empty news.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:news:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="news" action="${ctx}/zjvideo/news/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">新闻标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属分类：</label>
			<div class="controls">
				<select name="clazzid" id="itemName" style="width: 118px">  
	                <!--  <option value="">请选择 </option>   -->
	                 <c:forEach items="${clazzs}" var="clazz">  
	                 	<c:if test="${clazz.id == news.clazzid}" > 
		                     <option selected="selected" value="${clazz.id}" >  
		                         ${clazz.name}  
		                     </option> 
		                 </c:if>  
						 <c:if test="${clazz.id != news.clazzid}">
	                		 <option value="${clazz.id}">
	                		 	${clazz.name}
	                		  </option>
	            		 </c:if>
	                 </c:forEach>  	                 
             	</select>  		
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">机顶盒缩略图：</label>
			<div class="controls">
				<button class="file" type="button" id="picurlID" fileType="pic" value="${news.picurl}" ><span class="glyphicon glyphicon-plus"><span>添加</button>
				<span class="help-inline"><font color="red">*</font></span>						
			</div>			
		</div>		
		<div class="control-group">
			<label class="control-label">标签：</label>
			<div class="controls">
				<form:input path="tag" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字列表：</label>
			<div class="controls">
				<form:input path="keylist" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">摘要信息：</label>
			<div class="controls">
				<form:input path="summary" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详情:</label>
			<div class="controls">
				<form:textarea id="copyright" htmlEscape="true" path="detail" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="copyright" uploadPath="/cms/site" />
			</div>
		</div>		
<%-- 		<div class="control-group">
			<label class="control-label">detail：</label>
			<div class="controls">
				<form:input path="detail" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">栏目id：</label>
			<div class="controls">
				<form:input path="clazzid" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">收藏数量：</label>
			<div class="controls">
				<form:input path="collectcount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">0:不是，1：是：</label>
			<div class="controls">
				<form:input path="isrecommend" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">赞数：</label>
			<div class="controls">
				<form:input path="praise" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">浏览次数：</label>
			<div class="controls">
				<form:input path="viewcount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">编辑：</label>
			<div class="controls">
				<form:input path="editer" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">编辑者姓名：</label>
			<div class="controls">
				<form:input path="editername" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否推荐</label>
			<div class="controls">
				<form:select path="isrecommend" class="input-xlarge ">
					<form:options items="${fns:getDictList('stu_ad_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('zjvideo_news_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</div>
		</div>				
<%-- 		<div class="control-group">
			<label class="control-label">发布时间：</label>
			<div class="controls">
				<form:input path="publishtime" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">0：默认，1：待审核，2：已经审核，3：预发布，4：发布：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('zjvideo_news_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">增加时间：</label>
			<div class="controls">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${news.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改时间：</label>
			<div class="controls">
				<input name="edittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${news.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">状态标志位0:正常，1：锁定：</label>
			<div class="controls">
				<form:input path="slock" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">picurl：</label>
			<div class="controls">
				<form:input path="picurl" htmlEscape="false" maxlength="155" class="input-xlarge "/>
			</div>
		</div> --%>
<%-- 		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:news:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>