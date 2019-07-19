<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>客户列表</title>
<style type="text/css">
#contentTable thead tr th {
	text-align: center;
}

#contentTable tbody tr td {
	text-align: center;
}
</style>
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>

</head>
<body>
	<div style="width: 98%; border: 1px solid #eee; margin: 10px auto;">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx }/oa/client/list">查阅列表</a></li>
			<c:if test="${rol eq 'admin' }"><li><a href="${ctx }/oa/client/form">客户新增</a></li></c:if>
			
		</ul>

		<form:form id="searchForm" modelAttribute="client"
			action="${ctx}/oa/client/list" method="post"
			class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
		</form:form>
		<sys:message content="${message}" />
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>客户名称</th>
					<th>客户公司</th>
					<th>客户负责人名称</th>
					<th>客户负责人手机</th>
					<th>客户负责人性别</th>
					<th>备注</th>
					<c:if test="${rol eq 'admin' }">
						<th>功能</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="client">
					<tr>
						<td>${client.clientName }</td>
						<td>${client.clientCompany }</td>
						<td>${client.clientManagerName }</td>
						<td>${client.clientManagerPhone }</td>
						<td>${client.clientManagerSex }</td>
						<td>${client.content }</td>
						<c:if test="${rol eq 'admin' }">
							<td><a
								href="${ctx}/oa/client/form?id=${client.id }">修改</a>
								<a
								href="${ctx}/oa/client/delete?id=${client.id }"
								onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</div>
</body>
</html>