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
		$('#flag').val('yes');
		committed=true;
		return true;
	}
	return false;
}
function stop() {
	$('#flag').val('no');
	return true;
}
function yanzhen(bool){
	var taxName = $("input[name='taxName']").val();
	/*var taxNumber = $("input[name='taxNumber']").val();
	var address = $("input[name='address']").val();
	var phone = $("input[name='phone']").val();
	var bank = $("input[name='bank']").val();
	var account = $("input[name='account']").val();*/
	var invoiceInfo = $("textarea[name='invoiceInfo']").val();
	var total = $("input[name='total']").val();
	var maAddress = $("input[name='maAddress']").val();
	/*var colPerson = $("input[name='colPerson']").val();
	var colPhone = $("input[name='colPhone']").val();*/
	var proname = $("input[name='proname']").val();
	var alMoney = $("input[name='alMoney']").val();
	var nowMoney = $("input[name='nowMoney']").val();
	if(proname==undefined||proname==null||proname==""){
		bool=false;
		$("#pronameWarn").text("项目名称不能为空");
	}
	if(invoiceInfo==undefined||invoiceInfo==null||invoiceInfo==""){
		bool=false;
		$("#invoiceInfoWarn").text("开票信息不能为空");
	}
	if(taxName==undefined||taxName==null||taxName==""){
		bool=false;
		$("#taxNameWarn").text("开票名称不能为空");
	}
	
	/*if(taxNumber==undefined||taxNumber==null||taxNumber==""){
		bool=false;
		$("#taxNumberWarn").text("请填写纳税人识别号");
	}
	if(address==undefined||address==null||address==""){
		bool=false;
		$("#addressWarn").text("请填写地址");
	}
	if(phone==undefined||phone==null||phone==""){
		bool=false;
		$("#phoneWarn").text("请填写电话号码");
	}
	
	if(bank==undefined||bank==null||bank==""){
		bool=false;
		$("#bankWarn").text("请输入开户银行");
	}
	if(account==undefined||account==null||account==""){
		bool=false;
		$("#accountWarn").text("请输入账号");
	}*/
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(total==undefined||total==null||total==""){
		bool=false;
		$("#totalWarn").text("请输入本次金额");
	}else if(!reg.test(total)){
		bool=false;
		$("#totalWarn").text("请输入正确格式");
	}
	if(alMoney==undefined||alMoney==null||alMoney==""){
		bool=false;
		$("#alMoneyWarn").text("请输入已付金额");
	}else if(!reg.test(alMoney)){
		bool=false;
		$("#alMoneyWarn").text("请输入正确格式");
	}
	if(nowMoney==undefined||nowMoney==null||nowMoney==""){
		bool=false;
		$("#nowMoneyWarn").text("请输入总计金额");
	}else if(!reg.test(nowMoney)){
		bool=false;
		$("#nowMoneyWarn").text("请输入正确格式");
	}
	
	
	if(maAddress==undefined||maAddress==null||maAddress==""){
		bool=false;
		$("#maAddressWarn").text("请输入邮寄信息");
	}
	/*if(colPerson==undefined||colPerson==null||colPerson==""){
		bool=false;
		$("#colPersonWarn").text("请输入收件人");
	}
	if(colPhone==undefined||colPhone==null||colPhone==""){
		bool=false;
		$("#colPhoneWarn").text("请输入收件电话");
	}	*/
	return bool;
}