<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>专利申请管理</title>
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
		<li><a href="${ctx}/tb/patent/list">专利申请列表</a></li>				
		<li class="active"><a href="">专利申请详情</a></li>	
	</ul>
	<form:form class="form-horizontal">
		<input type="hidden" name="statu" value="${patent.statu}"/>
		<fieldset>
			<legend>申请详情</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
						${patent.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${patent.office.name}
					</td>
				</tr>
				<tr>
					<td class="tit">申请类型</td>
					<td>
						${patent.applyType }
					</td>
					<td class="tit">专利名称</td>
					<td>
						${patent.patentName }
					</td>
				</tr>
				
				<c:choose>
					<c:when test="${empty patent.reason}">
	                    <tr id="patentTel">
							<td class="tit">申请号</td>
							<td>
								${patent.applyTel }
							</td>
							<td class="tit">日期</td>
							<td><fmt:formatDate value="${patent.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td class="tit">奖励金额</td>
							<td colspan="6">
							${patent.applyType }金额:${patent.money }
							元</td>
						</tr>
						<tr class="proportion"><td colspan="7" style="text-align: center;font-weight:bold;">分配人员及比例</td></tr>
						<c:forEach items="${list }" var="rs">
								<tr class="proportion">
									<td class="tit">姓名</td>
									<td>
										${rs.person }
									</td>
									<td class="tit">比例</td>
									<td>
										${rs.position }
									</td>
								</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="tit">奖励金额</td>
							<td colspan="6">
							${patent.applyType }金额:${patent.money }
							元</td>
						</tr>
						<tr >
							<td class="tit">侵权情况简述</td>
							<td colspan="7" style="text-indent: 2em;">
									${patent.reason }
							</td>
						</tr>
					</c:otherwise> 
				</c:choose>
					<c:if test="${not empty patent.proneText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${patent.proneText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty patent.prtwoText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${patent.prtwoText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty patent.prthreeText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${patent.prthreeText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty patent.prfourText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${patent.prfourText}</td>
						</tr>
					</c:if>
					
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${patent.act.procInsId}" />
		<div class="form-actions">
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
		</div>
		
	</form:form>
</body>
</html>