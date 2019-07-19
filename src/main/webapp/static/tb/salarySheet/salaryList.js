$(document).ready(function(){
	$("#btnImport").click(function(){
		$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
			bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
	})
	$("#email").click(function(){
		email();
	})
});
function sub(){
	var emailName = $("#emailName").val();
	var password = $("#password").val();
	var title = $("#title").val();
	$("input[name='emailName']").val(emailName);
	$("input[name='password']").val(password);
	$("input[name='title']").val(title);
	$("#importForm").submit();
}
function email(){
	var okCount=0;
	var errorCount=[];
	var url = "email";
	debugger
	$("span[name='number']").each(function(i,e){
		var number = $("span[name='number']").eq(i).text();
		var name = $("span[name='name']").eq(i).text();
		var idCardNumber = $("span[name='idCardNumber']").eq(i).text();
		var department = $("span[name='department']").eq(i).text();
		var job = $("span[name='job']").eq(i).text();
		var basePay = $("span[name='basePay']").eq(i).text();
		var computerSubsidy = $("span[name='computerSubsidy']").eq(i).text();
		var bonusOrFine = $("span[name='bonusOrFine']").eq(i).text();
		var trafficPay = $("span[name='trafficPay']").eq(i).text();
		var countFirst = $("span[name='countFirst']").eq(i).text();
		var exemptIncome = $("span[name='exemptIncome']").eq(i).text();
		var endowmentInsurance = $("span[name='endowmentInsurance']").eq(i).text();
		var medicalInsurance = $("span[name='medicalInsurance']").eq(i).text();
		var unemploymentInsurance = $("span[name='unemploymentInsurance']").eq(i).text();
		var housingProvidentFund = $("span[name='housingProvidentFund']").eq(i).text();
		var countSecond = $("span[name='countSecond']").eq(i).text();
		var childrenEducation = $("span[name='childrenEducation']").eq(i).text();
		var continuingEducation = $("span[name='continuingEducation']").eq(i).text();
		var medicalTreatmentForSeriousIllness = $("span[name='medicalTreatmentForSeriousIllness']").eq(i).text();
		var interestOnHousingLoans = $("span[name='interestOnHousingLoans']").eq(i).text();
		var housingRent = $("span[name='housingRent']").eq(i).text();
		var supportForTheElderly = $("span[name='supportForTheElderly']").eq(i).text();
		var countThird = $("span[name='countThird']").eq(i).text();
		var countFourth = $("span[name='countFourth']").eq(i).text();
		var basicDeductions = $("span[name='basicDeductions']").eq(i).text();
		var totalDeductionForThisMonth = $("span[name='totalDeductionForThisMonth']").eq(i).text();
		var accumulatedIncome = $("span[name='accumulatedIncome']").eq(i).text();
		var upToDeductionAmount = $("span[name='upToDeductionAmount']").eq(i).text();
		var taxableIncome = $("span[name='taxableIncome']").eq(i).text();
		var applicableTaxRate = $("span[name='applicableTaxRate']").eq(i).text();
		var accumulatedTaxPayable = $("span[name='accumulatedTaxPayable']").eq(i).text();
		var aTaxHasBeenPaid = $("span[name='aTaxHasBeenPaid']").eq(i).text();
		var withholdingTax = $("span[name='withholdingTax']").eq(i).text();
		var socialSecurityPersonalBurden = $("span[name='socialSecurityPersonalBurden']").eq(i).text();
		var countFivth = $("span[name='countFivth']").eq(i).text();
		var actualAmount = $("span[name='actualAmount']").eq(i).text();
		var emailAddress = $("span[name='emailAddress']").eq(i).text();
		var receiverSignature = $("span[name='receiverSignature']").eq(i).text();
		var emailName = $("#emailName").val();
		var password = $("#password").val();
		var title = $("#title").val();
		var data = {"number":number,
			"name":name,
			"idCardNumber":idCardNumber,
			"department":department,
			"job":job,
			"basePay":basePay,
			"computerSubsidy":computerSubsidy,
			"bonusOrFine":bonusOrFine,
			"trafficPay":trafficPay,
			"countFirst":countFirst,
			"exemptIncome":exemptIncome,
			"endowmentInsurance":endowmentInsurance,
			"medicalInsurance":medicalInsurance,
			"unemploymentInsurance":unemploymentInsurance,
			"housingProvidentFund":housingProvidentFund,
			"countSecond":countSecond,
			"childrenEducation":childrenEducation,
			"continuingEducation":continuingEducation,
			"medicalTreatmentForSeriousIllness":medicalTreatmentForSeriousIllness,
			"interestOnHousingLoans":interestOnHousingLoans,
			"housingRent":housingRent,
			"supportForTheElderly":supportForTheElderly,
			"countThird":countThird,
			"countFourth":countFourth,
			"basicDeductions":basicDeductions,
			"totalDeductionForThisMonth":totalDeductionForThisMonth,
			"accumulatedIncome":accumulatedIncome,
			"upToDeductionAmount":upToDeductionAmount,
			"taxableIncome":taxableIncome,
			"applicableTaxRate":applicableTaxRate,
			"accumulatedTaxPayable":accumulatedTaxPayable,
			"aTaxHasBeenPaid":aTaxHasBeenPaid,
			"withholdingTax":withholdingTax,
			"socialSecurityPersonalBurden":socialSecurityPersonalBurden,
			"countFivth":countFivth,
			"actualAmount":actualAmount,
			"emailAddress":emailAddress,
			"receiverSignature":receiverSignature,
			"emailName":emailName,
			"password":password,
			"title":title
		}
		$.ajax({
			"url":url,
			"data":data,
			"dataType":"json",
			"success":function(result){
				if(result.success=="ok"){
					okCount++;
					$("span[name='okCount']").text("成功发送"+okCount+"条数据")
				}else{
					errorCount.push(i+1);
					console.log(errorCount);
					$("span[name='errorCount']").text("发送失败的序号为"+errorCount);
				}
			}
		})
	})
}
