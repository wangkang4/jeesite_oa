<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>联系人列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/contact/contactList.js"></script>
	<script type="text/javascript">
	function downContact(){
		var customerContactName = $("#customerContactName").val();
		var customerId = $("#customerId").val();
		location.href = "${ctx}/oapms/contact/downContact?customerContactName="+customerContactName
				+ "&customerId="+customerId;
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">联系人列表</a></li>
		<li><a href="${ctx}/oapms/contact/toAdd">联系人添加</a></li>
	</ul>
	<input type="hidden" id="thinkgem" value=${fns:getUser()}>
	<form:form id="addForm" modelAttribute="contact"
		action="${ctx}/oapms/contact/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>姓名:</label>
		<input type="text" name="customerContactName" id="customerContactName" value="${contact.customerContactName }">
		<label>所属公司:</label>
		<select class="input-medium" id="customerId" name="customer.customerId">
					<option value="">请选择</option>
					<c:forEach items="${customerList }" var="custo">
						<option value=${custo.customerId }<c:if test='${contact.customer.customerId== custo.customerId }'>  selected  </c:if>>${custo.customerName }</option>
					</c:forEach>
				</select>
		&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary"
			type="submit" value="查询" />
		&nbsp;&nbsp;<input type="button" id="clear" class="btn btn-primary" onclick="javascript:window.location.href=''" value="清除">
		&nbsp;
	</form:form>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>代号</th>
				<th>所属公司</th>
				<th>联系电话</th>
				<th>职位</th>
				<th>兴趣爱好</th>
				<th>性格特征</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="contact">
				<tr>
					<td>
						${contact.customerContactName }
					</td>
					<td>
						${contact.codeName }
					</td>
					<td>
						${contact.customer.customerName }
					</td>
					<td>
						${contact.phone }
					</td>
					<td>
						${contact.position }
					</td>
					<td>
						${contact.interest }
					</td>
					<td>
						${contact.customerCharacter }
					</td>
					<td>
						<a onclick="updateContact(this)">修改</a>
						<input type="hidden" value=${contact.customerContactId }>
						<a onclick="deleteContact(this)">删除</a>
						<a onclick="contactDetail(this)">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
