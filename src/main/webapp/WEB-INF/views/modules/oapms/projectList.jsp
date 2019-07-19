<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目管理</title>
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
		<li class="active"><a href="">项目列表</a></li>
		<li><a href="${ctx }/pms/project/form">项目添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsProject"
		action="${ctx}/pms/project/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>项目名称：</label>
				<form:input path="projectName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>产品类型：</label>
				<form:select id="productType" path="productType" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_project_productType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>客户</th>
				<th>客户联系人</th>
				<th>目前阶段</th>
				<th>重要程度</th>
				<th>产品类别</th>
				<th>销售经理</th>
				<th>产品经理</th>
				<th>研发经理</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="project">
				<tr>
					<td>
						<a href="${ctx}/pms/project/projectDetail?id=${project.projectId}">${project.projectName }</a>
					</td>
					<td>
						${project.customer.customerName }
					</td>
					<td>
						${project.customerContact.customerContactName }
					</td>
					<td>
						<c:if test="${project.status ne '7'}">
							${fns:getDictLabel(project.status, 'pms_project_status', '')}
						</c:if>
						<c:if test="${project.status eq '7'}">
							${fns:getDictLabel(project.status, 'pms_project_status', '')}(${project.progress}%)
						</c:if>
						
					</td>
					<td>
						${fns:getDictLabel(project.importantDepende, 'pms_project_importantDepende', '')}
					</td>
					<td>
						${fns:getDictLabel(project.productType, 'pms_project_productType', '')}
					</td>
					<td>
						${project.saler.name }
					</td>
					<td>
						${project.producter.name }
					</td>
					<td>
						${project.devloper.name }
					</td>
					<td><a href="${ctx}/pms/project/form?id=${project.projectId}">修改</a> <a
						href="${ctx}/pms/project/delete?id=${project.projectId}"
						onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a>
						<a href="${ctx}/pms/project/projectDetail?id=${project.projectId}">更多</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>