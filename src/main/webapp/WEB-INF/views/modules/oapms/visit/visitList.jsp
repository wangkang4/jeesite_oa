<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拜访记录列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/visit/visitList.js"></script>
	<style type="text/css">  
	.limit { 
		 width: 100px; 
		 text-overflow: ellipsis; 
		 -moz-text-overflow: ellipsis;
		 white-space: nowrap; 
		 overflow: hidden; 
	}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oapms/contact/list">联系人列表</a></li>
		<li class="active"><a href="">拜访记录列表</a></li>
		<li><a href="javascript:toAddVisit()">拜访记录添加</a></li>
	</ul>
	<table id="contentTable" style="table-layout:fixed;word-wrap:break-word;"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>拜访时间</th>
				<th>拜访纪要</th>
				<th>下一步计划</th>
				<th>拜访客户</th>
				<th>拜访联系人</th>
				<th>拜访地址</th>
				<th>删除记录</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="visit">
				<tr>
					<td>
						${visit.title }
					</td>
					<td>
						<fmt:formatDate value="${visit.visitTime }" type="both" pattern="yyyy-MM-dd"/>
					</td>
					<td class="limit">
						<label>
							<abbr style="text-decoration:none; border-bottom: 0 dotted;" title="${visit.visitSummary } ">
								${visit.visitSummary }
							</abbr>
						</label>
					</td>
					<td class="limit">
						<label>
							<abbr style="text-decoration:none; border-bottom: 0 dotted;" title="${visit.nextPlan } ">
								${visit.nextPlan }
							</abbr>
						</label>
					</td>
					<td>
						${visit.customer.customerName }
					</td>
					<td>
						${visit.customerContact.customerContactName }
					</td>
					<td>
						${visit.visitAddress }
					</td>
					<td>
						<input type="hidden" value=${visit.visitId }>
						<input type="button" class="btn btn-primary" onclick="deleteVisit(this)" value="删除">
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
