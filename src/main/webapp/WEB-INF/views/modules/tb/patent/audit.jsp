<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>专利申请管理</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="patent"
		action="${ctx}/tb/patent/saveAudit" method="post"
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
			<legend>专利申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
						${patent.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${patent.office.name}
					</td>
				</tr>
				<tr>
					<td class="tit">申请类型</td>
					<td>
						${patent.applyType }
					</td>
					<td class="tit">专利名称</td>
					<td>
						${patent.patentName }
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty patent.reason}">
	                    <tr id="patentTel">
							<td class="tit">申请号</td>
							<td>
								${patent.applyTel }
							</td>
							<td class="tit">日期</td>
							<td><fmt:formatDate value="${patent.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td class="tit">奖励金额</td>
							<td colspan="6">
							${patent.applyType }金额:${patent.money }
							元</td>
						</tr>
						<tr class="proportion"><td colspan="7" style="text-align: center;font-weight:bold;">分配人员及比例</td></tr>
						<c:forEach items="${list }" var="rs">
								<tr class="proportion">
									<td class="tit">姓名</td>
									<td>
										${rs.person }
									</td>
									<td class="tit">比例</td>
									<td>
										${rs.position }
									</td>
								</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="tit">奖励金额</td>
							<td colspan="6">
							${patent.applyType }金额:${patent.money }
							元</td>
						</tr>
						<tr >
							<td class="tit">侵权情况简述</td>
							<td colspan="7" style="text-indent: 2em;">
									${patent.reason }
							</td>
						</tr>
					</c:otherwise> 
				</c:choose>
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
		<act:histoicFlow procInsId="${patent.act.procInsId}" />
	</form:form>
</body>
</html>