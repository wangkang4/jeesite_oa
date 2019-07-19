<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>出差申请管理</title>
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
		<li><a href="${ctx}/tb/travelApply/list">出差申请列表</a></li>				
		<li class="active"><a href="">出差申请详情</a></li>	
	</ul>
	<form:form class="form-horizontal">
		<input type="hidden" name="statu" value="${travelApply.statu}"/>
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">部门</td>
					<td>
						${travelApply.office.name }
					</td>
					<td class="tit">姓名</td>
					<td>
						${travelApply.user.name }
					</td>
					<td class="tit">申请时间</td>
					<td>
						<fmt:formatDate value="${travelApply.createDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="tit">出发地</td>
					<td>
						${travelApply.startAddress }
					</td>
					<td class="tit">目的地</td>
					<td>
						${travelApply.endAddress }
					</td>
					<td class="tit">项目名称</td>
					<td>
						${travelApply.project }
					</td>
				</tr>
				<tr>
					<td class="tit">出差开始日期</td>
					<td>
						<fmt:formatDate value="${travelApply.startDate }" type="both" pattern="yyyy-MM-dd"/>  
					</td>
					<td class="tit">出差结束日期</td>
					<td>
						<fmt:formatDate value="${travelApply.endDate }" type="both" pattern="yyyy-MM-dd"/>  
					</td>
					<td colspan="5">
					   共${travelApply.day}天
					</td>
				</tr>
				<tr>
					<td class="tit">交通工具</td>
						<td>
						${travelApply.traffic }
						</td>
					<td class="tit">同行人员</td>
					<td >
						${travelApply.person }
					</td>
					<td class="tit">出差编号</td>
						<td colspan="4">
						${travelApply.num }
						</td>
				</tr>
					<tr><td colspan="7" style="text-align: center;font-weight:bold;">行程计划</td></tr>
					<c:forEach items="${list }" var="rs">  
							<tr>
								<td class="tit">日期</td>
								<td>
								${rs.planDate }
								</td>
								<td class="tit">客户名称</td>
								<td>
								${rs.customerName }
								</td>
								<td class="tit">工作内容</td>
								<td>
								${rs.content }
								</td>
							</tr>
					</c:forEach>  
					<tr>
						<td class="tit">费用预算</td>
						<td colspan="5">
							城际交通费：${travelApply.costOne }元; 
							住宿费：${travelApply.costTwo }元; 
							差旅补助：${travelApply.costThree}元; 
							其他：${travelApply.costFour }元; 
							合计：${travelApply.allCost }元 
						
						</td>
					</tr>
					<tr style="display: none;">
					<td class="tit" >出差总结</td>
					<td colspan="5">
						${travelApply.summary}
					</td> 
				</tr>
				<c:if test="${not empty travelApply.proneText }">
						<tr>
							<td class="tit">审核人意见</td>
							<td colspan="3">${travelApply.proneText}</td>
						</tr>
					</c:if>
					<c:if test="${not empty travelApply.prtwoText }">
						<tr>
							<td class="tit">主管评价</td>
							<td colspan="3">${travelApply.prtwoText}</td>
						</tr>
					</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${travelApply.act.procInsId}" />
		<div class="form-actions">
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
		</div>
		
	</form:form>
</body>
</html>