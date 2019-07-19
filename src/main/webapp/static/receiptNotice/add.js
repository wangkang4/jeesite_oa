$(document).ready(function(){
});
var isCommitted=false;//是否已提交
function save() {	
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	$(".warn").text("");
	bool = yanzhen(bool);
	
	if (bool) {
		committed=true;
		return true;
	}
	return false;
}

function yanzhen(bool){
	var receiptDate = $("input[name='receiptDate']").val();
	var paymentName = $("input[name='paymentName']").val();
	var receiptName = $("input[name='receiptName']").val();
	var contractNumber = $("input[name='contractNumber']").val();
	var times = $("input[name='times']").val();
	var pbank = $("input[name='pbank']").val();
	var pname = $("input[name='pname']").val();
	var pnumber = $("input[name='pnumber']").val();
	
	var totalMoney = $("input[name='totalMoney']").val();
	var alreadyMoney = $("input[name='alreadyMoney']").val();
	var nowMoney = $("input[name='nowMoney']").val();
	var receiptNature = $("select[name='receiptNature']").val();
	
	
	if(receiptDate==undefined||receiptDate==null||receiptDate==""){
		bool=false;
		$("#receiptDateWarn").text("此处不能为空");
	}
	if(paymentName==undefined||paymentName==null||paymentName==""){
		bool=false;
		$("#paymentNameWarn").text("此处不能为空");
	}
	if(receiptName==undefined||receiptName==null||receiptName==""){
		bool=false;
		$("#receiptNameWarn").text("此处不能为空");
	}
	if(contractNumber==undefined||contractNumber==null||contractNumber==""){
		bool=false;
		$("#contractNumberWarn").text("此处不能为空");
	}
	if(times==undefined||times==null||times==""){
		bool=false;
		$("#timesWarn").text("此处不能为空");
	}
	if(pbank==undefined||pbank==null||pbank==""){
		bool=false;
		$("#pbankWarn").text("此处不能为空");
	}
	if(pname==undefined||pname==null||pname==""){
		bool=false;
		$("#pnameWarn").text("此处不能为空");
	}
	if(pnumber==undefined||pnumber==null||pnumber==""){
		bool=false;
		$("#pnumberWarn").text("此处不能为空");
	}
	if(receiptNature==undefined||receiptNature==null||receiptNature==""){
		bool=false;
		$("#receiptNatureWarn").text("此处不能为空");
	}
	
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(totalMoney==undefined||totalMoney==null||totalMoney==""){
		bool=false;
		$("#totalMoneyWarn").text("请输入总计金额");
	}else if(!reg.test(totalMoney)){
		bool=false;
		$("#totalMoneyWarn").text("请输入正确格式");
	}
	if(alreadyMoney==undefined||alreadyMoney==null||alreadyMoney==""){
		bool=false;
		$("#alreadyMoneyWarn").text("请输入总计金额");
	}else if(!reg.test(alreadyMoney)){
		bool=false;
		$("#alreadyMoneyWarn").text("请输入正确格式");
	}
	
	if(nowMoney==undefined||nowMoney==null||nowMoney==""){
		bool=false;
		$("#nowMoneyWarn").text("请输入总计金额");
	}else if(!reg.test(nowMoney)){
		bool=false;
		$("#nowMoneyWarn").text("请输入正确格式");
	}
	return bool;
}