<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>证照管理</title>
<script type="text/javascript" src="${ctxStatic}/tb/licenses/addLicenses.js?ver=1"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/licenses/list">证照列表</a></li>
		<li class="active"><a href="">证照申请流程</a></li>
		<shiro:hasRole name="caiwuzhongnan">
		<li><a href="${ctx}/tb/licenses/employeesList">员工证照列表</a></li>
		</shiro:hasRole>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/licenses/list2">所属区域员工证照列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="licenses"
		action="${ctx}/tb/licenses/add" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>证照申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>${name }
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${applyDate }" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">使用日期</td>
					<td>
						<input name="useDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${licenses.useDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="useDateWarn" style="color: red"></span>
					</td>
					<td class="tit">归还日期</td>
					<td>
						<input name="returnDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${licenses.returnDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="returnDateWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">证件种类</td>
					<td>
						<form:select  path="type" class="input-medium">
							<form:option value="" label="请选择"/>
							<form:options items="${fns:getDictList('licenses_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						<span class="warn" id="typeWarn" style="color: red"></span>
					</td>
					<td class="tit">借用方式</td>
					<td>原件外借</td>
				</tr>
				<tr>
					<td class="tit">用途</td>
					<td colspan="3">
						<textarea rows="3" style="width:80%" name="notes">${licenses.notes }</textarea>
						<span class="warn" id="notesWarn" style="color: red"></span>
					</td>
				</tr>
				<c:if test="${not empty licenses.proneText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${licenses.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty licenses.prtwoText}">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${licenses.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty licenses.prthreeText}">
					<tr>
						<td class="tit">应章管理人审核意见</td>
						<td colspan="3">${licenses.prthreeText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty licenses.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
				</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty licenses.id}">
			<act:histoicFlow procInsId="${licenses.act.procInsId}" />
		</c:if>
	</form:form>
		<table id="hint"  border="1" cellspacing="0" align="center" width="100%"></table>
</body>
</html>