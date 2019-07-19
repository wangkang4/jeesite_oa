<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/customer/customerList.js"></script>
	<script type="text/javascript">
	function downCustomer(){
		var customerName = $("#customerName").val();
		var industry = $("#industry").val();
		location.href = "${ctx}/oapms/customer/downCustomer?customerName="+customerName
				+ "&industry="+industry;
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">客户列表</a></li>
		<li><a href="${ctx}/oapms/customer/toAdd">客户添加</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="customer"
		action="${ctx}/oapms/customer/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>客户名称:</label>
		<input type="text" name="customerName" id="customerName" value="${customer.customerName }">
		<label>所属行业:</label>
		<form:select path="industry" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_customer_industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
		
		&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary"
			type="submit" value="查询" />
		&nbsp;&nbsp;<input type="button" class="btn btn-primary" onclick="javascript:window.location.href=''" value="清除">
		&nbsp;&nbsp;<input type="button" class="btn btn-primary" onclick="downCustomer()" value="导出">
	</form:form>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>客户名称</th>
				<th>所属行业</th>
				<th>所属类别</th>
				<th>附件</th>
				<th>销售经理</th>
				<th>产品经理</th>
				<th>创建人</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="customer">
				<tr>
					<td>
						${customer.customerName }
					</td>
					<td>
						${fns:getDictLabel(customer.industry, 'pms_customer_industry', '')}
					</td>
					<td>
						${fns:getDictLabel(customer.category, 'pms_customer_category', '')}
					</td>
					<td>
						<a href="${ctx}/oapms/customer/download?id=${customer.attachmentAddress}">${customer.attachmentName}</a>
					</td>
					<td>
						${customer.saler.name }
					</td>
					<td>
						${customer.producter.name }
					</td>
					<td>
						${customer.createBy.name }
					</td>
					<td>
						<a onclick="updateCustomer(this)">修改</a>
						
						<input type="hidden" value=${customer.customerId }>
						<a onclick="deleteCustomer(this)">删除</a>
						<a onclick="customerDetail(this)">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
