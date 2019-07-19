<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>请假管理</title>
	<script type="text/javascript">
			$(document).ready(function() {
				
			});
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/leaAndove/list">请假加班历史</a></li>
		<li class="active"><a href="#">请假列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ActivityLeave2"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>请假类型</th><th>请假时间</th><th>请假原因</th><th>申请时间</th><th>销假总时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="activityLeave2">
			<tr>
				<td><a href="${ctx}/activity/leave2/form?id=${activityLeave2.id}">${activityLeave2.user.name}</a></td>
				<td>${activityLeave2.office.name}</td>
				<td>${fns:getDictLabel(activityLeave2.leaveType, 'oa_leave_type', '')}</td>
				<td>
					<fmt:formatNumber type="number" value="${activityLeave2.days/8}" maxFractionDigits="0"/>天 
				</td>
				<td>${activityLeave2.reason}</td>
				<td><fmt:formatDate value="${activityLeave2.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<fmt:formatNumber type="number" value="${activityLeave2.removeDays}" maxFractionDigits="0"/>小时 
				</td>
				<td>
    				<a href="${ctx}/activity/leaAndove/leavedetaile?id=${activityLeave2.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>