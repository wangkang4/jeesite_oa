<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>证照管理</title>
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
		url = url.substr(0,url.indexOf("tb/"))
		window.location = url+"tb/roll/back?taskId="+taskId;
	}else{
		
	}
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/licenses/list">证照列表</a></li>
		<li class="active"><a
			href="">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${signet.statu }">
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="3">
                		${signet.title }
					</td>
				</tr>
				<tr>
					<td class="tit">申请人</td>
					<td>${signet.user.name }
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${signet.applyDate }" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">刻制印章名称</td>
					<td colspan="3">
						${signet.signetName }
					</td>
				</tr>
				<tr>
					<td class="tit">申请刻制原因</td>
					<td colspan="3">
						${signet.notes }
					</td>
				</tr>
				<c:if test="${not empty signet.prtwoText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${signet.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty signet.proneText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${signet.proneText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${signet.act.procInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>