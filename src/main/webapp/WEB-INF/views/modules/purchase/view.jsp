<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>采购申请管理</title>
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
		<li><a href="${ctx}/tb/purchase/list">采购申请列表</a></li>				
		<%-- <li class=""><a href="${ctx}/tb/purchase/list2">员工采购申请列表</a></li>	 --%>
		<li class="active"><a href="">申请详情</a></li>	
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${purchase.statu }"/>
		<fieldset>
			<legend>申请详情</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
						${purchase.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${purchase.office.name}
					</td>
				</tr>
				<tr>
					<td class="tit">物资名称</td>
					<td>
						${purchase.pName}
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${purchase.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">合计金额</td>
					<td>
						${purchase.money}
					</td>
					<td class="tit">采购日期</td>
					<td><fmt:formatDate value="${purchase.purchaseDate}" type="both" pattern="yyyy-MM-dd"/></td>
				<tr>
					<td class="tit">附件</td>
					<td>
						<c:if test="${not empty purchase.applyAddress }">
							<a href="${ctx}/tb/purchase/download?id=${purchase.id}">附件下载</a>
						</c:if>
						<c:if test="${empty purchase.applyAddress }">
							无附件
						</c:if>
					</td>
					<td class="tit" >备注</td>
					<td>
						${purchase.report }
					</td> 
				</tr>
					<c:if test="${not empty purchase.proneText }">
						<tr>
							<td class="tit">部门领导审核意见</td>
							<td colspan="3">${purchase.proneText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty purchase.prtwoText }">
						<tr>
							<td class="tit">办事处审核意见</td>
							<td colspan="3">${purchase.prtwoText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty purchase.prthreeText }">
						<tr>
							<td class="tit">财务总监审核意见</td>
							<td colspan="3">${purchase.prthreeText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty purchase.prfourText }">
						<tr>
							<td class="tit">总经理审核意见</td>
							<td colspan="3">${purchase.prfourText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty purchase.prfiveText }">
						<tr>
							<td class="tit">行政人员审核意见</td>
							<td colspan="3">${purchase.prfiveText}</td>
						</tr>
					</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${purchase.act.procInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>