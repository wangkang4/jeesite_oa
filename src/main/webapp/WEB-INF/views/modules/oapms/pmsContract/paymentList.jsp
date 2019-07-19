<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>回款计划列表</title>
<meta name="decorator" content="default" />
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
		<li class="active"><a href="${ctx}/oapms/pmsPayment/list">回款计划列表</a></li>
		<li><a href="${ctx}/oapms/pmsPayment/form">回款计划添加</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="pmsPayment"
		action="${ctx}/oapms/pmsPayment/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<label>回款时间:</label>
		<input id="paymentTime" name="paymentTime" type="text"
			readonly="readonly" maxlength="20" class="input-medium Wdate"
			value="<fmt:formatDate value="${pmsPayment.paymentTime }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>回款状态:</label>
		<form:select path="paymentStatus" class="input-medium">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('pms_payment_status')}"
				itemLabel="label" itemValue="value" htmlEscape="false" />
		</form:select>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;
		<input type="button" class="btn btn-primary" onclick="location.href=''" value="清除">
		<!-- &nbsp;&nbsp;<input type="button" class="btn btn-primary" onclick="downCustomer()" value="导出"> -->
	</form:form>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;vertical-align:middle;">回款时间</th>
				<th style="text-align:center;vertical-align:middle;">回款金额</th>
				<th style="text-align:center;vertical-align:middle;">回款方式</th>
				<th style="text-align:center;vertical-align:middle;">回款状态</th>
				<th style="text-align:center;vertical-align:middle;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pmsPayment">
				<tr>
					<td style="text-align:center;vertical-align:middle;"><fmt:formatDate value="${pmsPayment.paymentTime }"
							type="both" pattern="yyyy-MM-dd" /></td>
					<td style="text-align:center;vertical-align:middle;">${pmsPayment.paymentAmount }</td>
					<td style="text-align:center;vertical-align:middle;">${fns:getDictLabel(pmsPayment.paymentMode, 'pms_payment_mode', '')}
					</td>
					<td style="text-align:center;vertical-align:middle;">${fns:getDictLabel(pmsPayment.paymentStatus, 'pms_payment_status', '')}
					</td>
					<td style="text-align:center;vertical-align:middle;"><a
						href="${ctx}/oapms/pmsPayment/toUpdate?paymentId=${pmsPayment.paymentId}">修改</a>
						<a
						href="${ctx}/oapms/pmsPayment/delete?paymentId=${pmsPayment.paymentId}"
						onclick="return confirmx('确认要删除该申请吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
