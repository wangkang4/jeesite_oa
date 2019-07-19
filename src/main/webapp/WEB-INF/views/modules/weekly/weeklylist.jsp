<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>保存成功管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.aaa {
	width: 100px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/weekly/">我发出的</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="weekly"
		action="${ctx}/oa/weekly/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>周报时间：</label>
		<input name="daytime" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<%-- <form:input path="daytime" htmlEscape="false" maxlength="50" class="input-medium"/>	 --%>	
		&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary"
			type="submit" value="查询" />
		&nbsp;&nbsp;<input type="reset" class="btn btn-primary" value="清除">
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工作内容</th>
				<th>工作计划</th>
				<th>收报人</th>
				<th>发送时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="weekly">
				<tr>
					<td>
						<div class="aaa">
							${weekly.daycontent }
						</div>
					</td>
					<td>
						<div class="aaa">
							${weekly.plancontent }
						</div>
					</td>
					<td>${weekly.remarks}</td>
					<td><fmt:formatDate value="${weekly.creatertime}"
							pattern="yyyy-MM-dd" /></td>
					<td><a href="${ctx}/oa/weekly/form?id=${weekly.id}">修改</a> <a
						href="${ctx}/oa/weekly/delete?id=${weekly.id}"
						onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>