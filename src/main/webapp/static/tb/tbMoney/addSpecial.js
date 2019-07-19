$(document).ready(function(){
	payTypeChange()
});
function payTypeChange(){
	var payType = $("select[name='payType']").val();
	if(payType==2){
		$("input[name='account']").prop("type","text");
	}else{
		$("input[name='account']").text("");
		$("input[name='account']").next().text("");
		$("input[name='account']").prop("type","hidden");
	}
}
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
		isCommitted = true;
		return true;
	}
	return false;
}
function stop() {
	$('#flag').val('no');
	return true;
}
function yanzhen(bool){
	var tbDate = $("input[name='tbDate']").val();
	var money = $("input[name='money']").val();
	var payType = $("select[name='payType']").val();
	var account = $("input[name='account']").val();
	var notes = $("textarea[name='notes']").val();
	if(tbDate==undefined||tbDate==null||tbDate==""){
		bool=false;
		$("#tbDateWarn").text("请填写费用使用日期");
	}
	if(money==undefined||money==null||money==""){
		bool=false;
		$("#moneyWarn").text("请输入预估费用金额");
	}
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(!reg.test(money)){
		bool=false;
		$("#moneyWarn").text("请输入正确格式");
	}
	if(payType==""){
		bool=false;
		$("#payTypeWarn").text("请选择支付方式");
	}
	if(payType==2){
		if(account!=undefined&&account!=null&&account!=""){
			$("#accountWarn").text("请输入收款方账户信息");
		}
	}
	if(notes==undefined||notes==null||notes==""){
		bool=false;
		$("#notesWarn").text("请填写申请原因");
	}
	else if(notes.length>200){
		bool=false;
		$("#notesWarn").text("请填写小于200个字");
	}
	return bool;
}