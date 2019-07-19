<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>员工离职申请管理</title>
<script type="text/javascript">
$(document).ready(
		function() {
			check();
		});
function check(){
	
	var url = window.location.href;
	var statu = $("input[name='statu']").val();
	console.log(url)
	if(url.indexOf("view?act")>=0){
		if(statu=="审核通过"){
			console.log("1")
		}else{
		$("#btnCancel").before('<input class="btn" type="button" value="回退" onclick="rollback()">');
		console.log(2)
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
		<li><a href="${ctx}/tb/quit/list">离职申请列表</a></li>				
		<li class="active"><a href="">申请详情</a></li>	
		<c:if test="${fns:getUser().loginName=='俞跃舒' || fns:getUser().loginName=='李国强'||fns:getUser().loginName=='高会敏'}">
			<li class=""><a href="${ctx}/tb/quit/list2">全体员工离职列表</a></li>
		</c:if>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${quit.statu}"/>
		<fieldset>
			<legend>申请详情</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
						${quit.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${quit.office.name}
					</td>
				</tr>
				<tr>
					<td class="tit">申请离职日期</td>
					<td><fmt:formatDate value="${quit.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
					<td class="tit">拟离职日期</td>
					<td><fmt:formatDate value="${quit.quitDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">职位</td>
					<td colspan="3">
						${quit.position}
					</td>
				</tr>
				<tr>
					<td class="tit" >离职原因</td>
					<td colspan="3">
						${quit.reason}
					</td> 
				</tr>
				<tr>
					<td class="tit">附件</td>
					<td colspan="3">
						<c:if test="${not empty quit.address }">
							<a href="${ctx}/tb/quit/download?id=${quit.id}">附件下载</a>
						</c:if>
						<c:if test="${empty quit.address }">
							无附件
						</c:if>
					</td>
				</tr>
					<c:if test="${not empty quit.proneText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${quit.proneText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty quit.prtwoText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${quit.prtwoText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty quit.prthreeText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${quit.prthreeText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty quit.prfourText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${quit.prfourText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty quit.prfiveText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${quit.prfiveText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty quit.prsixText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${quit.prsixText}</td>
						</tr>
					</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${quit.act.procInsId}" />
		<div class="form-actions">
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
		</div>
		
	</form:form>
</body>
</html>