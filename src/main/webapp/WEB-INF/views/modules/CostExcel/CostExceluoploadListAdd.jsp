<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>保存成功管理</title>
<meta name="decorator" content="default" />
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
		$("#clientId").blur(function() {
			var clientId = $("#clientId").val();
			if (clientId.length < 1) {
				$("#a").html("请输入客户名称！");
				$("#a").addClass("err");
				$("#clientId").focus();
			} else if (clientId.length >= 1) {
				$("#a").html("输入符合！");
				$("#a").removeClass("err");
				$("#a").addClass("ok");
			}
		});

		$("#projectId").blur(function() {
			var projectId = $("#projectId").val();
			if (projectId.length < 1) {
				$("#b").html("请输入项目名称！");
				$("#b").addClass("err");
				$("#projectId").focus();
			} else if (projectId.length >= 1) {
				$("#b").html("输入符合！");
				$("#b").removeClass("err");
				$("#b").addClass("ok");
			}
		});
	});

	function getProjectList() {
		var clientId = $("#clientId").val();
		$.ajax({
			type : 'post',
			url : "${ctx}/oa/costexcel/getProjectList",
			data : {
				"clientId" : clientId
			},
			dataType : 'json',
			success : function(data) {
				$("#projectId").empty();
				$("#projectId").append("<option>--请选择--</option>");
				for (var i = 0; i < data.length; i++) {
					$("#projectId").append(
							"<option value='"+data[i].id+"'>"
									+ data[i].projectName + "</option>")
				}
			}

		});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/costexcel/">费用统计管理列表</a></li>
		<li class="active"><a href="${ctx}/oa/costexcel/costmessagefrom">费用信息添加</a></li>
	</ul>
	<br />
	<input id="sign" type="hidden" />
	<form:form id="searchForm" modelAttribute="costRecondExcel"
		action="${ctx}/oa/costexcel/costmessageinsert" method="post"
		class="form-horizontal">
		<form:hidden path="id" value="${costid }" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select id="type" path="type" class="input-medium">
					<form:option value="" label="---请选择---" />
					<form:option value="1" label="销售" />
					<form:option value="2" label="技术" />
					<form:option value="3" label="行政" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户名称：</label>
			<div class="controls">
				<form:select id="clientId" path="clientId" onclick="getProjectList()" class="input-xlarge">
					<option value="">--请选择--</option>
					<c:forEach var="client" items="${clientList }">
						<option value="${client.id }">${client.clientName }</option>
					</c:forEach>
				</form:select> <span id="a" class="help-inline"><font color="red">*</font>必填
				</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<select id="projectId" name="projectId" onclick="" class="input-xlarge">
				</select> <span id="b" class="help-inline"><font color="red">*</font>必填
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目金额（万元）：</label>
			<div class="controls">
				<form:input id="projectMoney" path="projectMoney" value="" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间：</label>
			<div class="controls">
				<input id="useTime" name="useTime1" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${now }" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">差旅费（含油费）：</label>
			<div class="controls">
				<form:input path="travelExpense" value="" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">餐费：</label>
			<div class="controls">
				<form:input path="mealMoney" value="" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文化礼品（自购）：</label>
			<div class="controls">
				<form:input path="culturalgiftsPerson" value="" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他费用：</label>
			<div class="controls">
				<form:input path="otherMoney" value="" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文化礼品（公司）：</label>
			<div class="controls">
				<form:input path="culturalgiftsCompeny" value="" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:textarea id="conment" path="conment" value=""
					htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" />
			</div>
		</div>
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="button"
				onclick="doSubmit()" value="保存" /> &nbsp; <input id="btnCancel"
				class="btn" type="reset" value="重置" />
		</div>
	</form:form>

	<script type="text/javascript">
		function doSubmit() {
			$("#sign").val(0);
			var clientId = $("#clientId").val();
			if (clientId.length < 1) {
				$("#a").html("请输入客户名称！");
				$("#a").addClass("err");
				$("#sign").val(0);
				$("#clientId").focus();
				return;
			} else if (clientId.length >= 1) {
				$("#a").html("输入符合！");
				$("#a").removeClass("err");
				$("#a").addClass("ok");
				$("#sign").val(1);
			}

			var projectId = $("#projectId").val();
			if (projectId.length < 1) {
				$("#b").html("请输入项目名称！");
				$("#b").addClass("err");
				$("#sign").val(0);
				return;
			} else if (projectId.length >= 1) {
				$("#b").html("输入符合！");
				$("#b").removeClass("err");
				$("#b").addClass("ok");
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