<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>请假加班管理</title>
	<script type="text/javascript">
			$(document).ready(function() {
				var leave=$("#leave").val();
				console.log("leave:"+leave);
				var day=leave/8;
			});
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }
			function resetForm(){
				location.href= "${ctx}/activity/leaAndove/list";
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/get/history/historyExpense">报销历史格式一</a></li>
		<li><a href="${ctx}/get/history/list">报销历史格式二</a></li>
		<li class="active"><a href="">请假加班历史</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
			<label>请假人：</label>
			<sys:treeselect id="id" name="id" value="${user.id}" labelName="user.name" labelValue="${user.name}"
				title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			<label>部门：</label>
			<sys:treeselect id="officeid" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
				title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="清除" onclick="resetForm()">
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>部门</th>
				<th>手机</th>
				<th>累计加班时间</th>
				<th>可用调休时间</th>
				<th>累计请假时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.name}</td>
				<td>${user.office.name}</td>
				<td>${user.phone}</td>
				<td>
					<fmt:formatNumber type="number" value="${user.numOverTimeDays}" maxFractionDigits="0"/>小时 
				</td>
				<td>
					<fmt:formatNumber type="number" value="${user.useOverTimeDays}" maxFractionDigits="0"/>小时 
				</td>
				<td >
					<fmt:formatNumber  type="number"  value="${user.numLeaveDays/8}" maxFractionDigits="0"/>天
				</td>
					
				<td>
    				<a href="${ctx}/activity/leaAndove/leaveList?id=${user.id}">请假</a>
    				&nbsp;&nbsp;&nbsp;&nbsp;
    				<a href="${ctx}/activity/leaAndove/overList?id=${user.id}">加班</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>