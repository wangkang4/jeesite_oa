<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>业务招待费用管理</title>
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
	url = url.replace("form","printBusiness");
	window.open(url);
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/money/businessList">业务招待费用列表</a></li>
		<li class="active"><a
			href="">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${business.statu }">
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="4">${business.reason }</td>
				</tr>
				<tr>
					<td class="tit">姓名</td>
					<td>${business.user.name}</td>
					<td class="tit">部门</td>
					<td>${business.office.name}</td>
				</tr>
				<tr>
					<td class="tit">日期</td>
					<td>
						<fmt:formatDate value="${business.tbDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
					<td class="tit">我司陪同人员</td>
					<td>
						<c:forEach items="${list }" var="rs">
							<c:if test="${rs.type==1 }">${rs.name }&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="tit">用餐地点</td>
					<td>
						${business.place }
					</td>
					<td class="tit">用餐金额</td>
					<td>
						<fmt:formatNumber type="number" value="${business.money }" maxFractionDigits="2" pattern="#0.00"/>元
					</td>
				</tr>
				<c:forEach items="${list }" var="rs">
					<c:if test="${rs.type==0 }">
						<tr>
							<td class="tit">被招待人员</td>
							<td>${rs.name }</td>
							<td class="tit">被招待人员职位</td>
							<td>${rs.position }</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<td class="tit">备注</td>
					<td colspan="4">${business.notes }</td>
				</tr>
                
				<c:if test="${not empty business.proneText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${business.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty business.prtwoText}">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${business.prtwoText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${business.act.procInsId}" />
		<div class="form-actions">
			<input class="btn" type="button" value="打 印" onclick="tiaozhuan();"/>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>