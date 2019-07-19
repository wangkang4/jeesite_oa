<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>合同管理</title>
<script type="text/javascript">
			$(document).ready(function() {
				
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
		<li><a href="${ctx}/tb/borrowing/list">借款列表</a></li>
		<li><a href="${ctx}/tb/borrowing/toAdd">借款申请</a></li>
		<li class="active"><a href="">员工借款列表</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/borrowing/borrowingList2">所属区域员工借款列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="borrowing"
		action="${ctx}/tb/borrowing/employeesList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>起始时间:</label>
		<input id="startTime1" name="st" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${borrowing.st }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>结束时间:</label>
		<input id="endTime1" name="et" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${borrowing.et }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>报销人:</label>	
			<sys:treeselect id="createBy" name="createBy.id" value="${borrowing.createBy.id}" labelName="createBy.name" labelValue="${borrowing.createBy.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href= '${ctx}/tb/borrowing/employeesList'">
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>借款人</th>
			<th>所属部门</th>
			<th>标题</th>
			<th>借款金额</th>
			<th>申请时间</th>
			<th>流程状态</th>
			<th>操作</th>
			<th>附件</th>
			<c:if test="${fns:getUser().loginName=='俞林伟'}">
			<th>附件上传</th>
			</c:if>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="borrowing">
			<tr>
				<td>${borrowing.name.name}</td>
				<td>${borrowing.office.name}</td>
				<td>
					${borrowing.title }
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowing.money}" maxFractionDigits="2" pattern="#.00"/>元
				</td>
				<td>
					<fmt:formatDate value="${borrowing.createDate}" type="both" pattern="yyyy-MM-dd"/>
				</td>
				<td>${borrowing.statu}</td>
				<td>
				<c:choose>
					<c:when test="${borrowing.statu=='驳回' }">
	                    <a href="${ctx}/tb/borrowing/toAdd?id=${borrowing.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/borrowing/form?id=${borrowing.id}">详情</a>
					</c:otherwise> 
				</c:choose>
				</td>
				<td>
					<c:if test="${not empty borrowing.address }">
						<a href="${ctx}/tb/borrowing/download?id=${borrowing.id}">银行流水单下载</a>
					</c:if>
				</td>
				<c:if test="${fns:getUser().loginName=='俞林伟'}">
				<td>
					<form style="margin-bottom:0px;" action="${ctx}/tb/borrowing/upload" enctype="multipart/form-data" method="post">
						<input type="hidden" value="${borrowing.id}" name="id">
						<input type="file" name="file" style="width:180px">
						<input type="submit" value="确认上传">
					</form>
				</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>