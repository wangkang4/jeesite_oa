<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>合同管理</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
					
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="induction"
		action="${ctx}/tb/induction/saveAudit" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />                 
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>${fns:getUser().name }</td>
					<td class="tit">申请地点</td>
					<td>${induction.applyAddress.name}</td>
					<td class="tit">申请部门</td>
					<td>${induction.applyOffice.name}</td>
				</tr>
				<tr>
					<td class="tit">面试日期</td>
					<td><fmt:formatDate value="${induction.interviewDate}" type="both" pattern="yyyy-MM-dd"/></td>
					<td class="tit">面试地点</td>
					<td>${induction.interviewAddress }</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${induction.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">录用人姓名</td>
					<td>${induction.employedName }</td>
					<td class="tit">试用期工资</td>
					<td><fmt:formatNumber type="number" value="${induction.trialMoney }" maxFractionDigits="2" pattern="#0.00"/>元</td>
					<td class="tit">转正后工资</td>
					<td><fmt:formatNumber type="number" value="${induction.positiveMoney }" maxFractionDigits="2" pattern="#0.00"/>元</td>
				</tr>
				<tr>
					<td class="tit">录用部门</td>
					<td>${induction.employedOffice.name}</td>
					<td class="tit">录用岗位</td>
					<td>${induction.employedJob }</td>
					<td class="tit">岗位级别</td>
					<td>${induction.jobLevel }</td>
				</tr>
				<tr>
					<td class="tit">到岗日期</td>
					<td><fmt:formatDate value="${induction.workDate}" type="both" pattern="yyyy-MM-dd"/></td>
					<td class="tit">联系电话</td>
					<td>${induction.phone }</td>
					<td class="tit">转正日期</td>
					<td><fmt:formatDate value="${induction.positiveDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="tit">合同签订日期</td>
					<td><fmt:formatDate value="${induction.contractSignedDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
					<td class="tit">合同起始日期</td>
					<td><fmt:formatDate value="${induction.contractStartDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
					<td class="tit">合同结束日期</td>
					<td><fmt:formatDate value="${induction.contractEndDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="tit">应聘人简历和面试意见</td>
					<td colspan="5">
					</form>
					</td>
				</tr>
				<tr>
					<td class="tit">相关证件</td>
					<td colspan="5">
					</td>
				</tr>
				<c:if test="${not empty induction.proneText }">
					<tr>
						<td class="tit">部门经理审核意见</td>
						<td colspan="5">${induction.proneText }</td>
					</tr>
				</c:if>
				<c:if test="${not empty induction.prtwoText }">
					<tr>
						<td class="tit">研发总监审核意见</td>
						<td colspan="5">${induction.prtwoText }</td>
					</tr>
				</c:if>
				<c:if test="${not empty induction.prthreeText }">
					<tr>
						<td class="tit">商务主管审核意见</td>
						<td colspan="5">${induction.prthreeText }</td>
					</tr>
				</c:if>
				<c:if test="${not empty induction.prfourText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="5">${induction.prfourText }</td>
					</tr>
				</c:if>
				<c:if test="${not empty induction.prfiveText }">
					<tr>
						<td class="tit">财务主管审核意见</td>
						<td colspan="5">${induction.prfiveText }</td>
					</tr>
				</c:if>
				<c:if test="${not empty induction.prsixText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="5">${induction.prsixText }</td>
					</tr>
				</c:if>
				<c:if test="${not empty induction.prsevenText }">
					<tr>
						<td class="tit">行政主管审核意见</td>
						<td colspan="5">${induction.prsevenText }</td>
					</tr>
				</c:if>
				<tr>
					<td class="tit">您的意见</td>
					<td colspan="3"><form:textarea path="act.comment"
							 rows="5" maxlength="500" cssStyle="width:500px" />
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" 
				value="同 意" onclick="$('#flag').val('yes')" />&nbsp; 
			<input id="btnSubmit" class="btn btn-inverse" type="submit" 
				value="驳 回" onclick="$('#flag').val('no')" />&nbsp; 
			<input id="btnCancel" class="btn" type="button" 
				value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<act:histoicFlow procInsId="${induction.act.procInsId}" />
	</form:form>
</body>
</html>