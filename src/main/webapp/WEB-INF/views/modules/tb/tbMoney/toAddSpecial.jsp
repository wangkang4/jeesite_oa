<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>专项费用单管理</title>

<script type="text/javascript" src="${ctxStatic}/tb/tbMoney/addSpecial.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/money/specialList">专项费用列表</a></li>
		<li class="active"><a href="#">专项费用申请</a></li>
		<c:if test="${fns:getUser().loginName=='俞跃舒' }">
			<li class=""><a href="${ctx}/tb/money/specialLeaderList">领导专项费用列表</a></li>
		</c:if>
		<shiro:hasAnyRoles name="seeEmployees,caiwuzhongnan">
		<li><a href="${ctx}/tb/money/specialEmployeesList">员工专项费用列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/money/specialList2">所属区域员工专项费用列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="special" name="form"
		action="${ctx}/tb/money/addSpecial" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>专项费用预批申请</legend>
			<div>
			<table class="table-form">
				<tr>
					<td class="tit">姓名</td>
					<td>
						<input id="user.name" name="user.id"
						value="${special.user.id }" type="hidden"> <input
						id="userId" name="userName" type="text" readonly="true"
						value="${special.user.name }">
					</td>
					<td class="tit">部门</td>
					<td>
						<input id="office.name" name="office.id" value="${special.office.id}" type="hidden">
						<input id="officeId" name="officeName" type="text" value="${special.office.name }" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td class="tit">日期</td>
					<td>
						<input id="useTime" name="tbDate" type="text"
						readonly="readonly" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${special.tbDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="tbDateWarn" style="color: red"></span>
					</td>
					<td class="tit">预估金额</td>
					<td>
						<input name="money" value="${special.money }" type="text"/>元
						<span class="warn" id="moneyWarn" style="color: red"></span>
					</td>
					
				</tr>
				<tr>
					<td class="tit">支付方式</td>
					<td>
						<select name="payType" class="input-medium" onchange="payTypeChange()">
							<option value="">请选择</option>
							<c:if test="${empty special.payType}">
								<option value="1">现金</option>
								<option value="2">转账</option>
							</c:if>
							<c:if test="${special.payType=='1'}">
								<option value="1" selected>现金</option>
								<option value="2">转账</option>
							</c:if>
							<c:if test="${special.payType=='2'}">
								<option value="1">现金</option>
								<option value="2" selected>转账</option>
							</c:if>
						</select>
						<span class="warn" id="payTypeWarn" style="color:red"></span>
					</td>
					<td class="tit">账户信息</td>
					<td>
						<input name="account" value="${special.account }" type="text">
						<span class="warn" id="accountWarn" style="color:red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit" >备注</td>
					<td colspan="7" >
					<form:textarea path="notes" 
							rows="5" maxlength="2000" cssStyle="width:500px;height:80px" />
					<span class="warn" id="notesWarn" style="color: red"></span>
					</td> 
				</tr>
			</div>
			</table>
			</div>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty special.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty special.id}">
			<act:histoicFlow procInsId="${special.act.procInsId}" />
		</c:if>
	</form:form>
	1）为了加强业务拓展专项费用的事前控制，在费用发生前需办理预审批，预审批通过后方可发生费用支出；<br>
	2）费用发生后应及时办理报销，报销时需将打印出的预审批粘贴于《报销粘贴单》后。<br>
	3）对于专项业务招待餐饮费，事前未办理预审批的，金额超500元的不予办理后续报销。<br>
	4）预审批的费用为预估金额，实际发生金额超出该预估金额10%以上（含）的，报销时需说明原因。<br>
	5）预审批类别如下：<br>
	<table border="1" cellspacing="0" align="center" width="100%">
	<tr>
		<td rowspan="3">预审批</td>
		<td>专项会务费</td>
		<td>指参加学术讨论会、行业年会等的会议费用</td>
		<td>请填写专项费用申请；</td>
	</tr>
	<tr>
		<td>专项业务招待费</td>
		<td>招待餐饮费用</td>
		<td>请填写业务招待申请；</td>
	</tr>
	<tr>
		<td>其他专项费用</td>
		<td>业务拓展可能发生的其他需预先审批的专项费用</td>
		<td>请填写专项费用申请；</td>
	</tr>
	</table>
</body>
</html>