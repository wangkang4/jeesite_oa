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
		<li class="active"><a href="">报销历史格式二</a></li>
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
				<th>办公费用</th>
				<th>通讯费</th>
				<th>交通费用</th>
				<th>汽车使用费</th>
				<th>差旅费用</th>
				<!-- <th>专项费用</th> -->
				<th>市场推广费</th>
				<th>维修费</th>
				<th>会务费</th>
				<th>业务招待费</th>
				<th>培训费</th>
				<th>福利费</th>
				<th>其他费用</th>
				<th>总计</th> 
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="getSaleSummary">
				<tr>
					<td>${getSaleSummary.userName}</td>
					<td>${getSaleSummary.officeName}</td>
					<td>${getSaleSummary.areaName }</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.dayExpenseOne} " maxFractionDigits="2"/>天
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.oneExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.twoExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.threeExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.fourExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.fiveExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.sixExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.sevenExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.eightExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.nineExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.tenExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.elevenExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.twelveExpense} " maxFractionDigits="2"/>元
					</td>
					<td>
						<fmt:formatNumber type="number" value="${getSaleSummary.expense} " maxFractionDigits="2"/>元
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>