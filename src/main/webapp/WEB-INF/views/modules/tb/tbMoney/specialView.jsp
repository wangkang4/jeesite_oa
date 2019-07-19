<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>专项费用管理</title>
<script type="text/javascript">
$(document).ready(
		function() {
			check();
		});
function check(){
	var url = window.location.href;
	var statu = $("input[name='statu']").val();
	if(url.indexOf("form?act")>=0){
		if(statu=="已付款"){
			
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
function tiaozhuan(){
	var url = window.location.href;
	url = url.replace("form","printSpecial");
	window.open(url);
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/money/specialList">专项费用列表</a></li>
		<li class="active"><a
			href="">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${special.statu }">
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="4">${special.reason }</td>
				</tr>
				<tr>
					<td class="tit">姓名</td>
					<td>${special.user.name}</td>
					<td class="tit">部门</td>
					<td>${special.office.name}</td>
				</tr>
				<tr>
					<td class="tit">日期</td>
					<td>
						<fmt:formatDate value="${special.tbDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
					<td class="tit">预估金额</td>
					<td>
						<fmt:formatNumber type="number" value="${special.money}" maxFractionDigits="2" pattern="#.00"/>元
					</td>
				</tr>
				<tr>
					<td class="tit">支付方式</td>
					<td>
						<c:if test="${special.payType==1 }">现金
						</c:if>
						<c:if test="${special.payType==2 }">转账
						</c:if>
					</td>
					<td class="tit">账户信息</td>
					<td>${special.account }</td>
				</tr>
				<tr>
					<td class="tit">备注</td>
					<td colspan="4">${special.notes }</td>
				</tr>
                
				<c:if test="${not empty special.proneText }">
					<tr>
						<td class="tit">主管意见</td>
						<td class="tit">${special.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty special.prtwoText}">
					<tr>
						<td class="tit">财务总监意见</td>
						<td class="tit">${special.prtwoText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${special.act.procInsId}" />
		<div class="form-actions">
			<input class="btn" type="button" value="打 印" onclick="tiaozhuan();"/>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>