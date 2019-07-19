<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>员工离职申请管理</title>
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
		<li class=""><a href="${ctx}/tb/quit/list">离职申请列表</a></li>
		<li  class=""><a href="${ctx}/tb/quit/add">离职申请表</a></li>
		<%--<c:if test="${fns:getUser().loginName=='俞跃舒' || fns:getUser().loginName=='李国强'}">
			<li class=""><a href="${ctx}/tb/quit/list2">全体员工离职列表</a></li>
		</c:if>--%>
		<shiro:hasAnyRoles name="caiwuzhongnan">
			<li class="active"><a href="${ctx}/tb/quit/list4">全体员工离职列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/quit/list3">所属区域员工离职列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="quit"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请人</th>
				<th>部门</th>
				<th>名称</th>
				<th>申请时间</th>
				<th>附件</th>
				<th>流程状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="quit">
			<tr>
				<td>${quit.user.name}</td>
				<td>${quit.office.name}</td>
				<td>离职申请</td>
				<td><fmt:formatDate value="${quit.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:if test="${not empty quit.address }">
						<a href="${ctx}/tb/quit/download?id=${quit.id}">附件下载</a>
					</c:if>
					<c:if test="${empty quit.address }">
						无附件
					</c:if>
				</td>
				<td>${quit.statu}</td>
				
				<td>
					<a href="${ctx}/tb/quit/view?id=${quit.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>