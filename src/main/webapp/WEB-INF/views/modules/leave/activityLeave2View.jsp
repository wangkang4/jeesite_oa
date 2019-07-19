<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>请假管理</title>
<style type="text/css">  
 #len{ 
 width: 300px; 
 text-overflow: ellipsis; 
 -moz-text-overflow: ellipsis;
 white-space: nowrap; 
 overflow: hidden; 
}  
</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
	<script type="text/javascript">
$(document).ready(
		function() {
			check();
		});
function check(){
	var url = window.location.href;
	var statu = $("input[name='statu']").val();
	if(url.indexOf("form?act")>=0){
		if(statu=="审核通过"){
			
		}else{
		$("#btnCancel").before('<input class="btn" type="button" value="回退" onclick="rollback()">');
		}
	}
}
function rollback(){
	var msg="你确定要回退到自己节点吗";
	if(confirm(msg)==true){
		var url = window.location.href;
		var ele = url.split("&");
		var task = ele[0].split("=");
		var taskId = task[1];
		url = url.substr(0,url.indexOf("activity/"))
		window.location = url+"tb/roll/back?taskId="+taskId;
	}else{
		
	}
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/leave2/">请假列表</a></li>
		<li class="active"><a href="${ctx}/activity/leave2/form/?procInsId=${activityLeave2.procInsId}&id=${activityLeave2.id}">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}"/>
		<input type="hidden" name="statu" value="${activityLeave2.status }">
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td>${activityLeave2.reason }</td>
					<td class="tit">姓名</td>
					<td>${activityLeave2.user.name}</td>
				</tr>
				
				<tr>
					<td class="tit">开始时间</td>
					<td>
						<fmt:formatDate value="${activityLeave2.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="tit">结束时间</td>
					<td>
						<fmt:formatDate value="${activityLeave2.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td class="tit">请假类型</td>
					<td>${fns:getDictLabel(activityLeave2.leaveType, 'oa_leave_type', '')}</td>
					<td class="tit">部门</td>
					<td>${activityLeave2.office.name}</td>
				</tr>
				<tr>
					<td class="tit">请假总时间</td>
					<td>
						<fmt:formatNumber type="number" value="${activityLeave2.days}" maxFractionDigits="0"/>小时
					</td>
					<td class="tit">请假原因</td>
					<td><label id="len"><abbr style="text-decoration:none; border-bottom: 0 dotted;" title="${activityLeave2.notes} ">${activityLeave2.notes}</abbr></label></td>
				</tr>
				<c:if test="${not empty activityLeave2.prText}">
				<tr>
					<td class="tit">项目经理意见</td>
					<td colspan="3">
						${activityLeave2.prText}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty activityLeave2.leaderText}">
				<tr>
					<td class="tit">地区领导意见</td>
					<td colspan="3">
						${activityLeave2.leaderText}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty activityLeave2.leadertwoText}">
				<tr>
					<td class="tit">市场部经理意见</td>
					<td colspan="3">
						${activityLeave2.leadertwoText}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty activityLeave2.leaderthreeText}">
				<tr>
					<td class="tit">市场部领导意见</td>
					<td colspan="3">
						${activityLeave2.leaderthreeText}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty activityLeave2.hrText}">
				<tr>
					<td class="tit">人事主管意见</td>
					<td colspan="3">
						${activityLeave2.hrText}
					</td>
				</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${activityLeave2.act.procInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>