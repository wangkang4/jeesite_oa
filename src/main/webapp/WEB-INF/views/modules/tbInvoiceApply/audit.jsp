<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>开票申请管理</title>
<script type="text/javascript" src="${ctxStatic}/invoice/audit.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="invoiceApply"
		action="${ctx}/tb/invoiceApply/saveAudit" method="post"
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
			<legend>开票申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">开票名称</td>
					<td colspan="5">
						${invoice.taxName }
					</td>
					<%-- <td class="tit">纳税人识别号</td>
					<td>
						${invoice.taxNumber }
					</td>
					<td class="tit">地址</td>
					<td>
						${invoice.address }
					</td> --%>
				</tr>
				<%-- <tr>
					<td class="tit">电话</td>
					<td>${invoice.phone }
					</td>
					<td class="tit">开户银行</td>
					<td>${invoice.bank }
					</td>
					<td class="tit">账号</td>
					<td>
						${invoice.account }
					</td>
				</tr> --%>
				<tr>
					<td class="tit" >开票信息</td>
					<td colspan="5" style="width:100px; word-wrap:break-word;word-break:break-all;">
					${invoice.invoiceInfo}
					</td> 
				</tr>
				<tr>
					<td class="tit">项目名称</td>
					<td>
						${invoice.proname} 
					</td>					
					<td class="tit">项目经理</td>
					<td>
						${invoice.manage.name} 
					</td>

					<td class="tit">办事处负责人</td>
					<td>
						${invoice.offPerson.name}
					</td>
				</tr>
				<tr>
					<td class="tit">本次开票金额</td>
					<td>
						${invoice.total} 元
					</td>					
					<td class="tit">已开票金额</td>
					<td>
						${invoice.alMoney}元 
					</td>

					<td class="tit">总金额</td>
					<td>
						${invoice.nowMoney}元
					</td>
				</tr>
				<tr>
					<td class="tit" >邮寄地址</td>
					<td colspan="6">${invoice.maAddress } ${invoice.colPerson } ${invoice.colPhone }</td>
					<%-- <td class="tit">收件人</td>
					<td>${invoice.colPerson }</td>
					<td class="tit">电话</td>
					<td>${invoice.colPhone }</td> --%>
				</tr>
				<tr>
					<td class="tit">附件</td>
					<td colspan="3">
						<c:if test="${not empty invoice.applyAddress }">
							<a href="${ctx}/tb/invoiceApply/download?id=${invoice.id}">附件下载</a>
						</c:if>
						<c:if test="${empty invoice.applyAddress }">
							无附件
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="tit">验收报告</td>
					<td colspan="6">
						${invoice.report }											 
					</td>
					
				</tr>
				<tr>
					<td class="tit">您的意见</td>
					<td colspan="3"><form:textarea path="act.comment"
							 rows="5" maxlength="500" cssStyle="width:500px" />
					</td>
				</tr>
				<c:if test="${fns:getUser().loginName=='李晓萌' }">
					<td class="tit">开票日期</td>
					<td>
						<input id="invoiceDate" type="text" name="invoiceDate" value="${invoice.invoiceDate }"/>
						<span class="warn" id="invoiceWarn" style="color: red"></span> 
					</td>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<c:choose>
					<c:when test="${fns:getUser().loginName=='李晓萌' }">
	                    <input id="btnSubmit" class="btn btn-primary" type="submit"
				value="同意" onclick="return save()" />&nbsp;
					</c:when>
					<c:otherwise>
						<input id="btnSubmit" class="btn btn-primary" type="submit" 
				value="同 意" onclick="$('#flag').val('yes')" />&nbsp; 
					</c:otherwise> 
			</c:choose>
			<input id="btnSubmit" class="btn btn-inverse" type="submit" 
				value="驳 回" onclick="$('#flag').val('no')" />&nbsp; 
			<input id="btnCancel" class="btn" type="button" 
				value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<act:histoicFlow procInsId="${invoice.act.procInsId}" />
	</form:form>
</body>

</html>