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
	var pName = $("input[name='pName']").val();
	var money = $("input[name='money']").val();
	
	if(pName==undefined||pName==null||pName==""){
		bool=false;
		$("#pNameWarn").text("采购名称不能为空");
	}
	
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(money==undefined||money==null||money==""){
		bool=false;
		$("#moneyWarn").text("请输入总计金额");
	}else if(!reg.test(money)){
		bool=false;
		$("#moneyWarn").text("请输入正确格式");
	}
	
	return bool;
}