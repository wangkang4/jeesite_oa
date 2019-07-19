<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>拜访记录添加页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/visit/addVisit.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oapms/contact/list">联系人列表</a></li>
		<li><a href="javascript:visitList()">拜访记录列表</a></li>
		<li class="active"><a href="">拜访记录添加</a></li>
	</ul>
	<form:form id="addVisit" modelAttribute="visit" action="${ctx}/oapms/visit/add" method="post" class="form-horizontal">
	<input type="hidden" name="customerId" value=${contact.customer.customerId }>
	<input type="hidden" name="customerContactId" value=${contact.customerContactId }>
	<input type="hidden" name="customerName" value=${contact.customer.customerName }>
	<input type="hidden" name="customerContactName" value=${contact.customerContactName }>
		<div class="control-group">
			<label class="control-label">标题:</label>
			<div class="controls">
                <input type="text" class="" name="title">
				<span class="help-inline"><font color="red" id="titleWarn"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">拜访时间:</label>
			<div class="controls">
                <input id="visitTimeString" name="visitTimeString" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate "
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				<span class="help-inline"><font color="red" id="visitTimeWarn"></font> </span>
			</div>
		</div>
		<div class="control-group"> 
			<label class="control-label">拜访纪要:</label>
			<div class="controls">
				<textarea name="visitSummary"></textarea>
				<span class="help-inline"><font color="red" id="visitSummaryWarn"></font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">下一步计划:</label>
			<div class="controls">
				<textarea name="nextPlan"></textarea>
				<span class="help-inline"><font color="red" id="nextPlanWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="customer">拜访客户:</label>
			<div class="controls">
				<select class="input-medium" name="customer.customerId" onchange="doSetMenus()">
					<option value="">请选择</option>
				</select>
				<span class="help-inline"><font color="red" id="customerIdWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">拜访联系人:</label>
			<div class="controls" id="contact">
				<select class="input-medium" name="customerContact.customerContactId">
					<option value="choose">请选择</option>
				</select>
				<span class="help-inline"><font color="red" id="customerContactIdWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">拜访地址:</label>
			<div class="controls">
				<input type="text" class="" name="visitAddress">
				<span class="help-inline"><font color="red"></font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitVisit()" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
