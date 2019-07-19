<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>日报列表</title>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/daily/tDaily/list">我收到的</a></li>
		<li class="active"><a href="">日报查阅</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="tDaily"
		action="${ctx}/daily/tDaily/list" method="post"
		class="breadcrumb form-search">
		
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th colspan="4"
						style="text-align: center; font-size: 30px; height: 30px;">日报查阅</th>
				</tr>
			</thead>
			<tbody>
<!-- 				<tr style="height: 40px"> -->
<!-- 					<td style="text-align: center">日报时间</td> -->
<!-- 					<td><input id="dayTime" name="dayTime" type="text" -->
<!-- 						readonly="readonly" maxlength="20" class="input-medium Wdate" -->
<!-- 						style="width: 163px;" -->
<%-- 						value="<fmt:formatDate value="${tDaily.dayTime}" pattern="yyyy-MM-dd"/>" --%>
<!-- 						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></td> -->
					<td style="text-align: center">完成情况(%)</td>
					<!-- 下方的path其实就相当value值，具体的内容值； -->
					<td><form:input path="performance"  readonly="true"/></td>
				</tr>
				<tr>
					<td style="text-align: center">发送人</td>
					<td><form:input path="sendName" readonly="true"/></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: center">日报内容</td>
					<td colspan="3"><form:textarea path="dayContent" readonly="true"
							htmlEscape="false" rows="4" maxlength="255" style="width:80%"
							class="input-xxlarge " /></td>
				</tr>
				<tr>
					<td style="text-align: center">明日计划</td>
					<td colspan="3"><form:textarea path="planContent" readonly="true"
							htmlEscape="false" rows="4" maxlength="255" style="width:80%"
							class="input-xxlarge " /></td>
				</tr>
				<tr>
					<td style="text-align: center">备注</td>
					<td colspan="3"><form:textarea path="remark"
							htmlEscape="false" rows="4" maxlength="255" style="width:80%"
							class="input-xxlarge " /></td>
				</tr>
			</tbody>
		</table>

	</form:form>

</body>
</html>