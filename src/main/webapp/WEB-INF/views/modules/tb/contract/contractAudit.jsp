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
	<form:form id="inputForm" modelAttribute="contract"
		action="${ctx}/tb/contract/saveAudit" method="post"
		class="form-horizontal">
		<input type="hidden" name="taskids" value="${taskId}">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />                 
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>合同申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit" colspan="2">申请人公司</td>
					<td colspan="2">${contract.ename }</td>
				</tr>
				<tr>
					<td class="tit">合同申请人</td>
					<td>${contract.createBy.name }</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${contract.createDate }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">产品经理加签</td>
					<td>
						${contract.managerName }
					</td>
					<td class="tit">项目经理加签</td>
					<td>
						${contract.managerTwoName }
					</td>
					
				</tr>
				<tr>
					<td class="tit">合同类别</td>
					<td>
						${fns:getDictLabel (contract.contractType, 'contract_type', '')}
					</td>
					<td class="tit">所属办事处</td>
					<td>
						${contract.areaName }
					</td>
					
				</tr>
				<tr>
					<td class="tit">合同名称</td>
					<td>
						${contract.contractName }
					</td>
					<td class="tit">合同编号</td>
					<td>
						${contract.contractNum }
					</td>
					
				</tr>
				<tr>
					<td class="tit">合同金额</td>
					<td>
						<fmt:formatNumber type="number" value="${contract.money }" maxFractionDigits="2" pattern="#0.00"/>元
					</td>
					
					<td class="tit">合同期限</td>
					<td>
						${contract.contractLimit }月
					</td>
				</tr>
				<tr>
					<td class="tit">对方公司名称</td>
					<td>
						${contract.companyName }
					</td>
					<td class="tit">支付比例</td>
					<td>
						${contract.paymentCycle }
					</td>
					
				</tr>
				<tr>
					<td class="tit">项目信息</td>
					<td>${contract.project }</td>
					<td class="tit">维保期限</td>
					<td>
						${contract.maintenance }月
					</td>
				</tr>
				<c:if test="${not empty contract.friendId && contract.friendId!='000'}">
					<tr>
						<td class="tit">销售合同</td>
						<td>
							<a href="${ctx}/tb/contract/form?id=${contract.friendId}">查看销售合同详情</a>
						</td>
					</tr>
				</c:if>
				<tr>
					<td rowspan="5" class="tit">内容概要</td>
					<td class="tit">主要合作内容</td>
					<td colspan="2">
						${contract.cooperationContent }
					</td>
				</tr>
				<tr>
					<td class="tit">毛利及毛利率情况说明</td>
					<td colspan="2">
						${contract.grossMargin }
					</td>
				</tr>
				<tr>
					<td class="tit">结算方式</td>
					<td colspan="2">
						${contract.settlement }
					</td>
				</tr>
				<tr>
					<td class="tit">违约责任</td>
					<td colspan="2">
						${contract.responsibility }
					</td>
				</tr>
				<tr>
					<td class="tit">其他</td>
					<td colspan="2">
						${contract.other }
					</td>
				</tr>
				<tr>
					<td class="tit">合同附件</td>
					<td colspan="3">
						<c:if test="${not empty contract.address }">
							<a href="${ctx}/tb/contract/download?id=${contract.id}">合同附件下载</a>
						</c:if>
					</td>
				</tr>
				<c:if test="${not empty contract.procId }">
					<tr>
						<td class="tit">作废原因</td>
						<td colspan="3">
							${contract.abandon }
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.proneText }">
					<tr>
						<td class="tit">产品经理审核意见</td>
						<td colspan="3">${contract.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prsevenText }">
					<tr>
						<td class="tit">项目经理审核意见</td>
						<td colspan="3">${contract.prsevenText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prtwoText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${contract.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prthreeText}">
					<tr>
						<td class="tit">商务部长总经理审核意见</td>
						<td colspan="3">${contract.prthreeText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prfourText}">
					<tr>
						<td class="tit">市场营销总经理审核意见</td>
						<td colspan="3">${contract.prfourText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prfiveText}">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${contract.prfiveText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prsixText}">
					<tr>
						<td class="tit">总经理审核意见</td>
						<td colspan="3">${contract.prsixText}</td>
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
			<%-- <c:if test="${fns:getUser().loginName == '谢晨'}">
				<input id="btnSubmit" class="btn btn-inverse" type="submit" value="转至市场营销总经理"
				onclick="$('#flag').val('major')" />&nbsp;
			</c:if>
			<c:if test="${fns:getUser().loginName == '高会敏'}">
				<input id="btnSubmit" class="btn btn-inverse" type="submit" value="转至总经理"
				onclick="$('#flag').val('major')" />&nbsp;
			</c:if> --%>
			<input id="btnSubmit" class="btn btn-inverse" type="submit" 
				value="驳 回" onclick="$('#flag').val('no')" />&nbsp; 
			<input id="btnCancel" class="btn" type="button" 
				value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<c:if test="${empty contract.procId }">
		<act:histoicFlow procInsId="${contract.act.procInsId}" />
		</c:if>
		<c:if test="${not empty contract.procId }">
		<act:histoicFlow procInsId="${contract.procId}" />
		</c:if>
	</form:form>
</body>
</html>