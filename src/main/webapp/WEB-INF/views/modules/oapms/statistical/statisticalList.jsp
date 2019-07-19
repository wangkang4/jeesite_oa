<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目分析</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/expenseStatistical/highcharts.js"></script>
<script src="${ctxStatic}/expenseStatistical/exporting.js"></script>
<script src="${ctxStatic}/expenseStatistical/data.js"></script>
<script src="${ctxStatic}/expenseStatistical/drilldown.js"></script>
<script src="${ctxStatic}/expenseStatistical/highcharts-zh_CN.js"></script>
<script src="${ctxStatic}/oapms/statistical/statisticalList.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">项目多维分析</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="customer"
		action="${ctx}/oapms/customer/list" method="post"
		class="breadcrumb form-search">
		<label>维度:</label>
		<select class="input-medium" name="category" onchange="getHistogramX()">
			<option value="industry">行业</option>
			<option value="category">办事处</option>
			<option value="area">区域</option>
			<option value="customer">客户</option>
			<option value="saler">销售经理</option>
			<option value="projecter">产品经理</option>
			<option value="time">时间</option>
			<option value="status">项目状态</option>
			<option value="productType">产品类型</option>
		</select>
		<label>量度:</label>
		<select class="input-medium" name="num" onchange="getHistogramY()">
			<option value="money">销售额</option>
			<option value="forMoney">费用支出</option>
			<option value="count">项目数</option>
		</select>
	</form:form>
	<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
</body>
</html>