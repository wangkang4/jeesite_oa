<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>市内自驾车出行申请管理</title>
<script type="text/javascript" src="${ctxStatic}/tb/driving/addDriving.js?ver=2"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/driving/list">市内自驾车出行申请列表</a></li>
		<li class="active"><a href="">市内自驾车出行申请</a></li>
		<shiro:hasAnyRoles name="swperson,caiwuzhongnan">
		<li><a href="${ctx}/tb/driving/employeesList">员工市内自驾车出行申请列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/driving/drivingList2">所属区域员工市内自驾车出行申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="driving"
		action="${ctx}/tb/driving/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>市内自驾车出行申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>${driving.createBy.name }
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${driving.createDate }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="tit">同行人员</td>
					<td>
						<input type="text" value="${driving.peer }" name="peer">
						<span class="warn" id="peerWarn" style="color: red"></span>
					</td>
					<td class="tit">用车时间</td>
					<td>
						<input name="transportTime" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${driving.transportTime}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="transportTimeWarn" style="color: red"></span>
					</td>
					
				</tr>
				<tr>
					<td class="tit">出发地</td>
					<td>
						<input type="text" value="${driving.origin }" name="origin">
						<span class="warn" id="originWarn" style="color: red"></span>
					</td>
					<td class="tit">目的地</td>
					<td>
						<input type="text" value="${driving.destination }" name="destination">
						<span class="warn" id="destinationWarn" style="color: red"></span>
					</td>
					
				</tr>
				<tr>
					<td class="tit">预计公里数</td>
					<td>
						<input type="text" value="${driving.estimatedMiles }" name="estimatedMiles" onblur="generateCost()">公里
						<span class="warn" id="estimatedMilesWarn" style="color: red"></span>
					</td>
					<td class="tit">每升汽油费</td>
					<td>
						<input type="text" value="${driving.gasoline }" name="gasoline">元
						<span class="warn" id="gasolineWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">预计费用</td>
					<td colspan="3">
						<input type="text" value="${driving.estimatedCost }" name="estimatedCost" readonly="readonly">元
						<span class="warn" id="estimatedCostWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">自驾原因</td>
					<td colspan="3">
						<textarea rows="3" style="width:80%" name="reason">${driving.reason }</textarea>
						<span class="warn" id="reasonWarn" style="color: red"></span>
					</td>
				</tr>
				
				
				<c:if test="${not empty driving.proneText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${driving.proneText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty driving.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
				</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty driving.id}">
			<act:histoicFlow procInsId="${driving.act.procInsId}" />
		</c:if>
		1）计费标准为一公里一元<br>
		2）最终预计费用会四舍五入到小数点后两位<br>
	</form:form>
</body>
</html>