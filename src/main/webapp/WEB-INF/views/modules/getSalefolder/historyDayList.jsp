<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>报销管理</title>
	<script type="text/javascript">
			$(document).ready(function() {
				
			});
			function downGetSale(){
				var startTime1 = $("#startTime1").val();
				var endTime1 = $("#endTime1").val();
				location.href = "${ctx}/get/history/SummaryExExcel?startTime1="+startTime1
				+ "&endTime1="+endTime1;
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/get/history/historyExpense">报销历史格式一</a></li>
		<li><a href="">报销历史格式二</a></li>
		<li class="active"><a href="">出差天数统计</a></li>
		<li><a href="${ctx}/activity/leaAndove/list">请假加班历史</a></li>
	</ul>
	
	<form:form id="searchExpenseForm" modelAttribute=""
		action="" method="post"
		class="breadcrumb form-search">
		<label>起始时间:</label>
		<input id="startTime1" name="startTime1" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${startTime }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>结束时间:</label>
		<input id="endTime1" name="endTime1" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href= '${ctx}/get/history/list'">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="导出" onclick="downGetSale()">
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>部门</th>
				<th>地点</th>
				<th>出差天数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="getSaleSummary">
				<tr>
					<td>${getSaleSummary.userName}</td>
					<td>${getSaleSummary.officeName}</td>
					<td>${getSaleSummary.areaName }</td>
					<td>day</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>