<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
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
	
	<script type="text/javascript">
		var listDescription = {};
		$(document).ready(function() {
			$.ajax({
				type : 'post',
				async:false,
				url : '${ctx}/oaproject/pmProjectMain/getCustomerList',
				dataType : 'json',
				success : function(data) {
					listDescription = data;
				},
				error : function(data) {
					alert("错误信息");
				}
			});
		});
		function getcustomer(btn) {
			var costTypeId = $(btn).val();
			debugger
			var costType=$(btn).find("option:selected").val();
			$("#customerId").empty();
			$("#customerId").append(
			"<option value=''>请选择</option>");
			$.each(listDescription[1], function(i, item) {
				if (item.projectId == costType) {
					$("#customerId").append(
						$('<option/>', {
							value : item.customerName,
							text : item.customerName
						}));
				} 
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oaproject/pmTaskMain/">单表列表</a></li>
		<li class="active"><a href="${ctx}/oaproject/pmTaskMain/form?id=${pmTaskMain.id}">单表查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pmTaskMain" action="${ctx}/oaproject/pmTaskMain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		
		<table class="table-form">
			<tr>
				<td><label class="control-label">任务主题</label></td>
				<td><form:input path="tashkTheme" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span></td>
				<td><label class="control-label">项目名称：</label></td>
				<td><select name='projectId' id="projectId" class="input-xlarge required" onchange="getcustomer(this)">
						<c:forEach  items="${pmProjectMainList}" var="item"  >
							<c:if test="${item.id == pmTaskMain.projectId }">
								<option value="${item.id}" selected="selected" >${item.projectName }</option>
							</c:if>
					       	<c:if test="${item.id != pmTaskMain.projectId }">
								<option value="${item.id}" selected="selected" >${item.projectName }</option>
							</c:if>
					    </c:forEach>
					</select>
				<span class="help-inline"><font color="red">*</font> </span></td>
			</tr>
			<tr>
				<td><label class="control-label">客户名称：</label></td>
				<td><%-- <form:input path="customerId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
					<span class="help-inline"><font color="red">*</font> </span> --%>
					<select name="customerId" id="customerId" class="input-medium" style="width: 270px;">
						<c:if test="${pmTaskMain.customerId !=null }">
							<option selected="selected" value="${pmTaskMain.customerId}">${pmTaskMain.customerId}</option>
						</c:if>
					</select>
				</td>
				<td><label class="control-label">任务类型:：</label></td>
				<td><form:select path="taskType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('taskType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
			</tr>
			<tr>
				<td><label class="control-label">项目阶段:：</label></td>
				<td><form:select path="projectStage" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_stage')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
				<td><label class="control-label">优先级:</label></td>
				<td><form:select path="priority" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('priority')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
			</tr>
			<tr>
				<td><label class="control-label">开始执行时间：</label></td>
				<td><input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pmTaskMain.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>
				<td><label class="control-label">结束执行时间：</label></td>
				<td><input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pmTaskMain.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>
			</tr>
			<tr>
				<td><label class="control-label">负责人：</label></td>
				<td><sys:treeselect id="responsibility" name="responsibility.id" value="${pmTaskMain.responsibility.id}" labelName="responsibility.name" labelValue="${pmTaskMain.responsibility.name}"
 					title="负责人" url="/sys/office/treeData?type=3"  allowClear="true" notAllowSelectParent="true"/></td>
				<td><label class="control-label">抄送人：</label></td>
				<td><sys:treeselect id="copy" name="copy.id" value="${pmTaskMain.copy.id}" labelName="copy.name" labelValue="${pmTaskMain.copy.name}"
 					title="抄送人" url="/sys/office/treeData?type=3"  allowClear="true" notAllowSelectParent="true" checked = "true"/></td>
			</tr>
			<tr>
				<td><label class="control-label">任务内容：</label></td>
				<td colspan="3"><form:textarea path="taskContent" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/></td>
			</tr>
			<tr>
				<td><label class="control-label">参考文档：</label></td>
				<td colspan="3">
					<form:hidden id="appendixId" path="appendixId" htmlEscape="false" maxlength="64" class="input-xlarge"/>
					<sys:ckfinder input="appendixId" type="files" uploadPath="/oaproject/pmTaskMain" selectMultiple="true"/>
				</td>
			</tr>
		</table>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>