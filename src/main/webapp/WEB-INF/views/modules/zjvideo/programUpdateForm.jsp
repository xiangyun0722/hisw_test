<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理管理</title>
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
		<li><a href="${ctx}/zjvideo/program/">课程管理列表</a></li>
		<li class="active"><a href="${ctx}/zjvideo/program/form?id=${program.id}">课程管理<shiro:hasPermission name="zjvideo:program:edit">${not empty program.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="zjvideo:program:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="program" action="${ctx}/zjvideo/program/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">课程名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文名称：</label>
			<div class="controls">
				<form:input path="ename" htmlEscape="false" maxlength="150" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">可观看权限组：</label>
			<div class="controls">
				<form:input path="ename" htmlEscape="false" maxlength="150" class="input-xlarge "/>
				<c:forEach items="${groupList}" var="group">
					<c:forEach items="${groupInfo}" var="info">    
	               	    <c:if test="${group.id == info.id}" > 
	         				<form:checkbox path="groupId" value="${group.id}" checked="checked"  label="${group.groupname}" htmlEscape="false" maxlength="150" class="input-xlarge " /> 
	                    </c:if>  
					    <c:if test="${group.id != info.id}">
							<form:checkbox path="groupId" value="${group.id}" label="${group.groupname}" htmlEscape="false" maxlength="150" class="input-xlarge " />
	           		    </c:if>	
           		    </c:forEach>  				
				</c:forEach>  
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">可观看权限组:</label>
			<div class="controls">
				<form:checkboxes path="groupId" items="${groupList}" itemLabel="groupname" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>				
<%-- 		<div class="control-group">
			<label class="control-label">视频分类：</label>
			<div class="controls">
				<form:input path="clazzid" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">课程海报：</label>
			<div class="controls">
				<button class="file" type="button" id="picurlID" fileType="pic" value="${program.picurl}" ><span class="glyphicon glyphicon-plus"><span>添加</button>
				<span class="help-inline"><font color="red">*</font></span>					
			</div>				
		</div>
		<div class="control-group">
			<label class="control-label">主讲人：</label>
			<div class="controls">
			 	<select name=actor id="itemName" style="width: 118px">  
	                 <c:forEach items="${teacherList}" var="teacher">  
		                 <c:if test="${teacher.realname == program.actor}" > 
		                     <option selected="selected" value="${teacher.realname}" >  
		                         ${teacher.realname}  
		                     </option> 
		                 </c:if>  
						 <c:if test="${teacher.realname != program.actor}">
	                		 <option value="${teacher.realname}">
	                		 	${teacher.realname}
	                		  </option>
	            		 </c:if>	                    
	                 </c:forEach>  
             	</select>  				
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">视频导演：</label>
			<div class="controls">
				<form:input path="director" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">发行年份：</label>
			<div class="controls">
				<form:input path="iyear" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域信息：</label>
			<div class="controls">
				<form:input path="areaid" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">语言：</label>
			<div class="controls">
				<form:input path="language" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">简介：</label>
			<div class="controls">
				<form:textarea path="detail" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推荐：</label>
			<div class="controls">
				<form:select path="isrecommend" class="input-xlarge ">
					<form:options items="${fns:getDictList('program_recommend_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>		
		<%-- <div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keyword" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签：</label>
			<div class="controls">
				<form:input path="taglist" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频看点：</label>
			<div class="controls">
				<form:input path="viewpoint" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评级级别：</label>
			<div class="controls">
				<form:input path="starlevel" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总集数：</label>
			<div class="controls">
				<form:input path="volume" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">视频状态</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('program_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>				
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">上映日期：</label>
			<div class="controls">
				<input name="publishtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${program.publishtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核者：</label>
			<div class="controls">
				<form:input path="auditer" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核时间：</label>
			<div class="controls">
				<input name="audittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${program.audittime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核失败原因：</label>
			<div class="controls">
				<form:textarea path="auditreason" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">增加时间：</label>
			<div class="controls">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${program.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">增加者：</label>
			<div class="controls">
				<form:input path="adder" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">最后修改时间：</label>
			<div class="controls">
				<input name="edittime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${program.edittime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最后修改者：</label>
			<div class="controls">
				<form:input path="editer" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">锁定标志：</label>
			<div class="controls">
				<form:input path="slock" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">1:推荐 0：不推荐：</label>
			<div class="controls">
				<form:input path="isrecommend" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">isnew：</label>
			<div class="controls">
				<form:input path="isnew" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">playtimes：</label>
			<div class="controls">
				<form:input path="playtimes" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">viewtimes：</label>
			<div class="controls">
				<form:input path="viewtimes" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">collectiontimes：</label>
			<div class="controls">
				<form:input path="collectiontimes" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">teacherno：</label>
			<div class="controls">
				<form:input path="teacherno" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">课程时长：</label>
			<div class="controls">
				<form:input path="hour" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">1.课堂直播，默认代表其它：</label>
			<div class="controls">
				<form:input path="isonline" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务器ID：</label>
			<div class="controls">
				<form:input path="serverid" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">主办单位：</label>
			<div class="controls">
				<form:input path="host" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">0.未开始 1.正在在线课堂 2.已经完成：</label>
			<div class="controls">
				<form:input path="flag" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="zjvideo:program:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<%@ include file="/static/plupload-2.1.2/plupload-2.1.2.jsp"%>	
</body>
</html>