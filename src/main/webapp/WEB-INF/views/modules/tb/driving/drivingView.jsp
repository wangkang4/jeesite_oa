<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>市内自驾车出行申请管理</title>
<script type="text/javascript" src="${ctxStatic}/tb/driving/addDriving.js?ver=1"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/driving/list">市内自驾车出行申请列表</a></li>
		<li class="active"><a href="">申请详情</a></li>
		
	</ul>
	<form:form class="form-horizontal">
		
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
						${driving.peer }
					</td>
					<td class="tit">用车时间</td>
					<td>
						<fmt:formatDate value="${driving.transportTime}" pattern="yyyy-MM-dd"/>
					</td>
					
				</tr>
				<tr>
					<td class="tit">出发地</td>
					<td>
						${driving.origin }
					</td>
					<td class="tit">目的地</td>
					<td>
						${driving.destination }
					</td>
					
				</tr>
				<tr>
					<td class="tit">预计公里数</td>
					<td>
						${driving.estimatedMiles }公里
					</td>
					<td class="tit">每升汽油费</td>
					<td>
						${driving.gasoline }元
					</td>
				</tr>
				<tr>
					<td class="tit">预计费用</td>
					<td colspan="3">
						${driving.estimatedCost }元
					</td>
				</tr>
				<tr>
					<td class="tit">自驾原因</td>
					<td colspan="3">
						${driving.reason }
					</td>
				</tr>
				
				
				<c:if test="${not empty driving.proneText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${driving.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty driving.prtwoText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${driving.prtwoText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty driving.id}">
			<act:histoicFlow procInsId="${driving.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>