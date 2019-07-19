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
		<li class=""><a href="${ctx}/tb/money/specialList">专项费用列表</a></li>
		<li><a href="${ctx}/tb/money/toAddSpecial">专项费用申请</a></li>
		<li class="active"><a href="${ctx}/tb/money/specialEmployeesList">员工专项费用列表</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="Special"
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
				<th>预审批金额</th>
				<th>申请理由</th>
				<th>申请时间</th>
				<th>流程状态</th>
				<th>操作</th>
				<th>附件</th>
				<shiro:hasRole name="seeEmployee">
				<th>附件下载</th>
				</shiro:hasRole>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="special">
			<tr>
				<td>${special.user.name}</td>
				<td>${special.office.name}</td>
				<td>
					<fmt:formatNumber type="number" value="${special.money}" maxFractionDigits="2" pattern="#.00"/>元
				</td>
				<td>
					${special.notes }
				</td>
				<td><fmt:formatDate value="${special.tbDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${special.statu}</td>
				<td>
				<c:choose>
					<c:when test="${special.statu=='驳回' }">
	                    <a href="${ctx}/tb/money/toAddSpecial?id=${special.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/money/form?id=${special.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				</td>
				<td>
					<c:if test="${not empty special.address }">
						<a href="${ctx}/tb/money/downloadSpecial?id=${special.id}">银行流水单下载</a>
					</c:if>
				</td>
				<shiro:hasRole name="seeEmployee">
				<td>
					<form style="margin-bottom:0px;" action="${ctx}/tb/money/uploadSpecial" enctype="multipart/form-data" method="post">
						<input type="hidden" value="${special.id}" name="id">
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