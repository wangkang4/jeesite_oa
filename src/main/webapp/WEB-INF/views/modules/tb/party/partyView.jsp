<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>团建管理</title>
<script type="text/javascript">
$(document).ready(
		function() {
			check();
		});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/party/list">团建列表</a></li>
		<li class="active"><a
			href="">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${party.statu }">
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="3">${party.title }</td>
				</tr>
				<tr>
					<td class="tit">部门</td>
					<td>${party.officeName}</td>
					
					<td class="tit">总人数</td>
					<td>${party.count }</td>
				</tr>
				<tr>
					<td class="tit">计划团建时间</td>
					<td>
						<fmt:formatDate value="${party.planTime}" pattern="yyyy-MM-dd"/>
					</td>
					<td class="tit">计划参加人数</td>
					<td>
						${party.planCount }
					</td>
				</tr>
				<tr>
					<td class="tit">可用团建经费：</td>
				<td>
					<fmt:formatNumber type="number" value="${party.availableFunds }" maxFractionDigits="2" pattern="#0.00"/>元
					
				</td>
				<td class="tit">本次团建经费预算：</td>
				<td>
					<fmt:formatNumber type="number" value="${party.budget }" maxFractionDigits="2" pattern="#0.00"/>元
				</td>
				</tr>
				<tr>
					<td class="tit">本次团建活动方案描述：</td>
					<td>
						${party.notes }
					</td>
				</tr>
				
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${party.act.procInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>