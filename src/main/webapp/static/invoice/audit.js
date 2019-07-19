$(document).ready(function(){
});

function save() {	
	var bool = true;
	$(".warn").text("");
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('yes');
		return true;
	}
	return false;
}
function stop() {
	$('#flag').val('no');
	return true;
}
function yanzhen(bool){
var invoiceDate = $("#invoiceDate").val();
	var reg =/^(\d{4})年(0\d{1}|1[0-2])月(0\d{1}|[12]\d{1}|3[01])日$/;
	if(invoiceDate==undefined||invoiceDate==null||invoiceDate==""){
		bool=false;
		$("#invoiceWarn").text("请输入本次开票日期");
	}else if(!reg.test(invoiceDate)){
		bool=false;
		$("#invoiceWarn").text("请输入正确格式如：2018年01月01日");
	}
	return bool;
}