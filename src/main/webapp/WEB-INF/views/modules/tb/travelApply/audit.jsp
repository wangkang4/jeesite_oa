<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>出差申请管理</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="travelApply"
		action="${ctx}/tb/travelApply/saveAudit" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />                 
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
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
					   共${travelApply.day }天
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
						<td colspan="5">
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
							城际交通费：${travelApply.costOne }元 ;
							住宿费：${travelApply.costTwo }元 ;
							差旅补助：${travelApply.costThree}元 ;
							其他：${travelApply.costFour }元 ;
							合计：${travelApply.allCost }元 
						
						</td>
					</tr>
					<tr style="display: none;">
					<td class="tit" >出差总结</td>
					<td colspan="5">
						${travelApply.summary}
					</td> 
				</tr>
				<tr>
					<td class="tit">您的评价</td>
					<td colspan="3"><form:textarea path="act.comment"
							 rows="5" maxlength="500" cssStyle="width:500px" />
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" 
				value="同 意" onclick="$('#flag').val('yes')" />&nbsp; 
			<input id="btnSubmit" class="btn btn-inverse" type="submit" 
				value="驳 回" onclick="$('#flag').val('no')" />&nbsp; 
			<input id="btnCancel" class="btn" type="button" 
				value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<act:histoicFlow procInsId="${travelApply.act.procInsId}" />
	</form:form>
</body>
</html>