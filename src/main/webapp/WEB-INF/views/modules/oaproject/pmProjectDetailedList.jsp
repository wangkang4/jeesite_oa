<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
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
		<li class="active"><a href="${ctx}/oaproject/pmProjectDetailed/">单表列表</a></li>
		<li><a href="${ctx}/oaproject/pmProjectDetailed/form">单表添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pmProjectDetailed" action="${ctx}/oaproject/pmProjectDetailed/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>其他说明</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oaproject:pmProjectDetailed:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pmProjectDetailed">
			<tr>
				<td><a href="${ctx}/oaproject/pmProjectDetailed/form?id=${pmProjectDetailed.id}">
					${pmProjectDetailed.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${pmProjectDetailed.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/oaproject/pmProjectDetailed/form?id=${pmProjectDetailed.id}">修改</a>
					<a href="${ctx}/oaproject/pmProjectDetailed/delete?id=${pmProjectDetailed.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>