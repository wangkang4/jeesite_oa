<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司人员日报（周报）情况表</title>
	<link rel="stylesheet" type="text/css" href="${ctxStatic }/layer/default/layer.css" />
 <style type="text/css">
  ul li{
   text-align:center;
   }
   #status1{
   display:none;
   }
	
</style> 
<script src="${ctxStatic }/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic }/jquery/layer.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("[for='status1']").css("display","none");
});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	function AlreadySubmitDaily(){
		layer.open({
		  type: 2,
		  title: '日报信息页',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['350px', '90%'],
		  content: '${ctx}/daily/tCheckBacklog/list' //iframe的url
		}); 
	}
	function AlreadySubmitWeek(){
		layer.open({
			  type: 2,
			  title: '日报信息页',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['350px', '90%'],
			  content: '${ctx}/oa/weekly/weeklyLeadList' //iframe的url
			}); 
	}
</script>
</head>

<body>
<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/daily/tCheckBacklog/list">详情页</a></li>
</ul> --%>
    <h3 style="text-align:center;">北京桃花岛信息公司研发部人员日常工作信息</h3>
    <br>
    <hr>
	<form:form id="searchForm" modelAttribute="TCheckBacklog"
		action="${ctx}/leader/leaderPage/getNum" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
			
			
		<ul class="ul-form">
		<li><input id="dailySubmit" name="dailySubmit"  type="button" value="日报已提交" onclick="AlreadySubmitDaily()" maxlength="20" 
		 class="input-medium Wdate" style="width: 110px;" />&nbsp;&nbsp;
		    <label>(已交人数):<input value="${dailyCount }" style="width: 16px;">   </label>
		   &nbsp;&nbsp;&nbsp;&nbsp;
		   <input id="dailyNotSubmit" name="dailyNotSubmit"  type="button" value="日报未提交" onclick="NotSubmitDaily()" maxlength="20" 
		 class="input-medium Wdate" style="width: 110px;" /> &nbsp;&nbsp;
		    <label>(未交人数): <input value="" style="width: 16px;" />     </label></li>
		    
		    <br />
		    
		    <li><input id="weekSubmit" name="weekSubmit"  type="button" value="周报已提交" onclick="AlreadySubmitWeek()" maxlength="20" 
		 class="input-medium Wdate" style="width: 110px;" />&nbsp;&nbsp;
		    <label>(已交人数):<input value="${weeklyCount }" style="width: 16px;" />     </label>
		   &nbsp;&nbsp;&nbsp;&nbsp;
		   <input id="weekNotSubmit" name="weekNotSubmit"  type="button" value="周报未提交" onclick="NotSubmitWeek()" maxlength="20" 
		 class="input-medium Wdate" style="width: 110px;" /> &nbsp;&nbsp;
		    <label>(未交人数):<input value="" style="width: 16px;" />      </label></li>
		</ul>
		<hr>
		
		<ul><li><h3>通告管理</h3></li></ul>
		<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaNotify/${oaNotify.self?'self':''}">详情见公告页</a></li>
		<c:if test="${!oaNotify.self}"><shiro:hasPermission name="oa:oaNotify:edit"><li><a href="${ctx}/oa/oaNotify/form">通知添加</a></li></shiro:hasPermission></c:if>
	</ul>
	
	</form:form>
	<%-- <div class="pagination">${page}</div> --%>

</body>
</html>