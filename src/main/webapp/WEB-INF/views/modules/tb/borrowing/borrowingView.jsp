<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>借款管理</title>
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
		url = url.substr(0,url.indexOf("tb/borrowing"))
		window.location = url+"tb/roll/back?taskId="+taskId;
	}else{
		
	}
}
function tiaozhuan(){
	var url = window.location.href;
	url = url.replace("form","print");
	window.open(url);
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/borrowing/list">借款列表</a></li>
		<li class="active"><a href="">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${borrowing.statu }">
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">所属公司</td>
					<td colspan="3" align="center">${borrowing.ename }</td>
				</tr>
				<tr>
					<td class="tit">标题</td>
					<td>${borrowing.title }</td>
					<td class="tit">日期</td>
					<td><fmt:formatDate value="${borrowing.time }"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td class="tit">借款人</td>
					<td>${borrowing.name.name }</td>
					<td class="tit">所属部门</td>
					<td>${borrowing.office.name }</td>
				</tr>
				<tr>
					<td class="tit">借款金额</td>
					<td><fmt:formatNumber value="${borrowing.money }"
							maxFractionDigits="2" pattern="#0.00" />元</td>
				</tr>
				<tr>
					<td class="tit">借款用处</td>
					<td colspan="3">${borrowing.reason }</td>
				</tr>
				<tr>
					<td class="tit">备注</td>
					<td colspan="3">${borrowing.notes }</td>
				</tr>

				<c:if test="${not empty borrowing.proneText }">
					<tr>
						<td class="tit">部门经理审核意见</td>
						<td colspan="3">${borrowing.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prtwoText}">
					<tr>
						<td class="tit">研发总监审核意见</td>
						<td colspan="3">${borrowing.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prthreeText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${borrowing.prthreeText}</td>
					</tr>
				</c:if>

				<c:if test="${not empty borrowing.prfiveText }">
					<tr>
						<td class="tit">财务主管审核意见</td>
						<td colspan="3">${borrowing.prfiveText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prsixText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${borrowing.prsixText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prsevenText }">
					<tr>
						<td class="tit">总裁审核意见</td>
						<td colspan="3">${borrowing.prsevenText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.preightText }">
					<tr>
						<td class="tit">总经理审核意见</td>
						<td colspan="3">${borrowing.preightText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty borrowing.prfourText }">
					<tr>
						<td class="tit">出纳审核意见</td>
						<td colspan="3">${borrowing.prfourText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${borrowing.act.procInsId}" />
		<div class="form-actions">
			<input class="btn" type="button" value="打 印" onclick="tiaozhuan();" />
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>