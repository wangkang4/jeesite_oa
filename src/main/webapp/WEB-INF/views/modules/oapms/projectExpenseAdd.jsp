<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目费用添加</title>
<meta name="decorator" content="default" />
<style type="text/css">
.ok {
	color: green;
	font: normal normal 12px/28px '微软雅黑';
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
		$("#money").blur(function() {
			var money = $("#money").val();
			var moneyreg = /^-?\d*\.?\d*$/;
			if (!moneyreg.test(money.trim())||money=='') {
				$("#moneyText").text("请输入正确的数字金额！");
				$("#moneyText").addClass("err");
			} else {
				$("#moneyText").text("输入正确！");
				$("#moneyText").removeClass("err");
				$("#moneyText").addClass("ok");
			}
		})
	});
	function doSubmit(){
		var flag = 0;
		var status = $("#status").val();
		if (status == '') {
			flag = 1;
			$("#statusText").text("请选择状态类型！");
			$("#statusText").addClass("err");
		}
		debugger;
		var money = $("#money").val();
		var moneyreg = /^-?\d*\.?\d*$/;
		if (!moneyreg.test(money.trim())||money=='') {
			flag = 1;
			$("#moneyText").text("请输入正确的数字金额！");
			$("#moneyText").addClass("err");
		}
		if(flag==0){
			$("#addForm").submit();
		}
	}
	
	function testStatus(){
		var status = $("#status").val();
		if (status == '') {
			$("#statusText").text("请输入状态分类！");
			$("#statusText").addClass("err");
		} else {
			$("#statusText").text("输入正确！");
			$("#statusText").removeClass("err");
			$("#statusText").addClass("ok");
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/projectHelp/expenseList?id=${pmsProject.projectId}">项目费用列表</a></li>
		<li class="active"><a href="">项目费用添加</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="pmsProjectExpense"
		action="${ctx }/pms/projectHelp/expenseAdd" method="post"
		class="form-horizontal">
		<div class="control-group">
			<label class="control-label">项目名称:</label>
			<div class="controls">
				<form:hidden path="project.projectId" value="${pmsProject.projectId}" />
				<input type="text" value="${pmsProject.projectName }" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费用类别:</label>
			<div class="controls">
				<form:select id="status" path="status" class="input-medium" onchange="testStatus()">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('project_expense_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span id="statusText" class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费用申请者:</label>
			<div class="controls">
				<form:hidden path="createBy" value="${fns:getUser()}" />
				<input type="text" value="${fns:getUser().name}" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费用金额:</label>
			<div class="controls">
				<form:input id="money" path="money" htmlEscape="false" maxlength="100"/>
				<span id="moneyText" class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="button"
				onclick="doSubmit()" value="保存" /> &nbsp;
			<input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
	</form:form>
</body>
</html>