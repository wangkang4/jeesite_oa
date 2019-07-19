<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>借款管理</title>
<script type="text/javascript"
	src="${ctxStatic}/tb/borrowing/addBorrowing.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/borrowing/list">借款列表</a></li>
		<li class="active"><a href="">借款申请</a></li>
		<shiro:hasAnyRoles name="seeEmployee,caiwuzhongnan">
			<li><a href="${ctx}/tb/borrowing/employeesList">员工借款列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/borrowing/borrowingList2">所属区域员工借款列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="borrowing"
		action="${ctx}/tb/borrowing/save" method="post"
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
			<legend>借款申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">所属公司</td>
					<td colspan="3" align="center"><select name="ename">
							<option value="${borrowing.ename }">${borrowing.ename }</option>
							<c:if test="${borrowing.ename =='北京桃花岛'}">
								<option value="安徽桃花岛">安徽桃花岛</option>
							</c:if>
							<c:if test="${borrowing.ename=='安徽桃花岛'}">
								<option value="北京桃花岛">北京桃花岛</option>
							</c:if>
					</select></td>
				</tr>
				<tr>
					<td class="tit">借款人</td>
					<td>${borrowing.name.name }</td>
					<td class="tit">所属部门</td>
					<td>${borrowing.office.name }</td>
				</tr>
				<tr>
					<td class="tit">借款金额</td>
					<td><input type="text" name="money"
						value="${borrowing.money }">元 <span class="warn"
						id="moneyWarn" style="color: red"></span></td>
					<td class="tit">日期</td>
					<td><fmt:formatDate value="${borrowing.time }"
							pattern="yyyy-MM-dd" /></td>

				</tr>
				<tr>
					<td class="tit">借款用处</td>
					<td colspan="3"><textarea rows="3" style="width: 80%"
							name="reason">${borrowing.reason }</textarea> <span class="warn"
						id="reasonWarn" style="color: red"></span></td>
				</tr>
				<tr>
					<td class="tit">备注</td>
					<td colspan="3"><textarea rows="3" style="width: 80%"
							name="notes">${borrowing.notes }</textarea> <span class="warn"
						id="notesWarn" style="color: red"></span></td>
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
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty borrowing.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
				</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty borrowing.id}">
			<act:histoicFlow procInsId="${borrowing.act.procInsId}" />
		</c:if>
	</form:form>
	<table id="hint" border="1" cellspacing="0" align="center" width="100%">

	</table>
</body>
</html>