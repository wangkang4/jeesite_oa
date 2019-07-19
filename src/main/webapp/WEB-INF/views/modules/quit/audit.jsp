<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>员工离职申请管理</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="quit"
		action="${ctx}/tb/quit/saveAudit" method="post"
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
			<legend>员工离职申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
						${quit.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${quit.office.name}
					</td>
				</tr>
				<tr>
					<td class="tit">申请离职日期</td>
					<td><fmt:formatDate value="${quit.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
					<td class="tit">拟离职日期</td>
					<td><fmt:formatDate value="${quit.quitDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">职位</td>
					<td colspan="3">
						${quit.position}
					</td>
				</tr>
				<tr>
					<td class="tit" >离职原因</td>
					<td colspan="3">
						${quit.reason}
					</td> 
				</tr>
				<tr>
					<td class="tit">附件</td>
					<td colspan="3">
						<c:if test="${not empty quit.address }">
							<a href="${ctx}/tb/quit/download?id=${quit.id}">附件下载</a>
						</c:if>
						<c:if test="${empty quit.address }">
							无附件
						</c:if>
					</td>
					
				</tr>
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
		<act:histoicFlow procInsId="${quit.act.procInsId}" />
	</form:form>
</body>
</html>