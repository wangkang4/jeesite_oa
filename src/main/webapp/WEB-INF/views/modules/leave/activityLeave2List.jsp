<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>请假管理</title>
	<script type="text/javascript">
			$(document).ready(function() {
				//获取当前页面请求路径
				var url=window.location.href;
				console.log("url:"+url);
				if(url.endsWith("allList")){
					$("#allList").attr("class","active");
					$("#searchForm").attr("action","${ctx}/activity/leave2/allList");
					$(".href").css('text-decoration',  'none');
					$(".href").css('color',  'grey');
					$(".href").removeAttr('href');
				}else{
					$("#list").attr("class","active");
					$("#searchForm").attr("action","${ctx}/activity/leave2/");
				}
				var loginName=$("#name").val();
				if("李国强"==loginName){
					$("#allList").children().text('公司人员请假汇总');
				}
				if("麻青青"==loginName){
					$("#allList").children().text('济南市场请假汇总');
				}
				if("王涛"==loginName){
					$("#allList").children().text('华东地区请假汇总');
				}	
			});
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }
	</script>
	
<style type="text/css">  
 tr td { 
 width: 5px; 
 text-overflow: ellipsis; 
 -moz-text-overflow: ellipsis;
 white-space: nowrap; 
 overflow: hidden; 
}  
</style>
</head>
<body>
	<input id="name" type="hidden" value="${name}"/>
	<ul class="nav nav-tabs">
		<li id="list"><a href="${ctx}/activity/leave2/">请假列表</a></li>
		<li><a href="${ctx}/activity/leave2/form">请假申请流程</a></li>
		<c:if test="${ not empty name}">
			<li id="allList"><a href="${ctx}/activity/leave2/allList">公司人员请假列表</a></li>
		</c:if>
		
	</ul>
	<form:form id="searchForm" modelAttribute="activityLeave2"
		action="" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<input  type="hidden" name="startTime"  value="${activityLeave2.startTime}" />
	
	</form:form>
	<sys:message content="${message}"/>
	<table style="table-layout:fixed;word-wrap:break-word;" id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>请假总时间</th><th>请假原因</th><th>申请时间</th><th>流程状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="activityLeave2">
			<tr>
				<td><a href="${ctx}/activity/leave2/form?id=${activityLeave2.id}">${activityLeave2.user.name}</a></td>
				<td>${activityLeave2.office.name}</td>
				
				<td>
					<fmt:formatNumber type="number" value="${activityLeave2.days}" maxFractionDigits="0"/>小时 
				</td>
				<td><label><abbr style="text-decoration:none; border-bottom: 0 dotted;" title="${activityLeave2.notes} ">${activityLeave2.notes}</abbr></label></td>
				<td><fmt:formatDate value="${activityLeave2.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td id="add">
					
						
					<a	class="href"  href="${ctx}/activity/leave2/${activityLeave2.url}?id=${activityLeave2.id}">${activityLeave2.status}</a>
				</td>
				<td>
    				<a href="${ctx}/activity/leave2/form?id=${activityLeave2.id}">详情</a>
					<%-- <a href="${ctx}/activity/leave2/delete?id=${activityLeave2.id}" onclick="return confirmx('确认要删除该申请吗？', this.href)">删除</a> --%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>