<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>借款管理</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission>

		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="borrowing"
		action="${ctx}/tb/borrowing/saveAudit" method="post"
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
			<legend>借款单申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit" >所属公司</td>
					<td colspan="3" align="center">${borrowing.ename }</td>
				</tr>
				<tr>
					<td class="tit">标题</td>
					<td>${borrowing.title }</td>
					<td class="tit">日期</td>
					<td><fmt:formatDate value="${borrowing.time }"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td class="tit">借款人</td>
					<td>${borrowing.name.name }</td>
					<td class="tit">所属部门</td>
					<td>${borrowing.office.name }</td>
				</tr>
				<tr>
					<td class="tit">借款金额</td>
					<td><fmt:formatNumber value="${borrowing.money }"
							maxFractionDigits="2" pattern="#0.00" />元</td>
				</tr>
				<tr>
					<td class="tit">借款用处</td>
					<td colspan="3">${borrowing.reason }</td>
				</tr>
				<tr>
					<td class="tit">备注</td>
					<td colspan="3">${borrowing.notes }</td>
				</tr>

				<c:if test="${not empty borrowing.proneText }">
					<tr>
						<td class="tit">部门经理审核意见</td>
						<td colspan="3">${borrowing.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prtwoText}">
					<tr>
						<td class="tit">研发总监审核意见</td>
						<td colspan="3">${borrowing.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prthreeText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${borrowing.prthreeText}</td>
					</tr>
				</c:if>

				<c:if test="${not empty borrowing.prfiveText }">
					<tr>
						<td class="tit">财务主管审核意见</td>
						<td colspan="3">${borrowing.prfiveText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prsixText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${borrowing.prsixText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prsevenText }">
					<tr>
						<td class="tit">总裁审核意见</td>
						<td colspan="3">${borrowing.prsevenText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.preightText }">
					<tr>
						<td class="tit">总经理审核意见</td>
						<td colspan="3">${borrowing.preightText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prfourText }">
					<tr>
						<td class="tit">出纳审核意见</td>
						<td colspan="3">${borrowing.prfourText}</td>
					</tr>
				</c:if>
				<tr>
					<td class="tit">您的意见</td>
					<td colspan="3"><form:textarea path="act.comment" rows="5"
							maxlength="500" cssStyle="width:500px" /></td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="同 意" onclick="$('#flag').val('yes')" />&nbsp;
			<%-- <c:if test="${fns:getUser().loginName == '李晓萌'}">
					<input id="btnSubmit" class="btn btn-inverse" type="submit" value="转至财务总监"
					onclick="$('#flag').val('major')" />&nbsp;
				</c:if> --%>
			<%-- <c:if test="${fns:getUser().loginName == '高会敏'}">
					<input id="btnSubmit" class="btn btn-inverse" type="submit" value="转至总裁"
					onclick="$('#flag').val('major')" />&nbsp;
				</c:if> --%>
			<input id="btnSubmit" class="btn btn-inverse" type="submit"
				value="驳 回" onclick="$('#flag').val('no')" />&nbsp; <input
				id="btnCancel" class="btn" type="button" value="返 回"
				onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<act:histoicFlow procInsId="${borrowing.act.procInsId}" />
	</form:form>
</body>
</html>