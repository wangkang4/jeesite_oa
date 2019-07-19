<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>周报列表</title>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/weekly/weeklyLeadList">我收到的</a></li>
		<li class="active"><a href="">周报查阅</a></li>
	</ul>
<form:form id="searchForm" modelAttribute="weekly" action="${ctx}/oa/weekly/save" method="post" class="form-horizontal">
		<form:hidden path="id" value="${weekly.id }"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">收报人：</label>
			<div class="controls">
				<form:input path="" value="${user.name }" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">本周详情：</label>
			<div class="controls">
				<span id="weeklycontent"></span>
			<c:forEach items="${tDailylist}" var="tDaily">
					<fmt:formatDate  value="${tDaily.dayTime}" pattern="yyyyMMdd"/>日报:${tDaily.dayContent }(完成${tDaily.performance }%)<br/>
			</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">周报内容：</label>
			<div class="controls">
				<form:textarea readonly="true" id="daycontent" path="daycontent" value="${weekly.daycontent }" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下周计划 ：</label>
			<div class="controls">
				<form:textarea readonly="true" id="plancontent" path="plancontent" value="${weekly.plancontent }" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息 ：</label>
			<div class="controls">
				<form:textarea id="remark" path="remark"  htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
				<span class="help-inline">选填 </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">历史附件：</label>
			<div class="controls">
				<c:forEach items="${filelist}" var="fileModel">
					<a href="${ctx}/oa/weekly/download3?fileid=${fileModel.id}">${fileModel.name }</a>
					<br>
				</c:forEach>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
</form:form>
</body>
</html>