<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>证照管理</title>
</head>
<body>
	<ul class="nav nav-tabs" id="list">
		<li><a href="${ctx}/tb/licenses/list">证照列表</a></li>
		<li><a href="${ctx}/tb/licenses/toAdd">证照申请流程</a></li>
		<li class="active"><a href="">员工证照列表</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/licenses/list2">所属区域员工证照列表</a></li>
		</shiro:hasAnyRoles>
	</ul>	
	<form:form id="searchForm" modelAttribute="licenses"
		action="${ctx}/tb/licenses/employeesList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>起始时间:</label>
		<input id="startTime1" name="st" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${licenses.st }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>结束时间:</label>
		<input id="endTime1" name="et" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${licenses.et }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>报销人:</label>	
			<sys:treeselect id="createBy" name="createBy.id" value="${licenses.createBy.id}" labelName="createBy.name" labelValue="${licenses.createBy.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href= '${ctx}/tb/licenses/employeesList'">
	</form:form>	
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
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>