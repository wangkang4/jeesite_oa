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
	<form:form id="inputForm" modelAttribute="licenses"
		action="${ctx}/tb/licenses/saveAudit" method="post"
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
			<legend>证照申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="3">
                		${licenses.title }
					</td>
				</tr>
				<tr>
					<td class="tit">申请人</td>
					<td>${licenses.user.name }
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${licenses.applyDate }" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">使用日期</td>
					<td>
						<fmt:formatDate value="${licenses.useDate }" type="both" pattern="yyyy-MM-dd"/>
					</td>
					<td class="tit">归还日期</td>
					<td>
						<fmt:formatDate value="${licenses.returnDate }" type="both" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="tit">证件种类</td>
					<td>
						${fns:getDictLabel(licenses.type, 'licenses_type', '')}
					</td>
					<td class="tit">借用方式</td>
					<td>原件外借</td>
				</tr>
				<tr>
					<td class="tit">用途</td>
					<td colspan="3">
						${licenses.notes }
					</td>
				</tr>
				<c:if test="${not empty licenses.proneText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${licenses.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty licenses.prtwoText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${licenses.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty licenses.prthreeText}">
					<tr>
						<td class="tit">证照管理人审核意见</td>
						<td colspan="3">${licenses.prthreeText}</td>
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
		<act:histoicFlow procInsId="${licenses.act.procInsId}" />
	</form:form>
</body>
</html>