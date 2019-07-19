<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>加班管理</title>
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
		url = url.substr(0,url.indexOf("work/"))
		window.location = url+"tb/roll/back?taskId="+taskId;
	}else{
		
	}
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/overtime/">加班列表</a></li>
		<li class="active"><a href="${ctx}/work/overtime/form/?procInsId=${overTime.procInsId}&id=${overTime.id}">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}"/>
		<input type="hidden" name="statu" value="${overTime.status }">
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td>${overTime.reason }</td>
					<td class="tit">姓名</td>
					<td>${overTime.user.name}</td>
					
				</tr>
				<tr>
					<td class="tit">开始时间</td>
					<td>
						<fmt:formatDate value="${overTime.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="tit">结束时间</td>
					<td>
						<fmt:formatDate value="${overTime.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<%-- <td class="tit">加班类型</td>
					<td>${fns:getDictLabel(overTime.overtimeType, 'oa_leave_type', '')}</td>   --%>
					<td class="tit">加班总时间</td>
					<td>
						<fmt:formatNumber type="number" value="${overTime.days}" maxFractionDigits="0"/>小时 
					</td>
					<td class="tit">部门</td>
					<td>${overTime.office.name}</td>
				</tr>
				<tr>
					<td class="tit">加班原因</td>
					<td colspan="3">${overTime.notes }</td>
				</tr>
				<c:if test="${not empty overTime.prText}">
				<tr>
					<td class="tit">项目经理意见</td>
					<td colspan="3">
						${overTime.prText}
					</td>
				</tr>
				</c:if>
				<c:if test="${not empty overTime.leaderText}">
				<tr>
					<td class="tit">地区领导意见</td>
					<td colspan="3">
						${overTime.leaderText}
					</td>
				</tr>
				</c:if>
					<c:if test="${not empty overTime.id && not empty overTime.leadertwoText}">
					<tr>
						<td class="tit">总经理意见</td>
						<td colspan="3">${overTime.leadertwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty overTime.hrText}">
				<tr>
					<td class="tit">人事主管意见</td>
					<td colspan="3">
						${overTime.hrText}
					</td>
				</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${overTime.act.procInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>