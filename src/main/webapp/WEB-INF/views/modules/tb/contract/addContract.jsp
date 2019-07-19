<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>合同管理</title>
<script type="text/javascript"
	src="${ctxStatic}/tb/contract/addContract.js?ver=4"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/contract/list">合同列表</a></li>
		<li class="active"><a href="">合同申请</a></li>
		<shiro:hasRole name="caiwuzhongnan">
			<li><a href="${ctx}/tb/contract/employeesList">员工合同列表</a></li>
		</shiro:hasRole>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/contract/contractList2">所属区域员工合同列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="contract" name="form1"
		enctype="multipart/form-data" action="${ctx}/tb/contract/save"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<input type="hidden" name="friend" value="${contract.friendId }">
		<input type="hidden" name="contractNum"
			value="${contract.contractNum }">
		<input type="hidden" name="address" value=${contract.address }>
		<fieldset>
			<legend>合同申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td>申请人公司</td>
					<td >
						<select name="ename">
								<option value="${contract.ename }">${contract.ename }</option>
								<c:if test="${contract.ename =='北京桃花岛'}">
									<option value="安徽桃花岛">安徽桃花岛</option>
								</c:if>
								<c:if test="${contract.ename=='安徽桃花岛'}">
									<option value="北京桃花岛">北京桃花岛</option>
								</c:if>
						</select>
					</td>
					<td>产品经理加签</td>
					<td>
						<sys:treeselect id="manager" name="manager" value="${userId}" labelName="userName" labelValue="${userName}"
					title="产品经理" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>
				<tr>
					<td>合同申请人</td>
					<td>${contract.createBy.name }</td>
					<td>申请日期</td>
					<td><fmt:formatDate value="${contract.createDate }"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				 <tr>
					<%--<td>产品经理加签</td>
					<td>
						<sys:treeselect id="manager" name="manager" value="${userId}" labelName="userName" labelValue="${userName}"
					title="产品经理" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
					</td>--%>
					<%--<td>项目经理加签</td>--%>
					<%--<td>--%>
						<%--<sys:treeselect id="managerTwo" name="managerTwo" value="${userTwoId}" labelName="userName" labelValue="${userTwoName}"--%>
					<%--title="项目经理" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>--%>
					<%--</td>--%>
				</tr>
				<tr>
					<td>合同类别</td>
					<td><form:select path="contractType" class="input-medium"
							onchange="changeContractType()">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getDictList('contract_type')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select> <span class="warn" id="contractTypeWarn" style="color: red"></span>
					</td>
					<td>所属办事处</td>
					<td><sys:treeselect id="area" name="area"
							value="${contract.area}" labelName="officeName"
							labelValue="${contract.areaName}" title="合同归属地"
							url="/sys/area/treeData" cssClass="input-small" allowClear="true"
							notAllowSelectParent="true" /> <span class="warn" id="areaWarn"
						style="color: red"></span></td>

				</tr>
				<tr>
					<td>合同名称</td>
					<td><input type="text" name="contractName"
						value=${contract.contractName }> <span class="warn"
						id="contractNameWarn" style="color: red"></span></td>
					<td>合同编号</td>
					<td><select name="type" class="input-medium">
							<option value="">请选择</option>
							<option value="XS">销售类合同</option>
							<option value="YWCG">业务采购类合同</option>
							<option value="ZCCG">资产采购类合同</option>
							<option value="QT">其他类合同</option>
					</select> <span class="warn" id="contractNumWarn" style="color: red"></span>
					</td>

				</tr>
				<tr>
					<td>合同金额</td>
					<td><input type="text" name="money" value="${contract.money }">元
						<span class="warn" id="moneyWarn" style="color: red"></span></td>
					<td>合同期限</td>
					<td><input type="text" name="contractLimit"
						value="${contract.contractLimit }">月 <span class="warn"
						id="contractLimitWarn" style="color: red"></span></td>
				</tr>
				<tr>
					<td>对方公司名称</td>
					<td><input type="text" name="companyName"
						value="${contract.companyName }" onblur="getPY()"> <span
						class="warn" id="companyNameWarn" style="color: red"></span></td>
					<td>支付比例</td>
					<td><input type="text" name="paymentCycle"
						value="${contract.paymentCycle }"> <span class="warn"
						id="paymentCycleWarn" style="color: red"></span></td>
				</tr>
				<tr>
					<td>项目信息</td>
					<td><input type="text" name="project"
						value="${contract.project }"> <span class="warn"
						id="projectWarn" style="color: red"></span></td>
					<td>维保期限</td>
					<td><input type="text" name="maintenance"
						value="${contract.maintenance }">月 <span class="warn"
						id="maintenanceWarn" style="color: red"></span></td>
				</tr>
				<tr id="xiaosou">
					<td style="display: none">销售合同</td>
					<td style="display: none"><select class="input-medium"
						name="friendId">
							<option value="">请选择</option>
							<option value="000">其他合同</option>
					</select> <span class="warn" id="friendIdWarn" style="color: red"></span></td>
				</tr>
				<tr>
					<td rowspan="5">内容概要</td>
					<td>主要合作内容</td>
					<td colspan="2"><textarea rows="3" style="width: 80%"
							name="cooperationContent">${contract.cooperationContent }</textarea>
						<span class="warn" id="cooperationContentWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td>毛利及毛利率情况说明</td>
					<td colspan="2"><textarea rows="3" style="width: 80%"
							name="grossMargin">${contract.grossMargin }</textarea> <span
						class="warn" id="grossMarginWarn" style="color: red"></span></td>
				</tr>
				<tr>
					<td>结算方式</td>
					<td colspan="2"><textarea rows="3" style="width: 80%"
							name="settlement">${contract.settlement }</textarea> <span
						class="warn" id="settlementWarn" style="color: red"></span></td>
				</tr>
				<tr>
					<td>违约责任</td>
					<td colspan="2"><textarea rows="3" style="width: 80%"
							name="responsibility">${contract.responsibility }</textarea> <span
						class="warn" id="responsibilityWarn" style="color: red"></span></td>
				</tr>
				<tr>
					<td>其他</td>
					<td colspan="2"><textarea rows="3" style="width: 80%"
							name="other">${contract.other }</textarea> <span class="warn"
						id="otherWarn" style="color: red"></span></td>
				</tr>
				<tr>
					<td>合同原件</td>
					<td colspan="3">
						<form style="margin-bottom: 0px;" action=""
							enctype="multipart/form-data" method="post">
							<input type="file" id="file1" name="file" style="width: 180px">
							<input type="button" value="确认上传" onclick="upload()">
							<progress value="0" max="100" id="progress"
								style="height: 20px; width: 100%"></progress>
						</form>
					</td>
				</tr>
				<c:if test="${not empty contract.proneText }">
					<tr>
						<td class="tit">产品经理审核意见</td>
						<td colspan="3">${contract.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prsevenText }">
					<tr>
						<td class="tit">项目经理审核意见</td>
						<td colspan="3">${contract.prsevenText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prtwoText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${contract.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prthreeText}">
					<tr>
						<td class="tit">商务部长总经理审核意见</td>
						<td colspan="3">${contract.prthreeText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prfourText}">
					<tr>
						<td class="tit">市场营销总经理审核意见</td>
						<td colspan="3">${contract.prfourText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prfiveText}">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${contract.prfiveText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty contract.prsixText}">
					<tr>
						<td class="tit">总经理审核意见</td>
						<td colspan="3">${contract.prsixText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty contract.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
				</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty contract.id}">
			<act:histoicFlow procInsId="${contract.act.procInsId}" />
		</c:if>
	</form:form>
	<table id="hint" border="1" cellspacing="0" align="center" width="100%">
		<tr>
			<td>合同编号注意事项</td>
			<td>编号第一部分代表：签合同公司名称的英文字头；自动生成<br>
				编号第二部分代表：合同类型，如销售类合同首字母为“XS”，业务采购类合同首字母为“YW”，资产采购类合同首字母为“ZC”，其他类合同首字母为“QT”；
				用户手动选择<br> 编号第三部分代表：签约方公司名称，如：兴业银行:XY；山东交警:SDJJ；安徽农信银行：AHNXYH；
				根据对方公司名称自动生成<br> 编号第四部分代表：当前日期；自动生成<br>
				编号第五部分代表：今年的第几个合同；自动生成。
			</td>
		</tr>
	</table>
</body>
</html>