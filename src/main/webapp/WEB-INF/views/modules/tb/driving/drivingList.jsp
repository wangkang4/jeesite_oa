<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>市内自驾车出行申请管理</title>
</head>
<body>
	<ul class="nav nav-tabs" id="list">
		<li class="active"><a href="">市内自驾车出行申请列表</a></li>
		<li><a href="${ctx}/tb/driving/toAdd">市内自驾车出行申请</a></li>
		<shiro:hasAnyRoles name="swperson,caiwuzhongnan">
		<li><a href="${ctx}/tb/driving/employeesList">员工市内自驾车出行申请</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/driving/drivingList2">所属区域员工市内自驾车出行申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>	
	<sys:message content="${message}"/>
	<table class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>申请人</th>
			<th>申请时间</th>
			<th>用车时间</th>
			<th>预计费用</th>
			<th>流程状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="driving">
			<tr>
				<td>${driving.createBy.name }</td>
				<td><fmt:formatDate value="${driving.createDate }" type="both" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${driving.transportTime }" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${driving.estimatedCost }元</td>
				<td>${driving.statu }</td>
				<td>
				<c:choose>
					<c:when test="${driving.statu=='驳回' }">
	                    <a href="${ctx}/tb/driving/toAdd?id=${driving.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/driving/form?id=${driving.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				<c:if test="${driving.statu=='审核中'}">
				<a href="${ctx }/tb/roll/withdraw?id=${driving.id }&tableName=tb_driving&view=driving">撤回</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>