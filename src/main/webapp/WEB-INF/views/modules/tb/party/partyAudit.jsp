<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>团建管理</title>
<script type="text/javascript" src="${ctxStatic}/tb/pay/payView.js?ver=1"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
					
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="party"
		action="${ctx}/tb/party/saveAudit" method="post"
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
			<legend>团建申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="3">${party.title }</td>
				</tr>
				<tr>
					<td class="tit">部门</td>
					<td>${party.officeName}</td>
					
					<td class="tit">总人数</td>
					<td>${party.count }</td>
				</tr>
				<tr>
					<td class="tit">计划团建时间</td>
					<td>
						<fmt:formatDate value="${party.planTime}" pattern="yyyy-MM-dd"/>
					</td>
					<td class="tit">计划参加人数</td>
					<td>
						${party.planCount }
					</td>
				</tr>
				<tr>
					<td class="tit">可用团建经费：</td>
				<td>
					${party.availableFunds }
				</td>
				<td class="tit">本次团建经费预算：</td>
				<td>
					${party.budget }
				</td>
				</tr>
				<tr>
					<td class="tit">本次团建活动方案描述：</td>
					<td>
						${party.notes }
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
		<act:histoicFlow procInsId="${party.act.procInsId}" />
	</form:form>
</body>
</html>