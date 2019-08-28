<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default" />
<title>收款通知</title>
<script type="text/javascript" src="${ctxStatic}/receiptNotice/add.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		 <li><a href="${ctx}/tb/receiptNotice/list">收款通知列表</a></li> 
		<li class="active"><a href="#">收款填写</a></li>
		<%--2019.8.28由谢晨改为马建新--%>
		<c:if test="${fns:getUser().loginName=='俞林伟'
			||fns:getUser().loginName=='马建新'
			||receiptNotice.office.name=='财务部'}">
		<li><a href="${ctx}/tb/receiptNotice/list2">员工收款通知列表</a></li>
		</c:if>
	</ul>
	<form:form id="inputForm" modelAttribute="receiptNotice" name="form" 
		action="${ctx}/tb/receiptNotice/save" method="post" class="form-horizontal" >
		<form:hidden path="id" />
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
						<input id="receiptDate" type="text" name="receiptDate" value="${receiptNotice.receiptDate}">
						<span class="warn" id="receiptDateWarn" style="color: red"></span>
					</td>
					<td class="tit">付款方名称</td>
					<td>
						<input id="paymentName" type="text" name="paymentName" value="${receiptNotice.paymentName}">
						<span class="warn" id="paymentNameWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">收款项目</td>
					<td>
						<input id="receiptName" type="text" name="receiptName" value="${receiptNotice.receiptName}">
						<span class="warn" id="receiptNameWarn" style="color: red"></span>
					</td>
					<td class="tit">对应合同编号</td>
					<td>
						<input id="contractNumber" type="text" name="contractNumber" value="${receiptNotice.contractNumber}">
						<span class="warn" id="contractNumberWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">合同总金额（元）</td>
					<td>
						<input id="totalMoney" name="totalMoney" type="text" value="${receiptNotice.totalMoney}">元
						<span class="warn" id="totalMoneyWarn" style="color: red"></span>
					</td>
					<td class="tit">已收款金额（元）</td>
					<td>
						<input id="alreadyMoney" name="alreadyMoney" type="text" value="${receiptNotice.alreadyMoney}">元
						<span class="warn" id="alreadyMoneyWarn" style="color: red"></span>
					</td>
				<tr>
				<tr>
					<td class="tit">本次收款金额（元）</td>
					<td>
						<input id="nowMoney" name="nowMoney" type="text" value="${receiptNotice.nowMoney}">元
						<span class="warn" id="nowMoneyWarn" style="color: red"></span>
					</td>
					<td class="tit">款项性质</td>
					<td>
						<select name="receiptNature" class="input-medium">
							<option value="">请选择</option>
							<option value="预付款">预付款</option>
							<option value="贷款">贷款</option>
							<option value="开发费">开发费</option>
							<option value="维护费">维护费</option>
							<option value="质保金">质保金</option>
							<option value="投标保证金">投标保证金</option>
							<option value="履约保证金">履约保证金</option>
							<option value="其他">其他</option>
						</select>
						<span class="warn" id="receiptNatureWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">属第几次收款</td>
					<td>
						<input id="times" name="times" type="text" value="${receiptNotice.times}">
						<span class="warn" id="timesWarn" style="color: red"></span>
					</td>
					<td class="tit">付款方信息</td>
					<td>
						<ul>
							<li style="list-style-type:none;">
								开户行
								<input id="pbank" name="pbank" type="text" value="${receiptNotice.pbank}">
								<span class="warn" id="pbankWarn" style="color: red"></span>
							</li>
						</ul>
						<ul>
							<li style="list-style-type:none;">
								账户名
								<input id="pname" name="pname" type="text" value="${receiptNotice.pname}">
								<span class="warn" id="pnameWarn" style="color: red"></span>
							</li>
						</ul>
						<ul>
							<li style="list-style-type:none;">
								账&nbsp;&nbsp;&nbsp;号
								<input id="pnumber" name="pnumber" type="text" value="${receiptNotice.pnumber}">
								<span class="warn" id="pnumberWarn" style="color: red"></span>
							</li>
						</ul>
					</td>
				</tr>
				
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="发布通知" onclick="return save();" />&nbsp;
			
			<c:if test="${not empty receiptNotice.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="button"
					value="销毁发布" onclick="del()" />&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	<script type="text/javascript">
			function del(){
				window.location.href="${ctx}/tb/receiptNotice/del?id=${receiptNotice.id}"
			}
		</script>
</body>
</html>