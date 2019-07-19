<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>签到管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li style="font-size: 15px"><a
			href="${ctx}/daysign/udaysign/allList">个人签到</a></li>
	</ul>
	<br />

	<form:form id="searchForm" modelAttribute="udaysign"
		action="${ctx}/daysign/udaysign/allList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<shiro:hasPermission name="daysign:udaysign:edit">
				<li>日期时间 ：<input id="dayTime" name="dayTime" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate"
					style="width: 163px;"
					value="<fmt:formatDate value="${tDaily.dayTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></li>
				<li><label>ip地址：</label> <form:input path="ip"
						htmlEscape="false" maxlength="100" class="input-medium" /></li>
			</shiro:hasPermission>
			<li><label>状态：</label> <form:select path="state"
					class="input-medium">
					<form:option value="" label="" />
					<form:option value="0" label="正常" />
					<form:option value="1" label="迟到" />
					<form:option value="2" label="早退" />

				</form:select></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>用户名</th>
				<th>ip地址</th>
				<th>签到时间</th>
				<th>签到状态</th>

				<th>签退时间</th>
				<th>签退状态</th>
				<th>备注</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="udaysign">
				<tr>
					<td>${udaysign.user.name}</td>
					<td>${udaysign.name}</td>
					<td>${udaysign.ip}</td>
					<td><fmt:formatDate value="${udaysign.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<c:if test="${udaysign.state eq 0}">
						<td>正常</td>
					</c:if>
					<c:if test="${udaysign.state eq 1}">
						<td>迟到</td>
					</c:if>

					<td><fmt:formatDate value="${udaysign.end}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<c:if test="${udaysign.endState eq 4}">
						<td>早退</td>
					</c:if>
					<c:if test="${udaysign.endState eq 0}">
						<td>正常</td>
					</c:if>
					<c:if test="${udaysign.endState eq null}">
						<td></td>
					</c:if>

					<td>${udaysign.remarks}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>