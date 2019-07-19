<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript">
function tiaozhuan(){
	var url = window.location.href;
	url = url.replace("historyExpenseDetail","print");
	window.open(url);
}
</script>
<title>报销详情</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/get/sale/form/?procInsId=${getSale.procInsId}&id=${getSale.id}">报销详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<fieldset>
			<legend>报销详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">姓名</td>
					<td class="tit">${getSale.user.name}</td>
					<td class="tit">公司</td>
					<td class="tit" colspan="2">${getSale.ename}</td>
					<td class="tit">部门</td>
					<td class="tit" colspan="3">${getSale.office.name}</td>
				</tr>
			
				<tr>
					<td class="tit">日期</td>
					<td class="tit">费用描述</td>
					<td class="tit">可报销金额</td>
					<td class="tit">实际报销金额</td>
					<td class="tit">项目名称</td>
					<td class="tit">详细信息</td>
				</tr>
				<c:forEach items="${listOther}" var="list">
					<tr>
						<td class="tit"><fmt:formatDate value="${list.detailDate}" pattern="yyyy-MM-dd"/></td>
						<td class="tit">${list.costDescription}</td>
						<td class="tit">${list.amountMoney}</td>
						<td class="tit">${list.allowMoney}</td>
						<td class="tit">${list.projectName}</td>
						<td class="tit">${list.information}</td>
					</tr>
				</c:forEach>
				<tr>
					<td class="tit">出差总天数</td>
					<td class="tit" colspan="6">${days}天</td>
				</tr>
				<tr>
					<td class="tit">可报销总金额</td>
					<td class="tit" colspan="6"><fmt:formatNumber type="number" value="${amountMoney}" maxFractionDigits="2" pattern="#.00"/>元</td>
				</tr>
				<tr>
					<td class="tit">实际报销总金额</td>
					<td class="tit" colspan="6"><fmt:formatNumber type="number" value="${allowMoney}" maxFractionDigits="2" pattern="#.00"/>元</td>
				</tr>
				<%-- <tr>
                	<td class="tit">报销原因</td>
					<td colspan="6" style="white-space:normal;width:50%;">${getSale.reasonTitle}</td>
                </tr> --%>

				<c:if test="${not empty getSale.prText }">
					<tr>
						<td class="tit">人事主管审核意见</td>
						<td colspan="6">${getSale.prText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.leaderText}">
					<tr>
						<td class="tit">部门经理审核意见</td>
						<td colspan="6">${getSale.leaderText}</td>
					</tr>
				</c:if>

				<!-- add -->
				<c:if test="${not empty getSale.leadertwoText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="6">${getSale.leadertwoText}</td>
					</tr>
				</c:if>

				<c:if test="${not empty getSale.managerText}">
					<tr>
						<td class="tit">研发总监审核意见</td>
						<td colspan="6">${getSale.managerText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.prthreeText }">
					<tr>
						<td class="tit">财务审核意见</td>
						<td colspan="6">${getSale.prthreeText}</td>
					</tr>
				</c:if>
				<!--add  -->
				<c:if test="${not empty getSale.prtwoText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="6">${getSale.prtwoText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${getSale.act.procInsId}" />
		<div class="form-actions">
			<input class="btn" type="button" value="打 印" onclick="tiaozhuan();"/>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>

</html>