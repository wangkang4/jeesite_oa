<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>周报列表</title>
<style type="text/css">
.aaa{
	width:100px; 
	overflow: hidden; 
	text-overflow: ellipsis; 
	white-space: nowrap;
}

</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/weekly/weeklyLeadList">我收到的</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="TCheckBacklog"
		action="${ctx}/oa/weekly/weeklyLeadList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>周报时间：</label> <input id="createrTime"
				name="createrTime" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate" style="width: 163px;"
				value="<fmt:formatDate value="${tCheckBacklog.createrTime }" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></li>
			<li><label>发送人：</label> <sys:treeselect id="user" name="createrId"
					value="" labelName="user.name"
					labelValue="${tCheckBacklog.sendName }" title="用户"
					url="/sys/office/treeData?type=3" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>

	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工作内容</th>
				<th>工作计划</th>
				<th>发送人</th>
				<th>发送时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="tCheckBacklog">
				<tr>
					<td>
						<div class="aaa">
							${tCheckBacklog.weekly.daycontent}
						</div>
					</td>
					<td>
						<div class="aaa">
							${tCheckBacklog.weekly.plancontent}
						</div>
					</td>
					<td>${tCheckBacklog.sendName}</td>
					<td><fmt:formatDate value="${tCheckBacklog.createrTime}"
							pattern="yyyy-MM-dd" /></td>
					<td><a
						href="${ctx}/oa/weekly/weeklyLeadform?profid=${tCheckBacklog.profId}">查看</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>