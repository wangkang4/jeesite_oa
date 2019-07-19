<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
		
		function tianjia(){
			window.location = "${ctx}/customer/customerForm";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/customer/mycustomerList">客户列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/customer/mycustomerList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${customerListPage.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${customerListPage.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>客户姓名：</label>
				<input name="customerName" maxlength="40" class="input-medium"/>
			</li>
			<li>
				<label>状态：</label> 
				<select name="status">
					<option>---请选择---</option>
				</select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="客户添加" onclick="tianjia()"/>
			</li>
		</ul>
	</form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>客户姓名</th>
				<th>联系电话</th>
				<th>单位</th>
				<th>职位</th>
				<th>审批状态</th>			
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${customerListPage.list}" var="customerInfo">
			<tr>
				<td><a href="${ctx}/customer/customerDetail?customerId=${customerInfo.id}">
					${customerInfo.customerName}
				</a></td>
				<td>
					${customerInfo.phone}
				</td>
				<td>
					${customerInfo.company}
				</td>
				<td>
					${customerInfo.position}
				</td>
				
				<td>
					<c:choose>
						<c:when test="${customerInfo.examine == '待审批'}">
							<a href="${ctx}/customer/examine?customerId=${customerInfo.id}">${customerInfo.examine}</a>
						</c:when>
						<c:otherwise>
							${customerInfo.examine}
						</c:otherwise>
					</c:choose>
				</td>
				<!-- 
				<td>
					<fmt:formatDate value="${dmStudent.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${dmStudent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				 -->
				
				<td>
    				<a href="${ctx}/customer/customerInfoUpdateForm?customerId=${customerInfo.id}">修改</a>
					<a href="${ctx}/customer/customerInfoDelete?customerId=${customerInfo.id}" onclick="return confirmx('确认要删除该客户的所有信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${customerListPage}</div>
</body>
</html>