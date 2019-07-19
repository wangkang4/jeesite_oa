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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oaproject/pmProjectFeedback/">单表列表</a></li>
		<li class="active"><a href="${ctx}/oaproject/pmProjectFeedback/form?id=${pmProjectFeedback.id}">单表查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pmProjectFeedback" action="${ctx}/oaproject/pmProjectFeedback/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">任务名称：</label>
			<div class="controls">
				<form:input path="" htmlEscape="false" maxlength="64" class="input-xlarge " value="${pmTaskMain.tashkTheme}" />
				<input type="hidden" name="taskId" value="${pmTaskMain.id}"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">反馈时间：</label>
			<div class="controls">
				<input name="feedbackTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pmProjectFeedback.feedbackTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进展说明：</label>
			<div class="controls">
				<form:input path="progress" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他说明：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>