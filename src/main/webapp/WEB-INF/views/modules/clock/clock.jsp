<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>打卡</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/clock/clock.js"></script>
<script type="text/javascript">
	function downChenckin(){
		var startTime1 = $("#startTime1").val();
		var endTime1 = $("#endTime1").val();
		var userId1 = $("#userId1").val();
		location.href = "${ctx}/clock/checkin/ExExcel?startTime1="+startTime1
				+ "&endTime1="+endTime1
				+ "&userId1="+userId1;
}
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">打卡列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="checkin"
		action="${ctx}/clock/checkin/checkin" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>开始时间:</label>
		<input id="startTime1" name="startTime1" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${startTime }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>结束时间:</label>
		<input id="endTime1" name="endTime1" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>打卡人:</label>	
			<input id="userId1" type="hidden" value="${userId }">
			<sys:treeselect id="user" name="user.id" value="${userId}" labelName="user.name" labelValue="${userName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
		&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary"
			type="submit" value="查询" />
		&nbsp;&nbsp;<input type="button" class="btn btn-primary" onclick="javascript:window.location.href='checkin'" value="清除">
		&nbsp;&nbsp;<input type="button" class="btn btn-primary" onclick="syn()" value="同步">
		&nbsp;&nbsp;<input type="button" class="btn btn-primary" onclick="downChenckin()" value="导出">
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>打卡人</th>
				<th>打卡类型</th>
				<th>打卡地点</th>
				<th>打卡时间</th>
				<th>打卡wifi</th>
				<th>异常类型</th>
				<th>打卡备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="checkin">
				<tr>
					<td>
						${checkin.user.name }
					</td>
					<td>
						${checkin.checkinType }
					</td>
					<td>
						${checkin.locationDetail}
					</td>
					<td>
						${checkin.checkinTime1}
					</td>
					<td>
						${checkin.wifiname}
					</td>
					<td>
						${checkin.exceptionType }
					</td>
					<td>
						${checkin.notes}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>