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
		<li class="active"><a href="">借款列表</a></li>
		<li><a href="${ctx}/tb/borrowing/toAdd">借款申请</a></li>
		<shiro:hasAnyRoles name="seeEmployee,caiwuzhongnan">
		<li><a href="${ctx}/tb/borrowing/employeesList">员工借款列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/borrowing/borrowingList2">所属区域员工借款列表</a></li>
		</shiro:hasAnyRoles>
	</ul>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>借款人</th>
			<th>所属部门</th>
			<th>标题</th>
			<th>借款金额</th>
			<th>申请时间</th>
			<th>银行流水单下载</th>
			<th>流程状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="borrowing">
			<tr>
				<td>${borrowing.name.name}</td>
				<td>${borrowing.office.name}</td>
				<td>
					${borrowing.title }
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowing.money}" maxFractionDigits="2" pattern="#.00"/>元
				</td>
				<td>
					<fmt:formatDate value="${borrowing.createDate}" type="both" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:if test="${not empty borrowing.address }">
						<a href="${ctx}/tb/borrowing/download?id=${borrowing.id}">银行流水单下载</a>
					</c:if>
				</td>
				<td>${borrowing.statu}</td>
				<td>
				<c:choose>
					<c:when test="${borrowing.statu=='驳回' }">
	                    <a href="${ctx}/tb/borrowing/toAdd?id=${borrowing.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/borrowing/form?id=${borrowing.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				<c:if test="${empty borrowing.prthreeText && empty borrowing.prsixText || borrowing.statu=='驳回'}">
				<a href="${ctx }/tb/roll/withdraw?id=${borrowing.id }&tableName=tb_borrowing&view=borrowing">撤回</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>