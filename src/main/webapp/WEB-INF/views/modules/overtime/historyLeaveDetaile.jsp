<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>请假管理</title>
	<script type="text/javascript">
		$(document).ready(
			);
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/leaAndove/leaveList?id=${leave.user.id}">请假列表</a></li>
		<li class="active"><a href="#">请假详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}"/>
		<fieldset>
			<legend>请假详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">姓名</td>
					<td>${leave.user.name}</td>
					<td class="tit">部门</td>
					<td>${leave.office.name}</td>
				</tr>
				<tr>
					<td class="tit">开始时间</td>
					<td>
						<fmt:formatDate value="${leave.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="tit">结束时间</td>
					<td>
						<fmt:formatDate value="${leave.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td class="tit">请假类型</td>
					<td>${fns:getDictLabel(leave.leaveType, 'oa_leave_type', '')}</td>
					<td class="tit">请假总时间</td>
					<td>
						<fmt:formatNumber type="number" value="${leave.days}" maxFractionDigits="0"/>小时 
					</td>
				</tr>
				<c:if test="${not empty leave.prText}">
				<tr>
					<td class="tit">项目经理意见</td>
					<td colspan="3">
						${leave.prText}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty leave.leaderText}">
				<tr>
					<td class="tit">地区领导意见</td>
					<td colspan="3">
						${leave.leaderText}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty leave.hrText}">
				<tr>
					<td class="tit">人事主管意见</td>
					<td colspan="3">
						${leave.hrText}
					</td>
				</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>