<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>费用统计</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic }/layer/default/layer.css" />
<style type="text/css">
#contentTable thead tr th {
	text-align: center;
}
#contentTable tbody tr td {
	text-align: center;
}
.clearfix {
	clear: both;
}
</style>
<script src="${ctxStatic }/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic }/jquery/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	//跳转到日报具体页面；
	function AlreadySubmitDaily(){
		layer.open({
		  type: 2,
		  title: '日报信息页',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['520px', '90%'],
		  content: '${ctx}/daily/tCheckBacklog/list' //iframe的url
		}); 
	}
	//跳转到周报具体信息页面；
	function AlreadySubmitWeek() {
		layer.open({
			type : 2,
			title : '周报报信息页',
			shadeClose : true,
			shade : 0.8,
			area : [ '520px', '90%' ],
			content : '${ctx}/oa/weekly/weeklyLeadList' //iframe的url
		});
	}
	
</script>

</head>
<body>
	<div style="width: 98%; border: 1px solid #eee; margin: 10px auto;">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx }/leader/leaderPage/index">查阅列表</a></li>
			<%-- <li><a href="${ctx }/oa/costexcel/costmessagefrom">费用新增</a></li> --%>
		</ul>
		<form:form id="searchForm" modelAttribute="costRecondExcel"
			action="${ctx}/leader/leaderPage/index" method="post"
			class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />

			<ul class="ul-form">
			 <%-- <li>
			    <label>&nbsp;&nbsp;&nbsp;
			         日报时间：</label> <input id="createrTime"
				name="createrTime" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate" style="width: 163px;"
				value="<fmt:formatDate value="${tCheckBacklog.createrTime }" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
				</li>
				<li class="btns">
				<input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>  --%>
				
				
			<li><input id="btnSubmit" style="margin-left: 50px;"
					class="btn btn-primary" type="button" value="日报已提交"
					onclick="AlreadySubmitDaily()" /> <label> (已交人数):<input
						value="${dailyCount }" style="width: 16px;">&nbsp;&nbsp;
						&nbsp;&nbsp;
				</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					class="btn btn-primary" value="日报未提交" onclick="NotSubmitDaily()">
					<label> (未交人数): <input value="${dailyNotCount }" style="width: 16px;" />&nbsp;&nbsp;
						&nbsp;&nbsp;
				</label></li>

				<li class="btns">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="btn"
					class="btn btn-primary" type="button" value="周报已提交"
					onclick="AlreadySubmitWeek()" /> <label> (已交人数):<input
						value="${weeklyCount }" style="width: 16px;" />
						&nbsp;&nbsp;&nbsp;&nbsp;
				</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input id="btn"
					class="btn btn-primary" type="button" value="周报未提交"
					onclick="NotSubmitWeek()" /> <label> (未交人数):<input
						value="${weeklyNotCount }" style="width: 16px;" /> &nbsp;&nbsp;&nbsp;&nbsp;
				</label></li>

				<li class="clearfix"></li>
				
				<%-- <li>
			    <label>&nbsp;&nbsp;&nbsp;
			         日报时间：</label> <input id="createrTime"
				name="createrTime" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate" style="width: 163px;"
				value="<fmt:formatDate value="${tCheckBacklog.createrTime }" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
				</li>
				
				<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li> --%>
				
			</ul>
		</form:form>
		
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		       <tr>
		       <th width="200%" style="text-align:center;">北京桃花岛信息科技公司通告通知</th>
		       <th><a href="${ctx}/oa/oaNotify/self">更多</a></th>
		       </tr>
			
		</thead>
		<tbody>
			<tr>
				<td>奖惩报告</td>
				<td><a href="${ctx }/oa/oaNotify">查看</a></td>
			</tr>
	        <tr>
	           <td>活动报告</td>
	           <td><a href="${ctx }/oa/oaNotify">查看</a></td>
	        </tr>
		</tbody>
	</table>
		<div class="pagination">${page}</div>
	</div>
</body>
</html>