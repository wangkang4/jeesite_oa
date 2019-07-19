<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>市内自驾车出行申请管理</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
					
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="driving"
		action="${ctx}/tb/driving/saveAudit" method="post"
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
			<legend>市内自驾车出行申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>${driving.createBy.name }
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${driving.createDate }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="tit">同行人员</td>
					<td>
						${driving.peer }
					</td>
					<td class="tit">用车时间</td>
					<td>
						<fmt:formatDate value="${driving.transportTime}" pattern="yyyy-MM-dd"/>
					</td>
					
				</tr>
				<tr>
					<td class="tit">出发地</td>
					<td>
						${driving.origin }
					</td>
					<td class="tit">目的地</td>
					<td>
						${driving.destination }
					</td>
				</tr>
				<tr>
					<td class="tit">预计公里数</td>
					<td>
						${driving.estimatedMiles }公里
					</td>
					<td class="tit">每升汽油费</td>
					<td>
						${driving.gasoline }元
					</td>
				</tr>
				<tr>
					<td class="tit">预计费用</td>
					<td colspan="3">
						${driving.estimatedCost }元
					</td>
				</tr>
				<tr>
					<td class="tit">自驾原因</td>
					<td colspan="3">
						${driving.reason }
					</td>
				</tr>
				<c:if test="${not empty driving.proneText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${driving.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty driving.prtwoText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${driving.prtwoText}</td>
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
			<c:if test="${fns:getUser().loginName == '谢晨'}">
				<input id="btnSubmit" class="btn btn-inverse" type="submit" value="转至市场营销总经理"
				onclick="$('#flag').val('major')" />&nbsp;
			</c:if>
			<c:if test="${fns:getUser().loginName == '高会敏'}">
				<input id="btnSubmit" class="btn btn-inverse" type="submit" value="转至总经理"
				onclick="$('#flag').val('major')" />&nbsp;
			</c:if>
			<input id="btnSubmit" class="btn btn-inverse" type="submit" 
				value="驳 回" onclick="$('#flag').val('no')" />&nbsp; 
			<input id="btnCancel" class="btn" type="button" 
				value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<c:if test="${not empty driving.act.procInsId }">
		<act:histoicFlow procInsId="${driving.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>