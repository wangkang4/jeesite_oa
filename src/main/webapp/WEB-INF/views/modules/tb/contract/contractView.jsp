<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>合同管理</title>
<script type="text/javascript" src="${ctxStatic}/tb/contract/contractView.js?ver=2"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/contract/list">合同列表</a></li>
		<li class="active"><a
			href="">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${contract.statu }">
		<input type="hidden" name="contractId" value="${contract.id }">
		<fieldset>
			<legend>申请详情</legend>
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
				<c:if test="${not empty contract.friendId }">
					<tr>
						<td class="tit">销售合同</td>
						<td>
						<c:choose>
							<c:when test="${contract.friendId=='000'}">其他合同</c:when>
							<c:otherwise>
								<a href="${ctx}/tb/contract/form?id=${contract.friendId}">查看销售合同详情</a>
							</c:otherwise>
						</c:choose>
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
				<c:if test="${contract.statu=='审核通过'&&contract.status!='作废中' }">
					<tr>
						<td class="tit">作废原因</td>
						<td colspan="3">
							<textarea rows="" cols="" name="abandon"></textarea>
						</td>
					</tr>
				</c:if>
				<c:if test="${contract.status=='作废中' }">
					<tr>
						<td class="tit">作废原因</td>
						<td colspan="3">
							${contract.abandon }
						</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<c:if test="${empty contract.procId }">
		<act:histoicFlow procInsId="${contract.act.procInsId}" />
		</c:if>
		<c:if test="${not empty contract.procId }">
		<act:histoicFlow procInsId="${contract.procId}" />
		</c:if>
		<div class="form-actions">
			<c:if test="${contract.statu=='审核通过' }">
				<input class="btn" type="button" value="作废" onclick="abandoned()">
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>