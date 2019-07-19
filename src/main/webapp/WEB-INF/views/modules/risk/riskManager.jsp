<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>风险管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	
</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active"><a href="">风险列表</a></li>
		<li><a href="${ctx}/pms/risk/addrisk">风险添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="RiskInfo"
		action="${ctx}/pms/risk/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>名称：</label>
				<input type="text"  name="pmsPro.projectName">
			</li>
			<li><label>状态：</label>
				<select style="width: 200px;"name="riskState">
					<option value="">--请选择--</option>
					<option value="已关闭">已关闭</option>
					<option value="未关闭">未关闭</option>
				</select>
				
				
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input type="button" class="btn btn-primary" onclick="javascript:window.location.href=''" value="清除"></li>
			<li class="clearfix"></li>
		</ul>
		
		
	</form:form>
	
	<table style ="text-align: center;"id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>风险标题</th>
				<th>概率</th>
				<th>影响</th>
				<th>状态</th>
				<th>责任人</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}"  var="risk">
				<tr>
					<td>	
					   	${risk.pmsPro.projectName}
					</td>
					<td>
						${risk.riskName}
					</td>
					<td>
						${risk.riskPro}
					</td>
					<td>
						${risk.riskInfu}
					</td>
					<td>
						${risk.riskState}
					</td>
					<td>
						${risk.user.name}
					</td>
					<td>	
						<fmt:formatDate value="${risk.createDate}" type="both" pattern="yyyy-MM-dd "/>
					</td>
						<td> <a
						href="${ctx}/pms/risk/delete?id=${risk.id}"
						onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a>
					   <a href="${ctx}/pms/risk/updaterisk?id=${risk.id}">修改</a>
						<a href="${ctx}/pms/riskback/showback?id=${risk.id}">反馈</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>