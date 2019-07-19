<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存成功管理</title>
	<meta name="decorator" content="default"/>
<style type="text/css">
.ok {
	color: green;
	font: normal normal 12px/28px '微软雅黑';
	padding: 0px 5px;
}

b {
	color: red;
	font: normal normal 15px/28px '微软雅黑';
	padding: 0px 5px;
}

.err {
	color: red;
	font: normal normal 12px/28px '微软雅黑';
	padding: 0px 5px;
}
</style>			
			
	<script type="text/javascript">
	
	$(function() {
		$("#clientName").blur(function() {
			var clientName = $("#clientName").val();
			if (clientName.length < 1) {
				$("#a").html("请输入客户名称！");
				$("#a").addClass("err");
				$("#clientName").focus();
			} else if (clientName.length >= 1) {
				$("#a").html("输入符合！");
				$("#a").removeClass("err");
				$("#a").addClass("ok");
			}
		});
		
	});
	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/client/list">客户管理列表</a></li>
		<li class="active"><a href="${ctx}/oa/client/form">客户添加</a></li>
	</ul><br/>
	<input id="sign" type="hidden" />
	<form:form id="searchForm" modelAttribute="client" action="${ctx}/oa/client/update" method="post" class="form-horizontal">
		<form:hidden path="id" value="${id}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">客户名称：</label>
			<div class="controls">
				<form:input id="clientName" path="clientName" value="${id}"/>
				<span id="a" class="help-inline"><font color="red">*</font>必填 </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户所属公司：</label>
			<div class="controls">
				<form:input id="clientCompany" path="clientCompany" value="${clientCompany}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户负责人名称：</label>
			<div class="controls">
				<form:input path="clientManagerName" value="${clientManagerName}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户负责人性别：</label>
			<div class="controls">
				<form:input path="clientManagerSex" value="${clientManagerSex}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户负责人手机：</label>
			<div class="controls">
				<form:input path="clientManagerPhone" value="${clientManagerPhone}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="content" value="${content}"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="button"
				onclick="doSubmit()" value="保存" /> &nbsp;
			<input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
	</form:form>
	
	<script type="text/javascript">
	function doSubmit() {
		$("#sign").val(0);
		var clientName = $("#clientName").val();
		if (clientName.length < 1) {
			$("#a").html("请输入客户名称！");
			$("#a").addClass("err");
			$("#sign").val(0);
			$("#clientName").focus();
			return;
		} else if (clientName.length >= 1) {
			$("#a").html("输入符合！");
			$("#a").removeClass("err");
			$("#a").addClass("ok");
			$("#sign").val(1);
		}
		
		var sign = $("#sign").val();
		if (sign == 1) {
			$("#searchForm").submit();
		}
	}
	</script>
</body>

</html>