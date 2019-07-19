<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>收款通知</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/receiptNotice/list">收款通知列表</a></li>				
		<li class="active"><a href="">收款通知详情</a></li>	
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<fieldset>
			<legend>收款填写</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">填写人</td>
					<td>
						${receiptNotice.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${receiptNotice.office.name}
					</td>
				</tr>
				<tr>
					<td class="tit">收款日期</td>
					<td>
						${receiptNotice.receiptDate}
					</td>
					<td class="tit">付款方名称</td>
					<td>
						${receiptNotice.paymentName}
					</td>
				</tr>
				<tr>
					<td class="tit">收款项目</td>
					<td>
						${receiptNotice.receiptName}
					</td>
					<td class="tit">对应合同编号</td>
					<td>
						${receiptNotice.contractNumber}
					</td>
				</tr>
				<tr>
					<td class="tit">合同总金额（元）</td>
					<td>
						${receiptNotice.totalMoney}元
					</td>
					<td class="tit">已收款金额（元）</td>
					<td>
						${receiptNotice.alreadyMoney}元
					</td>
				<tr>
				<tr>
					<td class="tit">本次收款金额（元）</td>
					<td>
						${receiptNotice.nowMoney}元
					</td>
					<td class="tit">款项性质</td>
					<td>
						${receiptNotice.receiptNature}
					</td>
				</tr>
				<tr>
					<td class="tit">属第几次收款</td>
					<td>
						${receiptNotice.times}
					</td>
					<td class="tit">付款方信息</td>
					<td>
						<ul>
							<li style="list-style-type:none;">
								开户行:
								${receiptNotice.pbank}
							</li>
						</ul>
						<ul>
							<li style="list-style-type:none;">
								账户名:
								${receiptNotice.pname}
							</li>
						</ul>
						<ul>
							<li style="list-style-type:none;">
								账&nbsp;&nbsp;&nbsp;号:
								${receiptNotice.pnumber}
							</li>
						</ul>
					</td>
				</tr>
				
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>