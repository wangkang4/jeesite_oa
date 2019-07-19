<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>对外付款管理</title>
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
	<ul class="nav nav-tabs" id="list">
		<li class="active"><a href="">对外付款列表</a></li>
		<li><a href="${ctx}/tb/pay/toAdd">对外付款申请流程</a></li>
		<shiro:hasAnyRoles name="seeEmployee,caiwuzhongnan">
		<li><a href="${ctx}/tb/pay/employeesList">员工对外付款列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/pay/payList2">所属区域员工对外付款列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="pay"
		action="${ctx}/tb/pay/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>姓名</th>
			<th>合同名称</th>
			<th>本次应付款金额</th>
			<th>申请时间</th>
			<th>流程状态</th>
			<th>附件</th>
			<th>银行流水</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="pay">
			<tr>
				<td>${pay.createBy.name}</td>
				<td>${pay.projectName}</td>
				<td>
					<fmt:formatNumber type="number" value="${pay.payMoney}" maxFractionDigits="2" pattern="#.00"/>元
				</td>
				<td>
					<fmt:formatDate value="${pay.createDate}" type="both" pattern="yyyy-MM-dd"/>
				</td>
				<td>${pay.statu}</td>
				<td>
					<c:if test="${pay.applyAddress!=null }">
						<a href="${ctx}/tb/pay/download?id=${pay.id}&sign=2">用户附件下载</a>
					</c:if>
				</td>

				<td>
					<c:if test="${pay.invoicAddress!=null }">
						<a href="${ctx}/tb/pay/download?id=${pay.id}&sign=1">银行流水单下载</a>
					</c:if>
				</td>

				<td>
				<c:choose>
					<c:when test="${pay.statu=='驳回' }">
	                    <a href="${ctx}/tb/pay/toAdd?id=${pay.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/pay/form?id=${pay.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				<c:if test="${empty pay.proneText and empty pay.prthreeText and empty pay.prfourText}">
				<a href="${ctx }/tb/roll/withdraw?id=${pay.id }&tableName=tb_pay&view=pay">撤回</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>