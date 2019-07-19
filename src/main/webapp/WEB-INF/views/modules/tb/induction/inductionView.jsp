<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>录用/转正管理</title>
<style type="text/css">
.warn{
	color:red;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/induction/list">录用列表</a></li>
		<li class="active"><a href="">申请详情</a></li>
	</ul>
<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${induction.statu }">
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
					<td>应聘人简历和面试意见</td>
					<td colspan="5">
					<c:if test="${not empty induction.fileOneAddress }">
					<a href="${ctx}/tb/induction/download?id=${induction.id}&sign=1">附件下载</a>
					</c:if>
					</td>
				</tr>
				<tr>
					<td>相关证件</td>
					<td colspan="5">
					<c:if test="${not empty induction.fileOneAddress }">
					<a href="${ctx}/tb/induction/download?id=${induction.id}&sign=2">相关证件下载</a>
					</c:if>
					</td>
				</tr>
				
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<act:histoicFlow procInsId="${induction.act.procInsId}" />
		</form:form>
</body>
</html>