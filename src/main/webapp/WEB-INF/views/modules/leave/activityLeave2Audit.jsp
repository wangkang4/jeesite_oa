<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>请假管理</title>
<style type="text/css">  
 #len{ 
 width: 200px; 
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
			var address=$("#address").val();
			if(address==null||address==""||address==''){		
			}else{
				$("#attach").append('<td id="style">含有附件&nbsp;&nbsp;&nbsp;<a id="href"  href="${ctx}/activity/leave2/download?attachAddress=${activityLeave2.attachAddress}">${activityLeave2.attachName}</a></td>');
				$("#style").attr("style","background:#39A8E8;");
				$("#href").attr("style","color:white;");
			}
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/leave2/">请假列表</a></li>
		<li class="active"><a href="#">
		<shiro:hasPermission name="oa:testAudit:edit">任务办理</shiro:hasPermission>
		<%-- <shiro:lacksPermission name="oa:testAudit:edit">查看</shiro:lacksPermission> --%>
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="activityLeave2" action="${ctx}/activity/leave2/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden id="flag" path="act.flag"/>
		<sys:message content="${message}"/>
		<fieldset>
			<legend>员工请假申请表</legend>
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
						<fmt:formatDate value="${activityLeave2.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
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
					<td  class="tit">请假原因</td>
					<td><label id="len"><abbr style="text-decoration:none; border-bottom: 0 dotted;" title="${activityLeave2.notes} ">${activityLeave2.notes}</abbr></label></td>
					
				</tr>
				<tr>
					<td class="tit">可用调休时间</td>
					<td>
							<fmt:formatNumber type="number"  value="${activityLeave2.user.useOverTimeDays}"  maxFractionDigits="0"/>小时
					</td>
				</tr>
			
				<%-- <c:if test="${not empty str}">  str不为空</c:if> --%>
				<c:if test="${not empty activityLeave2.prText }">
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
					<td class="tit">市场部经理审核意见</td>
					<td colspan="3">
						${activityLeave2.leaderText}
					</td>
				</tr>
				</c:if>
				
				<c:if test="${not empty activityLeave2.leaderthreeText}">
				<tr>
					<td class="tit">市场部领导审核意见</td>
					<td colspan="3">
						${activityLeave2.leaderText}
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
				<tr  >
					<td class="tit">您的意见</td>
					<td id="attach"  colspan="3">
						<form:textarea path="act.comment" rows="5" maxlength="20" cssStyle="width:500px"/>
					</td>
						
				</tr>	
			</table>
			<input id="address" type="hidden"  value="${activityLeave2.attachName}"/>
		</fieldset>
		<div class="form-actions">
<%-- 			<shiro:hasPermission name="oa:testAudit:edit"> --%>
<%-- 				<c:if test="${testAudit.act.taskDefKey eq 'apply_end'}"> --%>
<!-- 					<input id="btnSubmit" class="btn btn-primary" type="submit" value="兑 现" onclick="$('#flag').val('yes')"/>&nbsp; -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${testAudit.act.taskDefKey ne 'apply_end'}"> --%>
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#flag').val('yes')"/>&nbsp;
					<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('no')"/>&nbsp;
<%-- 				</c:if> --%>
<%-- 			</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'"/>
		</div>
		<act:histoicFlow procInsId="${activityLeave2.act.procInsId}"/>
	</form:form>
</body>
</html>