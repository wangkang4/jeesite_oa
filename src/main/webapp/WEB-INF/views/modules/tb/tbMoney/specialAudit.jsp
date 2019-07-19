<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>预批管理</title>
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
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
					<%-- 	<shiro:lacksPermission name="oa:testAudit:edit">查看</shiro:lacksPermission> --%>
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="special"
		action="${ctx}/tb/money/saveAuditSpecial" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden id="taskId" path="act.taskId" />
		<form:hidden id="taskName" path="act.taskName" />                 
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>员工专项费用申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="4" class="tit">${special.reason }</td>
				</tr>
				<tr>
					<td class="tit">姓名</td>
					<td>${special.user.name}</td>
					<td class="tit">部门</td>
					<td>${special.office.name}</td>
				</tr>
				<tr>
					<td class="tit">日期</td>
					<td>
						<fmt:formatDate value="${special.tbDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
					<td class="tit">预估金额</td>
					<td>
						<fmt:formatNumber type="number" value="${special.money}" maxFractionDigits="2" pattern="#.00"/>元
					</td>
				</tr>
				<tr>
					<td class="tit">支付方式</td>
					<td>
						<c:if test="${special.payType==1 }">现金
						</c:if>
						<c:if test="${special.payType==2 }">转账
						</c:if>
					</td>
					<td class="tit">账户信息</td>
					<td>${special.account }</td>
				</tr>
				<tr>
					<td class="tit">申请理由</td>
					<td colspan="4">${special.notes }</td>
				</tr>
				<c:if test="${not empty special.proneText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="4">${special.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty special.prtwoText}">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="4">${special.prtwoText}</td>
					</tr>
				</c:if>

				
				<tr>
					<td class="tit">您的意见</td>
					<td colspan="4"><form:textarea path="act.comment"
							 rows="5" maxlength="500" cssStyle="width:500px" />
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="同 意" onclick="$('#flag').val('yes')" />&nbsp; 
				<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回"
				onclick="$('#flag').val('no')" />&nbsp; 
				<input id="btnCancel"
				class="btn" type="button" value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<act:histoicFlow procInsId="${special.act.procInsId}" />
		</form:form>	
		
</body>
</html>