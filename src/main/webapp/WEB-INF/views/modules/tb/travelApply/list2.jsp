<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>出差申请管理</title>
	<script type="text/javascript">
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="list">
		<li class=""><a href="${ctx}/tb/travelApply/list">出差申请列表</a></li>
		<li  class=""><a href="${ctx}/tb/travelApply/add">出差申请表</a></li>	
		<shiro:hasAnyRoles name="xingzheng">
			<li class="active"><a href="${ctx}/tb/travelApply/list2">所属区域员工出差申请列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="caiwu">
			<li class="active"><a href="${ctx}/tb/travelApply/listCaiWu">所有员工出差申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="travelApply"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请人</th>
				<th>部门</th>
				<th>出差编号</th>
				<th>申请时间</th>
				<th>流程状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="travelApply">
			<tr>
				<td>${travelApply.user.name}</td>
				<td>${travelApply.office.name}</td>
				<td>${travelApply.num}</td>
				<td><fmt:formatDate value="${travelApply.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${travelApply.statu}</td>
				<td>
					<a href="${ctx}/tb/travelApply/view?id=${travelApply.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>