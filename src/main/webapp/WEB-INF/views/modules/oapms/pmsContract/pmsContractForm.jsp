<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>合同添加页面</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function submitForm() {
		var bool = true;
		$("font").empty();
		var contract = $('form').serializeArray();
		console.log(contract);
		var title = $("input[name='title']").val()
				.replace(/(^\s*)|(\s*$)/g, '');
		var customerId = $("#customerId").val();
		var projectId = $("#projectId").val();
		var amount = $("input[name='amount']").val().replace(/(^\s*)|(\s*$)/g,
				'');
		var totalAmount = $("input[name='totalAmount']").val().replace(
				/(^\s*)|(\s*$)/g, '');
		var startTime = $("input[name='startTime']").val().replace(
				/(^\s*)|(\s*$)/g, '');
		var endTime = $("input[name='endTime']").val().replace(
				/(^\s*)|(\s*$)/g, '');
		var servicePeriod = $("input[name='servicePeriod']").val().replace(
				/(^\s*)|(\s*$)/g, '');
		var contractTerms = $("input[name='contractTerms']").val().replace(
				/(^\s*)|(\s*$)/g, '');

		if (title == "" || title == undefined || title == null) {
			bool = false;
			$("#titleWarn").text("请输入标题");
		}
		if (customerId == "" || customerId == undefined || customerId == null) {
			bool = false;
			$("#customerIdWarn").text("请选择客户");
		}
		if (projectId == "" || projectId == undefined || projectId == null) {
			bool = false;
			$("#projectIdWarn").text("请选择项目");
		}

		var amountTeg = /^([1-9]\d*(\.\d[1-9]{0,1})?)|(0\.\d[1-9]{0,1})$/;
		if (amount != "" && amount != undefined && amount != null
				&& !amountTeg.test(amount)) {
			bool = false;
			$("input[name='amount']").next().children().text("金额格式不对");
		} else if (amount == "" || amount == null) {
			bool = false;
			$("input[name='amount']").next().children().text("金额不能为空");
		}

		var totalAmountTeg = /^([1-9]\d*(\.\d[1-9]{0,1})?)|(0\.\d[1-9]{0,1})$/;
		if (totalAmount != "" && totalAmount != undefined
				&& totalAmount != null && !totalAmountTeg.test(totalAmount)) {
			bool = false;
			$("input[name='totalAmount']").next().children().text("累计金额格式不对");
		} else if (totalAmount == "" || totalAmount == null) {
			bool = false;
			$("input[name='totalAmount']").next().children().text("累计金额不能为空");
		}

		if (startTime == "" || startTime == undefined || startTime == null) {
			bool = false;
			$("#startTimeWarn").text("请选择开始时间");
		}

		if (endTime == "" || endTime == undefined || endTime == null) {
			bool = false;
			$("#endTimeWarn").text("请选择结束时间");
		}

		if (servicePeriod == "" || servicePeriod == undefined
				|| servicePeriod == null) {
			bool = false;
			$("#servicePeriodWarn").text("请输入服务期限");
		}

		if (contractTerms != "" && contractTerms != undefined
				&& contractTerms != null && contractTerms.length > 200) {
			bool = false;
			$("#contractTerms").next().children().text("不超过200字");
		}

		if (bool == true) {
			$("#inputForm").submit();
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oapms/pmsContract/list">合同列表</a></li>
		<li class="active"><a href="${ctx}/oapms/pmsContract/form">新增合同</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="pmsContract" enctype="multipart/form-data"
		action="${ctx}/oapms/pmsContract/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">合同标题:</label>
			<div class="controls">
				<input type="text" class="" name="title"> <span
					class="help-inline"><font color="red" id="titleWarn">*</font>
				</span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">所属客户:</label>
			<div class="controls">
				<select class="input-medium" name="customerId" id="customerId">
					<option value="">请选择</option>
					<c:forEach var="customer" items="${customerList }">
						<option value="${customer.customerId }">${customer.customerName }</option>
					</c:forEach>
					<select>
						<span class="help-inline"><font color="red"
							id="customerIdWarn">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">所属项目:</label>
			<div class="controls">
				<select class="input-medium" name="projectId" id="projectId">
					<option value="">请选择</option>
					<c:forEach var="project" items="${pmsProjectList }">
						<option value="${project.projectId }">${project.projectName }</option>
					</c:forEach>
					<select>
						<span class="help-inline"><font color="red"
							id="projectIdWarn">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">合同金额:</label>
			<div class="controls">
				<input type="text" class="" name="amount"> <span
					class="help-inline"><font color="red">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">累计收到金额:</label>
			<div class="controls">
				<input type="text" class="" name="totalAmount"> <span
					class="help-inline"><font color="red" id="totalAmountWarn">*</font>
				</span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">开始日期:</label>
			<div class="controls">
				<input id="startTime" name="startTime" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				<span class="help-inline"><font color="red"
					id="startTimeWarn">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">结束日期:</label>
			<div class="controls">
				<input id="endTime" name="endTime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				<span class="help-inline"><font color="red" id="endTimeWarn">*</font>
				</span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">服务期限:</label>
			<div class="controls">
				<input type="text" class="" name="servicePeriod"> <span
					class="help-inline"><font color="red" id="servicePeriodWarn">*</font>
				</span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">合同条款:</label>
			<div class="controls">
				<%-- <form:textarea path="contractTerms" rows="5" maxlength="2000" cssStyle="width:500px" />  --%>
				<input type="text" class="" name="contractTerms"> <span
					class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group" id="attachment">
			<label class="control-label">合同附件:</label>
			<div class="controls">
				<input type="file" name="file">
				<%-- <textarea style="display: none" name="attachmentAddress"
					id="fileids"></textarea>
				<iframe src="${ctx }/pms/comment/fileuploads" width="50%"
					height="110px;" frameborder="no" border="1" marginwidth="0"
					marginheight="0" scrolling="no" allowtransparency="yes"></iframe> --%>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button"
				onclick="return submitForm()" value="保 存" />&nbsp; <input
				id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>
