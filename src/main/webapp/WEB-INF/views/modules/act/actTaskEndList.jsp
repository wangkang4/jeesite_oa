<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>完结任务</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#userName{
			width:150px;	
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		 function page(n,s){
        	location = '${ctx}/act/task/end/?pageNo='+n+'&pageSize='+s;
        } 
        /* function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        } */
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/act/task/todo/">待办任务</a></li>
		<li><a href="${ctx}/act/task/getSale/">待办报销任务</a></li>
		<li><a href="${ctx}/act/task/historic/">已办任务</a></li>
		<li class="active"><a href="${ctx}/act/task/end/">完结任务</a></li>
		<!-- <li><a href="${ctx}/act/task/process/">新建任务</a></li> -->
	</ul>
	<form:form id="searchForm" modelAttribute="act" action="${ctx}/act/task/end/" method="get" class="breadcrumb form-search">
		<div>
			<label>流程类型：&nbsp;</label>
			<form:select path="procDefKey" class="input-medium">
				<form:option value="" label="全部流程"/>
				<form:options items="${fns:getDictList('act_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label>申请人：</label>
			<sys:treeselect id="user" name="userId" value="${userId}" labelName="userName" labelValue="${userName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			<label>标题：</label>
			<input  class="input-medium" style="width:100px" name="title" value="${title }">
			<label>完成时间：</label>
			<input id="beginDate" style="width:100px;"  name="beginDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${act.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
			<input id="endDate" style="width:100px;"  name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${act.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			&nbsp;<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href='${ctx}/act/task/end/'">
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			   <!--  <th>姓名</th>
			    <th>部门</th> -->
				<th style="text-align:center;">标题</th>
				<th style="text-align:center;">当前环节</th>
				<%-- <th>任务内容</th> --%>
				<th style="text-align:center;">流程名称</th>
				<th style="text-align:center;">流程版本</th>
				<th style="text-align:center;">完成时间</th>
				<th style="text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="act">
				<c:set var="task" value="${act.histTask}" />
				<c:set var="vars" value="${act.vars}" />
				<c:set var="procDef" value="${act.procDef}" />
				<c:set var="status" value="${act.status}" />
				<c:set var="taskName" value="${act.taskName }" />
				<tr>
				   <%--  <td>${userName }</td>
				    <td>${OfficeName}</td> --%>
					<td style="text-align:center;">
						<a href="${ctx}/act/task/form?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">
						${fns:abbr(not empty vars.map.title ? vars.map.title : task.id, 60)}</a>
					</td>
					<td style="text-align:center;">
						<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">${taskName}</a>
					</td>
						<%--
					</td><%--
					<td>${task.description}</td> --%>
					<td style="text-align:center;">${procDef.name}</td>
					<td style="text-align:center;"><b title='流程版本号'>V: ${procDef.version}</b></td>
					<td style="text-align:center;"><fmt:formatDate value="${task.endTime}" type="both"/></td>
					<td style="text-align:center;">
						<a href="${ctx}/act/task/form?
						taskId=${task.id}
						&taskName=${fns:urlEncode(task.name)}
						&taskDefKey=${task.taskDefinitionKey}
						&procInsId=${task.processInstanceId} 
						&procDefId=${task.processDefinitionId}
						&status=${status}">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
