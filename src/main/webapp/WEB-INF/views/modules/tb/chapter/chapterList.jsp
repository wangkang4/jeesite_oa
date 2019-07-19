<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>预批管理</title>
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
		<li class="active"><a href="">用印列表</a></li>
		<li><a href="${ctx}/tb/chapter/toAdd">用印申请流程</a></li>
		<shiro:hasRole name="caiwuzhongnan">
		<li><a href="${ctx}/tb/chapter/employeesList">员工用印列表</a></li>
		</shiro:hasRole>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/chapter/list2">所属区域员工用印列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="TBMoney"
		action="${ctx}/tb/chapter/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>标题</th><th>用印文件名</th><th>申请时间</th><th>流程状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="chapter">
			<tr>
				<td>${chapter.user.name}</td>
				<td>${chapter.office.name}</td>
				<td>${chapter.reason}</td>
				<td>${chapter.fileForChapter }</td>
				<td><fmt:formatDate value="${chapter.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${chapter.statu}</td>
				<td>
				<c:choose>
					<c:when test="${chapter.statu=='驳回' }">
	                    <a href="${ctx}/tb/chapter/toAdd?id=${chapter.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/chapter/form?id=${chapter.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				<c:if test="${empty chapter.prthreeText && empty chapter.prfourText || chapter.statu=='驳回'}">
				<a href="${ctx }/tb/roll/withdraw?id=${chapter.id }&tableName=tb_chapter&view=chapter">撤回</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>