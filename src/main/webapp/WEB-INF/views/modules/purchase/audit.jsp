<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>采购申请管理</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="purchase"
		action="${ctx}/tb/purchase/saveAudit" method="post"
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
			<legend>采购申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
						${purchase.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${purchase.office.name}
					</td>
				</tr>
				<tr>
					<td class="tit">物资名称</td>
					<td>
						${purchase.pName}
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${purchase.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">合计金额</td>
					<td>
						${purchase.money}
					</td>
					<td class="tit">采购日期</td>
					<td><fmt:formatDate value="${purchase.purchaseDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<tr>
					<td class="tit">附件</td>
					<td>
						<c:if test="${not empty purchase.applyAddress }">
							<a href="${ctx}/tb/purchase/download?id=${purchase.id}">附件下载</a>
						</c:if>
						<c:if test="${empty purchase.applyAddress }">
							无附件
						</c:if>
					</td>
					<td class="tit" >备注</td>
					<td>
						${purchase.report }
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
				value="同 意" onclick="$('#flag').val('yes')" />
			<input id="btnSubmit" class="btn btn-inverse" type="submit" 
				value="驳 回" onclick="$('#flag').val('no')" />&nbsp; 
			<input id="btnCancel" class="btn" type="button" 
				value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<act:histoicFlow procInsId="${purchase.act.procInsId}" />
	</form:form>
</body>
</html>