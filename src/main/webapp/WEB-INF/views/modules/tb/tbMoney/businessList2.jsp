<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>专项费用预批管理</title>
	<script type="text/javascript">
			$(document).ready(function() {
				var url = window.location.href;
				var a = url.endsWith("businessLeaderList");
				if(a){
					$("#list li:last-child").attr("class","active");
					$("#searchForm").attr("action","${ctx}/tb/money/businessLeaderList");
				}else{
					$("#list li:first-child").attr("class","active");
					$("#searchForm").attr("action","${ctx}/tb/money/businessList");
				}
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
		<li class=""><a href="${ctx}/tb/money/businessList">业务招待费用列表</a></li>
		<li class=""><a href="${ctx}/tb/money/toAddBusiness">业务招待费用申请</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class="active"><a href="${ctx}/tb/money/businessList2">所属区域员工业务招待费用列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="Business"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>用餐金额</th><th>用餐事由</th><th>申请时间</th><th>流程状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="business">
			<tr>
				<td>${business.user.name}</td>
				<td>${business.office.name}</td>
				<td>
					<fmt:formatNumber type="number" value="${business.money }" maxFractionDigits="2" pattern="#.00"/>元
				</td>
				<td>
					${business.notes }
				</td>
				<td><fmt:formatDate value="${business.tbDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${business.statu}</td>
				<td>
	               <a href="${ctx}/tb/money/form?id=${business.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>