<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>日报列表</title>
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
		<li class="active"><a href="${ctx}/daily/tDaily/list">我发出的</a></li>
	</ul>
	
	
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工作进度</th>
				<th>工作内容</th>
				<th>工作计划</th>
				<th>收报人</th>
				<th>发送时间</th>
				<th>操作</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="tDaily">
				<tr>
					<td>${tDaily.performance}%</td>
					<td><div class="aaa">
						${tDaily.dayContent}
						</div>
					</td>
					<td><div class="aaa">
						${tDaily.planContent}
						</div>
					</td>
					<td>${tDaily.remarks}</td>
					<td><fmt:formatDate value="${tDaily.createrTime}"
							pattern="yyyy-MM-dd" /></td>
					<td><a href="${ctx}/daily/tDaily/form?id=${tDaily.id}">修改</a>
						<a href="${ctx}/daily/tDaily/delete?id=${tDaily.id}"
						onclick="return confirmx('确认要删除该日报吗？', this.href)">删除</a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>