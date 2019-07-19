<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>加班管理</title>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/overtime/">加班列表</a></li>
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务办理</shiro:hasPermission> <%-- <shiro:lacksPermission name="oa:testAudit:edit">查看</shiro:lacksPermission> --%>
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="overTime"
		action="${ctx}/work/overtime/saveAudit" method="post"
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
			<legend>员工加班申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td>${overTime.reason }</td>
					<td class="tit">姓名</td>
					<td>${overTime.user.name}</td>
					
				</tr>
				<tr>
					<td class="tit">开始时间</td>
					<td><fmt:formatDate value="${overTime.startTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="tit">结束时间</td>
					<td><fmt:formatDate value="${overTime.endTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<%-- 	<td class="tit">请假类型</td>
					<td>${fns:getDictLabel(overTime.leaveType, 'oa_leave_type', '')}</td> --%>
					<td class="tit">加班总时间</td>
					<td>
						<fmt:formatNumber type="number" value="${overTime.days}" maxFractionDigits="0"/>小时 
					</td>
					<td class="tit">部门</td>
					<td>${overTime.office.name}</td>
				</tr>
				<tr>
					<td class="tit">加班原因</td>
					<td colspan="3">${overTime.notes }</td>
				</tr>
				<%-- <c:if test="${not empty str}">  str不为空</c:if> --%>
				<c:if test="${not empty overTime.prText }">
					<tr>
						<td class="tit">项目经理意见</td>
						<td colspan="3">${overTime.prText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty overTime.leaderText}">
					<tr>
						<td class="tit">地区领导意见</td>
						<td colspan="3">${overTime.leaderText}</td>
					</tr>
				</c:if>
					<c:if test="${not empty overTime.id && not empty overTime.leadertwoText}">
					<tr>
						<td class="tit">总经理意见</td>
						<td colspan="3">${overTime.leadertwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty overTime.hrText}">
					<tr>
						<td class="tit">人事主管意见</td>
						<td colspan="3">${overTime.hrText}</td>
					</tr>
				</c:if>
				<tr>
					<td class="tit">您的意见</td>
					<td colspan="3"><form:textarea path="act.comment"
							rows="5" maxlength="20" cssStyle="width:500px" />
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="同 意" onclick="$('#flag').val('yes')" />&nbsp; 
				<input
				id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回"
				onclick="$('#flag').val('no')" />&nbsp; 
				<input id="btnCancel"
				class="btn" type="button" value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo' " />
		</div>
		<act:histoicFlow procInsId="${overTime.act.procInsId}" />
	</form:form>
</body>
</html>