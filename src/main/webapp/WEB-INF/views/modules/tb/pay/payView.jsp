<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>对外付款管理</title>
<script type="text/javascript"
	src="${ctxStatic}/tb/pay/payView.js?ver=1"></script>
<script type="text/javascript">
	$(document).ready(function() {
		check();
	});
	function check() {
		var url = window.location.href;
		var statu = $("input[name='statu']").val();
		if (url.indexOf("form?act") >= 0) {
			if (statu == "已付款") {

			} else {
				$("#btnCancel")
						.before(
								'<input class="btn" type="button" value="回退" onclick="rollback()">');
			}
		}
	}
	function rollback() {
		var msg = "你确定要回退到自己节点吗";
		if (confirm(msg) == true) {
			var url = window.location.href;
			var ele = url.split("&");
			var task = ele[0].split("=");
			var taskId = task[1];
			url = url.substr(0, url.indexOf("tb/"))
			window.location = url + "tb/roll/back?taskId=" + taskId;
		} else {

		}
	}
	function tiaozhuan() {
		var url = window.location.href;
		url = url.replace("form", "print");
		window.open(url);
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/pay/list">对外付款列表</a></li>
		<li class="active"><a href="">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${pay.statu }">
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="3">${pay.reason }</td>
				</tr>
				<tr>
					<td class="tit">合同名称</td>
					<td>${pay.projectName}</td>

					<td class="tit">合同编号</td>
					<td>${pay.projectNum}</td>
				</tr>
				<tr>
					<td class="tit">合同总金额</td>
					<td><fmt:formatNumber type="number" value="${pay.money}"
							maxFractionDigits="2" pattern="#0.00" />元</td>
					<td class="tit">合同签署时间</td>
					<td><fmt:formatDate value="${pay.projectDate}" type="both"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td class="tit">本次应付款金额</td>
					<td><fmt:formatNumber type="number" value="${pay.payMoney}"
							maxFractionDigits="2" pattern="#0.00" />元</td>
					<td class="tit">本次最晚付款时间</td>
					<td><fmt:formatDate value="${pay.lastTime}" type="both"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td class="tit">付款类别</td>
					<td><input name="payTypeBig" type="hidden"
						value=${pay.payTypeBig }></td>
					<td class="tit">付款类型</td>
					<td><input name="payTypeSmall" type="hidden"
						value=${pay.payTypeSmall }></td>
				</tr>
				<tr>
					<td class="tit">付款方式</td>
					<td><c:if test="${pay.payMethods==2 }">电汇</c:if> <c:if
							test="${pay.payMethods==1 }">现金</c:if> <c:if
							test="${pay.payMethods==3 }">延期支票</c:if></td>
					<td class="tit">已付款金额</td>
					<td><fmt:formatNumber type="number" value="${pay.amountPaid }"
							maxFractionDigits="2" pattern="0.00" />元</td>
				</tr>
				<tr>
					<td class="tit">收款人名称</td>
					<td>${pay.payeeName }</td>
					<td class="tit">收款人账户</td>
					<td>${pay.payeeAccount }</td>
				</tr>
				<tr>
					<td class="tit">申请人</td>
					<td>${pay.createBy.name}</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${pay.createDate}" type="both"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td class="tit" colspan="2">申请人公司</td>
					<td colspan="2" align="center">${pay.ename }</td>
				</tr>
				<tr>
					<td class="tit">因付款情况说明</td>
					<td colspan="3">${pay.notes }</td>
				</tr>

				<c:if test="${not empty pay.proneText }">
					<tr>
						<td class="tit">部门经理意见</td>
						<td>${pay.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty pay.prtwoText}">
					<tr>
						<td class="tit">研发总监意见</td>
						<td>${pay.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty pay.prthreeText}">
					<tr>
						<td class="tit">主管意见</td>
						<td>${pay.prthreeText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty pay.prsixText}">
					<tr>
						<td class="tit">商务主管意见</td>
						<td>${pay.prsixText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty pay.prfourText}">
					<tr>
						<td class="tit">财务总监意见</td>
						<td>${pay.prfourText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty pay.prsevText }">
					<tr>
						<td class="tit">总经理意见</td>
						<td>${pay.prsevText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty pay.prfiveText}">
					<tr>
						<td class="tit">出纳</td>
						<td>${pay.prfiveText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${pay.act.procInsId}" />
		<div class="form-actions">
			<input class="btn" type="button" value="打 印" onclick="tiaozhuan();" />
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" /> <input class="btn" type="button"
				value="作废" onclick="zuofei();" />
			<shiro:hasAnyRoles name="seeEmployee,caiwuzhongnan">
				<a class="btn"
					href="${ctx}/tb/pay/getSysIndex/?procInsId=${pay.procInsId}&id=${pay.id}"
					target="_blank">财务页面打印</a>
			</shiro:hasAnyRoles>
		</div>
	</form:form>
	<script type="text/javascript">
		function zuofei() {
			var msg = "您确定作废此条付款内容？";
			if (confirm(msg) == true) {
				window.location = "${ctx}/tb/pay/back?id=${pay.id}"
			} else {

			}
		}
	</script>
</body>
</html>