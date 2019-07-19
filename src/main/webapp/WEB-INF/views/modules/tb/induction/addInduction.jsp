<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/tb/induction/addInduction.js"></script>
<title>录用/转正管理</title>
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/induction/list">录用列表</a></li>
		<li class="active"><a href="">录用申请</a></li>
		<shiro:hasRole name="caiwu">
		<li><a href="${ctx}/tb/induction/list3">员工录用列表</a></li>
		</shiro:hasRole>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/induction/list2">所属区域员工录用列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="induction" enctype="multipart/form-data"
		action="${ctx}/tb/induction/save" method="post" class="form-horizontal">
		<input type="hidden" id="flag" name="act.flag">
		<input type="hidden" name="id" value=${induction.id }>
		<sys:message content="${message}" />
		<input type="hidden" name="fileOneAddress" value="${induction.fileOneAddress }">
		<input type="hidden" name="fileTwoAddress" value="${induction.fileTwoAddress }">
		
		<fieldset>
			<legend>录用申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>${fns:getUser().name }</td>
					<td class="tit">申请地点</td>
					<td>
						<sys:treeselect id="applyAddress" name="applyAddress.id" 
							value="${induction.applyAddress.id}" labelName="applyAddress.name" 
							labelValue="${induction.applyAddress.name}"  title="区域" 
							url="/sys/area/treeData" cssClass="input-small" 
							allowClear="true" notAllowSelectParent="true"/>
						<span class="warn" id="applyAddressWarn" style="color: red"></span>
					</td>
					<td class="tit">申请部门</td>
					<td>
						<sys:treeselect id="applyOffice" name="applyOffice.id" 
							value="${induction.applyOffice.id}" labelName="applyOffice.name" 
							labelValue="${induction.applyOffice.name}"  title="部门" 
							url="/sys/office/treeData?type=2" cssClass="input-small" 
							allowClear="true" notAllowSelectParent="true"/>
						<span class="warn" id="applyOfficeWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">面试日期</td>
					<td>
						<input name="interviewDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${induction.interviewDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="interviewDateWarn" style="color: red"></span>
					</td>
					<td class="tit">面试地点</td>
					<td>
						<input type="text" name="interviewAddress" value=${induction.interviewAddress }>
						<span class="warn" id="interviewAddressWarn" style="color: red"></span>
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${induction.createDate }" type="both" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="tit">录用人姓名</td>
					<td>
						<input type="text" name="employedName" value=${induction.employedName }>
						<span class="warn" id="employedNameWarn" style="color: red"></span>
					</td>
					<td class="tit">试用期工资</td>
					<td>
						<input type="text" name="trialMoney" value=${induction.trialMoney }>元
						<span class="warn" id="trialMoneyWarn" style="color: red"></span>
					</td>
					<td class="tit">转正后工资</td>
					<td>
						<input type="text" name="positiveMoney" value=${induction.positiveMoney }>元
						<span class="warn" id="positiveMoneyWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">录用部门</td>
					<td>
						<sys:treeselect id="employedOffice" name="employedOffice.id" 
							value="${induction.employedOffice.id}" labelName="employedOffice.name" 
							labelValue="${induction.employedOffice.name}"  title="部门" 
							url="/sys/office/treeData?type=2" cssClass="input-small" 
							allowClear="true" notAllowSelectParent="true"/>
						<span class="warn" id="employedOfficeWarn" style="color: red"></span>
					</td>
					<td class="tit">录用岗位</td>
					<td>
						<input type="text" name="employedJob" value=${induction.employedJob }>
						<span class="warn" id="employedJobWarn" style="color: red"></span>
					</td>
					<td class="tit">岗位级别</td>
					<td>
						<input type="text" name="jobLevel" value=${induction.jobLevel }>
						<span class="warn" id="jobLevelWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">到岗日期</td>
					<td>
						<input name="workDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${induction.workDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="workDateWarn" style="color: red"></span>
					</td>
					<td class="tit">联系电话</td>
					<td>
						<input type="text" name="phone" value=${induction.phone }>
						<span class="warn" id="phoneWarn" style="color: red"></span>
					</td>
					<td class="tit">转正日期</td>
					<td>
						<input name="positiveDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${induction.positiveDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="positiveDateWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">合同签订日期</td>
					<td>
						<input name="contractSignedDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${induction.contractSignedDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="contractSignedDateWarn" style="color: red"></span>
					</td>
					<td class="tit">合同起始日期</td>
					<td>
						<input name="contractStartDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${induction.contractStartDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="contractStartDateWarn" style="color: red"></span>
					</td>
					<td class="tit">合同结束日期</td>
					<td>
						<input name="contractEndDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${induction.contractEndDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="contractEndDateWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td>应聘人简历和面试意见</td>
					<td colspan="5">
						<form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
							<input type="file" id="file1" name="file" style="width:180px">
						<input type="button" value="确认上传" onclick = "upload(1)">
						 <progress value="0" max="100" id="progress1" style="height: 20px; width: 100%"></progress>
						 </form>
					</td>
				</tr>
				<tr>
					<td>相关证件</td>
					<td colspan="5">
						<form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
							<input type="file" id="file2" name="file" style="width:180px">
						<input type="button" value="确认上传" onclick = "upload(2)">
						 <progress value="0" max="100" id="progress2" style="height: 20px; width: 100%"></progress>
						 </form>
					</td>
				</tr>
				
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty induction.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
				</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty induction.id }">
			<act:histoicFlow procInsId="${induction.act.procInsId}" />
		</c:if>
		</form:form>
</body>
</html>