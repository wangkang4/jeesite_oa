<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>证照管理</title>
</head>
<body>
	<ul class="nav nav-tabs" id="list">
		<li class="active"><a href="">证照列表</a></li>
		<li><a href="${ctx}/tb/licenses/toAdd">证照申请流程</a></li>
		<shiro:hasRole name="caiwuzhongnan">
		<li><a href="${ctx}/tb/licenses/employeesList">员工证照列表</a></li>
		</shiro:hasRole>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/licenses/list2">所属区域员工证照列表</a></li>
		</shiro:hasAnyRoles>
	</ul>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>姓名</th>
			<th>申请日期</th>
			<th>归还日期</th>
			<th>流程状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="licenses">
			<tr>
				<td>${licenses.user.name}</td>
				<td><fmt:formatDate value="${licenses.applyDate }" type="both" pattern="yyyy-MM-dd"/></td>
				<td>
					<fmt:formatDate value="${licenses.returnDate }" type="both" pattern="yyyy-MM-dd"/>
				</td>
				<td>${licenses.statu}</td>
				<td>
				<c:choose>
					<c:when test="${licenses.statu=='驳回' }">
	                    <a href="${ctx}/tb/licenses/toAdd?id=${licenses.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/licenses/form?id=${licenses.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				<c:if test="${empty licenses.proneText && empty licenses.prtwoText || licenses.statu=='驳回'}">
				<a href="${ctx }/tb/roll/withdraw?id=${licenses.id }&tableName=tb_licenses&view=licenses">撤回</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>