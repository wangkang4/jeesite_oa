<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>开票申请管理</title>
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
		<li class=""><a href="${ctx}/tb/invoiceApply/list">开票申请表列表</a></li>
		<li><a href="${ctx}/tb/invoiceApply/add">开票申请表</a></li>
		<li class="active"><a href="${ctx}/tb/invoiceApply/list2">全体员工开票申请列表</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/invoiceApply/list3">所属区域员工开票申请列表</a></li>
		</shiro:hasAnyRoles>	
	</ul>
	<form:form id="searchForm" modelAttribute="InvoiceApply"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>提交人</th>
				<th>开票名称</th>
				<th>本次开票金额</th>
				<th>申请时间</th>
				<th>流程状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="invoice">
			<tr>
				<td>${invoice.user.name}</td>
				<td>${invoice.taxName}</td>
				<td>${invoice.total}</td>
				<td><fmt:formatDate value="${invoice.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${invoice.statu}</td>
				<td>
					<a href="${ctx}/tb/invoiceApply/view?id=${invoice.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>