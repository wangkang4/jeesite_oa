<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>专项费用预批管理</title>
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
		<li class=""><a href="${ctx}/tb/money/businessList">业务招待费用列表</a></li>
		<li><a href="${ctx}/tb/money/toAddBusiness">业务招待费用申请</a></li>
		<li class="active"><a href="${ctx}/tb/money/businessEmployeesList">员工业务招待费用列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="Business"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>部门</th>
				<th>用餐金额</th>
				<th>用餐事由</th>
				<th>申请时间</th>
				<th>流程状态</th>
				<th>操作</th>
				<th>附件</th>
				<shiro:hasRole name="seeEmployee">
				<th>附件上传</th>
				</shiro:hasRole>
			</tr>
		</thead>
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
				<c:choose>
					<c:when test="${business.statu=='驳回' }">
	                    <a href="${ctx}/tb/money/toAddBusiness?id=${business.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/money/form?id=${business.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				</td>
				<td>
					<c:if test="${not empty business.address }">
						<a href="${ctx}/tb/money/downloadBusiness?id=${business.id}">银行流水单下载</a>
					</c:if>
				</td>
				<shiro:hasRole name="seeEmployee">
				<td>
					<form style="margin-bottom:0px;" action="${ctx}/tb/money/uploadBusiness" enctype="multipart/form-data" method="post">
						<input type="hidden" value="${business.id}" name="id">
						<input type="file" name="file" style="width:180px">
						<input type="submit" value="确认上传">
					</form>
				</td>
				</shiro:hasRole>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>