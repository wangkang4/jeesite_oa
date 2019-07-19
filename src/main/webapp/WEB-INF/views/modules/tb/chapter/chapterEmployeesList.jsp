<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>预批管理</title>
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/chapter/list">用印列表</a></li>
		<li><a href="${ctx}/tb/chapter/toAdd">用印申请流程</a></li>
		<li class="active"><a href="">员工用印列表</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/chapter/list2">所属区域员工用印列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="chapter"
		action="${ctx}/tb/chapter/employeesList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>起始时间:</label>
		<input id="startTime1" name="st" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${chapter.st }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>结束时间:</label>
		<input id="endTime1" name="et" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${chapter.et }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>报销人:</label>	
			<sys:treeselect id="createBy" name="createBy.id" value="${chapter.createBy.id}" labelName="createBy.name" labelValue="${chapter.createBy.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href= '${ctx}/tb/chapter/employeesList'">
	</form:form>		
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>标题</th><th>用印文件名</th><th>申请时间</th><th>流程状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="chapter">
			<tr>
				<td>${chapter.user.name}</td>
				<td>${chapter.office.name}</td>
				<td>${chapter.reason}</td>
				<td>${chapter.fileForChapter }</td>
				<td><fmt:formatDate value="${chapter.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>${chapter.statu}</td>
				<td>
				<c:choose>
					<c:when test="${chapter.statu=='驳回' }">
	                    <a href="${ctx}/tb/chapter/toAdd?id=${chapter.id}">详情</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/tb/chapter/form?id=${chapter.id}">详情</a>
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