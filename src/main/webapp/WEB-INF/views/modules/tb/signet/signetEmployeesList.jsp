<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>证照管理</title>
</head>
<body>
	<ul class="nav nav-tabs" id="list">
		<li><a href="${ctx}/tb/signet/list">印章刻制列表</a></li>
		<li><a href="${ctx}/tb/signet/toAdd">印章刻制申请流程</a></li>
		<li class="active"><a href="">员工印章刻制列表</a></li>
		<shiro:hasRole name="xingzheng">
			<li class=""><a href="${ctx}/tb/signet/list2">所属区域员工印章刻制列表</a></li>
		</shiro:hasRole>
	</ul>
	<form:form id="searchForm" modelAttribute="signet"
		action="${ctx}/tb/signet/employeesList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>起始时间:</label>
		<input id="startTime1" name="st" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${signet.st }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>结束时间:</label>
		<input id="endTime1" name="et" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${signet.et }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>报销人:</label>	
			<sys:treeselect id="createBy" name="createBy.id" value="${signet.createBy.id}" labelName="createBy.name" labelValue="${signet.createBy.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href= '${ctx}/tb/signet/employeesList'">
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>申请人</th>
			<th>申请日期</th>
			<th>刻制印章名称</th>
			<th>流程状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="signet">
			<tr>
				<td>${signet.user.name}</td>
				<td><fmt:formatDate value="${signet.applyDate }" type="both" pattern="yyyy-MM-dd"/></td>
				<td>
					${signet.signetName }
				</td>
				<td>${signet.statu}</td>
				<td>
				<c:choose>
					<c:when test="${signet.statu=='驳回' }">
	                    <a href="${ctx}/tb/signet/toAdd?id=${signet.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/signet/form?id=${signet.id}">详情</a>
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