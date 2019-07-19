<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>加班管理</title>
<script type="text/javascript">
	$(document).ready(
			
			);
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/leaAndove/overList?id=${overTime.user.id}">加班列表</a></li>
		<li class="active"><a href="#">加班详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="overTime"
		action="" method="post"
		class="form-horizontal">
		<sys:message content="${message}" />
		<fieldset>
			<legend>员工加班申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">姓名</td>
					<td>${overTime.user.name}</td>
					<td class="tit">部门</td>
					<td>${overTime.office.name}</td>
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
				</tr>
				<tr>
					<td class="tit">加班原因</td>
					<td colspan="3">${overTime.reason }</td>
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
				<c:if test="${not empty overTime.hrText}">
					<tr>
						<td class="tit">人事主管意见</td>
						<td colspan="3">${overTime.hrText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
				<input id="btnCancel"
				class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>