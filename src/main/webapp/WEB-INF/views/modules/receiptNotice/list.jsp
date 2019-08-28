<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>收款通知</title>
	<script type="text/javascript">
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="list">
		<li class="active"><a href="${ctx}/tb/receiptNotice/list">收款通知列表</a></li>
		<li  class=""><a href="${ctx}/tb/receiptNotice/add">收款填写</a></li>
		<%--2019.8.28由谢晨改为马建新--%>
		<c:if test="${fns:getUser().loginName=='俞林伟'
			||fns:getUser().loginName=='马建新'
			||receiptNotice.office.name=='财务部'}">
		<li><a href="${ctx}/tb/receiptNotice/list2">员工收款通知列表</a></li>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="receiptNotice"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>填写人</th>
				<th>部门</th>
				<th>收款项目</th>
				<th>收款日期</th>
				<th>本次收款金额</th>
				<th>款项性质</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="receiptNotice">
			<tr>
				<td>${receiptNotice.user.name}</td>
				<td>${receiptNotice.office.name}</td>
				<td>${receiptNotice.receiptName}</td>
				<td>${receiptNotice.receiptDate}</td>
				<td>${receiptNotice.nowMoney}</td>
				<td>${receiptNotice.receiptNature}</td>
				<td>
					<a href="${ctx}/tb/receiptNotice/view?id=${receiptNotice.id}">详情</a>
					<a href="${ctx}/tb/receiptNotice/add?id=${receiptNotice.id}">修改</a>
					<a href="${ctx}/tb/receiptNotice/del?id=${receiptNotice.id}">销毁</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>