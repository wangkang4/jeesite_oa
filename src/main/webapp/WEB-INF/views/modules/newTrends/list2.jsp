<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>最新动态</title>

<style type="text/css">

.newMsg{
	text-decoration: none;
	font-weight: normal;
	color: #999999;
	font-family: 微软雅黑;
	font-size: 14px;
	
	
	width:400px;
	white-space:nowrap;
	text-overflow:ellipsis;
	overflow:hidden;
}
#td{
	/* text-align: center; */
	font-size: 18px;
}

</style>
	<!-- <script type="text/javascript">
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }
	</script> -->
</head>
<body>
	<ul class="nav nav-tabs" id="list">
		<li class="active"><a href="${ctx}/newTrends/list2">我的发布</a></li>
		<li class=""><a href="${ctx}/newTrends/add">发布动态</a></li>		
	</ul>
	<form:form id="searchForm" modelAttribute=""
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>动态</th>
				<th>时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="newTrends"> 
			<tr>
				<td id="td">
					<div class='mixNewsStyleTitle'>
						<a href="${ctx}/newTrends/view?id=${newTrends.id}">${newTrends.title}</a>
					</div>
						
				</td>
				<td>
					<p>${newTrends.user.name}</p>
					<p><fmt:formatDate value="${newTrends.date}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				</td>
				<td>
					<a href="${ctx}/newTrends/add?id=${newTrends.id}">详情</a>
					<a href="${ctx}/newTrends/del?id=${newTrends.id}">删除</a>
				</td>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>