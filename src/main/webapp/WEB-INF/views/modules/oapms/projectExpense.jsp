<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目费用</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		function ExpenseAdd(){
			var id = $("#projectId").val();
			location.href = "${ctx}/pms/projectHelp/expenseForm?id=" + id;
		}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/project/projectDetail?id=${pmsProjectExpense.project.projectId}">基本信息</a></li>
		<li><a href="${ctx}/pms/comment/projectComment?id=${pmsProjectExpense.project.projectId}">评论</a></li>
		<li><a href="${ctx}/pms/comment/projectDocument?id=${pmsProjectExpense.project.projectId}">文档</a></li>
		<li><a href="${ctx}/pms/projectHelp/helpList?id=${pmsProjectExpense.project.projectId}">求助</a></li>
		<li class="active"><a href="#">费用</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsProjectExpense"
		action="${ctx}/pms/projectHelp/expenseList?id=${pmsProjectExpense.project.projectId}" method="post"
		class="breadcrumb form-search">
		<form:hidden path="project.projectId" id="projectId" value="${pmsProjectExpense.project.projectId}" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>费用类型：</label>
			<form:select id="status" path="status" class="input-medium" >
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('project_expense_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input type="button" class="btn btn-primary"  onclick="javascript:window.location.href=''" value="清除"></li>
			<li class="btns" style="margin-right:50px;"><input id="btn"  class="btn btn-primary" type="button" value="费用新增"
				onclick="ExpenseAdd()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属项目</th>
				<th>费用类型</th>
				<th>金额</th>
				<th>申请者</th>
				<th>申请时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="projectExpense">
				<tr>
					<td>
						${projectExpense.project.projectName }
					</td>
					<td>
						${fns:getDictLabel(projectExpense.status , 'project_expense_status', '')}
					</td>
					<td>
						${projectExpense.money }元
					</td>
					<td>
						${projectExpense.createBy.name }
					</td>
					<td>
						<fmt:formatDate value="${projectExpense.createDate }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td><a href="${ctx}/pms/projectHelp/expenseForm?expenseId=${projectExpense.expenseId}">修改</a> <a
						href="${ctx}/pms/projectHelp/expenseDelete?id=${projectExpense.expenseId}"
						onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>