<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目动态展示</title>
<meta name="decorator" content="default" />
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
		<li class="active"><a href="">项目动态列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsProjectOperation"
		action="${ctx}/pms/projectOpertion/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>项目名称：</label>
				<form:input path="project.projectName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input type="button" class="btn btn-primary" onclick="javascript:window.location.href=''" value="清除"></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>操作人</th>
				<th>操作时间</th>
				<th>操作内容</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pmsProjectOperation">
				<tr>
					<td>
						${pmsProjectOperation.project.projectName }
					</td>
					<td>
						${pmsProjectOperation.operationBy.name }
					</td>
					<td>
						<fmt:formatDate value="${pmsProjectOperation.operationTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						${pmsProjectOperation.content }
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>