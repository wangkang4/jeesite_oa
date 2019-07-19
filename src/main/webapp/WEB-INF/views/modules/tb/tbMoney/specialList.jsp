<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>专项费用预批管理</title>
	<script type="text/javascript">
			$(document).ready(function() {
				var url = window.location.href;
				var a = url.endsWith("specialLeaderList");
				if(a){
					$("#list li:last-child").attr("class","active");
					$("#searchForm").attr("action","${ctx}/tb/money/specialLeaderList");
				}else{
					$("#list li:first-child").attr("class","active");
					$("#searchForm").attr("action","${ctx}/tb/money/specialList");
				}
			});
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
		<li class=""><a href="${ctx}/tb/money/specialList">专项费用列表</a></li>
		<li><a href="${ctx}/tb/money/toAddSpecial">专项费用申请</a></li>
		<c:if test="${fns:getUser().loginName=='俞跃舒' }">
			<li class=""><a href="${ctx}/tb/money/specialLeaderList">领导专项费用列表</a></li>
		</c:if>
		<shiro:hasAnyRoles name="seeEmployees,caiwuzhongnan">
		<li><a href="${ctx}/tb/money/specialEmployeesList">员工专项费用列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/money/specialList2">所属区域员工专项费用列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="Special"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>预审批金额</th><th>申请理由</th><th>申请时间</th><th>流程状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="special">
			<tr>
				<td>${special.user.name}</td>
				<td>${special.office.name}</td>
				<td>
					<fmt:formatNumber type="number" value="${special.money}" maxFractionDigits="2" pattern="#.00"/>元
				</td>
				<td>
					${special.notes }
				</td>
				<td><fmt:formatDate value="${special.tbDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${special.statu}</td>
				<td>
				<c:choose>
					<c:when test="${special.statu=='驳回' }">
	                    <a href="${ctx}/tb/money/toAddSpecial?id=${special.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/money/form?id=${special.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				<c:if test="${empty special.proneText and empty special.prtwoText}">
				<a href="${ctx }/tb/roll/withdraw?id=${special.id }&tableName=tb_special&view=special">撤回</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>