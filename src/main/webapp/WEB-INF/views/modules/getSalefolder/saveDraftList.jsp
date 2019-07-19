<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>报销管理</title>
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
		<li><a href="${ctx}/get/sale/">报销列表</a></li>
		<li class="active"><a href="${ctx}/get/sale/listDraft">草稿列表</a></li>
		<li><a href="${ctx}/get/sale/form">报销申请流程</a></li>
		<c:if test="${fns:getUser().loginName=='俞林伟'}">
			<li><a href="${ctx}/get/sale/CWSubList">全体人员报销单</a></li>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="getSale"
		action="${ctx}/get/sale/listDraft" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>报销总金额</th><th>申请时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="getSale">
			<tr>
				<td>${getSale.user.name}</td>
				<td>${getSale.office.name}</td>
				<td>
				<fmt:formatNumber type="number" value="${getSale.forMoney}" maxFractionDigits="2" pattern="#.00"/>元
				</td>
				<td><fmt:formatDate value="${getSale.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
    				<a href="${ctx}/get/sale/form1?id=${getSale.id}">查看</a>
					<a href="${ctx}/get/sale/delete?id=${getSale.id}" onclick="return confirmx('确认要删除该申请吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>