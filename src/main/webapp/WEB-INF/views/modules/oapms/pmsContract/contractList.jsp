<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>合同管理</title>
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#addForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oapms/pmsContract/list">合同列表</a></li>
		<li><a href="${ctx}/oapms/pmsContract/form">新增合同</a></li>	
	</ul>
	<form:form id="addForm" modelAttribute="pmsContract"
		action="${ctx}/oapms/pmsContract/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>开始时间:</label>
		<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${pmsContract.startTime}" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')}'});" />
		<label>结束时间:</label>
		<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${pmsContract.endTime}" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}'});" />
		
		<label>所属项目:</label>
		 <select class="input-medium" name="projectId">
			  <option value="">请选择</option>
			  <c:forEach var="project" items="${pmsProjectList }">
			  <option value = "${project.projectId }" ${project.projectId  == param['projectId']?'selected':''}>${project.projectName }</option>
				<%-- <option value="${project.projectId }">${project.projectName }</option> --%>
			  </c:forEach>
		      <select>
		
	    <label>所属客户:</label>
	    <select class="input-medium" name="customerId">
			  <option value="">请选择</option>
			  <c:forEach var="customer" items="${customerList }">
			    <option value = "${customer.customerId }" ${customer.customerId  == param['customerId']?'selected':''}>${customer.customerName }</option>
				<%-- <option value="${customer.customerId }">${customer.customerName }</option> --%>
			  </c:forEach>
		   <select>
		&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary"
			type="submit" value="查询" />
		&nbsp;&nbsp;<input type="button" class="btn btn-primary" onclick="javascript:window.location.href=''" value="清除">
		<!-- &nbsp;&nbsp;<input type="button" class="btn btn-primary" onclick="downCustomer()" value="导出"> -->
	</form:form>
	
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>合同标题</th>
				<th>所属客户</th>
				<th>所属项目</th>
				<th>合同金额</th>
				<th>累计收到金额</th>
				<th>开始日期</th>
				<th>结束日期</th>
				<th>服务期限</th>
				<th>合同条款</th>
				<th>附件</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pmsContract">
				<tr>
					<td>${pmsContract.title }</td>
					<td>${pmsContract.customer.customerName }</td>
					<td>${pmsContract.pmsProject.projectName }</td>
					<td>${pmsContract.amount}</td>
					<td>${pmsContract.totalAmount }</td>
					<td><fmt:formatDate value="${pmsContract.startTime}"
							type="both" pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${pmsContract.endTime}" type="both"
							pattern="yyyy-MM-dd" /></td>
					<td>${pmsContract.servicePeriod }</td>
					<td>${pmsContract.contractTerms }</td>
					<td><a href="${ctx }/project/upload/projectDocumentDownload?id=${pmsContract.attachmentAddress}">${pmsContract.attachmentName }</a></td>
					<td><a href="${ctx}/oapms/pmsPayment/list">回款计划</a>
						<a href="${ctx}/oapms/pmsContract/toUpdate?id=${pmsContract.id}">修改</a>
					 <a href="${ctx}/oapms/pmsContract/delete?id=${pmsContract.id}"
						onclick="return confirmx('确认要删除该申请吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>


