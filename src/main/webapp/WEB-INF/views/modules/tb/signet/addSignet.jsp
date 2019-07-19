<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>印章刻制管理</title>
<script type="text/javascript" src="${ctxStatic}/tb/signet/addSignet.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/signet/list">印章刻制列表</a></li>
		<li class="active"><a href="">印章刻制申请流程</a></li>
		<shiro:hasRole name="caiwuzhongnan">
		<li><a href="${ctx}/tb/signet/employeesList">员工印章刻制列表</a></li>
		</shiro:hasRole>
		<shiro:hasRole name="xingzheng">
			<li class=""><a href="${ctx}/tb/signet/list2">所属区域员工印章刻制列表</a></li>
		</shiro:hasRole>
	</ul>
	<form:form id="inputForm" modelAttribute="signet"
		action="${ctx}/tb/signet/add" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>印章刻制申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>${name }
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${applyDate }" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">刻制印章名称</td>
					<td colspan="3">
						<input type="text" name="signetName" value="${signet.signetName }">
						<span class="warn" id="signetNameWarn" style="color: red"></span>
					</td>
					
				</tr>
				
				<tr>
					<td class="tit">申请刻制原因</td>
					<td colspan="3">
						<textarea rows="3" style="width:80%" name="notes">${signet.notes }</textarea>
						<span class="warn" id="notesWarn" style="color: red"></span>
					</td>
				</tr>
				<c:if test="${not empty signet.prtwoText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${signet.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty signet.proneText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${signet.proneText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty signet.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
				</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty signet.id}">
			<act:histoicFlow procInsId="${signet.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>