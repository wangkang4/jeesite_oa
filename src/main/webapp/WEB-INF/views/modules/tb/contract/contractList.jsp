<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同管理</title>
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
		<li class="active"><a href="">合同列表</a></li>
		<li><a href="${ctx}/tb/contract/toAdd">合同申请</a></li>
		<shiro:hasRole name="caiwuzhongnan">
		<li><a href="${ctx}/tb/contract/employeesList">员工合同列表</a></li>
		</shiro:hasRole>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/contract/contractList2">所属区域员工合同列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="contract"
			   action="" method="post"
			   class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>姓名</th>
			<th>合同名称</th>
			<th>合同编号</th>
			<th>合同金额</th>
			<th>申请时间</th>
			<th>流程状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="contract">
			<tr>
				<td>${contract.createBy.name}</td>
				<td>${contract.contractName}</td>
				<td>
					${contract.contractNum }
				</td>
				<td>
					<fmt:formatNumber type="number" value="${contract.money}" maxFractionDigits="2" pattern="#.00"/>元
				</td>
				<td>
					<fmt:formatDate value="${contract.createDate}" type="both" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:if test="${empty contract.status }">
					${contract.statu}
					</c:if>
					<c:if test="${not empty contract.status }">
					${contract.status}
					</c:if>
				</td>
				<td>
				<c:choose>
					<c:when test="${contract.statu=='驳回' }">
	                    <a href="${ctx}/tb/contract/toAdd?id=${contract.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/contract/form?id=${contract.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				<c:if test="${empty contract.proneText and empty contract.prthreeText and empty contract.prsevenText}">
				<a href="${ctx }/tb/roll/withdraw?id=${contract.id }&tableName=tb_contract&view=contract">撤回</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>