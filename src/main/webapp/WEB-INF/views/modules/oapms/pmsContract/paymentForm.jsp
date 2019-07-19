<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>回款计划添加页面</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});

	function submitForm() {
		var bool = true;
		$("font").empty();
		var payment = $('form').serializeArray();
		console.log(payment);

		var paymentTime = $("input[name='paymentTime']").val().replace(
				/(^\s*)|(\s*$)/g, '');
		var paymentAmount = $("input[name='paymentAmount']").val().replace(
				/(^\s*)|(\s*$)/g, '');
		var paymentMode = $("#paymentMode").val();
		var paymentStatus = $("#paymentStatus").val();

		if (paymentTime == "" || paymentTime == undefined
				|| paymentTime == null) {
			bool = false;
			$("#paymentTimeWarn").text("请选择回款时间");
		}

		var amountTeg = /^([1-9]\d*(\.\d[1-9]{0,1})?)|(0\.\d[1-9]{0,1})$/;
		if (paymentAmount != "" && paymentAmount != undefined
				&& paymentAmount != null && !amountTeg.test(paymentAmount)) {
			bool = false;
			$("input[name='paymentAmount']").next().children().text("回款金额格式不对");
		} else if (paymentAmount == "" || paymentAmount == null) {
			bool = false;
			$("input[name='paymentAmount']").next().children().text("回款金额不能为空");
		}

		if (paymentMode == "" || paymentMode == undefined
				|| paymentMode == null) {
			bool = false;
			$("#paymentModeWarn").text("请选择回款方式");
		}
		if (paymentStatus == "" || paymentStatus == undefined
				|| paymentStatus == null) {
			bool = false;
			$("#paymentStatusWarn").text("请选择回款状态");
		}

		if (bool == true) {
			$("#inputForm").submit();
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oapms/pmsPayment/list">回款计划列表</a></li>
		<li class="active"><a href="">回款计划添加</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="pmsPayment"
		action="${ctx}/oapms/pmsPayment/save" method="post"
		class="form-horizontal">
		<form:hidden path="paymentId" />
		<form:hidden path="contractId" />
		<div class="control-group">
			<label class="control-label">回款时间:</label>
			<div class="controls">
				<input id="paymentTime" name="paymentTime" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				<span class="help-inline"><font color="red"
					id="paymentTimeWarn">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回款金额:</label>
			<div class="controls">
				<input type="text" class="" name="paymentAmount"> <span
					class="help-inline"><font color="red" id="paymentAmountWarn">*</font>
				</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回款方式:</label>
			<div class="controls">
				<form:select path="paymentMode" class="input-medium"
					id="paymentMode">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('pms_payment_mode')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red"
					id="paymentModeWarn">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">回款状态:</label>
			<div class="controls">
				<form:select path="paymentStatus" class="input-medium"
					id="paymentStatus">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('pms_payment_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red"
					id="paymentStatusWarn">*</font> </span>
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
