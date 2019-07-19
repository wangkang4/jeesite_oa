<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>加班管理</title>
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
		<li class="active"><a href="#">加班列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ActivityLeave2"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>加班类型</th><th>加班时间</th><th>加班原因</th><th>申请时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="overTime">
			<tr>
				<td><a href="${ctx}/work/overtime/form?id=${overTime.id}">${overTime.user.name}</a></td>
				<td>${overTime.office.name}</td>
				<%-- <td>${fns:getDictLabel(overTime.overtimeType, 'oa_leave_type', '')}</td>  --%>
				<td>${overTime.overtimeType}</td>
				<td>
					<fmt:formatNumber type="number" value="${overTime.days}" maxFractionDigits="0"/>小时 
				</td>
				<td>${overTime.reason}</td>
				<td><fmt:formatDate value="${overTime.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
    				<a href="${ctx}/activity/leaAndove/overdetaile?id=${overTime.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>