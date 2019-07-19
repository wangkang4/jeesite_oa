<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<title>工资条转发</title>
	<script type="text/javascript" src="${ctxStatic}/tb/salarySheet/salaryList.js?ver=1"></script>
</head>
<body>
<div id="importBox" class="hide">
	<form id="importForm" modelAttribute="salary" action="${ctx}/tb/salary/import" method="post" enctype="multipart/form-data"
		  class="form-search" style="padding-left:20px;text-align:center;"><br/>
		<input name="emailName" type="hidden"/>
		<input name="password" type="hidden"/>
		<input name="title" type="hidden"/>
		<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
		<input id="btnImportSubmit" class="btn btn-primary" type="submit"  value="   导    入   "/>
		<a href="${ctx}/tb/salary/import/template">下载模板</a>
	</form>
</div>
<ul class="nav nav-tabs">
	<li class="active"><a href="">工资条列表</a></li>
</ul>
<label>发件人邮箱号:</label>
<input id="emailName" type="text" value="${sa.emailName }"/>
<label>发件人邮箱密码</label>
<input id="password" type="password" value="${sa.password }"/>
<label>主题</label>
<input id="title" type="text" value="${sa.title }"/>
<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
<input id="email" class="btn btn-primary" type="button" value="发送"/>
<span name="okCount"></span><span style="color: red" name="errorCount"></span>
<table border="1">
	<tr>
		<th rowspan="4" align="center">序号</th>
		<th rowspan="4" align="center">姓名</th>
		<th rowspan="4" align="center">身份证号码</th>
		<th rowspan="4" align="center">部门</th>
		<th rowspan="4" align="center">职务</th>
		<th colspan="6" rowspan="2" align="center">工资、薪金所得</th>
		<th colspan="14" align="center">扣除项目</th>
		<th rowspan="4" align="center">本月扣除合计</th>
		<th rowspan="4" align="center">累计收入</th>
		<th rowspan="4" align="center">截至扣除金额</th>
		<th rowspan="4" align="center">应纳税所得额</th>
		<th rowspan="4" align="center">适用税率</th>
		<th rowspan="4" align="center">累计应纳个税</th>
		<th rowspan="4" align="center">已缴纳个税</th>
		<th colspan="3" align="center">公司扣款</th>
		<th rowspan="4" align="center">实发金额</th>
		<th rowspan="4" align="center">邮箱地址</th>
		<th rowspan="4" align="center">领款人签字(备注)</th>
	</tr>
	<tr>
		<th colspan="5" align="center">专项扣除</th>
		<th colspan="7" align="center">专项附加扣除</th>
		<th align="center">依法确定的其</th>
		<th rowspan="3" align="center">基本减除额</th>
		<th rowspan="3" align="center">代扣个税</th>
		<th rowspan="3" align="center">社保个人负担</th>
		<th rowspan="3" align="center">小记</th>
	</tr>
	<tr>
		<th rowspan="2" align="center">基本工资</th>
		<th rowspan="2" align="center">电脑补助</th>
		<th rowspan="2" align="center">奖金/罚款</th>
		<th rowspan="2" align="center">交通补助</th>
		<th rowspan="2" align="center">小计(含免税所得)</th>
		<th rowspan="2" align="center">免税所得</th>
		<th align="center">养老保险</th>
		<th align="center">医疗保险</th>
		<th align="center">失业保险</th>
		<th align="center">住房公积金</th>
		<th rowspan="2" align="center">小计</th>
		<th rowspan="2" align="center">子女教育</th>
		<th rowspan="2" align="center">继续教育</th>
		<th rowspan="2" align="center">大病医疗</th>
		<th rowspan="2" align="center">住房贷款利息</th>
		<th rowspan="2" align="center">住房租金</th>
		<th rowspan="2" align="center">赡养老人</th>
		<th rowspan="2" align="center">小计</th>
		<th rowspan="2" align="center">小计</th>
	</tr>
	<tr>
		<th align="center" style="color: red;">8%</th>
		<th align="center" style="color: red;">2%</th>
		<th align="center" style="color: red;">0.02%</th>
		<th align="center" style="color: red;">12%</th>

	</tr>
	<input type="hidden" name="list" value="${list }"/>
	<c:forEach items="${list}" var="SalarySheetEntity">
		<tr>
			<td><span name="number">${SalarySheetEntity.number }</span></td>
			<td><span name="name">${SalarySheetEntity.name }</span></td>
			<td><span name="idCardNumber">${SalarySheetEntity.idCardNumber }</span></td>
			<td><span name="department">${SalarySheetEntity.department }</span></td>
			<td><span name="job">${SalarySheetEntity.job }</span></td>
			<td><span name="basePay">${SalarySheetEntity.basePay }</span></td>
			<td><span name="computerSubsidy">${SalarySheetEntity.computerSubsidy }</span></td>
			<td><span name="bonusOrFine">${SalarySheetEntity.bonusOrFine }</span></td>
			<td><span name="trafficPay">${SalarySheetEntity.trafficPay }</span></td>
			<td><span name="countFirst">${SalarySheetEntity.countFirst }</span></td>
			<td><span name="exemptIncome">${SalarySheetEntity.exemptIncome }</span></td>
			<td><span name="endowmentInsurance">${SalarySheetEntity.endowmentInsurance }</span></td>
			<td><span name="medicalInsurance">${SalarySheetEntity.medicalInsurance }</span></td>
			<td><span name="unemploymentInsurance">${SalarySheetEntity.unemploymentInsurance }</span></td>
			<td><span name="housingProvidentFund">${SalarySheetEntity.housingProvidentFund }</span></td>
			<td><span name="countSecond">${SalarySheetEntity.countSecond }</span></td>
			<td><span name="childrenEducation">${SalarySheetEntity.childrenEducation }</span></td>
			<td><span name="continuingEducation">${SalarySheetEntity.continuingEducation }</span></td>
			<td><span name="medicalTreatmentForSeriousIllness">${SalarySheetEntity.medicalTreatmentForSeriousIllness }</span></td>
			<td><span name="interestOnHousingLoans">${SalarySheetEntity.interestOnHousingLoans }</span></td>
			<td><span name="housingRent">${SalarySheetEntity.housingRent }</span></td>
			<td><span name="supportForTheElderly">${SalarySheetEntity.supportForTheElderly }</span></td>
			<td><span name="countThird">${SalarySheetEntity.countThird }</span></td>
			<td><span name="countFourth">${SalarySheetEntity.countFourth }</span></td>
			<td><span name="basicDeductions">${SalarySheetEntity.basicDeductions }</span></td>
			<td><span name="totalDeductionForThisMonth">${SalarySheetEntity.totalDeductionForThisMonth }</span></td>
			<td><span name="accumulatedIncome">${SalarySheetEntity.accumulatedIncome }</span></td>
			<td><span name="upToDeductionAmount">${SalarySheetEntity.upToDeductionAmount }</span></td>
			<td><span name="taxableIncome">${SalarySheetEntity.taxableIncome }</span></td>
			<td><span name="applicableTaxRate">${SalarySheetEntity.applicableTaxRate }</span></td>
			<td><span name="accumulatedTaxPayable">${SalarySheetEntity.accumulatedTaxPayable }</span></td>
			<td><span name="aTaxHasBeenPaid">${SalarySheetEntity.aTaxHasBeenPaid }</span></td>
			<td><span name="withholdingTax">${SalarySheetEntity.withholdingTax }</span></td>
			<td><span name="socialSecurityPersonalBurden">${SalarySheetEntity.socialSecurityPersonalBurden }</span></td>
			<td><span name="countFivth">${SalarySheetEntity.countFivth }</span></td>
			<td><span name="actualAmount">${SalarySheetEntity.actualAmount }</span></td>
			<td><span name="emailAddress">${SalarySheetEntity.emailAddress }</span></td>
			<td><span name="receiverSignature">${SalarySheetEntity.receiverSignature }</span></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>