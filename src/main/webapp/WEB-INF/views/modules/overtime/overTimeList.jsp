<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>加班管理</title>
	<script type="text/javascript">
					$(document).ready(function(){
						var url=window.location.href;
						console.log("url:"+url);
						if(url.endsWith("allList")){
							$("#allList").attr("class","active");
							$("#searchForm").attr("action","${ctx}/work/overtime/allList");
							$(".href").css('text-decoration',  'none');
							$(".href").css('color',  'grey');
							$(".href").removeAttr('href');
						}else{
							$("#list").attr("class","active");
							$("#searchForm").attr("action","${ctx}/work/overtime/");
						}
						var loginName=$("#name").val();
						if ("俞伶群"==loginName){
							$("#allList").children().text('公司人员加班汇总');
						}
						if("李国强"==loginName){
							$("#allList").children().text('公司人员加班汇总');
						}
						if("方娜"==loginName){
							$("#allList").children().text('公司人员加班汇总');
						}
						if("麻青青"==loginName){
							$("#allList").children().text('济南市场加班汇总');
						}
						if("王涛"==loginName){
							$("#allList").children().text('华东地区加班汇总');
						}
						if("李晓萌"==loginName){
							$("#allList").children().text('公司人员加班汇总');
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
		<li id="list"><a href="${ctx}/work/overtime/">加班列表</a></li>
		<li><a href="${ctx}/work/overtime/form">加班申请流程</a></li>
		<c:if test="${not empty name}">
			<li id="allList"><a href="${ctx}/work/overtime/allList">人员加假列表</a></li>
		</c:if>
		<c:if test="${fns:getUser().loginName=='李国强'}">
			<li><a href="${ctx}/work/overtime/overTimeList2">上个月加班情况</a></li>
		</c:if>
		<c:if test="${fns:getUser().loginName=='方娜'}">
			<li><a href="${ctx}/work/overtime/overTimeList2">上个月加班情况</a></li>
		</c:if>
		<c:if test="${fns:getUser().loginName=='俞伶群'}">
			<li><a href="${ctx}/work/overtime/overTimeList2">上个月加班情况</a></li>
		</c:if>
		<c:if test="${fns:getUser().loginName=='李晓萌'}">
			<li><a href="${ctx}/work/overtime/overTimeList2">上个月加班情况</a></li>
		</c:if>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="overTime"
		action=""  method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />	
			
	</form:form>
	<table style="table-layout:fixed;word-wrap:break-word;" id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>部门</th><th>加班总时间</th><th>加班原因</th><th>加班类型</th><th>申请时间</th><th>状态</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="overTime">
			<tr>
				<td><a href="${ctx}/work/overtime/form?id=${overTime.id}">${overTime.user.name}</a></td>
				<td>${overTime.office.name}</td>
				<td>
					<fmt:formatNumber type="number" value="${overTime.days}" maxFractionDigits="0"/>小时 
				</td>
				<td><label><abbr style="text-decoration:none; border-bottom: 0 dotted;" title="${overTime.notes} ">${overTime.notes}</abbr></label></td>
				<td>${overTime.overtimeType}</td>
				<td><fmt:formatDate value="${overTime.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><a  id="href" href="${ctx}/work/overtime/${overTime.url}?id=${overTime.id}">${overTime.status }</a></td>
				<td>
    				<a href="${ctx}/work/overtime/form?id=${overTime.id}">详情</a>
					<%-- <a href="${ctx}/work/overtime/delete?id=${overTime.id}" onclick="return confirmx('确认要删除该申请吗？', this.href)">删除</a> --%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>