<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
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
		<li class="active"><a href="${ctx}/oanotify/notifyType/getTypeList/">类型列表</a></li>
		<li><a href="${ctx}/oanotify/notifyType/addDictForm">类型添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dict" action="${ctx}/oanotify/notifyType/addNotifyType" method="post" class="form-horizontal">

		<div class="control-group">
			<label class="control-label">键值:</label>
			<div class="controls">
				<input name="value"  maxlength="50" class="required" value=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签:</label>
			<div class="controls">
				<input name="label"  maxlength="50" class="required" value=""/>
			</div>
		</div>
		<div class="control-group" style="display:none">
			<label class="control-label">类型:</label>
			<div class="controls">
				<input name="type" type="hidden" maxlength="50" class="required abc" value="oa_notify_type"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述:</label>
			<div class="controls">
				<input name="description"  maxlength="50" class="required" value=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<input name="sort" maxlength="11" class="required digits" value=""/>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<textarea name="remarks" rows="3" maxlength="200" class="input-xlarge" value=""></textarea>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>