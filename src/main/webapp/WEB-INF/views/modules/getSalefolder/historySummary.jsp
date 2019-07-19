<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>报销管理</title>
	<script type="text/javascript">
			$(document).ready(function() {
				
			});
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchExpenseForm").submit();
	        	return false;
	        }
			function downGetSale(){
				var startTime1 = $("#startTime1").val();
				var endTime1 = $("#endTime1").val();
				var userId1 = $("#userId1").val();
				location.href = "${ctx}/get/history/ExExcel?startTime1="+startTime1
				+ "&endTime1="+endTime1
				+ "&userId1="+userId1;
			}
			
			/**
			 * 批量打印
			 */
			function printAdd(){
				var tasks = "";
				$(".print:checked").each(function(){
					tasks = tasks + $(this).val()+"?";
				});
				var newTab=window.open('about:blank');
				$.ajax({
					url:"${ctx}/get/history/printAdd",
					data:{"tasks":tasks},
					dataType:"json",
					success:function(data){
						if(data.result=="ok"){
							newTab.location.href ='${ctx}/get/history/printList?task='+tasks;
						}else if(data.result=="error"){
							newTab.location.href ='${ctx}/get/history/historyExpense';
						}
					}
				});
			}
			
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">报销历史格式一</a></li>
		<li><a href="${ctx}/get/history/list">报销历史格式二</a></li>
		<li><a href="${ctx}/activity/leaAndove/list">请假加班历史</a></li>
	</ul>
	<form:form id="searchExpenseForm" modelAttribute=""
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>起始时间:</label>
		<input id="startTime1" name="startTime1" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${startTime }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>结束时间:</label>
		<input id="endTime1" name="endTime1" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>报销人:</label>	
			<input id="userId1" type="hidden" value="${userId }">
			<sys:treeselect id="user" name="user.id" value="${userId}" labelName="user.name" labelValue="${userName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href= '${ctx}/get/history/historyExpense'">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="导出" onclick="downGetSale()">
		&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-primary" value="批量打印" onclick="printAdd()"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选中</th>
				<th>姓名</th>
				<th>公司</th>
				<th>部门</th>
				<th>报销类型</th>
				<th>报销金额</th>
				<th>报销标题</th>
				<th>批准时间</th>
				<th>附件</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="getSale">
			<tr>
				<td style="text-align:center;">
				   	<input type="checkbox" class="print" value="${getSale.id}">
				</td>
				<td><a href="${ctx}/get/sale/form?id=${getSale.id}">${getSale.user.name}</a></td>
				<td>${getSale.ename}</td>
				<td>${getSale.office.name}</td>
				<td>${getSale.costType }</td>
				<td>${getSale.forMoney}</td>
				<td>${getSale.reason}</td>
				<td><fmt:formatDate value="${getSale.updateDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:if test="${getSale.address!=null }">
						<a href="${ctx}/get/sale/download?id=${getSale.id}">银行流水单下载</a>
					</c:if>
				</td>
				<td>
    				<a href="${ctx}/get/history/historyExpenseDetail?id=${getSale.id}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>