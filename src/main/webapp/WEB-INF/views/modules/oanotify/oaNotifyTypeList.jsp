<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知类型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
		
		$(document).ready(function(){
			$('#type').val('oa_notify_type');
			$('#searchForm').submit();
			return false; 
			});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oanotify/notifyType/getTypeList/">类型列表</a></li>
		<li><a href="${ctx}/oanotify/notifyType/form">类型添加</a></li>
	</ul>
<%-- 	<form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>类型：</label><form:select id="type" path="type" class="input-medium"><form:option value="" label=""/><form:options items="${typeList}" htmlEscape="false"/></form:select>
		&nbsp;&nbsp;<label>描述 ：</label><form:input path="description" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form> --%>
<%-- 	<sys:message content="${message}"/> --%>

<form action="" id="#searchForm" method="post" class="breadcrumb form-search">
<label>类型：</label><%-- <form:select id="type" path="type" class="input-medium"><form:option value="" label=""/><form:options items="${typeList}" htmlEscape="false"/></form:select>
		&nbsp;&nbsp;<label>描述 ：</label><form:input path="description" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
</form> --%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>键值</th><th>标签</th><th>类型</th><th>描述</th><th>排序</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${typeList}" var="dict">
			<tr>
				<td>${dict.value}</td>
				<td><a href="${ctx}/oanotify/notifyType/form?id=${dict.id}">${dict.label}</a></td>
				<td><a href="javascript:" onclick="$('#type').val('${dict.type}');$('#searchForm').submit();return false;">${dict.type}</a></td>
				<td>${dict.description}</td>
				<td>${dict.sort}</td>
			<td>
    				<a href="${ctx}/oanotify/notifyType/form?id=${dict.id}">修改</a>
					<a href="${ctx}/oanotify/notifyType/delete?id=${dict.id}&type=${dict.type}" onclick="return confirmx('确认要删除该字典吗？', this.href)">删除</a>
    				<%-- <a href="<c:url value='${fns:getAdminPath()}/sys/dict/form?type=${dict.type}&sort=${dict.sort+10}'><c:param name='description' value='${dict.description}'/></c:url>">添加键值</a> --%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>