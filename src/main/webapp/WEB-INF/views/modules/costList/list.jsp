<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>费用统计总表</title>
<style type="text/css">
#contentTable thead tr th {
	text-align: center;
}

#contentTable tbody tr td {
	text-align: center;
}
</style>
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	
	function getDetailsList() {
		var costId = $("#costId").val();
		$.ajax({
			type : 'post',
			url : "${ctx}/cost/costDetails/getDetailsList",
			data : {
				"costId" : costId
			},
			dataType : 'json',
			success : function(data) {

				$("#costDetails").empty();
				$("#costDetails").append("<option value=''>请选择</option>");
				for (var i = 0; i < data.length; i++) {
					$("#costDetails").append(
							"<option value='"+data[i].detailsId+"'>"
									+ data[i].detailsName + "</option>");
				}
			},
			error : function(data) {
				alert("错误信息");
			}

		});
	}

	
</script>

</head>
<body>
	<div style="width: 98%; border: 1px solid #eee; margin: 10px auto;">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx }/cost/costList/list">费用统计</a></li>
			<li><a href="${ctx }/cost/costList/toAdd">费用添加</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="account" 
		action="${ctx}/cost/costList/list" method="post" 
		class="breadcrumb form-search">

			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<ul class="ul-form">
			
				<label>费用分类：</label>
				<form:select id="costId" path="costId" class="input-xlarge"
					onclick="getDetailsList()">
					<option value="">请选择</option>
					<c:forEach var="costList" items="${list }">
						<form:option value="${costList.costId }" label="${costList.costName }"/>
					</c:forEach>
				</form:select>
				<label>详情分类：</label>
				<select id="costDetails" name="detailsId" class="input-xlarge">
					<option value="">请选择</option>
				</select>

				<!-- <input id=""  class="btn btn-primary" type="submit" value="查询" /> -->


			</ul>
		</form:form>
		<sys:message content="${message}" />
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>日期</th>
					<th>收据金额</th>
					<th>费用类型</th>
					<th>费用描述</th>
					<th>可报销金额</th>
					<th>详细信息</th>
					<th>操作选项</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="account">
					<tr>
						<td><fmt:formatDate value="${account.time}"
								pattern="yyyy-MM-dd" /></td>
						<td>${account.money}</td>
						<td>${account.detailsName}</td>
						<td>${account.costDescription }</td>
						<td>${account.amount }</td>
						<td>${account.information }</td>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</div>
</body>
</html>