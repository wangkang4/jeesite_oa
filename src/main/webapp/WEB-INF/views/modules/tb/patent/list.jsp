<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>专利申请管理</title>
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
		<li class="active"><a href="${ctx}/tb/patent/list">专利申请列表</a></li>
		<li  class="active"><a href="${ctx}/tb/patent/add">专利申请表</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class="active"><a href="${ctx}/tb/patent/list2">所属区域员工专利申请列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="caiwu">
			<li class="active"><a href="${ctx}/tb/patent/list3">员工专利申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="patent"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请人</th>
				<th>部门</th>
				<th>专利名称</th>
				<th>申请时间</th>
				<th>流程状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="patent">
			<tr>
				<td>${patent.user.name}</td>
				<td>${patent.office.name}</td>
				<td>${patent.patentName}</td>
				<td><fmt:formatDate value="${patent.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${patent.statu}</td>
				<td>
				<c:choose>
					<c:when test="${patent.statu=='驳回'}">
	                    <a href="${ctx}/tb/patent/add?id=${patent.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/patent/view?id=${patent.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				<c:choose>
					<c:when test="${patent.proneText!=null || patent.prtwoText!=null || patent.prthreeText!=null || patent.prfourText!=null}">
	                   	<c:choose>
							<c:when test="${patent.statu=='驳回'}">
			                   	<a href="${ctx}/tb/patent/back?id=${patent.id}">销毁申请</a>
							</c:when>
							<c:otherwise>
								不可撤销
							</c:otherwise> 
						</c:choose>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/patent/back?id=${patent.id}">销毁申请</a>
					</c:otherwise> 
				</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>